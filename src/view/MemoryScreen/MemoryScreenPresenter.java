package view.MemoryScreen;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Tile;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Lars De Loenen
 * 19/02/2020.
 */
public class MemoryScreenPresenter {
    private Tile tile;
    private Stage stage;
    private MemoryScreenView view;
    private Image topImg;
    private Pane playField;
    private ArrayList<Image> botImgs;
    private ArrayList<ImageView> checkmark;
    private Integer lastClickIndex;
    private Integer toRemoveIndex;
    private Integer toRemoveSecondIndex;
    private Image checkmarkimg;
    private int index;
    private Scene scene;
    private Timer timer = new Timer();
    private TimerTask timerTask;

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage) {
        stage = curStage;
        stage.setHeight(700);
        stage.setWidth(675);
        botImgs = new ArrayList<>();
        checkmark = new ArrayList<>();
        view = memoryScreenView;
        playField = view.getPlayField();
        index = 0;
        tile = new Tile();

        try {
            loadImgs();
        } catch (Exception ex){
            // ¯\_(ツ)_/¯
            System.out.println(ex.getMessage());
            Platform.exit();
        }
        tile.startTimer();
        scene = stage.getScene();
        setCursors();
        addEventHandlers();
        keycontrols();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                updateView();
//            }
//        }, 0, 1000);
    }
    private void loadImgs() throws FileNotFoundException{
        topImg = new Image(new FileInputStream("resources\\top.png"));
        for (var file:new File("resources\\bottom").listFiles()) {
            var img = new Image(new FileInputStream(file.getAbsolutePath()));
            botImgs.add(img);
            botImgs.add(img);
        }
        Collections.shuffle(botImgs);
    }
    private void onTileClick(Image originalImg, ImageView view, Integer imgIndex){
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
            else
                lastClickIndex = null;
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
        view.getPlayField().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case KP_RIGHT:
                    System.out.println("Right");
                    break;
                case KP_LEFT:
                    System.out.print("left nigga");
                    break;
                case KP_DOWN:
                    System.out.print("down nigga");
                    break;
                case KP_UP:
                    System.out.print("up nigga");
                    break;
            }
        });

        int a = 0;
        for (var observable : playField.getChildren()) {
            var index = a;
            var img = (ImageView) observable;
            img.setFocusTraversable(true);
            img.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                @Override
                public void handle(ContextMenuEvent contextMenuEvent) {
                    img.setStyle("-fx-border-color: Yellow; -fx-border-width: 5;");

                }
            });
            img.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    if (e.getCode() == KeyCode.ENTER) {
                        onTileClick(botImgs.get(index), img, index);
                    }
                }
            });
            a++;
        }
    }


    private void setCursors(){
        for (var observable:playField.getChildren()) {
            var img = (ImageView) observable;
            img.setCursor(Cursor.HAND);
        }
    }

    private void updateView() {
//        view.getTimer().setText(tile.getString());
    }

    int a = 0;
}
