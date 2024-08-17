package dnd.GameTile.Units;


import dnd.GameTile.MoveUnit;

public class Boss extends Monster implements HeroicUnit {

    private final int abilityFrequency;
    private int combatTicks;
    private Player player;

    public Boss(char tileChar, String name, int health, int AP, int DP, int xp, int visionRange, int abilityFrequency){
        super(tileChar, name, health, AP, DP, xp, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility(){
        combat.AbilityAttack(this, player, AbilityDamage());
    }

    @Override
    public void checkRange(Player player, MoveUnit moveUnit){

        if ((this.getPosition().Range(player.getPosition())) < this.getRange()){
            noticePlayer(player, moveUnit);
        }   
        else{
            combatTicks = 0;
            moveUnit.RandomMove(this);
        }
    }


    @Override
    public int AbilityDamage(){
        return getAP();
    }

    @Override
    public void noticePlayer(Player player, MoveUnit moveUnit){
        if(combatTicks == abilityFrequency){
            this.player = player;
            combatTicks = 0;
            castAbility();
        }
        else{
            combatTicks++;
            super.noticePlayer(player, moveUnit);
        }
    }

}
