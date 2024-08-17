package dnd.GameTile.Units;

import static java.lang.Math.min;
import java.util.List;

import Controller.GameTickSingleton;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.MagicNumbers;


public class Mage extends Player{
    private final AbilityBar ManaBar;
    private int spellPower;
    private final int hitCount;
    private final Double range;


    public Mage(String name, int health, int AP, int DP, int maxMana, int manaCost, int spellPower, int hitCount, double range){
        super(name, health, AP, DP);
        this.ManaBar = new AbilityBar(maxMana/4, maxMana, manaCost);
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
    }

    @Override
    public void levelUP(){
        super.levelUP();
        ManaBar.setMax(ManaBar.getMax() + super.GetLevel() * MagicNumbers.TWENTYFIVE.getValue());
        ManaBar.setCurrent(min(ManaBar.getCurrent() + ManaBar.getMax() / 4, ManaBar.getMax()));
        spellPower += spellPower + super.GetLevel() * MagicNumbers.TEN.getValue();
    }

    @Override
    public void castAbility(){
        if (ManaBar.castAbility()){
            Blizzard();
        }
        else{
            mc.send(getUnitName() + "Doesn't have enough mana");
        }
    }
    @Override
    public int AbilityDamage(){
        return this.spellPower;
    }

    private void Blizzard(){
        mc.send(getUnitName() + " cast Blizzard.");
        //list l = get list of enemys in range
        List<Enemy> l = GameTickSingleton.getInstance().getValue().getEnemiesInRange(range);

        for (int i = 0; i < hitCount  && !l.isEmpty(); i++){
            int x = (int)(Math.random() * l.size());
            Enemy enemy = l.get(x);
            combat.AbilityAttack(this, enemy, AbilityDamage());
            if (enemy.isDead()){
                l.remove(x);
            }
        }
        
    }
    @Override
    public void regainAbility(){
        this.ManaBar.regain(this.GetLevel());
    }

    @Override
    public String toString(){
        return super.toString() + "\tMana: " + ManaBar.getCurrent() + "/" + ManaBar.getMax() + "\tSpellPower: " + spellPower;
    }
    
}
