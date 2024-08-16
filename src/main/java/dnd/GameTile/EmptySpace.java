package dnd.GameTile;

import dnd.UnitManagment.Bars.MagicChars;

public class EmptySpace extends Tile{
    public EmptySpace(Point pos){
        super(MagicChars.EMPTYSPACE.getSymbol(), pos);
    }

    @Override
    public void AttackTile(Unit unit){
        throw new UnsupportedOperationException("Cannot attack EmptySpace");
    }

}
