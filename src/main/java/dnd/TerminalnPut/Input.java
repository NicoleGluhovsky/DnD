package dnd.TerminalnPut;

import java.util.Scanner;

import View.CLI;
import dnd.UnitManagment.Bars.Directions;

public class Input {
    private final CLI cli = new CLI();
    private final Scanner scanner = new Scanner(System.in);

    public Input() {}

    public int getInputInt() {
        return scanner.nextInt();
    }

    public Directions getInputChar() {
        while(true){
            char diraction = scanner.next().charAt(0);
            switch (diraction) {
                case 'w' -> {
                    return Directions.UP;
                }
                case 'a' -> {
                    return Directions.LEFT;
                }
                case 's' -> {
                    return Directions.DOWN;
                }
                case 'd' -> {
                    return Directions.RIGHT;
                }
                default -> cli.display("Error: Invalid input. Please enter 'w', 'a', 's', or 'd'.");
            }
        }
        
    }
}
