public class MPlayer extends Player {
    private int difficulty;

    public MPlayer(String name, PieceColor color, int difficulty) {
        super(name, color);
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}