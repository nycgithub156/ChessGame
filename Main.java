public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();

        GameUI gameUI = new GameUI();
        gameUI.displayBoard(board);
    }
}

// Board board = new Board();
// board.initializeBoard();
// displayBoard(board);