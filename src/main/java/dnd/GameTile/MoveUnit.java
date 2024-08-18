package dnd.GameTile;

import java.util.Random;

import Controller.GameTickSingleton;
import dnd.UnitManagment.Bars.Directions;
    

public class MoveUnit {

    
    public void RandomMove(Unit U){
        Random rand = new Random();
        int directionIndex = rand.nextInt(Directions.values().length-2); //to not castability
        Directions direction = Directions.values()[directionIndex];
        Move(U, direction);
    }

    public void Move(Unit U, Directions direction){
        // get current position VS next position
        Point currentPos = U.getPosition();
        Point nextPos = new Point(currentPos);
        nextPos.Move(direction);
        // chack what is on next tile
        Tile nextTile = GameTickSingleton.getInstance().getValue().getTileValue(nextPos);
        char nextTileChar = nextTile.getHiddenChar();
        char thisTileChar = U.getTileChar();
        switch (nextTileChar) {
            case '#' -> {
            }
            case '.' -> // if empty, move
                GameTickSingleton.getInstance().getValue().swapPosition(U, nextTile);
            case '@' -> // if it is a unit, visitor, only if playerVSMonster attack. else do nothing
                nextTile.AttackTile(U);
            default -> {
                if (thisTileChar == '@') {
                    nextTile.AttackTile(U);
                }
            }
        }
        // if it is a wall, do nothing
                
        
       
    }

}
