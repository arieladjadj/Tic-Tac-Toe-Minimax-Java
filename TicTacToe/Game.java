import java.util.Scanner;

public class Game {

    public static Scanner r = new Scanner(System.in);

    //private Context context;
    private AI_Player aiPlayer;
    private char aiPLayerCh, playerCh;
    private char[][] board;

    public Game(char aiPLayerCh, char playerCH) {
        this.aiPlayer = new AI_Player(aiPLayerCh, playerCH);
        this.playerCh = playerCH;
        this.aiPLayerCh = aiPLayerCh;
        generateBoard();
    }

    public void startGame() {
        String boardSate = BoardState.NOTHING;
        while(boardSate == BoardState.NOTHING) {  //until finish - draw/win
            printBoard();
            playerTurn(); //player turn
            boardSate = getBoardState();
            if(boardSate == BoardState.NOTHING) {  //AI turn
              this.aiPlayer.makeMove(this.board);
              boardSate = getBoardState();
            }
        }
        finishGame(boardSate);
    }

    private void playerTurn() {
        int row,col;
        do{   //input player pos
            System.out.print("Enter row: ");
            row = r.nextInt();
            System.out.print("Enter col: ");
            col = r.nextInt();
        }while(!isFreeSlot(row, col));
        this.board[row][col] = this.playerCh;
    }

    private void printBoard() {
        System.out.println("\t0\t\t1\t\t2");
        for(int i=0; i<3; i++) {
            System.out.print(i+ " ");
            for(int j=0; j<3; j++) {
                System.out.print("|\t" + this.board[i][j] + "\t");
            }
            if(i<2) System.out.println("|\n  -----------------------");
            else System.out.println("|");
        }
    }

    private String getBoardState() {
        String boardState = BoardState.NOTHING;
        if(isWin(this.playerCh)) boardState = BoardState.PLAYER_WIN;
        else if(isWin(this.aiPLayerCh)) boardState = BoardState.AI_PLAYER_WIN;
        else if(isDraw()) boardState = BoardState.DRAW;
        return boardState;
    }

    private boolean isFreeSlot(int row, int col) {
        return (col >= 0 && row <=2 && col >= 0 && col <=2 && this.board[row][col] == '-');
    }

    private void generateBoard() {
        this.board = new char[3][3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                this.board[i][j] = '-';
            }
        }
    }

    private boolean isWin(char player) {
        for(int i=0; i<3; i++) { //checks for column and rows
            if(this.board[i][0] == player && this.board[i][0] == this.board[i][1]
                && this.board[i][1] == this.board[i][2]) return true;
            if(this.board[0][i] == player && this.board[0][i] == this.board[1][i]
                && this.board[1][i] == this.board[2][i]) return true;
        }

        //checks diagonal
        if(this.board[0][0] == player && this.board[1][1] == this.board[0][0]
                && this.board[1][1] == this.board[2][2]) return true;
        if(this.board[0][2] == player && this.board[0][2] == this.board[1][1]
                && this.board[2][0] == this.board[1][1]) return true;

        return false;
    }

    private boolean isDraw() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(this.board[i][j] == '-') return false;
            }
        }
        return true;
    }

    private void finishGame(String gameRes) {
        //reset board
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                this.board[i][j] = '-';
            }
        }
        if(gameRes == BoardState.DRAW) printDraw();
        else if(gameRes == BoardState.AI_PLAYER_WIN) printAIWon();
        else printPlayerWon();
    }

    private void printDraw() {
        String drawAsciiArt = "██████╗ ██████╗  █████╗ ██╗    ██╗\n" +
                "██╔══██╗██╔══██╗██╔══██╗██║    ██║\n" +
                "██║  ██║██████╔╝███████║██║ █╗ ██║\n" +
                "██║  ██║██╔══██╗██╔══██║██║███╗██║\n" +
                "██████╔╝██║  ██║██║  ██║╚███╔███╔╝\n" +
                "╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚══╝╚══╝";
        System.out.println(drawAsciiArt);
    }

    private void printAIWon(){
        String aiWonAsciiArt = " █████╗ ██╗    ██╗    ██╗ ██████╗ ███╗   ██╗\n" +
                "██╔══██╗██║    ██║    ██║██╔═══██╗████╗  ██║\n" +
                "███████║██║    ██║ █╗ ██║██║   ██║██╔██╗ ██║\n" +
                "██╔══██║██║    ██║███╗██║██║   ██║██║╚██╗██║\n" +
                "██║  ██║██║    ╚███╔███╔╝╚██████╔╝██║ ╚████║\n" +
                "╚═╝  ╚═╝╚═╝     ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝\n" +
                "                                            ";
        System.out.println(aiWonAsciiArt);
    }

    private void printPlayerWon() { //never going to happen (:
        String playerWonAsciiArt = "██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗ ██████╗ ███╗   ██╗\n" +
                "╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██╔═══██╗████╗  ██║\n" +
                " ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║   ██║██╔██╗ ██║\n" +
                "  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║   ██║██║╚██╗██║\n" +
                "   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝╚██████╔╝██║ ╚████║\n" +
                "   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝\n" +
                "                                                            ";
        System.out.println(playerWonAsciiArt);
    }
}
