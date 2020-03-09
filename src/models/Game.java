package models;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

import java.util.Optional;

/**
 * Maxim Van den Eede
 * 17/12/2019.
 */
public class Game {
    private int GameTime;
    private int Score = 0;
    private int ClickAmount = 0;
    private Player Player = new Player();

    // Getters
    public int getOrderScore() {return GameTime - Score; }
    public int getScore() { return Score; }
    public int getGameTime() { return GameTime; }
    public int getClickAmount(){ return ClickAmount; }
    public Player getPlayer() { return Player; }

    // Setters
    public void adjustScore (int score) { Score += score; }
    public void setScore (int score) {Score = score; }
    public void setGameTime(int gameTime) { GameTime = gameTime; }
    public void adjustClickAmount(int clicks) { ClickAmount += clicks; }

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

    public void showEndScreen(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You won!");
        alert.setHeaderText("Congratulations blyat");
        //view.setStyle("-fx-opacity: 0.5"); //FIX THIS
        ButtonType scoreboard = new ButtonType("Scoreboard");
        ButtonType mainmenu = new ButtonType("Main menu");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(scoreboard,mainmenu);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == scoreboard) {
            var sView = new ScoreboardScreenView();
            new ScoreboardScreenPresenter(sView, stage);
            stage.setScene(new Scene(sView));
        } else if (option.get() == mainmenu) {
            var mmView = new MainMenuScreenView();
            new MainMenuScreenPresenter(mmView, stage);
            stage.setScene(new Scene(mmView));
        }
    }
}
