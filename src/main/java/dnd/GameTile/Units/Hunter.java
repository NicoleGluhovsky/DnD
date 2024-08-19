package dnd.GameTile.Units;

import Controller.GameTickSingleton;
import dnd.UnitManagment.MagicNumbers;

public class Hunter extends Player{
    private final double range;
    private int arrowCount;
    private int tickCount;


    public Hunter(String name, int health, int AP, int DP, double range){
        super(name, health, AP, DP);
        this.arrowCount = MagicNumbers.TEN.getValue() * super.GetLevel();
        this.range = range;
        this.tickCount = MagicNumbers.ZERO.getValue();
    }

    @Override
    public int AbilityDamage(){
        return getAP();
    }

    @Override
    public void castAbility(){
        if (arrowCount > 0){
            Shoot();
        }
    }

    @Override
    protected int levelUpAP(){
        int currentAP = super.getAP();
        super.setAP(currentAP + MagicNumbers.TWO.getValue() * super.GetLevel());
        return super.getAP() - currentAP;
    }

    @Override
    protected void levelUP(){
        super.levelUP();
        arrowCount += MagicNumbers.TEN.getValue() * super.GetLevel();
    }

    public void Shoot(){
        //choose how to attack
        Enemy enemy = GameTickSingleton.getInstance().getValue().getClosesetEnemy();
        //attack
        if(InRange(enemy, this.range)){
            mc.send(getUnitName() + " fired an arrow at " + enemy.getUnitName() + ".");
            combat.AbilityAttack(this, enemy, AbilityDamage()); 
            arrowCount --;
        }
        else{
            mc.send(getUnitName() + " tried to shoot an arrow but there were no enemies in range.");
        }
    }
    @Override
    public void regainAbility(){
        if(tickCount == 10){
            tickCount = 0;
            arrowCount += GetLevel();
        }
        else{
            tickCount++;
        }
    }

    @Override
    public String toString(){
        return super.toString() + "\tArrows: " + arrowCount + "\tRange: " + range;
    }

}
