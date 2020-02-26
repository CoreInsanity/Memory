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

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage) {
        stage = curStage;
        stage.setHeight(700);
        stage.setWidth(675);
        stage.setResizable(true);

        view = memoryScreenView;

        genPlayfield();
        addEventHandler();
    }
    private void genPlayfield(){
        var tiles = new ArrayList<ImageView>();
        for (var file : new File("resources\\bottom").listFiles()) {
            try {
                var image = new Image(new FileInputStream(file.getAbsolutePath()));//ONLY CREATE ONE INSTANCE OF THE IMAGE! Otherwise we get different hashcodes
                tiles.add(new ImageView(image));
                tiles.add(new ImageView(image));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        Collections.shuffle(tiles);

        Pane playField = new Pane();
        for (int i = 0; i < tiles.size(); i++) {
            ImageView tile = tiles.get(i);
            tile.setTranslateX(128 * (i % 4));
            tile.setTranslateY(128 * (i / 4));
            playField.getChildren().add(tile);
        }
        view.initPlayfield(playField);
    }

    private void topImg() {

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
    }
}
