package Controller;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Usage: java -jar Game.jar <path_to_level>");
            System.exit(1);
        }
        GameSetup game = new GameSetup(args[0]);
        game.Run();
    }
}