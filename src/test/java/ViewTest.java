import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import Controller.GameTick;
import Controller.GameTickSingleton;
import View.CLI;
import dnd.GameTile.Combat;
import dnd.GameTile.Point;
import dnd.GameTile.Units.Boss;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;

public class ViewTest {
    private final PrintStream originalOut = System.out;
    private CLI cli;
    private GameTick game;
    private Player player;
    private ByteArrayOutputStream outputStreamCaptor; 
    private Enemy enemy;   

    @Before
    public void setUp() {
        game = GameTickSingleton.getInstance(1).getValue();
        cli = new CLI();
        Combat combat = new Combat(cli);
        game.init(cli, cli, combat);
        player = game.getPlayer();
        game.init(cli, cli, combat);
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
    public void displayTest() {
        String toDisply = "Test";
        cli.send(toDisply);

        toDisply += "\n";
        String capturedOutput = outputStreamCaptor.toString();
        assertEquals(toDisply, capturedOutput);
        
    }

    @Test
    public void displayBoardTest() {
        String path = "/Users/ranbrachel/Desktop/university/simester_2/OOP/Assintment 3/DND/DnD/src/main/resources/Levels/level1.txt";
        cli.displayBoard(game.toDisplay());
        String capturedOutput = outputStreamCaptor.toString();
        List<String> boardString;
        try {
            boardString =  Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = boardString.toString().replace("[", "").replace("]", "").replace(", ", "\n") + "\n";
        assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    public void displayCombatTest() {
        cli.displayCombat(player, enemy, 10, 5);
        String capturedOutput = outputStreamCaptor.toString();

        String expectedOutput = player.getUnitName() + " engaged in combat with tomas.\n" + player.toString() + "\n" + enemy.toString() + "\n" + player.getUnitName() +" rolled 10 attack points.\n" + "tomas rolled 5 defense points.\n" + player.getUnitName() + " dealt 5 damage to tomas.\n";
        
        assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    public void displayLevelUpTest() {
        int healthDiff = player.getHealth().getMax();
        int attackDiff = player.getAP();
        int defenseDiff = player.getDP();
        int level = player.GetLevel() + 1;
        player.levelUP();
        String capturedOutput = outputStreamCaptor.toString();
        healthDiff = player.getHealth().getMax() - healthDiff;
        attackDiff = player.getAP() - attackDiff;
        defenseDiff = player.getDP() - defenseDiff;
        String expectedOutput = player.getUnitName() + " reached level " + level + ": +" + healthDiff + " Health, +" + attackDiff + " Attack, +" + defenseDiff + " Defence\n";
        assertEquals(expectedOutput, capturedOutput);
    }

    
    @Test
    public void displayGameOverTest() {

        player.setAsDead();
        boolean b = game.status();

        String capturedOutput = outputStreamCaptor.toString();
        String expectedOutput = "You Lost.\n_______Game Over_______\n";
        
        assertEquals(expectedOutput, capturedOutput);
    }
}
