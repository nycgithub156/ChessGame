import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Piece> pieces = new ArrayList<>();

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void initializeBoard() {
        pieces.clear();
        pieces.addAll(insertPawns());
        pieces.addAll(insertRooks());
        pieces.addAll(insertKnights());
        pieces.addAll(insertBishops());
        pieces.addAll(insertQueens());
        pieces.addAll(insertKings());
    }

    public List<Piece> insertPawns() {
        List<Piece> pawns = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            pawns.add(new Piece(new Position(i, 2), PieceColor.WHITE, PieceType.PAWN));
            pawns.add(new Piece(new Position(i, 7), PieceColor.BLACK, PieceType.PAWN));
        }

        return pawns;
    }

    public List<Piece> insertRooks() {
        List<Piece> rooks = new ArrayList<>();

        rooks.add(new Piece(new Position(1, 1), PieceColor.WHITE, PieceType.ROOK));
        rooks.add(new Piece(new Position(8, 1), PieceColor.WHITE, PieceType.ROOK));
        rooks.add(new Piece(new Position(1, 8), PieceColor.BLACK, PieceType.ROOK));
        rooks.add(new Piece(new Position(8, 8), PieceColor.BLACK, PieceType.ROOK));

        return rooks;
    }

    public List<Piece> insertKnights() {
        List<Piece> knights = new ArrayList<>();

        knights.add(new Piece(new Position(2, 1), PieceColor.WHITE, PieceType.KNIGHT));
        knights.add(new Piece(new Position(7, 1), PieceColor.WHITE, PieceType.KNIGHT));
        knights.add(new Piece(new Position(2, 8), PieceColor.BLACK, PieceType.KNIGHT));
        knights.add(new Piece(new Position(7, 8), PieceColor.BLACK, PieceType.KNIGHT));

        return knights;
    }

    public List<Piece> insertBishops() {
        List<Piece> bishops = new ArrayList<>();

        bishops.add(new Piece(new Position(3, 1), PieceColor.WHITE, PieceType.BISHOP));
        bishops.add(new Piece(new Position(6, 1), PieceColor.WHITE, PieceType.BISHOP));
        bishops.add(new Piece(new Position(3, 8), PieceColor.BLACK, PieceType.BISHOP));
        bishops.add(new Piece(new Position(6, 8), PieceColor.BLACK, PieceType.BISHOP));

        return bishops;
    }

    public List<Piece> insertQueens() {
        List<Piece> queens = new ArrayList<>();

        queens.add(new Piece(new Position(4, 1), PieceColor.WHITE, PieceType.QUEEN));
        queens.add(new Piece(new Position(4, 8), PieceColor.BLACK, PieceType.QUEEN));

        return queens;
    }

    public List<Piece> insertKings() {
        List<Piece> kings = new ArrayList<>();

        kings.add(new Piece(new Position(5, 1), PieceColor.WHITE, PieceType.KING));
        kings.add(new Piece(new Position(5, 8), PieceColor.BLACK, PieceType.KING));

        return kings;
    }

    public Piece getPieceAt(int x, int y) {
        for (Piece piece : pieces) {
            Position pos = piece.getPosition();
            
            if (pos.getPositionX() == x && pos.getPositionY() == y) {
                return piece;
            }
        }

        return null;
    }
}
