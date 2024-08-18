package dnd.GameTile.Units;

import dnd.GameTile.Unit;
import dnd.UnitManagment.Bars.ExperienceBar;
import dnd.UnitManagment.Bars.MagicChars;
import dnd.UnitManagment.Bars.MagicNumbers;
import View.CLI;


public abstract class Player extends Unit implements HeroicUnit {
    private final ExperienceBar XP;
    private int Level;
    private CLI cli;

    public Player(String name, int health, int AP, int DP){
        super(MagicChars.PLAYER.getSymbol(), name, health, AP, DP);
        Level = 1;
        XP = new ExperienceBar(0 ,MagicNumbers.FIFTY.getValue()*Level);
    }

    public void init(CLI cli){
        this.cli = cli;
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
        mc.send(pray.getUnitName() + " died, " + getUnitName() + " gained " +pray.getXP()+ " experience");
        while(XP.checkExperience()){
            levelUP();
        }
        pray.setAsDead();
    }
    
    public int GetLevel(){
        return Level;
    }
    
    public void levelUP(){
        XP.setCurrent(0);
        Level++;
        XP.setMax(MagicNumbers.FIFTY.getValue() * Level);

        int healthButh = getHealth().getMax();
        getHealth().setMax(getHealth().getMax() + healthButh);
        getHealth().setCurrent(getHealth().getMax());

        int APButh = MagicNumbers.FOUR.getValue() * Level;
        super.setAP(super.getAP() + APButh);

        int DPButh = MagicNumbers.ONE.getValue() * Level;
        super.setDP(super.getDP() + DPButh);

        cli.displayLevelUp(this, healthButh, APButh, DPButh);
    }
    
    public abstract void regainAbility();

    @Override
    public String toString(){
        return super.toString() + "\tLevel: " + Level + "\tXP: " + XP.getCurrent() + "/" + XP.getMax();
    }

}

