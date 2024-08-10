package dnd.GameTile.Units;

import dnd.GameTile.Point;
import dnd.GameTile.Unit;
import dnd.UnitManagment.Bars.ExperienceBar;
import dnd.UnitManagment.Bars.HealthBar;

public abstract class Player extends Unit implements HeroicUnit {
    private final ExperienceBar XP;
    private  int Level;
    protected final int FIFTY = 50;
    protected final int TEN = 10;
    protected final int FOUR = 4;
    protected final int ONE = 1;
    protected static final char PLAYERCHAR = '@';

    public Player(Point pos, String name, HealthBar health, int AP, int DP){
        super(PLAYERCHAR, pos, name, health, AP, DP);
        Level = 1;
        XP = new ExperienceBar(0, FIFTY*Level);
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
        XP.gainExperience(Visited.getXP());
    }




    public int GetLevel(){
        return Level;
    }
    
    public void levelUP(){
        XP.setCurrent(0);
        Level++;
        XP.setMax(FIFTY * Level);
        getHealth().setMax(getHealth().getMax() + TEN * Level);
        getHealth().setCurrent(getHealth().getMax());
        super.setAP(super.getAP() + FOUR * Level);
        super.setDP(super.getDP() + ONE * Level);
    }
}

