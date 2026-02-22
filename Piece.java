public class Piece {
    private Position position;
    private PieceColor pieceColor;
    private PieceType pieceType;

    public Piece(Position position, PieceColor pieceColor, PieceType pieceType) {
        this.position = position;
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public String toString() {
        return pieceType + " " + pieceColor + " em " + position;
    }
}