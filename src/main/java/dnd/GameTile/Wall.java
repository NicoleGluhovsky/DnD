package dnd.GameTile;
import dnd.UnitManagment.Bars.MagicChars;
import View.CLIManagement.MessageCallBack;


public class Wall extends Tile{
    public Wall(Point pos, MessageCallBack mc){
        super(MagicChars.WALL.getSymbol(), pos, mc);
    }

    @Override
    public void AttackTile(Unit unit){
        mc.send("Cannot attack a wall");
    }

}
