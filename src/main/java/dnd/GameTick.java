package dnd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import dnd.GameTile.EmptySpace;
import dnd.GameTile.Point;
import dnd.GameTile.Tile;
import dnd.GameTile.TileFactory;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;
import dnd.GameTile.Wall;
import dnd.UnitManagment.Bars.MagicNumbers;



public final class GameTick {
    TileFactory factory = new TileFactory();
    private int tick = MagicNumbers.ZERO.getValue();
    private TreeMap<Point, Tile> gameLevel;
    private Player player;
    private final List<Enemy> enemies = new ArrayList<>();
    private int maxX;
    private int maxY;
    private boolean combatAccured = false;

    public GameTick(int Level){
        LoadBoardLevel(Level);
    }

    public GameTick(int PlayerID, int Level){
        player = factory.producePlayer(PlayerID+1);
        LoadBoardLevel(Level);
    }

    public void LoadBoardLevel(int level) {
        List<String> fileContent = readLevelFile(level);
        // for each char add to gameLevel
       gameLevel =  createNewTiles(fileContent);

        // for each enemy add to enemies[]
    }

    private TreeMap<Point, Tile> createNewTiles(List<String> lines){

        TreeMap<Point, Tile> Board = new TreeMap<>();
        int Xcounter = 0;
        int Ycounter = 0;

        for(String line : lines){
            for(char c : line.toCharArray()){
                Tile newtile;
                Point pos = new Point(Xcounter, Ycounter);
                newtile = switch (c) {
                    case '.' -> createEmptyTile(pos);
                    case '#' -> createWallTile(pos);
                    case '@' -> createPlayerTile(pos);
                    default -> createEnemyTile(c, pos);
                };
                Board.put(pos, newtile);
                Xcounter +=1;
            }
            Ycounter += 1;
            maxX = Xcounter;
            Xcounter = 0;
        }
        maxY = Ycounter;
        return Board;
    }


    private Tile createEmptyTile(Point pos){
        return new EmptySpace(pos);
    }
    private Tile createWallTile(Point pos){
        return new Wall(pos);
    }
    private Tile createPlayerTile(Point pos){
        player.setPosition(pos); 
        return player;
    }
    private Tile createEnemyTile(char c, Point pos){
        Enemy e = factory.produceEnemy(c, pos);
        enemies.add(e);
        return e;
    }

    public Tile getTileValue(Point pos){
        return gameLevel.get(pos);
    }

    private List<String> readLevelFile(int level){
        String path = "src/main/resources/Levels/level";
        String filePath = path + level + ".txt";

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public void killedAnEnemy(Player p, Enemy rip){
        p.swapPosition(new EmptySpace(rip.getPosition()));
        enemies.remove(rip);
    }

    public char[][] toDisplay(){
        
        char[][] board = new char[maxY][maxX];
        for(Point p : gameLevel.keySet()){
            board[p.getY()][p.getX()] = gameLevel.get(p).getTileChar();
        }
        return board;
    }

    String getGameState() {
        if(combatAccured){
            return "Combat accured";// game tick is obsercer that observes the game state
        }
        else{
            return player.toString();
        }
        
    }

    boolean isEnded() {
        return player.isDead();
    }

}

//observable - tick counter
//observers - everyone else