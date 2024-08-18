package dnd.UnitManagment;
import java.util.Random;

import dnd.UnitManagment.Bars.MagicNumbers;

    public enum Direction {
            UP,
            DOWN,
            LEFT,
            RIGHT,
            DoNothing,
            castAbility;
        
        public static Direction randomDirection(){
            Random rand = new Random();
            return Direction.values()[rand.nextInt(MagicNumbers.FOUR.getValue())];
        }

    }
