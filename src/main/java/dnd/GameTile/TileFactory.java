package dnd.GameTile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import dnd.GameTile.Units.Boss;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Hunter;
import dnd.GameTile.Units.Mage;
import dnd.GameTile.Units.Monster;
import dnd.GameTile.Units.Player;
import dnd.GameTile.Units.Rogue;
import dnd.GameTile.Units.Trap;
import dnd.GameTile.Units.Warrior;
import dnd.UnitManagment.MagicChars;

public class TileFactory {
    private Player p;
        private static final List<Supplier<Player>> playerTypes = Arrays.asList(

            () -> new Warrior("Jon Snow", 300, 30, 4, 3),
            () -> new Warrior("The Hound", 400, 20, 6, 5),
            () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6.0),
            () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 25, 3, 4.0),
            () -> new Rogue("Arya Stark", 150, 400, 2, 20),
            () -> new Rogue("Bronn", 250, 35, 3, 50),
            () -> new Hunter("Ygritte", 220, 30, 2, 6)
        );

        private static final Map<Character, Supplier<Enemy>> enemyTypes = new HashMap<>();

        static {
            enemyTypes.put(MagicChars.LannisterSolider.getSymbol(), () -> new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3));
            enemyTypes.put(MagicChars.LannisterKnight.getSymbol(), () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50, 4));
            enemyTypes.put(MagicChars.QueensGuard.getSymbol() , () -> new Monster('q', "Queen’s Guard", 400, 20, 15, 100, 5));
            enemyTypes.put(MagicChars.Wright.getSymbol(), () -> new Monster('z', "Wright", 600, 30, 15, 100, 3));
            enemyTypes.put(MagicChars.BearWright.getSymbol(), () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250, 4));
            enemyTypes.put(MagicChars.GiantWright.getSymbol() , () -> new Monster('g', "Giant-Wright", 1500, 100, 40, 500, 5));
            enemyTypes.put(MagicChars.WhiteWalker.getSymbol(), () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6));
            enemyTypes.put(MagicChars.TheMountain.getSymbol(), () -> new Boss('M', "The Mountain", 1000, 60, 25, 500, 6, 5));
            enemyTypes.put(MagicChars.QueenCersei.getSymbol(), () -> new Boss('C', "Queen Cersei", 100, 10, 10, 1000, 1, 8));
            enemyTypes.put(MagicChars.NightsKing.getSymbol(), () -> new Boss('K', "Night’s King", 5000, 300, 150, 5000, 8, 3));
            enemyTypes.put(MagicChars.BonusTrap.getSymbol(), () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 5, 0, false));
            enemyTypes.put(MagicChars.QueensTrap.getSymbol(), () -> new Trap('Q', "Queen’s Trap", 250, 50, 10, 100, 3, 7, 0, false));
            enemyTypes.put(MagicChars.DeathTrap.getSymbol(), () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10, 0, false));
        }

        public TileFactory(){}

        public Player producePlayer(int playerID){
            Supplier<Player> supp = playerTypes.get(playerID-1);
            this.p = supp.get();
            return this.p;
        }

        public Enemy produceEnemy(char tile, Point p){
            Enemy e = enemyTypes.get(tile).get();
            e.setPosition(p);
            return e;
        }

        public String[] getAllPlayerTypes(){
            String[] characters = new String[playerTypes.size()];
            for(int i = 0; i < playerTypes.size(); i++){
                characters[i] = playerTypes.get(i).get().toString();
            }
            return characters;
        }

}

