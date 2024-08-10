package dnd.GameTile;

public class Tile {
    private char tileChar;
    private Point position;


    protected Tile(char tileChar, Point pos){
        this.tileChar = tileChar;
        this.position = pos;
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
}



