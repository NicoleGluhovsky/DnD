package dnd;

import View.CLI;
import dnd.TerminalnPut.Input;

public class GameSetup {
        private final CLI cli;
        private final Input input;

        public GameSetup() {
                cli = new CLI();
                input = new Input();
        }
        private GameTick game;

        public void initiate(){

                // show player the charecter options
                cli.displayCharecterOptions();
                // player selects a charecter
                int PlayerID = input.getInputInt();
                // player selects Level // start from level 1
                int level = 1;
                // create game object
                game = new GameTick(PlayerID, level);
        }
        
        public void Run(){
                initiate();
                // start game
                // game loop
                boolean gameEnd = false;
                int tickCounter = 0;
                while(!gameEnd){
                        tickCounter++;
                        // get player input
                        // process player turn
                        // process enemys turn
                        cli.displayBoard(game.toDisplay());
                        // display game state
                        String gameState = game.getGameState();
                        cli.display(gameState);



                        // check if game ends
                        if(tickCounter == 1){
                                gameEnd = true;
                        }
                        //gameEnd = game.isEnded();


                }
                // game ends
        }
}
