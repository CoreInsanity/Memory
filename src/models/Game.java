package models;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void getHint(ImageView view, Image botImg, Image topImg){
        view.setImage(botImg);
        var resetTileTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(1000);
                view.setImage(topImg);
                return null;
            }
        };

        var thread = new Thread(resetTileTask);
        thread.start();
    }
}
