package dnd.GameTile.Units;

import dnd.GameTile.Unit;
import dnd.GameTile.MoveUnit;

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

    public double getRange(){
        return range;
    }

    public void checkRange(Player player, MoveUnit moveUnit){
        if((this.getPosition().Range(player.getPosition())) < this.getRange()){
            noticePlayer(player, moveUnit);
        }
    }

    public void noticePlayer(Player player, MoveUnit moveUnit){};

    @Override
    public void death(Unit killer){
        killer.kill(this);
    }

    @Override
    public void kill(Player luser){
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
