package dnd.GameTile.Units;

import static java.lang.Math.min;
import java.util.List;

import Controller.GameTickSingleton;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.MagicNumbers;


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

    private int levelUpMaxMana(){
        int maxManaCurr = ManaBar.getMax();
        ManaBar.setMax(maxManaCurr + super.GetLevel() * MagicNumbers.TWENTYFIVE.getValue());
        ManaBar.setCurrent(min(ManaBar.getCurrent() + (ManaBar.getMax() / 4), ManaBar.getMax()));
        return ManaBar.getMax() - maxManaCurr;
    }

    private int levelUpSpellPower(){
        int spellPowerCurr = spellPower;
        spellPower = spellPower + super.GetLevel() * MagicNumbers.TEN.getValue();
        return spellPower - spellPowerCurr;
    }

    @Override
    public void levelUP(){
        super.levelUP();
        int maxManaDiff = levelUpMaxMana();
        int spellPowerdiff = levelUpSpellPower();

        mc.send("+" + maxManaDiff + " maximum mana, +" + spellPowerdiff + " spell power");
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
