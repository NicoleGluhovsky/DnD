package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.HealthBar;

public class Boss extends Monster implements HeroicUnit {

    public Boss(char tileChar, Point pos, String name, HealthBar health, int AP, int DP, int xp, int visionRange){
        super(tileChar, pos, name, health, AP, DP, xp, visionRange);
    }

    @Override
    public void castAbility(){
        //TODO
    }

    @Override
    public int AbilityDamage(){
        return getAP();
    }

}
