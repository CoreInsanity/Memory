package Models;

import Helpers.Scoreboard;

/**
 * Maxim Van den Eede
 * 17/12/2019.
 */
public class Game {
    private int GameTime;
    private int Score = 0;
    private Player Player = new Player();

    // Getters
    public int getOrderScore() {return GameTime - Score; }
    public int getScore() { return Score; }
    public int getGameTime() { return GameTime; }
    public Player getPlayer() { return Player; }

    // Setters
    public void setScore(int score) { Score = score; }
    public void adjustScore (int score) { Score += score; }
    public void setGameTime(int gameTime){ GameTime = gameTime; }
}
