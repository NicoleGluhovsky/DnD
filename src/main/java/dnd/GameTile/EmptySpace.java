package dnd.GameTile;

import dnd.UnitManagment.Bars.MagicChars;
import View.CLIManagement.MessageCallBack;


public class EmptySpace extends Tile{
    public EmptySpace(Point pos, MessageCallBack mc){
        super(MagicChars.EMPTYSPACE.getSymbol(), pos, mc);
    }

    @Override
    public void AttackTile(Unit unit){
        mc.send("Cannot attack EmptySpace");
    }

}
