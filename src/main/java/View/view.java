package View;
import View.CLIManagement.MessageCallBack;

public abstract class view {

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
}