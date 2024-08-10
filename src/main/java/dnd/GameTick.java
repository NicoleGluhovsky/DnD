package dnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

import dnd.GameTile.Point;
import dnd.GameTile.Tile;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.Bars.MagicNumbers;

public class GameTick {
    private int tick = MagicNumbers.ZERO.getValue();
    private TreeMap<Point, Tile> gameLevel;
    private Player player;
    private Enemy[] enemies;
    
    public GameTick(TreeMap<Point, Tile> gameLevel, Player player, Enemy[] enemies){
        this.gameLevel = gameLevel;
        this.player = player;
        this.enemies = enemies;
    }

    public void LoadBoardLevel(int level){
        //get level file and read
        String fileContent = readLevelFile(level);
        //for each char add to gameLeve
        int xCounter = 0;
        int yCounter = 0;
        for (char tileName : fileContent) {
            Tile tileVal = createNewTile(tileName);
            
        }
        
        //for each enemy add to enemies[]
    }

    private Tile createNewTile(char tilename){
        switch (UnitObjects){
                case '@':

            }
    }

    private String readLevelFile(int level){
        String path = "Levels";
        String filePath = path + "/" + level;

        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
