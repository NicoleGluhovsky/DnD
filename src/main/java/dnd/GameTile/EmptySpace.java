package dnd.GameTile;

public class EmptySpace extends Tile{
    private static final char EmptySpace_Tile = '.';
    public EmptySpace(Point pos){
        super(EmptySpace_Tile, pos);
    }

}
