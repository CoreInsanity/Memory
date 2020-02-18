package view.PlayerCreationScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Game;
import scenes.Memory;

public class PlayerCreationScreenView {
    private Label lblName;
    private TextField txtName;
    private Label lblAge;
    private TextField txtAge;
    private GridPane grid;
    private Button playBtn;
    private HBox playBtnBox;

    public PlayerCreationScreenView(){
        initNodes();
        layoutNodes();
    }
    private void initNodes(){
        lblName = new Label("Name:");
        txtName = new TextField();
        lblAge = new Label("Age:");
        txtAge = new TextField();
        playBtn = new Button("Play");
        grid = new GridPane();
        playBtnBox = new HBox(10);
    }
    private void layoutNodes(){
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        playBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
        playBtnBox.getChildren().add(playBtn);

        grid.add(lblName,0, 1);
        grid.add(txtName,1, 1);
        grid.add(lblAge, 0, 2);
        grid.add(txtAge, 1, 2);
        grid.add(playBtnBox, 1, 3);
    }

    TextField getName(){return txtName;}
    TextField getAge(){return txtAge;}
    Button getPlayBtn(){return playBtn;}
}
