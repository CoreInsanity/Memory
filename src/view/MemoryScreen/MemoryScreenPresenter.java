package view.MemoryScreen;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Tile;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.PlayerCreationScreen.PlayerCreationScreenPresenter;
import view.PlayerCreationScreen.PlayerCreationScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


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

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage) {
        stage = curStage;
        stage.setHeight(700);
        stage.setWidth(675);
        botImgs = new ArrayList<>();
        checkmark = new ArrayList<>();
        view = memoryScreenView;
        playField = view.getPlayField();

        try {
            loadImgs();
        } catch (Exception ex) {
            // ¯\_(ツ)_/¯
            System.out.println(ex.getMessage());
            Platform.exit();
        }

        setCursors();
        addEventHandlers();
        keycontrols();
    }

    private void loadImgs() throws FileNotFoundException {
        topImg = new Image(new FileInputStream("resources\\top.png"));
        checkmarkimg = new Image(new FileInputStream("resources\\checkmark.png"));
        var checkmarkview = new ImageView(checkmarkimg);
        checkmarkview.setFitHeight(128);
        checkmarkview.setFitWidth(128);
        for (var file : new File("resources\\bottom").listFiles()) {
            var img = new Image(new FileInputStream(file.getAbsolutePath()));
            botImgs.add(img);
            botImgs.add(img);
        }
        Collections.shuffle(botImgs);
    }

    private void onTileClick(Image originalImg, ImageView view, Integer imgIndex) {
        view.setImage(originalImg);
        view.setCursor(Cursor.DEFAULT);

        if (toRemoveIndex != null && lastClickIndex != null) {
            var toRemoveImg = (ImageView) playField.getChildren().get(toRemoveIndex);
            var toRemoveSecImg = (ImageView) playField.getChildren().get(lastClickIndex);
            toRemoveImg.setImage(topImg);
            toRemoveSecImg.setImage(topImg);
            toRemoveIndex = null;
            lastClickIndex = null;
        }

        if (lastClickIndex != null) {
            var lastClickedImg = (ImageView) playField.getChildren().get(lastClickIndex);
            if (view.getImage().hashCode() != lastClickedImg.getImage().hashCode())
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
        view.getMenu2().setOnAction(b -> {
            var sbView = new ScoreboardScreenView();
            new ScoreboardScreenPresenter(sbView, stage);
            stage.setScene(new Scene(sbView));
        });
        view.getMenu3().setOnAction(b -> Platform.exit());

        int i = 0;
        for (var observable : playField.getChildren()) {
            var index = i;
            var img = (ImageView) observable;
            img.setOnMouseClicked(m -> onTileClick(botImgs.get(index), img, index));
            i++;
        }
    }

    private void keycontrols() {
        view.getCheckmarkview().get(0).setVisible(true);
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            int index = 0;
            @Override
            public void handle(KeyEvent a) {
                switch(a.getCode()){
                    case A: if(index!=20){
                        System.out.println(a.getCode());
                        view.getCheckmarkview().get(index).setVisible(false);
                        index++;
                        view.getCheckmarkview().get(index).setVisible(true);
                    } break;
                    case LEFT: if (index>0){view.getCheckmarkview().get(index).setVisible(false); index--; view.getCheckmarkview().get(index).setVisible(true); } break;
                    case DOWN: if (index<16){ index = index +4; } break;
                    case UP: if (index>3){ index = index -4; } break;
                }
                view.getCheckmarkview().get(index).setVisible(true);
            }
        });


        int a = 0;
        for (var observable : playField.getChildren()) {
            var index = a;
            var img = (ImageView) observable;
            img.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    if (e.getCode() == KeyCode.A) {
                        onTileClick(botImgs.get(index), img, index);
                    }
                }
            });
            a++;
        }
    }

    private void setCursors() {
        for (var observable : playField.getChildren()) {
            var img = (ImageView) observable;
            img.setCursor(Cursor.HAND);
        }
    }

}
