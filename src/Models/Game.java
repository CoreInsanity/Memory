package Models;
/**
 * Maxim Van den Eede
 * 17/12/2019.
 */
public class Game {
    private long GameTime;
    private int Score = 0;
    private Player Player = new Player();

    // Getters
    public int getScore() {
        return Score;
    }
    public long getGameTime() {
        return GameTime;
    }
    public Player getPlayer() {
        return Player;
    }

    // Setters
    public void setScore(int score) {
        Score = score;
    }
    public void adjustScore (int score) { Score += score; }
    public void setGameTime(long gameTime){
        GameTime = gameTime;
    }
}
