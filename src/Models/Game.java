package Models;
/**
 * Maxim Van den Eede
 * 17/12/2019.
 */
public class Game {
    private long GameTime;
    private int Score;
    private Player Player;

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
        Score += score;
    }
    public void setGameTime(long gameTime){
        GameTime = gameTime;
    }
    public void setPlayer(Player player){
        Player = player;
    }
}
