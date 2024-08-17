package dnd.GameTile.Units;

import dnd.GameTile.Unit;
import dnd.UnitManagment.Bars.ExperienceBar;
import dnd.UnitManagment.Bars.MagicChars;
import dnd.UnitManagment.Bars.MagicNumbers;


public abstract class Player extends Unit implements HeroicUnit {
    private final ExperienceBar XP;
    private  int Level;

    public Player(String name, int health, int AP, int DP){
        super(MagicChars.PLAYER.getSymbol(), name, health, AP, DP);
        Level = 1;
        XP = new ExperienceBar(0 ,MagicNumbers.FIFTY.getValue()*Level);
    }

    @Override       
    public void death(Unit killer){
        killer.kill(this);
    }

    @Override
    public void kill(Player Visited){
        throw new UnsupportedOperationException("Cannot support two Players at once");
    }
    
    @Override
    public void kill(Enemy pray){
        XP.gainExperience(pray.getXP());
        if(XP.checkExperience()){
            levelUP();
        }
        //GameTickSingleton.getInstance().getValue().killedAnEnemy(this, Visited);
        pray.setAsDead();
    }
    
    public int GetLevel(){
        return Level;
    }
    
    public void levelUP(){
        XP.setCurrent(0);
        Level++;
        XP.setMax(MagicNumbers.FIFTY.getValue() * Level);
        getHealth().setMax(getHealth().getMax() + MagicNumbers.TEN.getValue() * Level);
        getHealth().setCurrent(getHealth().getMax());
        super.setAP(super.getAP() + MagicNumbers.FOUR.getValue() * Level);
        super.setDP(super.getDP() + MagicNumbers.ONE.getValue() * Level);
    }

    @Override
    public String toString(){
        return super.toString() + "\tLevel: " + Level + "\tXP: " + XP.getCurrent() + "/" + XP.getMax();
    }

    public abstract void regainAbility();
}

