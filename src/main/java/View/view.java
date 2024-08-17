package View;

import View.CLIManagement.MessageCallBack;
import View.CLIManagement.DeathCallBack;
import dnd.GameTile.TileFactory;
import dnd.GameTile.Unit;

public abstract class view implements MessageCallBack, DeathCallBack {

    public abstract void display(String msg);

    public MessageCallBack getCallback() {
        return this::display;
    }

    public void displayBoard(char[][] board) {
        for (char[] board1 : board) {
            String row = "";
            for (int j = 0; j < board1.length; j++) {
                row += board1[j];
            }
            display(row);
        }
    }

    public void displayCharacterOptions() {
        TileFactory tileFactory = new TileFactory();
        String[] characters = tileFactory.getAllPlayerTypes();
        for (int i = 0; i < characters.length; i++) {
            display((i+1) + " " + characters[i]);
        }
    }

    public void displayCombat(Unit Attaker, Unit Attacked, int attackPower, int defensePoints) {
      
        display(Attaker.getUnitName() + " engaged in combat with " + Attacked.getUnitName() + ".");
        display(Attaker.toString());
        display(Attacked.toString());
        display(Attaker.getUnitName() + " rolled " + attackPower + " attack points.");
        display(Attacked.getUnitName() + " rolled " + defensePoints + " defense points.");
        display(Attaker.getUnitName() + " dealt " + (attackPower-defensePoints) + " damage to " + Attacked.getUnitName() + ".");

    }

    public void displayAbilityCombat(Unit Attaker, Unit Attacked, int attackPower, int defensePoints) {
        display(Attacked.getUnitName() + "rolled " + defensePoints + " defense points.");
        display(Attaker.getUnitName() + " hit " + Attacked.getUnitName() + " for " +  (attackPower-defensePoints) + ".");
    }

    public void deathMessage(Unit Attaker, Unit Attacked) {
        display(Attacked.getUnitName() + " has been slain by " + Attaker.getUnitName() + ".");
    }

    public void playerDeathMessage(Unit Attaker, Unit player) {
        deathMessage(Attaker, player);
        onDeath();
    }

    @Override
    public void onDeath(){
        display("You Lost.");
    }

    @Override
    public void send(String message){
        display(message);
    }

}