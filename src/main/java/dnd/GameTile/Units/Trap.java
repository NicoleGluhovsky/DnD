package dnd.GameTile.Units;


import dnd.GameTile.MoveUnit;
public class Trap extends Enemy{

    private final int visibilityTime;
    private final int invisibilityTime;
    private int tickCount;
    private boolean visible;
    private final double TRAPRANGE = 2.0;

    public Trap(char tileChar, String name, int health, int AP, int DP, int xp, int visibilityTime, int invisibilityTime, int tickCount, boolean visible){
        super(tileChar, name, health, AP, DP, xp);
        super.setRange(TRAPRANGE);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.tickCount = tickCount;
        this.visible = visible;
    }

    @Override
    public void checkRange(Player player, MoveUnit moveUnit){
        changeVisibilityState();
        if((this.getPosition().Range(player.getPosition())) < this.getRange()){
            noticePlayer(player, moveUnit);
        }
    }

    @Override
    public void noticePlayer(Player player, MoveUnit moveUnit){
        changeTileVisibility(true);
        tickCount = 0;
        player.takeHit(this.getAP());
    }

    @Override
    public boolean takeHit(int damage)
    {
        tickCount = 0;
        changeTileVisibility(true);
        return this.getHealth().takeDamage(damage);
    } 

    public void changeVisibilityState(){
        if(visible != tickCount < visibilityTime){
            visible = !visible;
            changeTileVisibility(visible);
        }
         
        if (visibilityTime + invisibilityTime == tickCount) {
            tickCount = 0;
        }
        else {
            tickCount++;
        }
    }
}
