package View;

import View.CLIManagement.DeathCallBack;
import View.CLIManagement.MessageCallBack;
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
            send(row);
        }
    }

    public void displayCharacterOptions() {
        TileFactory tileFactory = new TileFactory();
        String[] characters = tileFactory.getAllPlayerTypes();
        for (int i = 0; i < characters.length; i++) {
            send((i+1) + " " + characters[i]);
        }
    }

    public void displayCombat(Unit Attaker, Unit Attacked, int attackPower, int defensePoints) {
      
        send(Attaker.getUnitName() + " engaged in combat with " + Attacked.getUnitName() + ".");
        send(Attaker.toString());
        send(Attacked.toString());
        send(Attaker.getUnitName() + " rolled " + attackPower + " attack points.");
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
        display("You Lost.\n_______Game Over_______");
    }

    @Override
    public void send(String message){
        display(message);
    }

}