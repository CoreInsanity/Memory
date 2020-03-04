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
    private Image topSelImg;
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
    private void onTileClick(Image originalImg, ImageView view, Integer imgIndex){
        view.setImage(originalImg);
        view.setCursor(Cursor.DEFAULT);
        view.setOpacity(1);

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

        int a = 0;
        for (var observable : playField.getChildren()) {
            var index = a;
            var img = (ImageView) observable;
            img.setFocusTraversable(true);

            img.setOnKeyPressed(i -> {
                switch (i.getCode()){
                    case ENTER:
                        onTileClick(botImgs.get(index), img, index);
                        break;
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
                }
            });
            a++;
        }
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
