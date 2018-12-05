public class CheatEngine {
    Board board;
    boolean isLeft;

    public CheatEngine(Board board, boolean isLeft) {
        this.board = board;
        this.isLeft = isLeft;
    }
    //TODO
    public int bestMove() {
        for (int i = 0; i < board.getSize()/2; i++) {
            if (bestMove(i, board)) {
                return i;
            }
        }
        return -1;
    }
    //TODO
    public boolean bestMove(int square, Board copyBoard) {
        Board temp = copyBoard.copy();
        for (int i = 0; i < temp.getSize() / 2; i++) {

        }
        return true;
    }
}
