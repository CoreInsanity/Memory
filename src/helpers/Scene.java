package helpers;

import javafx.stage.Stage;
import models.Game;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.MemoryScreen.MemoryScreenPresenter;
import view.MemoryScreen.MemoryScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

public class Scene {
    public static void showMainMenu(Stage stage){
        stage.setHeight(400);
        stage.setWidth(600);
        var viewer = new MainMenuScreenView();
        new MainMenuScreenPresenter(viewer, stage);
        stage.setScene(new javafx.scene.Scene(viewer));
    }
    public static void showMemory(Stage stage, Game game){
        stage.setHeight(700);
        stage.setWidth(675);
        var viewer = new MemoryScreenView();
        new MemoryScreenPresenter(viewer, stage, game);
        stage.setScene(new javafx.scene.Scene(viewer));
    }
    public static void showScoreboard(Stage stage){
        stage.setHeight(700);
        stage.setWidth(572);
        var viewer = new ScoreboardScreenView();
        new ScoreboardScreenPresenter(viewer, stage);
        stage.setScene(new javafx.scene.Scene(viewer));
    }
}
