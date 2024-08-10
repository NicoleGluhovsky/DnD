package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.HealthBar;
import dnd.UnitManagment.Bars.MagicNumbers;

public class Warrior extends Player{
    private AbilityBar abilityBar; //current will be the remaining cooldown. max will be the cooldown time. each game tick will increase current by 1.


    public Warrior(char tileChar, Point pos, String name, HealthBar health, int AP, int DP, AbilityBar abilityBar, int abilityCooldown){
        super(pos, name, health, AP, DP);
        this.abilityBar = abilityBar;
    }

    @Override
    public void levelUP(){
        super.levelUP();
        abilityBar.setCurrent(MagicNumbers.ZERO.getValue());
        super.getHealth().setMax(super.getHealth().getMax() + MagicNumbers.FIVE.getValue() * super.GetLevel());
        super.setAP(super.getAP() + MagicNumbers.TWO.getValue() * super.GetLevel());
        super.setDP(super.getDP() + MagicNumbers.ONE.getValue() * super.GetLevel());
    }

    @Override
    public void castAbility(){
        if(abilityBar.getCurrent() == abilityBar.getMax()){
            abilityBar.setCurrent(MagicNumbers.ZERO.getValue());
            AvengersShield();
        }
    }

    private void AvengersShield(){
        //deal 10% of max health to all enemies in range
        Enemy enemy = null;

        combat.AvengersShield(this, enemy);
    }
    
    @Override
    public int AbilityDamage(){
        return (int)(this.getHealth().getMax() * 0.10);
    }

}

