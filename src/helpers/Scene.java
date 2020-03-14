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
        var mmView = new MainMenuScreenView();
        new MainMenuScreenPresenter(mmView, stage);
        stage.setScene(new javafx.scene.Scene(mmView));
    }
    public static void showMemory(Stage stage, Game game){
        var viewer = new MemoryScreenView();
        new MemoryScreenPresenter(viewer, stage, game);
        stage.setScene(new javafx.scene.Scene(viewer));
    }
    public static void showScoreboard(Stage stage){
        var sView = new ScoreboardScreenView();
        new ScoreboardScreenPresenter(sView, stage);
        stage.setScene(new javafx.scene.Scene(sView));
    }
}
