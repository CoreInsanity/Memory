package view.MemoryScreen;

import helpers.Popup;
import javafx.scene.control.Alert;
import models.Game;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import java.io.*;
import java.util.*;


/**
 * Lars De Loenen
 * 19/02/2020.
 */
public class MemoryScreenPresenter {
    private Stage stage;
    private MemoryScreenView view;
    private Pane playField;
    private Game game;
    private Image topImg;
    private Image topSelImg;
    private ArrayList<Image> botImgs;
    private Thread onClickThread;

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage, Game gameMod) {
        //Initialize variables
        stage = curStage;
        view = memoryScreenView;
        game = gameMod;
        game.startTimer(view);
        playField = view.getPlayField();
        botImgs = new ArrayList<>();

        try {
            loadImgs();
        } catch (Exception ex) {
            Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
            Platform.exit();
        }

        setCursors();
        addEventHandlers();
        keycontrols();
    }
    private void loadImgs() throws IOException {
        topImg = view.getTopImg();
        topSelImg = view.getTopSelImg();
        for (var file : new File("resources\\bottom").listFiles()) {
            var img = new Image(new FileInputStream(file.getAbsolutePath()));
            botImgs.add(img);
            botImgs.add(img);
        }
        Collections.shuffle(botImgs);
    }
    private void addEventHandlers() {
        view.getMainMenu().setOnAction(b -> {
            game.stopTimer();
            helpers.Scene.showMainMenu(stage);
        });
        view.getScoreboard().setOnAction(b -> {
            game.stopTimer();
            helpers.Scene.showScoreboard(stage);
        });
        view.getExit().setOnAction(b -> {
            game.stopTimer();
            Platform.exit();
        });
        view.getRestart().setOnAction(b -> {
            game.stopTimer();
            try {
                var newGame = new Game(game.getName(), game.getAge());
                game = null;
                helpers.Scene.showMemory(stage, newGame);
            } catch (Exception ex) {
                Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        // Set onClicks for each tile, also checking for doubleclicks
        int i = 0;
        for (var observable : playField.getChildren()) {
            var index = i;
            var img = (ImageView) observable;

            img.setOnMouseClicked(m -> {
                if (m.getClickCount() == 1) {
                    onClickThread = new Thread(new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            Thread.sleep(250); //Add slight delay, if the user clicks again within 250ms this thread will be killed
                            Platform.runLater(()->game.tileClick(botImgs.get(index), topImg, topSelImg, img, index, playField, stage)); //We can assume it was a single click
                            return null;
                        }
                    });
                    onClickThread.start();
                } else if (m.getClickCount() == 2) {
                    onClickThread.interrupt(); //Kill the previously generated onclick thread
                    game.getHint(img, botImgs.get(index), topImg, topSelImg);
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
                //If user presses CTRL+Enter the getHint function should be called
                if (new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN).match(i))
                    game.getHint(img, botImgs.get(index), topImg, topSelImg);
                else switch (i.getCode()) {
                    case UP:
                        //Make sure the selector is not on the top of the playfield
                        if (index >= 4) setHoverView(index - 4);
                        break;
                    case DOWN:
                        //Make sure the selector is not on the bottom of the playfield
                        if (index <= 15) setHoverView(index + 4);
                        break;
                    case LEFT:
                        //Make sure the selector is not on the left side of the playfield
                        if (index != 0 && index != 4 && index != 8 && index != 12 && index != 16)
                            setHoverView(index - 1);
                        break;
                    case RIGHT:
                        //Make sure the selector is not on the right side of the playfield
                        if (index != 3 && index != 7 && index != 11 && index != 15 && index != 19)
                            setHoverView(index + 1);
                        break;
                    case TAB:
                        setHoverView(index + 1);
                        break;
                    case ENTER:
                        game.tileClick(botImgs.get(index), topImg, topSelImg, img, index, playField, stage);
                        break;
                }
            });

        }
    }
    private void setHoverView(int index) {
        var i = 0;
        for (var observable : playField.getChildren()) {
            var img = (ImageView) observable;
            if (i == index && img.getImage().hashCode() == topImg.hashCode()) img.setImage(topSelImg);
            else if (img.getImage().hashCode() == topSelImg.hashCode()) img.setImage(topImg);
            i++;
        }
    }
    private void setCursors() {
        for (var observable : playField.getChildren()) {
            var img = (ImageView) observable;
            img.setCursor(Cursor.HAND);
        }
    }
}
