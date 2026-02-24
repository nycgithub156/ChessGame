public class HPlayer extends Player{
    private int score;

    public HPlayer(String name, PieceColor color, int score) {
        super(name, color);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
