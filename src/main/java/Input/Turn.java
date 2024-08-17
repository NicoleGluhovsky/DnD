package Input;

import dnd.GameTile.MoveUnit;
import dnd.GameTile.Units.Player;
import View.CLIManagement.MessageCallBack;

public class Turn {
    protected final Player player;
    protected final MoveUnit moveUnit;
    protected final MessageCallBack mc;


    public Turn(Player p, MessageCallBack mc){
        this.player = p;
        this.moveUnit = new MoveUnit(); 
        this.mc = mc;
    }

}
