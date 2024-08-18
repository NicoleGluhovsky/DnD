package Controller;

import dnd.GameTile.TileFactory;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.MagicNumbers;

public class GameTickSingleton {
    private static final TileFactory factory = new TileFactory();
    private static GameTickSingleton instance = null;
    private final GameTick value;
    private static Player player;


    private  GameTickSingleton(Player p){
        value = new GameTick(p, MagicNumbers.ONE.getValue());
    }

    public static GameTickSingleton getInstance(int playerID) {
        if (instance == null) {
            player = factory.producePlayer(playerID+1);
            instance = new GameTickSingleton(player);
        }
        return instance;
    }

    public static GameTickSingleton getInstance() {
        return instance;
    }
    
    public GameTick getValue(){
        return value;
    }

}
