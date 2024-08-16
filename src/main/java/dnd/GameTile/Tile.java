package dnd.GameTile;

import dnd.UnitManagment.Bars.MagicChars;

public abstract class Tile {
    private char tileChar;
    private Point position;


    protected Tile(char tileChar, Point pos){
        this.tileChar = tileChar;
        this.position = pos;
    }
    protected Tile(char tileChar){
        this.tileChar = tileChar;
    }
    protected void setAsDead(){
        this.tileChar = MagicChars.DEAD.getSymbol();
    }

    public void swapPosition(Tile tile) {
        Point temp = tile.position;
        tile.position = this.position;
        this.position = temp;
    }

    @Override
    public String toString(){
        return String.valueOf(tileChar);
    }

    public void setPosition(Point pos){
        this.position = pos;
    }
    public Point getPosition(){
        return this.position;
    }
    public char getTileChar(){
        return this.tileChar;
    }
    public abstract void AttackTile(Unit unit);
}



