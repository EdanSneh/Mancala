public class Main {

    public static void main(String[] args) {
        Board test = new Board();
        test.printBoard(true);
        System.out.println(test.moveAvalanche(false, 4));
        test.printBoard(true);
    }
}
