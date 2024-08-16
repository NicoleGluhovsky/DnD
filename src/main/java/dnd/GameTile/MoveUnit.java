package dnd.GameTile;

import java.util.Random;

import dnd.GameTickSingleton;
import dnd.GameTile.Units.Monster;

    

class MoveUnit {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
    
    public void RandomMove(Unit U){
        Random rand = new Random();
        int directionIndex = rand.nextInt(Direction.values().length);
        Direction direction = Direction.values()[directionIndex];
        Move(U, direction);
    }

    public void Move(Unit U, Direction direction){
        // get current position VS next position
        Point currentPos = U.getPosition();
        Point nextPos = new Point(currentPos);
        nextPos.Move(direction);
        // chack what is on next tile
        Tile nextTile = GameTickSingleton.getInstance().getValue().getTileValue(nextPos);
        char nextTileChar = nextTile.getTileChar();
        char thisTileChar = U.getTileChar();
        switch (nextTileChar) {
            case '#' -> {
            }
            case '.' -> // if empty, move
                U.swapPosition(nextTile);
            case '@' -> // if it is a unit, visitor, only if playerVSMonster attack. else do nothing
                nextTile.AttackTile(U);
            default -> {
                if (thisTileChar == '@' && nextTileChar == 'M') {
                    nextTile.AttackTile(U);
                }
            }
        }
        // if it is a wall, do nothing
                
        
       
    }

    public static void main(String[] args) {
        System.out.println("in MoveUnit");
        Monster U = new Monster('@', "Monsty", 100 , 10, 20, 100, 2);
        U.setPosition(new Point(5, 5));
        MoveUnit MU = new MoveUnit();
        MU.RandomMove(U);
    }
}
