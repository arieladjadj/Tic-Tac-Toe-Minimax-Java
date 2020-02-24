import java.util.Scanner;

public class Program {

    public static Scanner r = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game('O', 'X');
        String userInput = "";
        printAsciiArt();

       while(!(userInput.toLowerCase().equals("quit") || userInput.toLowerCase().equals("q"))) {
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
}
