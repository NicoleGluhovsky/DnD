package dnd.GameTile.Units;


public class Monster extends Enemy {

    public Monster(char tileChar, String name, int health, int AP, int DP, int xp, int visionRange){
        super(tileChar, name, health, AP, DP, xp);
        super.setRange(visionRange);
    }

    public void chasePlayer(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
