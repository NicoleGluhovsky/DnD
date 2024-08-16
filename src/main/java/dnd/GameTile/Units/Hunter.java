package dnd.GameTile.Units;

import dnd.UnitManagment.Bars.MagicNumbers;

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
            arrowCount --;
        }
    }

    @Override
    public void levelUP(){
        super.levelUP();
        arrowCount += MagicNumbers.TEN.getValue() * super.GetLevel();
        super.setAP(super.getAP() + MagicNumbers.TWO.getValue() * super.GetLevel());
        super.setDP(super.getDP() + MagicNumbers.ONE.getValue() * super.GetLevel());
    }

    public void Shoot(){
        //choose how to attack
        Enemy enemy = null;
        //attack
        combat.AbilityAttack(this, enemy, AbilityDamage()); 
    }

    @Override
    public String toString(){
        return super.toString() + "\\tArrows: " + arrowCount + "\\tRange: " + range;
    }

}
