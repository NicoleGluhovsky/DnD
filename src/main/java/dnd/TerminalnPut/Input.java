package dnd.TerminalnPut;

import java.util.Scanner;

import View.CLI;

public class Input {
    private final CLI cli = new CLI();
    private final Scanner scanner = new Scanner(System.in);
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Input() {}

    public int getInputInt() {
        return scanner.nextInt();
    }

    public Direction getInputChar() {
        while(true){
            char diraction = scanner.next().charAt(0);
            switch (diraction) {
                case 'w' -> {
                    return Direction.UP;
                }
                case 'a' -> {
                    return Direction.LEFT;
                }
                case 's' -> {
                    return Direction.DOWN;
                }
                case 'd' -> {
                    return Direction.RIGHT;
                }
                default -> cli.display("Error: Invalid input. Please enter 'w', 'a', 's', or 'd'.");
            }
        }
        
    }
}
