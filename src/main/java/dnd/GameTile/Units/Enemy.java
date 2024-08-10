package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.GameTile.Unit;
import dnd.UnitManagment.Bars.HealthBar;

public class Enemy extends Unit{
    private final int experienceVal;
    private double range;

    public Enemy(char tileChar, Point pos, String name, HealthBar health, int AP, int DP, int xp){
        super(tileChar, pos, name, health, AP, DP);
        this.experienceVal = xp;
    }
    public int getXP()
    {
        return experienceVal;
    }

    public void setRange(double range){
        this.range = range;
    }

    @Override
    public void death(Unit killer){
        killer.accept(this);
    }
    
    @Override
    public void accept(Unit killer){
        killer.visit(this);
    }

    @Override
    public void visit(Player Visited){
        //
    }

    @Override
    public void visit(Enemy Visited){
        //
    }


}
