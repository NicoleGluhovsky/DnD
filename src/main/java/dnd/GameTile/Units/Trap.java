package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.UnitManagment.Bars.HealthBar;

public class Trap extends Enemy{

    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;
    private final double TRAPRANGE = 2.0;

    public Trap(char tileChar, Point pos, String name, HealthBar health, int AP, int DP, int xp, int visibilityTime, int invisibilityTime, int tickCount, boolean visible){
        super(tileChar, pos, name, health, AP, DP, xp);
        super.setRange(TRAPRANGE);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.tickCount = tickCount;
        this.visible = visible;
    }

}
