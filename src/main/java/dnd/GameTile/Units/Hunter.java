package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.HealthBar;
import dnd.UnitManagment.Bars.MagicNumbers;

public class Hunter extends Player{
    private final AbilityBar quiver;
    private final double range;
    private int arrowCount;
    private int tickCount;


    public Hunter(Point pos, String name, HealthBar health, int AP, int DP, AbilityBar quiver, int arrowCount, double range, int tickCount){
        super(pos, name, health, AP, DP);
        this.quiver = quiver;
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
        if (quiver.castAbility()){
            Shoot();
        }
    }

    @Override
    public void levelUP(){
        super.levelUP();
        arrowCount += TEN * super.GetLevel();
        super.setAP(super.getAP() + MagicNumbers.TWO.getValue() * super.GetLevel());
        super.setDP(super.getDP() + MagicNumbers.ONE.getValue() * super.GetLevel());
    }

    public void Shoot(){
        //choose how to attack
        Enemy enemy = null;
        //attack
        combat.AbilityAttack(this, enemy, AbilityDamage()); 
    }

}
