package dnd.GameTile.Units;

import java.util.List;

import Controller.GameTickSingleton;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.MagicNumbers;

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
    protected int levelUpAP(){//works
        int currentAP = super.getAP();
        super.setAP(currentAP + MagicNumbers.THREE.getValue() * super.GetLevel());
        return super.getAP() - currentAP;
    }

    @Override
    public void levelUP(){
        super.levelUP();    
        energyBar.fillBar();

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
