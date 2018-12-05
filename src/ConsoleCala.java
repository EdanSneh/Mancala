import java.util.*;

public class ConsoleCala {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Board board = new Board();
        boolean isLeft = true;

        while(!board.isWin()) {
            int player;
            if (isLeft) {
                player = 1;
            } else {
                player = 2;
            }
            System.out.println("Player " + player + " make your move 1-6.");
            move(console, board, isLeft);
            isLeft = !isLeft;
        }
        int winner = board.whoWins();
        if (winner > 0) {
            System.out.println("Player 1 wins!");
        } else if (winner < 0) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void move(Scanner console, Board board, boolean isLeft) {
        board.printBoard(isLeft);
        int move = console.nextInt();
        boolean anotherTurn = false;
        try {
            anotherTurn = board.moveAvalanche(isLeft, move - 1);
        } catch (Exception E) {
            System.out.println("Oops invalid move, try again");
            move(console, board, isLeft);
        }
        if (anotherTurn && !board.isWin()) {
            System.out.println("Congrats another turn!");
            move(console, board, isLeft);
        }
    }
}
