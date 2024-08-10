package dnd.GameTile.Units;

import static java.lang.Math.min;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.HealthBar;
import dnd.UnitManagment.Bars.MagicNumbers;


public class Mage extends Player{
    private final AbilityBar ManaBar;
    private int spellPower;
    private int hitCount;
    private final Double range;


    public Mage(Point pos, String name, HealthBar health, int AP, int DP, int maxMana, int manaCost, double range, int spellPower, int hitCount){
        super(pos, name, health, AP, DP);
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
        spellPower += spellPower + super.GetLevel() * TEN;
    }

    @Override
    public void castAbility(){
        if (ManaBar.castAbility()){
            Blizzard();
        }
        else{
            throw new IllegalArgumentException("Not enough mana");
        }
    }
    @Override
    public int AbilityDamage(){
        return this.spellPower;
    }

    private void Blizzard(){
        //list l = get list of enemys in range
        //for heatCount:
        //  x = random(len(list))
        Enemy enemy = null;
        //  atack: list[x] with: spellPower
        combat.AbilityAttack(this, enemy, AbilityDamage()); 
        //  if list[x] dead, list.pop(x)
    }
    
}
