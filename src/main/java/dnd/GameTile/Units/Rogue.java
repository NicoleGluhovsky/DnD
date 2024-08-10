package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.HealthBar;
import dnd.UnitManagment.Bars.MagicNumbers;

public class Rogue extends Player{

    private  AbilityBar energyBar;
    private int energyCost;

    public Rogue(Point pos, String name, HealthBar health, int AP, int DP, AbilityBar energyBar, int energyCost){
        super(pos, name, health, AP, DP);
        this.energyBar = energyBar;
        this.energyCost = energyCost;
    }

    @Override
    public int AbilityDamage(){
        return getAP();
    }

    @Override
    public void levelUP(){
        super.levelUP();    
        energyBar.fillBar();
        super.setAP(getAP() + MagicNumbers.THREE.getValue() * super.GetLevel());
    }

    @Override
    public void castAbility(){
        if (energyBar.castAbility()){
            FanOfKnives();
        }
    }

    private void FanOfKnives(){
        //attack all in range 2
        Enemy enemy = null;
        //attack
        combat.AbilityAttack(this, enemy, AbilityDamage()); 
    }

}
