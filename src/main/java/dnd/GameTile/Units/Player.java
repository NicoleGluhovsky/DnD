package dnd.GameTile.Units;

import dnd.GameTile.Unit;
import dnd.UnitManagment.Bars.ExperienceBar;
import dnd.UnitManagment.MagicChars;
import dnd.UnitManagment.MagicNumbers;


public abstract class Player extends Unit implements HeroicUnit {
    private final ExperienceBar XP;
    protected int Level;

    public Player(String name, int health, int AP, int DP){
        super(MagicChars.PLAYER.getSymbol(), name, health, AP, DP);
        Level = 1;
        XP = new ExperienceBar(0 ,MagicNumbers.FIFTY.getValue()*Level);
    }

    public ExperienceBar getXP(){
        return XP;
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
    
    protected int GetLevel(){
        return Level;
    }

    protected void levelUpXP(){
        XP.setCurrent(XP.getCurrent() - XP.getMax());
        XP.setMax(MagicNumbers.FIFTY.getValue() * Level);
    }

    protected void levelUpLevel(){
        Level++;
    }

    protected int levelUpHealth(){
        int currentHealth = getHealth().getMax();

        getHealth().setMax(currentHealth + MagicNumbers.TEN.getValue() * Level);
        getHealth().setCurrent(getHealth().getMax());

        return getHealth().getMax() - currentHealth;
    }

    protected int levelUpAP(){
        int currentAP = getAP();

        setAP(currentAP + MagicNumbers.FOUR.getValue() * Level);

        return getAP() - currentAP;
    }

    protected int levelUpDP(){
        int currentDP = getDP();

        setDP(currentDP + MagicNumbers.ONE.getValue() * Level);

        return getDP() - currentDP;
    }
    
    protected void levelUP(){
        levelUpLevel();
        levelUpXP();
        int healthButh = levelUpHealth();
        int APButh = levelUpAP();
        int DPButh = levelUpDP();

        mc.send(getUnitName() + " reached level " + Level + ": +" + healthButh + " Health, +" + APButh + " Attack, +" + DPButh + " Defence");
    }
    
    public abstract void regainAbility();

    @Override
    public String toString(){
        return super.toString() + "\tLevel: " + Level + "\tXP: " + XP.getCurrent() + "/" + XP.getMax();
    }

}

