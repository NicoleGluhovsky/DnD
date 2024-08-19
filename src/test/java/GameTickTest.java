import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Controller.GameTick;
import Controller.GameTickSingleton;
import View.CLI;
import dnd.GameTile.Combat;
import dnd.GameTile.Point;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Monster;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.Directions;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTickTest {

    private GameTick game;
    private CLI cli;
    private Player player;
    private Enemy enemy;
    private final String path = "levels_dir";


    @Before
    public void setUp(){
        game = GameTickSingleton.getInstance(1).getValue();
        cli = new CLI();
        Combat combat = new Combat(cli);
        game.init(cli, cli, combat, path);
        player = game.getPlayer();
        enemy = new Monster('T', "tomas", 100, 100, 1, 51,10);
        enemy.init(cli, cli, combat);
        game.getEnemies().add(enemy);
        Point nextPos = new Point(5,8, cli);
        enemy.setPosition(nextPos);
    }

    @After
    public void tearDown() {
        game.getEnemies().clear();
        game = null;
        cli = null;
        player = null;
        enemy = null;
    }


    @Test
    public void A_LoadBoardLevelTest(){
        char[][] board = game.toDisplay();
        List<String> loadedBoard = new ArrayList<>();
         for (char[] board1 : board) {
            String row = "";
            for (int j = 0; j < board1.length; j++) {
                row += board1[j];
            }
            loadedBoard.add(row);
        }


        List<String> boardString;
        String filepath = path + "/level1.txt";
        try {
            boardString =  Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(loadedBoard, boardString);
    }

    @Test
    public void B_getEnemiesInRangeTest(){
        List<Enemy> enemies = game.getEnemiesInRange(3.0);
        assertEquals(0, enemies.size());

        game.swapPosition(enemy, game.getTileValue(new Point(2,9,cli)));

        enemies = game.getEnemiesInRange(3.0);
        assertEquals(1, enemies.size());
    }

    @Test
    public void C_getClosesetEnemy(){
        Enemy b = new Monster('T', "tomas", 100, 100, 1, 51,10);
        b.init(cli, cli, new Combat(cli));
        b.setPosition(new Point(12, 25, cli));
        game.getEnemies().add(b);
        game.swapPosition(b, game.getTileValue(new Point(3,10,cli)));

        Enemy closestEnemy = game.getClosesetEnemy();
        assertEquals(b, closestEnemy);

        game.swapPosition(enemy, game.getTileValue(new Point(2,9,cli)));

        closestEnemy = game.getClosesetEnemy();
        assertEquals(enemy, closestEnemy);
    }

    @Test
    public void D_killedAnEnemyTest(){
        game.killedAnEnemy(enemy, Directions.UP);
        assertFalse(game.getEnemies().contains(enemy));
        assertEquals(enemy.getPosition(), player.getPosition());
    }
}
