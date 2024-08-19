import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Controller.GameTick;
import Controller.GameTickSingleton;
import View.CLI;
import dnd.GameTile.Combat;
import dnd.GameTile.Point;
import dnd.GameTile.Units.Boss;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ViewTest {
    private final PrintStream originalOut = System.out;
    private CLI cli;
    private GameTick game;
    private Player player;
    private ByteArrayOutputStream outputStreamCaptor; 
    private Enemy enemy;  
    private final String path =  "levels_dir";

    @Before
    public void setUp(){
        game = GameTickSingleton.getInstance(1).getValue();
        cli = new CLI();
        Combat combat = new Combat(cli);
        game.init(cli, cli, combat, path);
        player = game.getPlayer();
        enemy = new Boss('T', "tomas", 100, 100, 1, 51,10,1);
        enemy.init(cli, cli, combat);
        game.getEnemies().add(enemy);
        Point nextPos = new Point(5,8, cli);
        enemy.setPosition(nextPos);
        

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @After
    public void tearDown() {
        game.getEnemies().clear();
        game = null;
        cli = null;
        player = null;
        System.setOut(originalOut);
    }

    



    @Test
    public void A_displayTest() {
        String toDisply = "Test";
        cli.send(toDisply);

        toDisply += "\r\n";
        String capturedOutput = outputStreamCaptor.toString();
        assertEquals(toDisply, capturedOutput);
        
    }

    @Test
    public void B_displayBoardTest() {
        String filename = path + "/level1.txt";
        cli.displayBoard(game.toDisplay());
        String capturedOutput = outputStreamCaptor.toString();
        List<String> boardString;
        try {
            boardString =  Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = boardString.toString().replace("[", "").replace("]", "").replace(", ", "\r\n") + "\r\n";
        assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    public void C_displayLevelUpTest(){
        player.levelUP();
        String capturedOutput = outputStreamCaptor.toString();
        String expectedOutput = player.getUnitName() + " reached level 2: +10 Health, +4 Attack, +2 Defence\r\n";
        assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    public void D_displayCombatTest() {
        cli.displayCombat(player, enemy, 10, 5);
        String capturedOutput = outputStreamCaptor.toString();

        String expectedOutput = player.getUnitName() + " engaged in combat with tomas.\r\n" + player.toString() + "\r\n" + enemy.toString() + "\r\n" + player.getUnitName() +" rolled 10 attack points.\r\n" + "tomas rolled 5 defense points.\r\n" + player.getUnitName() + " dealt 5 damage to tomas.\r\n";
        
        assertEquals(expectedOutput, capturedOutput);
    }

    
    @Test
    public void E_displayGameOverTest() {

        player.setAsDead();
        game.status();

        String capturedOutput = outputStreamCaptor.toString();
        String expectedOutput = "You Lost.\n_______Game Over_______\r\n";
        
        assertEquals(expectedOutput, capturedOutput);
    }
}
