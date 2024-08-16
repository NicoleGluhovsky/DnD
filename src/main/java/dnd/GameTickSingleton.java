package dnd;
public class GameTickSingleton {
    private static GameTickSingleton instance = null;
    private final GameTick value;
    private static int Level = 1;

    private GameTickSingleton() {
        value = new GameTick(Level);
        
    }

    public static GameTickSingleton getInstance() {
        if (instance == null) {
            instance = new GameTickSingleton();
        }
        return instance;
    }
    
    public GameTick getValue(){
        return value;
    }

    public void LevelUp(){
        Level++;
        instance = new GameTickSingleton();
    }

}
