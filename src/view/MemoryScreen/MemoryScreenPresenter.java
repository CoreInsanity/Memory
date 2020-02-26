package view.MemoryScreen;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Tile;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.PlayerCreationScreen.PlayerCreationScreenPresenter;
import view.PlayerCreationScreen.PlayerCreationScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;

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

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage) {
        stage = curStage;
        stage.setHeight(700);
        stage.setWidth(675);
        stage.setResizable(true);
        botImgs = new ArrayList<>();
        view = memoryScreenView;
        playField = view.getPlayField();

        try {
            loadImgs();
        } catch (Exception ex){
            // ¯\_(ツ)_/¯
            System.out.println(ex.getMessage());
            Platform.exit();
        }

        setCursors();
        addEventHandler();
    }

    private void setCursors(){
        for (var observable:playField.getChildren()) {
            var img = (ImageView) observable;
            img.setCursor(Cursor.HAND);
        }
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
    private void onTileClick(Image originalImg, ImageView view){
        view.setImage(originalImg);
    }

    private void addEventHandler() {
        view.getMenu1().setOnAction(b -> {
            var mmView = new MainMenuScreenView();
            new MainMenuScreenPresenter(mmView, stage);
            stage.setScene(new Scene(mmView));
        });
        view.getMenu2().setOnAction(b -> {
            var sbView = new ScoreboardScreenView();
            new ScoreboardScreenPresenter(sbView, stage);
            stage.setScene(new Scene(sbView));
            stage.show();
        });
        view.getMenu3().setOnAction(b -> Platform.exit());

        int i = 0;
        for (var observable:playField.getChildren()) {
            var img = (ImageView) observable;
            var botImg = botImgs.get(i); //Fetch image here since this is not allowed in lambda expressions
            img.setOnMouseClicked(m -> onTileClick(botImg, img));
            i++;
        }
    }
}
