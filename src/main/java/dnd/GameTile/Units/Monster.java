package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.HealthBar ;;

public class Monster extends Enemy {

    public Monster(char tileChar, Point pos, String name, HealthBar health, int AP, int DP, int xp, int visionRange){
        super(tileChar, pos, name, health, AP, DP, xp);
        super.setRange(visionRange);
    }

    public void chasePlayer(){
        //TODO
    }
}
