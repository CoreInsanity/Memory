package view.MemoryScreen;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.concurrent.Task;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

import java.io.*;
import java.util.*;


/**
 * Lars De Loenen
 * 19/02/2020.
 */
public class MemoryScreenPresenter {
    private Stage stage;
    private MemoryScreenView view;
    private Image topImg;
    private Image topSelImg;
    private Pane playField;
    private ArrayList<Image> botImgs;
    private Integer lastClickIndex;
    private Integer toRemoveIndex;
    private Integer toRemoveSecondIndex;
    private Scene scene;
    private Timer gameTimer;
    private int timerSec;
    private MediaPlayer tickPlayer; //Only use this one for timer ticks
    private MediaPlayer cflipPlayer; //Only use this one for card flips
    private MediaPlayer sfPlayer; //Use this one for all the other SFX

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage) {
        initStage(curStage);

        view = memoryScreenView;

        scene = stage.getScene();
        playField = view.getPlayField();

        botImgs = new ArrayList<>();
        gameTimer = new Timer();

        timerSec = 0;

        try {
            loadImgs();
            loadAudio();
        } catch (Exception ex){
            System.out.println("Error while loading resources: " + ex.getMessage());
            Platform.exit();
        }

        setCursors();
        addEventHandlers();
        keycontrols();
        startTimer();
    }
    private void initStage(Stage stg){
        stage = stg;
        stage.setHeight(700);
        stage.setWidth(675);
    }
    private void startTimer(){
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerSec++;

                int Minutes = timerSec/60;
                int Seconds = timerSec%60;
                var minutes = (Minutes < 10? "0":"") + Minutes;
                var seconds = (Seconds < 10? "0":"") + Seconds;

                Platform.runLater(() -> view.getTimer().setText(minutes + ":"+ seconds));
                tickPlayer.seek(Duration.ZERO);
                tickPlayer.play();
            }
        },1000,1000);
    }
    private void loadImgs() throws FileNotFoundException{
        topImg = view.getTopImg();
        topSelImg = view.getTopSelImg();
        for (var file:new File("resources\\bottom").listFiles()) {
            var img = new Image(new FileInputStream(file.getAbsolutePath()));
            botImgs.add(img);
            botImgs.add(img);
        }
        Collections.shuffle(botImgs);
    }
    private void loadAudio() throws FileNotFoundException{
        tickPlayer = new MediaPlayer(new Media(new File("resources\\audio\\clock_tick.mp3").toURI().toString()));
        cflipPlayer = new MediaPlayer(new Media(new File("resources\\audio\\card_flip.mp3").toURI().toString()));
        sfPlayer = new MediaPlayer(new Media(new File("resources\\audio\\match.mp3").toURI().toString()));
    }
    private void onTileClick(Image originalImg, ImageView view, Integer imgIndex){
        cflipPlayer.seek(Duration.ZERO);
        cflipPlayer.play();
        view.setImage(originalImg);
        view.setCursor(Cursor.DEFAULT);

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
            if(view.getImage().hashCode() != lastClickedImg.getImage().hashCode())
                toRemoveIndex = imgIndex;
            else {
                lastClickIndex = null;
                sfPlayer.seek(Duration.ZERO);
                sfPlayer.play();
            }
            return;
        }

        lastClickIndex = imgIndex;
    }
    private void addEventHandlers() {
        view.getMenu1().setOnAction(b -> {
            var mmView = new MainMenuScreenView();
            new MainMenuScreenPresenter(mmView, stage);
            stage.setScene(new Scene(mmView));
        });
        view.getMenu4().setOnAction(b -> {
            var mView = new MemoryScreenView();
            new MemoryScreenPresenter(mView, stage);
            stage.setScene(new Scene(mView));
        });
        view.getMenu2().setOnAction(b -> {
            var sbView = new ScoreboardScreenView();
            new ScoreboardScreenPresenter(sbView, stage);
            stage.setScene(new Scene(sbView));
        });
        view.getMenu3().setOnAction(b -> Platform.exit());

        int i = 0;
        for (var observable:playField.getChildren()) {
            var index = i;
            var img = (ImageView) observable;
            img.setOnMouseClicked(m -> onTileClick(botImgs.get(index), img, index));
            img.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("YEET");
                    onTileClick(botImgs.get(index), img, index);
                }
            });
            i++;
        }
    }
    private void keycontrols() {
        view.setFocusTraversable(true);

        int a = 0;
        for (var observable : playField.getChildren()) {
            var index = a;
            a++;
            var img = (ImageView) observable;
            img.setFocusTraversable(true);

            img.setOnKeyPressed(i -> {
                if(new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN).match(i)) getHint(img, botImgs.get(index));
                else switch (i.getCode()){
                    case UP:
                        setHoverView(index-4);
                        break;
                    case DOWN:
                        setHoverView(index+4);
                        break;
                    case LEFT:
                        setHoverView(index-1);
                        break;
                    case RIGHT:
                        setHoverView(index+1);
                        break;
                    case ENTER:
                        onTileClick(botImgs.get(index),img,index);
                        break;
                }
            });

        }
    }
    private void getHint(ImageView view, Image botImg){
        view.setImage(botImg);

        var resetTileTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(1000);
                view.setImage(topSelImg);
                return null;
            }
        };

        var thread = new Thread(resetTileTask);
        thread.start();
    }
    private void setHoverView(int index){
        var i = 0;
        for (var observable : playField.getChildren()) {
            var img = (ImageView) observable;
            if(i == index && img.getImage().hashCode() == topImg.hashCode()) img.setImage(topSelImg);
            else if(img.getImage().hashCode() == topSelImg.hashCode()) img.setImage(topImg);
            i++;
        }
    }
    private void setCursors(){
        for (var observable:playField.getChildren()) {
            var img = (ImageView) observable;
            img.setCursor(Cursor.HAND);
        }
    }
}
