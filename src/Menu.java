import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Game;
import view.Tile;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Menu extends Application {
    private static String ScoreboardPath = "scoreboard.json";

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(Tile.vul());
        stage.setScene(scene);
        stage.setHeight(350);
        stage.setWidth(325);

        stage.setResizable(false);
        stage.setTitle("Memory");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}