package dnd.GameTile;


import View.CLIManagement.MessageCallBack;
import dnd.UnitManagment.MagicChars;

public abstract class Tile{
    private char tileChar;
    private Point position;
    protected MessageCallBack mc;
    private final char charBackup;



    protected Tile(char tileChar, Point pos, MessageCallBack mc){
        this.tileChar = tileChar;
        this.position = pos;
        this.mc = mc;
        this.charBackup = tileChar;

    }
    protected Tile(char tileChar){
        this.tileChar = tileChar;
        this.charBackup = tileChar;
    }
    protected void setPlayerAsDead(){
        this.tileChar = MagicChars.DEAD.getSymbol();
    }
    protected char getHiddenChar(){
        return this.charBackup;
    }

    protected void changeTileVisibility(boolean visible){
        if(visible){
            this.tileChar = charBackup;
        }
        else{
            this.tileChar = MagicChars.EMPTYSPACE.getSymbol();
        }
    }

    public void swapPosition(Tile tile) {
        Point temp = tile.position;
        tile.position = this.position;
        this.position = temp;
    }

    @Override
    public String toString(){
        return String.valueOf(tileChar);
    }

    public void setPosition(Point pos){
        this.position = pos;
    }
    public Point getPosition(){
        return this.position;
    }
    public char getTileChar(){
        return this.tileChar;
    }
    public abstract void AttackTile(Unit unit);
}



