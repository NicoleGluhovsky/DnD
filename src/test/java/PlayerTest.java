
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import Controller.*;
import Input.*;
import View.CLIManagement.MessageCallBack;
import dnd.GameTile.Units.*;
import dnd.UnitManagment.Bars.*;



public class PlayerTest {
    private GameTick game;
    private MessageCallBack mc;
    private Player player;

    @Before
    public void setUp() {
        this.game = GameTickSingleton.getInstance(1).getValue();
        this.player = game.getPlayer();
        this.mc = new MessageCallBack(){
            @Override
            public void send(String message) {
                System.out.println(message);
            }
        };
    }
    
    @Test
    public void MovePlayer() {
        PlayerTurn turn = new PlayerTurn(player, mc);
        turn.play(Directions.UP);
    }
}
