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
import dnd.GameTile.Wall;



public class GameTick {
    private int tick = MagicNumbers.ZERO.getValue();
    private TreeMap<Point, Tile> gameLevel;
    private Player player;
    private Enemy[] enemies;

    public GameTick(TreeMap<Point, Tile> gameLevel, Player player, Enemy[] enemies) {
        this.gameLevel = gameLevel;
        this.player = player;
        this.enemies = enemies;
    }

    public void LoadBoardLevel(int level) {
        List<String> fileContent = readLevelFile(level);
        // for each char add to gameLevel
        createNewTiles(fileContent);

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
                        newtile = createEnemyTile(pos);
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
        return player;
    }
    private Tile createEnemyTile(Point pos){
        //case for enemy tipes
        //function for each the spacific anamy
        return null;
    }



    private List<String> readLevelFile(int level){
        String path = "Levels";
        String filePath = path + "/" + level;

        StringBuilder fileContent = new StringBuilder();

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}

