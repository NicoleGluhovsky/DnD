import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Controller.GameTick;
import Controller.GameTickSingleton;
import Input.EnemyTurn;
import View.CLI;
import dnd.GameTile.Combat;
import dnd.GameTile.Point;
import dnd.GameTile.Tile;
import dnd.GameTile.Units.Boss;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Monster;
import dnd.GameTile.Units.Player;
import dnd.GameTile.Units.Trap;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnemyTest{
    private GameTick game;
    private CLI cli;
    private Player player;
    private Enemy enemy;
    private final String path = "levels_dir";

    @Before
    public void setUp() {
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
        game.swapPosition(enemy, game.getTileValue(nextPos));
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
    public void A_EnemyMoveRandom(){
        List<Point> enemyPositions = new ArrayList<>();
        for (Enemy e : game.getEnemies()) {
            enemyPositions.add(e.getPosition());
        }
        for (Enemy e : game.getEnemies()) {
            EnemyTurn turn = new EnemyTurn(player, e, cli);
            turn.play();
        }
        List<Point> newEnemyPositions = new ArrayList<>();
        for (Enemy e : game.getEnemies()) {
            newEnemyPositions.add(e.getPosition());
        }
        for (int i = 0; i < enemyPositions.size(); i++) {
            boolean b = enemyPositions.get(i).isInRange(newEnemyPositions.get(i),2.0);
            assertTrue(b);
        }
    }

    @Test
    public void B_EnemyChase(){
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

        for (Enemy e : game.getEnemies()) {
            EnemyTurn turn = new EnemyTurn(player, e, cli);
            turn.play();
        }
        for(int i=0; i<monsters.size(); i++){
            boolean bool = game.getEnemies().get(monsters.get(i)).getPosition().equals(expectedResPosition[i]);
            assertTrue(bool);
        }
    }

    @Test
    public void C_EnemyAttack(){
        int currentHealth = player.getHealth().getCurrent();
        
        player.getHealth().setCurrent(400);
        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));

        while(player.getHealth().getCurrent() == currentHealth){//in case the AP comes out 0
            EnemyTurn turn = new EnemyTurn(player, enemy, cli);
            turn.play();
        }

        assertNotEquals(player.getHealth().getCurrent(), currentHealth);
    }

    @Test
    public void D_BossCastAcility(){
        Enemy boss = new Boss('T', "TOMAS", 100, 10, 100, 100, 5, 1);
        boss.init(cli, cli, new Combat(cli));
        boss.setPosition(new Point(10, 10, cli));
        game.swapPosition(boss, game.getTileValue(new Point(5, 9, cli)));
        

        int currentHealth = player.getHealth().getCurrent();
        
        EnemyTurn turn = new EnemyTurn(player, boss, cli);
        turn.play();

        assertEquals(player.getHealth().getCurrent(), currentHealth);
        while(player.getHealth().getCurrent() == currentHealth){
            turn = new EnemyTurn(player, boss, cli);
            turn.play();
        }
        


        assertNotEquals(player.getHealth().getCurrent(), currentHealth);



    }

    @Test
    public void E_TrapTurnsInvisableAndVisable(){
        List<Point> trapPositions = new ArrayList<>();
        char[][] gameMap = game.toDisplay();
        for (int x = 0; x < gameMap.length; x++) {
            for (int y = 0; y < gameMap[0].length; y++) {
                if (gameMap[x][y] == 'B') {
                    trapPositions.add(new Point(y, x, cli));
                }
            }
        }

        for(Enemy e : game.getEnemies()){
            EnemyTurn turn = new EnemyTurn(player, e, cli);
            turn.play();
            turn = new EnemyTurn(player, e, cli);
            turn.play();
        }
        

        for (Point trapPosition : trapPositions) {
            Tile trap = game.getTileValue(trapPosition);
            boolean b = trap.getTileChar() == '.';
            assertTrue(b);
        }        

        for(int i=0;i<6;i++){
            for(Enemy e : game.getEnemies()){
                EnemyTurn turn = new EnemyTurn(player, e, cli);
                turn.play();
            }
        }
        

        for (Point trapPosition : trapPositions) {
            boolean b = game.getTileValue(trapPosition).getTileChar() == 'B';
            assertTrue(b);
        }
    }

    @Test
    public void F_TrapAttackesPlayer(){
        Enemy trap = new Trap('T', "thomas", 100, 100, 1, 51,10, 10, 0);
        trap.init(cli, cli, new Combat(cli));
        trap.setPosition(new Point(10, 10, cli));

        game.swapPosition(trap, game.getTileValue(new Point(2, 9, cli)));
        int currentHealth = player.getHealth().getCurrent();

        while(player.getHealth().getCurrent() == currentHealth){//in case the AP comes out 0
            EnemyTurn turn = new EnemyTurn(player, trap, cli);
            turn.play();
        }

        assertNotEquals(player.getHealth().getCurrent(), currentHealth);

    }

    @Test
    public void G_MonsterAttackesPlayer(){

        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));
        int currentHealth = player.getHealth().getCurrent();

        while(player.getHealth().getCurrent() == currentHealth){//in case the AP comes out 0
            EnemyTurn turn = new EnemyTurn(player, enemy, cli);
            turn.play();
        }

        assertNotEquals(player.getHealth().getCurrent(), currentHealth);

    }

    @Test
    public void H_EnemyColidesEnemy(){
        Enemy enemy2 = new Monster('T', "tomas", 100, 100, 1, 51,10);
        enemy2.init(cli, cli, new Combat(cli));
        game.getEnemies().add(enemy2);
        
        Point nextPos = new Point(15,15, cli);
        enemy2.setPosition(nextPos);
        game.swapPosition(enemy2, game.getTileValue(new Point(1, 10, cli)));

        Point nextPos2 = new Point(1,11, cli);
        enemy.setPosition(nextPos2);
        game.swapPosition(enemy, game.getTileValue(nextPos2));

        EnemyTurn turn = new EnemyTurn(player, enemy, cli);
        turn.play();

        assertEquals(nextPos2, enemy.getPosition());
    }

}
