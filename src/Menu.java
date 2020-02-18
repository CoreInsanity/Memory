import javafx.application.Application;
import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.Game;
import scenes.Player;
import view.Tile;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Menu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("Tarkov Memory");
        Player.showScene(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}