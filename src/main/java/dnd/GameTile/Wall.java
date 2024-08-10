package dnd.GameTile;

public class Wall extends Tile{
    public static final char Wall_Tile = '#';
    public Wall(Point pos){
        super(Wall_Tile, pos);
    }

}
