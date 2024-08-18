package Input;

import View.CLIManagement.MessageCallBack;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.Bars.Directions;

public class PlayerTurn extends Turn {
    private final TerminalInput terminalInput;

    public PlayerTurn(Player player, MessageCallBack mc) {
        super(player, mc);
        this.terminalInput = new TerminalInput(mc);
    }    

    public void play() {
        player.regainAbility();
        Directions dir = terminalInput.getDirection();
        switch (dir){
            case SKIP -> mc.send("You skipped your turn");
            case CASTABILITY -> player.castAbility();
            default -> moveUnit.Move(player, dir);
        }
    }
}
