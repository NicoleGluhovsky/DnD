package dnd.GameTile.Units;

import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.MagicNumbers;

public class Rogue extends Player{

    private  AbilityBar energyBar;
    private int energyCost;

    public Rogue(String name, int health, int AP, int DP, int energyCost){
        super(name, health, AP, DP);
        this.energyBar = new AbilityBar(MagicNumbers.HUNDRED.getValue(), MagicNumbers.HUNDRED.getValue(), energyCost);
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
