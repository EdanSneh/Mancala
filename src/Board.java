import java.util.*;

public class Board {
    private List<HoleNode> left;
    private List<HoleNode> right;
    private HoleNode leftBank;
    private HoleNode rightBank;

    public Board() {
        left = new ArrayList<HoleNode>();
        right = new ArrayList<HoleNode>();
        leftBank = new HoleNode(0);
        rightBank = new HoleNode(0);
        for (int i = 0; i < 6; i++) {
            left.add(new HoleNode(4));
            right.add(new HoleNode(4));
        }
    }

    public boolean moveAvalanche(boolean isLeft, int square) {
        List<HoleNode> p1;
        if (isLeft) {
            p1 = CombineArrayAndBank(left, leftBank, right);
        } else {
            p1 = CombineArrayAndBank(right, rightBank, left);
        }
        if(square < 0 || square > left.size() - 1) {
            throw new IllegalArgumentException();

        }
        if (p1.get(square).value < 1) {
            throw new IllegalStateException();
        }
        int hand = p1.get(square).value;
        p1.get(square).value = 0;

        while(true) {
            for (int i = 0; i < hand; i++) {
                square++;
                if (square == p1.size()) {
                    square = 0;
                }
                p1.get(square).value++;
            }
            if (square == p1.size() / 2) {
                return true;
            } else {
                HoleNode hole = p1.get(square);
                if (hole.value == 1) {
                    return false;
                } else {
                    hand = hole.value;
                    hole.value = 0;
                }
            }
        }
    }

    public void moveCapture(boolean isLeft, int square) {

    }

    public boolean isWin() {
        return sum(left) == 0 || sum(right) == 0;
    }

    public int whoWins() {
        return leftBank.value - rightBank.value;
    }

    public int sum(List<HoleNode> side) {
        int sum = 0;
        for (int i = 0; i < side.size(); i++) {
            sum += side.get(i).value;
        }
        return sum;
    }

    public Board copy() {
        Board other = new Board();
        List<HoleNode> p1 = CombineArrayAndBank(left, leftBank, right);
        List<HoleNode> p2 = CombineArrayAndBank(other.left, other.leftBank, other.right);
        for (int i = 0; i < p2.size(); i++) {
            p2.get(i).value = p1.get(i).value;
        }
        other.rightBank.value = rightBank.value;
        return other;
    }

    public void printBoard(boolean isLeft) {
        List<HoleNode> p1;
        List<HoleNode> p2;
        String board = "   ";
        if (isLeft) {
            p1 = left;
            p2 = right;
        } else {
            p1 = right;
            p2 = left;
        }
        for (int i = p2.size() - 1; i >= 0; i--) {
            board += p2.get(i).value + " ";
        }
        System.out.println(board);
        int length = board.length();
        if (isLeft) {
            board = rightBank.value + "";
        } else {
            board = leftBank.value + "";
        }
        for(int i = 0; i < length; i++) {
            board += " ";
        }
        if (isLeft) {
            board += leftBank.value;
        } else {
            board += rightBank.value;
        }
        System.out.println(board);
        board = "   ";
        for (int i = 0; i < p1.size(); i++) {
            board += p1.get(i).value + " ";
        }
        System.out.println(board);
    }

    public int getSize() {
        return left.size() + right.size();
    }

    private List<HoleNode> CombineArrayAndBank(List<HoleNode> first, HoleNode bank, List<HoleNode> second) {
        List<HoleNode> list = new ArrayList<HoleNode>();
        for(HoleNode hole : first) {
            list.add(hole);
        }
        list.add(bank);
        for(HoleNode hole : second) {
            list.add(hole);
        }
        return list;
    }
}
