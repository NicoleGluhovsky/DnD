package dnd.GameTile;
import dnd.UnitManagment.Bars.MagicChars;


public class Wall extends Tile{
    public Wall(Point pos){
        super(MagicChars.WALL.getSymbol(), pos);
    }

    @Override
    public void AttackTile(Unit unit){
        throw new UnsupportedOperationException("Cannot attack a wall");
    }

}
