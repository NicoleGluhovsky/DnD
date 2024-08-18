package Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import View.CLIManagement.DeathCallBack;
import View.CLIManagement.MessageCallBack;
import dnd.GameTile.Combat;
import dnd.GameTile.EmptySpace;
import dnd.GameTile.Point;
import dnd.GameTile.Tile;
import dnd.GameTile.TileFactory;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;
import dnd.GameTile.Wall;
import dnd.UnitManagment.Bars.MagicNumbers;



public final class GameTick{
    TileFactory factory = new TileFactory();
    private TreeMap<Point, Tile> gameLevel;
    private Player player;
    private final List<Enemy> enemies = new ArrayList<>();
    private int level;
    private int maxX;
    private int maxY;
    private MessageCallBack mc;
    private DeathCallBack dc;
    private final int MaxLevel = MagicNumbers.FOUR.getValue();
    private Combat combat;

    public GameTick(Player player, int level){
        this.level = level;
        this.player = player;
    }

    public void init(MessageCallBack mc, DeathCallBack dc, Combat combat){
        this.mc = mc;
        this.dc = dc;
        this.combat = combat;
        LoadBoardLevel();
    }

    public Player getPlayer(){
        return player;
    }

    public GameTick(int PlayerID, int level){
        this.level = level;
        LoadBoardLevel();
    }

    private boolean LevelUp(){
        level++;
        if(level <= MaxLevel){
            LoadBoardLevel();
            return false;
        }
        return true;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void LoadBoardLevel() {
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
                Point pos = new Point(Xcounter, Ycounter, mc);
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
        return new EmptySpace(pos, mc);
    }
    private Tile createWallTile(Point pos){
        return new Wall(pos, mc);
    }
    private Tile createPlayerTile(Point pos){
        player.setPosition(pos);
        player.init(mc, dc, combat);
        return player;
    }
    private Tile createEnemyTile(char c, Point pos){
        Enemy e = factory.produceEnemy(c, pos);
        e.init(mc, dc, combat);
        enemies.add(e);
        //if enemy type = trup add to trups
        return e;
    }

    public Tile getTileValue(Point pos){
        return gameLevel.get(pos);
    }

    private List<String> readLevelFile(int level){
        String path = "DnD/src/main/resources/Levels/level";
        String filePath = path + level + ".txt";

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public void killedAnEnemy(Enemy rip){
        swapPosition(player, new EmptySpace(rip.getPosition(), mc));
        enemies.remove(rip);
    }
    
    public void swapPosition(Tile t1, Tile t2){
        t1.swapPosition(t2);
        gameLevel.put(t1.getPosition(), t1);
        gameLevel.put(t2.getPosition(), t2);
    }

    public char[][] toDisplay(){
        
        char[][] board = new char[maxY][maxX];
        for(Point p : gameLevel.keySet()){
            if(p.getX() == 29 && p.getY() == 9){
                System.out.println("here");
            }
            Tile t = gameLevel.get(p);
            board[p.getY()][p.getX()] = t.getTileChar();
        }
        return board;
    }

    String getGameState() {
            return player.toString();
        
    }
    boolean status(){
        if(winLevel()) {
            return LevelUp();
        }
        return lose();
    }


    private boolean lose(){
        return player.isDead();
    }

    private boolean winLevel(){
        return this.enemies.isEmpty();
    }


    public Enemy getClosesetEnemy() {
        Enemy closest = null;
        double minRange = Integer.MAX_VALUE;
        for(Enemy e : enemies){
            double distance = player.getPosition().Range(e.getPosition());
            if(distance < minRange){
                minRange = distance;
                closest = e;
            }
        }
        return closest;
    }

    public List<Enemy> getEnemiesInRange(double range){
        List<Enemy> inRange = new ArrayList<>();
        for(Enemy e : enemies){
            if(player.getPosition().isInRange(e.getPosition(), range)){
                inRange.add(e);
            }
        }
        return inRange;
    }


}

//observable - tick counter
//observers - everyone else