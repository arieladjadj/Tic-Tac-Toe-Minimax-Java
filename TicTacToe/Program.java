import java.util.Scanner;

public class Program {

    public static Scanner r = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game('O', 'X');
        String userInput = "";
        printAsciiArt();
        //checkAI();

        while(!(userInput.toLowerCase().equals("quit") || userInput.toLowerCase().equals("q"))) {// && userInput != "q" && userInput != "Quit") {
            game.startGame();
            System.out.print("Print 'quit' or 'q' to exit or any other key to continue: ");
            userInput = r.next();
        }
        System.out.println("Good bye!");
    }

    private static void printAsciiArt() {
        String catAsciiArt = "   |\\---/|\n" +
                "   | ,_, |\n" +
                "    \\_`_/-..----.\n" +
                " ___/ `   ' ,\"\"+ \\  sk\n" +
                "(__...'   __\\    |`.___.';\n" +
                "  (_,...'(_,.`__)/'.....+\n";

        System.out.println("\nWelcome to my Tic Tac Toe Game");
        System.out.println(catAsciiArt);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void checkAI() {
        char[][] board = {{'X','O','x'},
                          {'O','O','x' },
                          {'-','X','-'}};
        AI_Player ai_player = new AI_Player('O','X');
        ai_player.makeMove(board);
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for(int i=0; i<3; i++) {
            //System.out.print("  ");
            for(int j=0; j<3; j++) {
                System.out.print("|\t" + board[i][j] + "\t");
            }
            if(i<2) System.out.println("|\n-------------------------");
            else System.out.println("|");
        }
    }

}
