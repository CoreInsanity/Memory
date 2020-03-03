package view.PlayerCreationScreen;

import com.sun.source.tree.LambdaExpressionTree;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import models.Tile;
import scenes.Memory;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.MemoryScreen.MemoryScreenPresenter;
import view.MemoryScreen.MemoryScreenView;

import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class PlayerCreationScreenPresenter {
    private PlayerCreationScreenView view;
    private Stage stage;
    private String name;
    private int age;
    private MainMenuScreenView mainMenuScreenView;

    public PlayerCreationScreenPresenter(PlayerCreationScreenView plView, Stage curstage){
        view = plView;
        stage = curstage;

        addEventhandlers();
    }
    private void addEventhandlers(){
        view.getPlayBtn().setOnAction(b -> {
            var viewer = new MemoryScreenView();
            new MemoryScreenPresenter(viewer, stage);
            stage.setScene(new Scene(viewer));
        });

        view.getPlayBtn().setCursor(Cursor.HAND);
        view.getBackBtn().setOnAction(b -> {
            var mmView = new MainMenuScreenView();
            new MainMenuScreenPresenter(mmView, stage);
            stage.setScene(new Scene(mmView));
        });

        view.getBackBtn().setCursor(Cursor.HAND);
    }
}
