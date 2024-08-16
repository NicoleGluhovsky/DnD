package dnd.GameTile.Units;

import dnd.UnitManagment.Bars.HealthBar;

public class Boss extends Monster implements HeroicUnit {

    private int abilityFrequency;

    public Boss(char tileChar, String name, int health, int AP, int DP, int xp, int visionRange, int abilityFrequency){
        super(tileChar, name, health, AP, DP, xp, visionRange);
        this.abilityFrequency = abilityFrequency;
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
