package dnd.GameTile.Units;

import dnd.GameTile.MoveUnit;
import dnd.UnitManagment.Directions;

public class Monster extends Enemy {

    public Monster(char tileChar, String name, int health, int AP, int DP, int xp, int visionRange){
        super(tileChar, name, health, AP, DP, xp);
        super.setRange(visionRange);
    }

    @Override
    public void checkRange(Player player, MoveUnit moveUnit){
        if ((this.getPosition().Range(player.getPosition())) < getRange()){
            noticePlayer(player, moveUnit);
        }   
        else{
            moveUnit.RandomMove(this);
        }
    }

    @Override
    public void noticePlayer(Player player, MoveUnit moveUnit){
        int dx = this.getPosition().xDiff(player.getPosition());
        int dy = this.getPosition().yDiff(player.getPosition());
            if (Math.abs(dx) > Math.abs(dy)){
                if (dx > 0){
                    moveUnit.Move(this, Directions.LEFT);
                }
                else{
                    moveUnit.Move(this, Directions.RIGHT);
                }
            }
            else{
                if (dy > 0){
                    moveUnit.Move(this, Directions.UP);
                }
                else{
                    moveUnit.Move(this, Directions.DOWN);
                }
            }
    }
}
