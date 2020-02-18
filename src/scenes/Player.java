package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Game;

public class Player {
    public static void showScene(Stage window){
        configureWindow(window);

        var grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        var lblName = new Label("Name:");
        var txtName = new TextField();
        var lblAge = new Label("Age:");
        var txtAge = new TextField();
        var playBtn = new Button("Play");
        playBtn.setOnAction(b -> {
            var game = new Game();
            game.getPlayer().setAge(Integer.parseInt(txtAge.getText()));
            game.getPlayer().setName(txtName.getText());
            Memory.showScene(window, game); //Start the actual game
        });
        var playBtnBox = new HBox(10);
        playBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
        playBtnBox.getChildren().add(playBtn);

        grid.add(lblName,0, 1);
        grid.add(txtName,1, 1);
        grid.add(lblAge, 0, 2);
        grid.add(txtAge, 1, 2);
        grid.add(playBtnBox, 1, 3);

        var createPlayerScene = new Scene(grid);

        window.setScene(createPlayerScene);
    }
    private static void configureWindow(Stage window){
        //Configure the window here

        window.setHeight(200);
        window.setWidth(275);
    }
}
