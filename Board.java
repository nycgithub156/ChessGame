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

    public List<Position> possibleMoves(Piece piece) {
        List<Position> moves = new ArrayList<>();

        if (piece == null || piece.getPieceType() == null) {
            return moves;
        }

        switch (piece.getPieceType()) {
            case PAWN:
                moves.addAll(possiblePawnMoves(piece));
                break;
            case BISHOP:
                moves.addAll(possibleBishopMoves(piece));
                break;
            case KNIGHT:
                moves.addAll(possibleKnightMoves(piece));
                break;
            case ROOK:
                moves.addAll(possibleRookMoves(piece));
                break;
            case QUEEN:
                moves.addAll(possibleQueenMoves(piece));
                break;
            case KING:
                moves.addAll(possibleKingMoves(piece));
                break;
            default:
                break;
        }

        return moves;
    }

    private List<Position> possiblePawnMoves(Piece pawn) {
        List<Position> moves = new ArrayList<>();
        int dir = pawn.getPieceColor() == PieceColor.WHITE ? 1 : -1;
        int x = pawn.getPosition().getPositionX();
        int y = pawn.getPosition().getPositionY();

        if (getPieceAt(x, y + dir) == null) {
            moves.add(new Position(x, y + dir));
            if ((pawn.getPieceColor() == PieceColor.WHITE && y == 2)
                    || (pawn.getPieceColor() == PieceColor.BLACK && y == 7)) {
                if (getPieceAt(x, y + 2 * dir) == null) {
                    moves.add(new Position(x, y + 2 * dir));
                }
            }
        }

        if (getPieceAt(x + 1, y + dir) != null && getPieceAt(x + 1, y + dir).getPieceColor() != pawn.getPieceColor()) {
            moves.add(new Position(x + 1, y + dir));
        }
        if (getPieceAt(x - 1, y + dir) != null && getPieceAt(x - 1, y + dir).getPieceColor() != pawn.getPieceColor()) {
            moves.add(new Position(x - 1, y + dir));
        }

        return moves;
    }

    private List<Position> possibleBishopMoves(Piece bishop) {
        List<Position> moves = new ArrayList<>();
        int x = bishop.getPosition().getPositionX();
        int y = bishop.getPosition().getPositionY();
        PieceColor color = bishop.getPieceColor();

        int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            int nx = x + dx;
            int ny = y + dy;

            while (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8) {
                Piece p = getPieceAt(nx, ny);
                if (p == null) {
                    moves.add(new Position(nx, ny));
                } else {
                    if (p.getPieceColor() != color) {
                        moves.add(new Position(nx, ny));
                    }
                    break;
                }
                nx += dx;
                ny += dy;
            }
        }

        return moves;
    }

    private List<Position> possibleKnightMoves(Piece knight) {
        List<Position> moves = new ArrayList<>();
        int x = knight.getPosition().getPositionX();
        int y = knight.getPosition().getPositionY();
        PieceColor color = knight.getPieceColor();

        int[][] offsets = {
                { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 },
                { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }
        };

        for (int[] offset : offsets) {
            int nx = x + offset[0];
            int ny = y + offset[1];

            if (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8) {
                Piece p = getPieceAt(nx, ny);
                if (p == null || p.getPieceColor() != color) {
                    moves.add(new Position(nx, ny));
                }
            }
        }

        return moves;
    }

    private List<Position> possibleRookMoves(Piece rook) {
        List<Position> moves = new ArrayList<>();
        int x = rook.getPosition().getPositionX();
        int y = rook.getPosition().getPositionY();
        PieceColor color = rook.getPieceColor();

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            int nx = x + dx;
            int ny = y + dy;

            while (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8) {
                Piece p = getPieceAt(nx, ny);
                if (p == null) {
                    moves.add(new Position(nx, ny));
                } else {
                    if (p.getPieceColor() != color) {
                        moves.add(new Position(nx, ny));
                    }
                    break;
                }
                nx += dx;
                ny += dy;
            }
        }

        return moves;
    }

    private List<Position> possibleQueenMoves(Piece queen) {
        List<Position> moves = new ArrayList<>();
        moves.addAll(possibleBishopMoves(queen));
        moves.addAll(possibleRookMoves(queen));
        return moves;
    }

    private List<Position> possibleKingMoves(Piece king) {
        List<Position> moves = new ArrayList<>();
        int x = king.getPosition().getPositionX();
        int y = king.getPosition().getPositionY();
        PieceColor color = king.getPieceColor();

        int[][] offsets = {
                { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 },
                { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }
        };

        for (int[] offset : offsets) {
            int nx = x + offset[0];
            int ny = y + offset[1];

            if (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8) {
                Piece p = getPieceAt(nx, ny);
                if (p == null || p.getPieceColor() != color) {
                    moves.add(new Position(nx, ny));
                }
            }
        }

        return moves;
    }

    public boolean isKingInCheck(PieceColor color) {
        Piece king = null;
        for (Piece p : pieces) {
            if (p.getPieceType() == PieceType.KING && p.getPieceColor() == color) {
                king = p;
                break;
            }
        }
        if (king == null)
            return false;

        Position kingPos = king.getPosition();

        for (Piece p : pieces) {
            if (p.getPieceColor() != color) {
                List<Position> moves = possibleMoves(p);
                for (Position move : moves) {
                    if (move.equals(kingPos)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean movePiece(Position from, Position to) {
        Piece piece = getPieceAt(from.getPositionX(), from.getPositionY());

        if (piece == null) {
            System.out.println("There are no pieces in that position.");
            return false;
        }

        List<Position> moves = possibleMoves(piece);
        boolean validMove = false;

        for (Position pos : moves) {
            if (pos.equals(to)) {
                validMove = true;
                break;
            }
        }

        if (!validMove) {
            System.out.println("Invalid move for that piece.");
            return false;
        }

        Piece target = getPieceAt(to.getPositionX(), to.getPositionY());
        if (target != null && target.getPieceColor() != piece.getPieceColor()) {
            pieces.remove(target);
        }

        piece.setPosition(to);
        return true;
    }

    public boolean isCheckmate(PieceColor color) {
        if (!isKingInCheck(color)) {
            return false;
        }

        for (Piece piece : pieces) {
            if (piece.getPieceColor() != color)
                continue;

            Position originalPos = piece.getPosition();
            List<Position> moves = possibleMoves(piece);

            for (Position move : moves) {
                Piece captured = getPieceAt(move.getPositionX(), move.getPositionY());

                piece.setPosition(move);
                if (captured != null) {
                    pieces.remove(captured);
                }

                boolean stillInCheck = isKingInCheck(color);

                piece.setPosition(originalPos);
                if (captured != null) {
                    pieces.add(captured);
                }

                if (!stillInCheck) {
                    return false;
                }
            }
        }

        return true;
    }
}