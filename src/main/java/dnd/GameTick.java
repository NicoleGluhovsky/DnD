package dnd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;

import dnd.GameTile.Point;
import dnd.GameTile.Tile;
import dnd.GameTile.Units.Player;
import dnd.GameTile.Units.Enemy;
import dnd.UnitManagment.Bars.MagicNumbers;
import dnd.GameTile.EmptySpace;
import dnd.GameTile.TileFactory;
import dnd.GameTile.Wall;



public final class GameTick {
    TileFactory factory = new TileFactory();
    private int tick = MagicNumbers.ZERO.getValue();
    private TreeMap<Point, Tile> gameLevel;
    private Player player;
    private List<Enemy> enemies;


    public GameTick(int Level){
        LoadBoardLevel(Level);
    }

    public GameTick(int PlayerID, int Level){
        player = factory.producePlayer(PlayerID);
        LoadBoardLevel(Level);
    }

    public void LoadBoardLevel(int level) {
        List<String> fileContent = readLevelFile(level);
        // for each char add to gameLevel
       gameLevel =  createNewTiles(fileContent);

        // for each enemy add to enemies[]
    }

    private TreeMap<Point, Tile> createNewTiles(List<String> lines){

        TreeMap<Point, Tile> Board = new TreeMap<Point, Tile>();
        int Xcounter = 0;
        int Ycounter = 0;

        for(String line : lines){
            for(char c : line.toCharArray()){
                Tile newtile;
                Point pos = new Point(Xcounter, Ycounter);
                switch(c) {
                    case '.':
                        newtile = createEmptyTile(pos);

                        break;
                    case '#':
                        newtile = createWallTile(pos);
                        break;
                    case '@':
                        newtile = createPlayerTile(pos);
                        break;
                    default:
                        newtile = createEnemyTile(c, pos);
                        break;
                }
                Board.put(pos, newtile);
                Xcounter +=1;
            }
            Ycounter += 1;
        }
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
        String path = "src\\main\\resources\\Levels\\level";
        String filePath = path + level + ".txt";

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public void killedAnEnemy(Player p, Point pos){
        p.swapPosition(new EmptySpace(pos));
    }

}

