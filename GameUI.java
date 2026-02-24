public class GameUI {
    public static void displayBoard(Board board) {
        for (int y = 8; y >= 1; y--) {
            for (int x = 1; x <= 8; x++) {
                Piece piece = board.getPieceAt(x, y);
                System.out.print(whatIsThePiece(piece) + " ");
            }
            System.out.println();
        }
    }

    public static char whatIsThePiece(Piece piece) {
        if (piece == null)
            return '.';

        char c;
        if (piece.getPieceType() == PieceType.PAWN)
            c = 'P';
        else if (piece.getPieceType() == PieceType.ROOK)
            c = 'R';
        else if (piece.getPieceType() == PieceType.KNIGHT)
            c = 'N';
        else if (piece.getPieceType() == PieceType.BISHOP)
            c = 'B';
        else if (piece.getPieceType() == PieceType.QUEEN)
            c = 'Q';
        else if (piece.getPieceType() == PieceType.KING)
            c = 'K';
        else
            c = '.';

        if (piece.getPieceColor() == PieceColor.BLACK) {
            c = Character.toLowerCase(c);
        }

        return c;
    }

    public static void startMenu() {
        System.out.println("+--------------------------ChessLegacy--------------------------+");
        System.out.println("|                       (1) - New Match                         |");
        System.out.println("|                       (2) - Ranking                           |");
        System.out.println("|                       (3) - New Player                        |");
        System.out.println("|                       (4) - Past matches                      |");
        System.out.println("|                       (5) - Exit                              |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("What is your option?");
    }
}