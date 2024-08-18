import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import Controller.GameTick;
import Controller.GameTickSingleton;
import Input.EnemyTurn;
import View.CLI;
import dnd.GameTile.Combat;
import dnd.GameTile.Point;
import dnd.GameTile.Tile;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;

public class EnemyTest {
    private GameTick game;
    private CLI cli;
    private Player player;


    @Before
    public void setUp(){
       this.game = GameTickSingleton.getInstance(1).getValue();
        this.cli = new CLI();
        game.init(cli, cli, new Combat(cli));
        player = game.getPlayer();
        player.init(cli);
    }

    @Test
    public void EnemyMoveRandom(){
        List<Point> enemyPositions = new ArrayList<>();
        for (Enemy enemy : game.getEnemies()) {
            enemyPositions.add(enemy.getPosition());
        }
        for (Enemy enemy : game.getEnemies()) {
            EnemyTurn turn = new EnemyTurn(player, enemy, cli);
            turn.play();
        }
        List<Point> newEnemyPositions = new ArrayList<>();
        for (Enemy enemy : game.getEnemies()) {
            newEnemyPositions.add(enemy.getPosition());
        }
        for (int i = 0; i < enemyPositions.size(); i++) {
            boolean b = enemyPositions.get(i).isInRange(newEnemyPositions.get(i),2.0);
            assertTrue(b);
        }
    }

    @Test
    public void EnemyChase(){
        Point playernewPosition = new Point(5, 5, cli);
        Tile t = game.getTileValue(playernewPosition);
        game.swapPosition(player, t);

        Point[] enemyPositions = new Point[]{new Point(5, 3, cli), new Point(3, 5, cli), new Point(5, 7, cli), new Point(7, 5, cli)};
        Point[] expectedResPosition = new Point[]{new Point(5, 4, cli), new Point(4, 5, cli), new Point(5, 6, cli), new Point(6, 5, cli)};

        List<Integer> monsters = Arrays.asList(3, 4, 5, 7);

        for (int i = 0; i < enemyPositions.length; i++) {
            Tile t1 = game.getTileValue(enemyPositions[i]);
            Tile t2 = game.getEnemies().get(monsters.get(i));
            game.swapPosition(t2, t1);
        }

        for (Enemy enemy : game.getEnemies()) {
            EnemyTurn turn = new EnemyTurn(player, enemy, cli);
            turn.play();
        }
        for(int i=0; i<monsters.size(); i++){
            boolean bool = game.getEnemies().get(monsters.get(i)).getPosition().equals(expectedResPosition[i]);
            assertTrue(bool);
        }
    }

    @Test
    public void EnemyAttack(){
        Enemy e = game.getEnemies().get(0);
        Point nextPos = new Point(player.getPosition().getX(), player.getPosition().getY() + 1, cli);
        game.swapPosition(e, game.getTileValue(nextPos));

        int currentHealth = player.getHealth().getCurrent();

        EnemyTurn turn = new EnemyTurn(player,e, cli);
        turn.play();

        assertNotEquals(player.getHealth().getCurrent(), currentHealth);
    }

    @Test
    public void BossCastAcility(){}

    @Test
    public void TrapTurnsInvisableAndVisable(){}

    @Test
    public void TrapAttackesPlayer(){}

    @Test
    public void MonsterAttackesPlayer(){}

    @Test
    public void EnemyColidesEnemy(){}




}
