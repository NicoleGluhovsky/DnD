package Controller;

import java.util.ArrayList;
import java.util.List;

import Input.EnemyTurn;
import Input.PlayerTurn;
import Input.TerminalInput;
import View.CLI;
import View.CLIManagement.DeathCallBack;
import View.CLIManagement.MessageCallBack;
import dnd.GameTile.Combat;
import dnd.GameTile.Units.Enemy;
import dnd.UnitManagment.Directions;


public class GameSetup {
        private final MessageCallBack mc;
        private final DeathCallBack dc;
        private final Combat combat;
        private final CLI cli;
        private final TerminalInput input;
        private GameTick game;
        private final String path;

        public GameSetup(String path) {
                cli = new CLI();
                mc = cli;
                dc = cli;
                this.path = path;
                combat = new Combat(cli);
                input = new TerminalInput(mc);

        }

        private void initiate(){

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
                game.init(mc, dc, combat, path);
                
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
                        mc.send(gameState);


                        //process next Tick
                        // process player turn
                        PlayerTurn playerTurn = new PlayerTurn(game.getPlayer(), mc); 
                        Directions d = playerTurn.chooseMove();
                        playerTurn.play(d);


                        // process enemys turn
                        List<Enemy> deadEnemies = new ArrayList<>();

                        for (Enemy e : game.getEnemies()){
                                if(e.isDead()){
                                        deadEnemies.add(e); 
                                        }
                                else{
                                        EnemyTurn enemyTurn = new EnemyTurn(game.getPlayer(),e, mc);
                                        enemyTurn.play();
                                }    
                        }

                        
                        for (Enemy e : deadEnemies){
                                game.killedAnEnemy(e, playerTurn.getDirection());
                        }
                
                        // check if game ends
                        
                        gameEnd = game.status();


                }
                // game ends
        }
}
