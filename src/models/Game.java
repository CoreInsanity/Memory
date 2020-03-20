package models;

import helpers.Popup;
import helpers.Scoreboard;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import java.io.IOException;
import java.util.ArrayList;
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
        Player = new Player();
    }
    public Game(String name, Integer age) throws Exception{
        //Initialize variables
        GameTimer = new Timer();
        Player = new Player();
        foundMatches = 0;
        ClickAmount = 0;
        GameTime = 0;

        tickPlayer = new MediaPlayer(new Media(new File("resources\\audio\\clock_tick.mp3").toURI().toString()));
        flipPlayer = new MediaPlayer(new Media(new File("resources\\audio\\card_flip.mp3").toURI().toString()));
        matchPlayer = new MediaPlayer(new Media(new File("resources\\audio\\match.mp3").toURI().toString()));
        Player.setName(name);
        Player.setAge(age);
    }

    // Getters
    public int getClickAmount() { return ClickAmount; }
    public int getGameTime() { return GameTime; }
    public Player getPlayer() { return Player; }
    public String getName() { return Player.getName(); }
    public Integer getAge() { return Player.getAge(); }

    // Setters
    public void setClickAmount (int clickAmount) {ClickAmount = clickAmount; }
    public void setGameTime(int gameTime) { GameTime = gameTime; }
    public void adjustClickAmount(int clicks) { ClickAmount += clicks; }
    public void adjustGameTime(int time) { GameTime += time; }

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

                Platform.runLater(() -> view.getTimer().setText( "  " + minutes + ":"+ seconds));
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
    public void endGame(Stage stage){
        stopTimer();

        try {
            Scoreboard.addGameData(this, "scoreboard.json");
        }catch (Exception ex){
            Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
        }

        //Show the endscreen popup
        var buttons = new ArrayList<ButtonType>();
        buttons.add(new ButtonType("Main menu"));
        buttons.add(new ButtonType("Scoreboard"));
        var popup = Popup.showPopup("You win!", "Congrats cyka", Alert.AlertType.CONFIRMATION, buttons);
        if(popup.isPresent() && popup.get().getText() == "Scoreboard") helpers.Scene.showScoreboard(stage) ;
        else helpers.Scene.showMainMenu(stage);
    }
    public void tileClick(Image botImg, Image topImg, Image topSelImg, ImageView tileView, Integer imgIndex, Pane playField, Stage stage){
        //Make sure user only clicks topImg or topSelImg
        if(tileView.getImage().hashCode() != topImg.hashCode() && tileView.getImage().hashCode() != topSelImg.hashCode()){
            Popup.showPopup("Oops!", "This tile has already been flipped!", Alert.AlertType.WARNING);
            return;
        }

        playAudio(Audio.FLIP);
        tileView.setImage(botImg);
        tileView.setCursor(Cursor.DEFAULT);
        adjustClickAmount(1);

        //On first tile selection, revert previously flipped tiles
        if (toRemoveIndex != null && lastClickIndex != null){
            var toRemoveImg = (ImageView) playField.getChildren().get(toRemoveIndex);
            var toRemoveSecImg = (ImageView) playField.getChildren().get(lastClickIndex);
            toRemoveImg.setImage(topImg);
            toRemoveSecImg.setImage(topImg);
            toRemoveIndex = null;
            lastClickIndex = null;
        }

        //Only continue when it's not the first click
        if(lastClickIndex != null){
            var lastClickedImg = (ImageView) playField.getChildren().get(lastClickIndex);
            //Check if second click matches the first click, if not, add it to the toRemoveIndex var
            if(tileView.getImage().hashCode() != lastClickedImg.getImage().hashCode())
                toRemoveIndex = imgIndex;
            else {
                //First click matches the second click, empty the lastClickIndex so the tiles wont get flipped on the next click
                lastClickIndex = null;
                playAudio(Audio.MATCH);
                foundMatches++;
                //Check if player flipped all cards, if so, start the endGame function
                if(foundMatches == 10) endGame(stage);
            }
            return;
        }

        //Used to identify the first tile that was flipped (fe. when on second click)
        lastClickIndex = imgIndex;
    }
    public void getHint(ImageView view, Image botImg, Image topImg, Image topSelImg){
        //Make sure user only clicks topImg or topSelImg
        if(view.getImage().hashCode() != topImg.hashCode() && view.getImage().hashCode() != topSelImg.hashCode()){
            Popup.showPopup("Oops!", "Can't show hint for tile that is already flipped!", Alert.AlertType.WARNING);
            return;
        }

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
