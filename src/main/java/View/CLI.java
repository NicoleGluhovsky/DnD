package View;

import dnd.GameTile.TileFactory;
import dnd.GameTile.Unit;

public class CLI  extends view {
    @Override
    public void display(String message) {
        System.out.println(message);
    }

    public void displayCharecterOptions() {
        TileFactory tileFactory = new TileFactory();
        String[] charecturs = tileFactory.getAllPlayerTypes();
        for (int i = 0; i < charecturs.length; i++) {
            display(i + " " + charecturs[i]);
        }
    }

    public void displatCombat(Unit Attaker, Unit Attacked, int attackPower, int defensePoints) {
      
        display(Attaker.getUnitName() + " engaged in combat with " + Attacked.getUnitName() + ".");
        display(Attaker.toString());
        display(Attacked.toString());
        display(Attaker.getUnitName() + " rolled " + attackPower + " attack points.");
        display(Attacked.getUnitName() + " rolled " + defensePoints + " defense points.");
        display(Attaker.getUnitName() + " dealt " + (attackPower-defensePoints) + " damage to " + Attacked.getUnitName() + ".");

    }

    public void deathMessage(Unit Attaker, Unit Attacked) {
        display(Attacked.getUnitName() + " has been slain by " + Attaker.getUnitName() + ".");
    }

    public void playerDeathMessage(Unit Attaker, Unit player) {
        deathMessage(Attaker, player);
        display("You lost.");
    }
}
