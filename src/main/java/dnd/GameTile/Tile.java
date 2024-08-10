package dnd.GameTile;

public class Tile {
    private char tileChar;
    private Point position;

    protected Tile(char tileChar, int x, int y){
        this.tileChar = tileChar;
        this.position = new Point(x, y);
    }

    protected Tile(char tileChar, Point pos){
        this.tileChar = tileChar;
        this.position = pos;
    }
    protected Tile(Point pos){
        this.position = pos;
    }
    protected void setChar(char ch){
        this.tileChar = ch;
    }
}



