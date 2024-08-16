package dnd.GameTile.Units;

import dnd.GameTickSingleton;
import dnd.GameTile.Unit;
import dnd.UnitManagment.Bars.HealthBar;

public abstract class Enemy extends Unit{
    private final int experienceVal;
    private double range;

    public Enemy(char tileChar, String name, int health, int AP, int DP, int xp){
        super(tileChar, name, health, AP, DP);
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
        killer.kill(this);
    }

    @Override
    public void kill(Player Visited){
        Visited.setAsDead();
    }

    @Override
    public void kill(Enemy Visited){
        throw new UnsupportedOperationException("Enemy cannot kill another enemy");
    }


}
