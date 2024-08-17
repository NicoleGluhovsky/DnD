package Controller;

import java.util.ArrayList;
import java.util.List;

import Input.PlayerTurn;
import Input.EnemyTurn;
import Input.TerminalInput;
import View.CLI;
import View.CLIManagement.*;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Combat;

public class GameSetup {
        private final CLI cli;
        private final MessageCallBack mc;
        private final DeathCallBack dc;
        private final Combat combat;

        private final TerminalInput input;

        public GameSetup() {
                cli = new CLI();
                mc = cli;
                dc = cli;
                combat = new Combat(cli);
                input = new TerminalInput(mc);

        }
        private GameTick game;

        public void initiate(){

                // show player the charecter options
                cli.displayCharacterOptions();
                // player selects a charecter
                int PlayerID = input.getInputInt();
                while (true) { 
                    if (PlayerID >= 7) {
                        mc.send("Invalid input, please try again");
                        PlayerID = input.getInputInt();
                    }
                    else {
                        break;
                    }
                }
                // create game object
                game = GameTickSingleton.getInstance(PlayerID).getValue();
                game.init(mc, dc, combat);

                
        }
        
        public void Run(){
                initiate();
                // start game
                // game loop
                boolean gameEnd = false;
                while(!gameEnd){

                        cli.displayBoard(game.toDisplay());
                        // display game state
                        String gameState = game.getGameState();
                        cli.display(gameState);


                        //process next Tick
                        // process player turn
                        PlayerTurn playerTurn = new PlayerTurn(game.getPlayer(), mc); 
                        playerTurn.play();


                        // process enemys turn
                        List<Enemy> deadEnemies = new ArrayList<>();
                        for (Enemy e : game.getEnemies()){
                                if(e.isDead()){ deadEnemies.add(e); }
                                else{
                                        EnemyTurn enemyTurn = new EnemyTurn(game.getPlayer(),e, mc);
                                        enemyTurn.play();
                                }    
                        }
                        
                        for (Enemy e : deadEnemies){
                                game.killedAnEnemy(e);
                        }
                
                        // check if game ends
                        
                        gameEnd = game.status();


                }
                System.out.println("Game Over");
                // game ends
        }
}
