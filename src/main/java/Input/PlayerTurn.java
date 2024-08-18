package Input;

import View.CLIManagement.MessageCallBack;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.Directions;

public class PlayerTurn extends Turn {
    private final TerminalInput terminalInput;
    private Directions diraction;

    public PlayerTurn(Player player, MessageCallBack mc) {
        super(player, mc);
        this.terminalInput = new TerminalInput(mc);
    }

    public Directions chooseMove(){
        diraction = terminalInput.getDirection();
        return diraction;
    }

    public void play(Directions diraction) {
        player.regainAbility();
        switch (diraction){
            case SKIP -> mc.send("You skipped your turn");
            case CASTABILITY -> {player.castAbility();System.out.println("you chose Ability");}
            default -> {moveUnit.Move(player, diraction); System.out.println("you chose to move");}
        }
    }
    public Directions getDirection(){
        return this.diraction;
    }
}
