
import java.lang.reflect.InvocationTargetException;

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
import Input.PlayerTurn;
import View.CLI;
import dnd.GameTile.Combat;
import dnd.GameTile.Point;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Monster;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.Directions;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PlayerTest{
    private GameTick game;
    private CLI cli;
    private Player player;
    private Enemy enemy;
    private final int playerID = 0;
    private final String path = "levels_dir";

    // please run with playerIDs 0-6

    @Before
    public void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        game = GameTickSingleton.getInstance(playerID).getValue();
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
    public void A_MovePlayer() {
        cli.send("____A_MovePlayer____");
        PlayerTurn turn = new PlayerTurn(player, cli);
        Point playerPosition = player.getPosition();
        turn.play(Directions.UP);
        assertEquals(playerPosition.yDiff(player.getPosition()), 1);
        assertEquals(playerPosition.xDiff(player.getPosition()), 0);


        turn = new PlayerTurn(player, cli);
        turn.play(Directions.DOWN);
        assertEquals(player.getPosition(), playerPosition);


        turn = new PlayerTurn(player, cli);
        turn.play(Directions.RIGHT);
        assertEquals(playerPosition.xDiff(player.getPosition()), -1);
        assertEquals(playerPosition.yDiff(player.getPosition()), 0);


        turn = new PlayerTurn(player, cli);
        turn.play(Directions.LEFT);
        assertEquals(player.getPosition(), playerPosition);

        turn = new PlayerTurn(player, cli);
        turn.play(Directions.LEFT); // cheacks Unit walking to a wall
        assertEquals(player.getPosition(), playerPosition);

        turn = new PlayerTurn(player, cli);
        turn.play(Directions.SKIP);
        assertEquals(player.getPosition(), playerPosition);

        turn = new PlayerTurn(player, cli);
        turn.play(Directions.CASTABILITY);
        assertEquals(player.getPosition(), playerPosition);
    }


    @Test
    public void B_PlayerAttack(){
        cli.send("____B_PlayerAttack____");
        enemy.getHealth().heal(80);
        int currentHealth = enemy.getHealth().getCurrent();
        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));

        while(enemy.getHealth().getCurrent() == currentHealth){
            PlayerTurn turn = new PlayerTurn(player, cli);
            turn.play(Directions.RIGHT);
        }
        

        assertNotEquals(enemy.getHealth().getCurrent(), currentHealth);
    }

    @Test
    public void C_PlayerGainXP(){
        cli.send("____C_PlayerGainXP____");
        int currentXP = player.getXP().getCurrent();
        
        enemy.getHealth().setCurrent(1);
        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));


        player.regainAbility();
        player.regainAbility();
        player.regainAbility();
        player.regainAbility();

        PlayerTurn turn = new PlayerTurn(player, cli);
        turn.play(Directions.CASTABILITY);

        assertNotEquals(currentXP, player.getXP().getCurrent());
    }

    @Test
    public void D_PlayerLevelUp(){
        cli.send("____D_PlayerLevelUp____");
        player.getXP().setCurrent(player.getXP().getMax()-10);
        int currentLevel = player.GetLevel();

        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));
        enemy.getHealth().setCurrent(1);

        while(enemy.getHealth().getCurrent() == 1){
            PlayerTurn turn = new PlayerTurn(player, cli);
            turn.play(Directions.RIGHT);
        }

        assertEquals(currentLevel + 1, player.GetLevel());
    }

    //we have chacked Enemies in range in GameTickTest, and every player uses it in their CastAbility 
    @Test 
    public void E_castAbility(){
        cli.send("____E_castAbility____");
        int currHealth = enemy.getHealth().getCurrent();

        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));

        player.regainAbility();
        player.regainAbility();
        player.regainAbility();
        player.regainAbility();

        PlayerTurn turn = new PlayerTurn(player, cli);
        turn.play(Directions.CASTABILITY);


        assertNotEquals(currHealth, enemy.getHealth().getCurrent());
    }

    @Test
    public void F_PlayerDie(){
        cli.send("____F_PlayerDie____");

        player.getHealth().setCurrent(1);
        game.swapPosition(enemy, game.getTileValue(new Point(2, 9, cli)));

        while(!player.isDead()){//in case the AP comes out 0
            EnemyTurn turn = new EnemyTurn(player, enemy, cli);
            turn.play();
        }

        assertTrue(player.isDead());
    }
}
