package models;

import helpers.Scoreboard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.MemoryScreen.MemoryScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Maxim Van den Eede
 * 17/12/2019.
 */
public class Game {
    private int GameTime;
    private int ClickAmount;
    private Player Player;
    private Timer GameTimer;
    private MediaPlayer tickPlayer;
    private MediaPlayer flipPlayer;
    private MediaPlayer matchPlayer;
    private Integer lastClickIndex;
    private Integer toRemoveIndex;
    private Integer toRemoveSecondIndex;
    private Integer foundMatches;

    // Constructor (Load ALL required resources here!)
    public Game(){
        //Initialize variables
        GameTimer = new Timer();
        Player = new Player();
        foundMatches = 0;
        ClickAmount = 0;
        GameTime = 0;

        try{
            tickPlayer = new MediaPlayer(new Media(new File("resources\\audio\\clock_tick.mp3").toURI().toString()));
            flipPlayer = new MediaPlayer(new Media(new File("resources\\audio\\card_flip.mp3").toURI().toString()));
            matchPlayer = new MediaPlayer(new Media(new File("resources\\audio\\match.mp3").toURI().toString()));
        }catch (Exception ex){
            System.out.println("Error while loading resources: " + ex.getMessage());
            Platform.exit();
        }
    }

    // Getters
    public int getClickAmount() { return ClickAmount; }
    public int getGameTime() { return GameTime; }
    public Player getPlayer() { return Player; }

    // Setters
    public void setClickAmount (int clickAmount) {ClickAmount = clickAmount; }
    public void setGameTime(int gameTime) { GameTime = gameTime; }
    public void adjustClickAmount(int clicks) { ClickAmount += clicks; }

    public void adjustGameTime(int time) {
        GameTime += time;
    }

    // Functions
    public void startTimer(MemoryScreenView view){
        GameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                adjustGameTime(1);
                int Minutes = getGameTime()/60;
                int Seconds = getGameTime()%60;
                var minutes = (Minutes < 10? "0":"") + Minutes;
                var seconds = (Seconds < 10? "0":"") + Seconds;

                Platform.runLater(() -> view.getTimer().setText( "Time: " + minutes + ":"+ seconds));
                playAudio(Audio.TICK);
            }
        },1000,1000);
    }
    public void stopTimer(){
        GameTimer.cancel();
    }
    public void playAudio(Audio type){
        switch (type){
            case FLIP:
                flipPlayer.seek(Duration.ZERO);
                flipPlayer.play();
                break;
            case TICK:
                tickPlayer.seek(Duration.ZERO);
                tickPlayer.play();
                break;
            case MATCH:
                matchPlayer.seek(Duration.ZERO);
                matchPlayer.play();
                break;
        }
    }
    public void showPopup(Stage stage, String title, String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
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
    public void endGame(Stage stage){
        stopTimer();

        try {
            Scoreboard.addGameData(this, "scoreboard.json");
        }catch (Exception ex){
            System.out.println("Something went wrong saving progress: " + ex.getMessage());
            Platform.exit(); // Da's pech, progress weg
        }

        showPopup(stage, "You win!", "Congrats cyka");
    }
    public void tileClick(Image originalImg, Image topImg, ImageView tileView, Integer imgIndex, Pane playField, Stage stage){
        playAudio(Audio.FLIP);
        tileView.setImage(originalImg);
        tileView.setCursor(Cursor.DEFAULT);
        adjustClickAmount(1);

        if (toRemoveIndex != null && lastClickIndex != null){
            var toRemoveImg = (ImageView) playField.getChildren().get(toRemoveIndex);
            var toRemoveSecImg = (ImageView) playField.getChildren().get(lastClickIndex);
            toRemoveImg.setImage(topImg);
            toRemoveSecImg.setImage(topImg);
            toRemoveIndex = null;
            lastClickIndex = null;
        }

        if(lastClickIndex != null){
            var lastClickedImg = (ImageView) playField.getChildren().get(lastClickIndex);
            if(tileView.getImage().hashCode() != lastClickedImg.getImage().hashCode())
                toRemoveIndex = imgIndex;
            else {
                lastClickIndex = null;
                playAudio(Audio.MATCH);
                foundMatches++;
                if(foundMatches == 10) endGame(stage);
            }
            return;
        }

        lastClickIndex = imgIndex;
    }
    public void getHint(ImageView view, Image botImg, Image topImg){
        view.setImage(botImg);
        adjustGameTime(10);

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
