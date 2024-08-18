package dnd.GameTile.Units;

import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.MagicNumbers;
import Controller.GameTickSingleton;
import java.util.List;

public class Rogue extends Player{

    private final AbilityBar energyBar;

    public Rogue(String name, int health, int AP, int DP, int energyCost){
        super(name, health, AP, DP);
        this.energyBar = new AbilityBar(MagicNumbers.HUNDRED.getValue(), MagicNumbers.HUNDRED.getValue(), energyCost);
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
        else{
            mc.send(getUnitName() + "Doesn't have enough energy");
        }
    }

    private void FanOfKnives(){
        mc.send(getUnitName() + " cast Fan of Knives.");
        List<Enemy> enemies = GameTickSingleton.getInstance().getValue().getEnemiesInRange(MagicNumbers.TWO.getValue());
        for (Enemy e : enemies){

            combat.AbilityAttack(this, e, AbilityDamage());
        }
    }

    @Override
    public void regainAbility(){
        energyBar.regain(10);
    }

    @Override
    public String toString(){
        return super.toString() + "\tEnergy: " + energyBar.getCurrent() + "/" + energyBar.getMax();
    }

}
