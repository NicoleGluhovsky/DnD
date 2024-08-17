package dnd.GameTile;


import dnd.UnitManagment.Bars.MagicChars;
import View.CLIManagement.MessageCallBack;

public abstract class Tile{
    private char tileChar;
    private Point position;
    protected MessageCallBack mc;
    private char hiddenChar;



    protected Tile(char tileChar, Point pos, MessageCallBack mc){
        this.tileChar = tileChar;
        this.position = pos;
        this.mc = mc;
        this.hiddenChar = MagicChars.EMPTYSPACE.getSymbol();

    }
    protected Tile(char tileChar){
        this.tileChar = tileChar;
        this.hiddenChar = MagicChars.EMPTYSPACE.getSymbol();
    }
    protected void setPlayerAsDead(){
        this.tileChar = MagicChars.DEAD.getSymbol();
    }

    public void changeTileVisibility(){
        char temp = tileChar;
        this.tileChar = hiddenChar;
        this.hiddenChar = temp;
        if(this.position.getX() == 29 && this.position.getY() == 9){
            System.out.println("29,9: " + this.tileChar);
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



