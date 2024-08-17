package Input;

import java.util.Scanner;
import dnd.UnitManagment.Bars.Directions;
import View.CLIManagement.MessageCallBack;


public class TerminalInput {

    private final Scanner scanner;
    private final MessageCallBack mc;

    public TerminalInput(MessageCallBack mc) {
        this.scanner = new Scanner(System.in);
        this.mc = mc;
    }

    public int getInputInt() {
        int input = scanner.nextInt();
        return input-1;
    }
    
    public Directions getDirection(){
        char character = scanner.next().charAt(0);
        while(true){
             switch (character){
                case 'w' -> {
                    return Directions.UP;
                }
                case 's' -> {
                    return Directions.DOWN;
                }
                case 'a' -> {
                    return Directions.LEFT;
                }
                case 'd' -> {
                    return Directions.RIGHT;
                }
                case 'e' -> {
                    return Directions.CASTABILITY;
                }
                case 'q' -> {
                    return Directions.SKIP;
                }
                default -> mc.send("Enter a valid direction");
            }

        }
    }
}
