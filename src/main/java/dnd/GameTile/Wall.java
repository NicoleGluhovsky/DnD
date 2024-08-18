package dnd.GameTile;
import View.CLIManagement.MessageCallBack;
import dnd.UnitManagment.MagicChars;


public class Wall extends Tile{
    public Wall(Point pos, MessageCallBack mc){
        super(MagicChars.WALL.getSymbol(), pos, mc);
    }

    @Override
    public void AttackTile(Unit unit){
        mc.send("Cannot attack a wall");
    }

}
