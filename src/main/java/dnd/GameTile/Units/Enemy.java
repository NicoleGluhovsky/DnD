package dnd.GameTile.Units;

import dnd.GameTile.MoveUnit;
import dnd.GameTile.Unit;

public abstract class Enemy extends Unit{
    private final int experienceVal;
    private double range;

    public Enemy(char tileChar, String name, int health, int AP, int DP, int xp){
        super(tileChar, name, health, AP, DP);
        this.experienceVal = xp;
    }
    
    protected int getXP()
    {
        return experienceVal;
    }

    protected void setRange(double range){
        this.range = range;
    }

    protected double getRange(){
        return range;
    }

    public void checkRange(Player player, MoveUnit moveUnit){
        if((this.getPosition().Range(player.getPosition())) < this.getRange()){
            noticePlayer(player, moveUnit);
        }
    }

    protected void noticePlayer(Player player, MoveUnit moveUnit){};

    @Override
    public void death(Unit killer){
        killer.kill(this);
    }

    @Override
    public void kill(Player luser){
        mc.send(luser.getUnitName() + " was killed by. " + getUnitName() + ".");
        luser.setAsDead();
    }

    @Override
    public void kill(Enemy Visited){
        mc.send("Enemy cannot kill another enemy");
    }

    @Override
    public String toString(){
        return super.toString() + "\tXP: " + experienceVal + "\tRange: " + range;
    }
}
