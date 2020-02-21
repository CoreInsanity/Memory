package view.PlayerCreationScreen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import models.Tile;
import scenes.Memory;
import view.MemoryScreen.MemoryScreenPresenter;
import view.MemoryScreen.MemoryScreenView;

import java.io.FileNotFoundException;

public class PlayerCreationScreenPresenter {
    private PlayerCreationScreenView view;
    private String name;
    private int age;

    public PlayerCreationScreenPresenter(PlayerCreationScreenView plView, Stage stage){
        stage.setHeight(200);
        stage.setWidth(275);

        view = plView;
        view.getPlayBtn().setOnAction(b -> {
            var viewer = new MemoryScreenView();
            new MemoryScreenPresenter(viewer, stage);
            stage.setScene(new Scene(viewer));
        });
    }
}
