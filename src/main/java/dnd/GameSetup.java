package dnd;

public class GameSetup {
        public GameSetup() {}

        public void Run(){

                // show player the charecter options
                // player selects a charecter
                int PlayerID = 4;
        
                // player selects Level // start from level 1
                int level = 1;
                // create game object
                
                GameTick game = new GameTick(PlayerID, level);

        }
        
}
