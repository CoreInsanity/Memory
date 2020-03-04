package view.PlayerCreationScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Game;

public class PlayerCreationScreenView extends GridPane {
    private Label lblName;
    private TextField txtName;
    private Label lblAge;
    private TextField txtAge;
    private Button playBtn;
    private HBox playBtnBox;
    private Button backBtn;
    private HBox backBtnBox;

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
        playBtnBox = new HBox(10);
        backBtn = new Button("Back");
        backBtnBox = new HBox(10);
    }
    private void layoutNodes(){
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        setBackground(new Background(new BackgroundFill(Color.rgb(42, 45, 54), CornerRadii.EMPTY, Insets.EMPTY)));

        lblAge.setTextFill(Color.rgb(197, 178, 0));
        lblName.setTextFill(Color.rgb(197, 178, 0));
        playBtn.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY,Insets.EMPTY)));
        backBtn.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY,Insets.EMPTY)));

        playBtn.setCursor(Cursor.HAND);
        backBtn.setCursor(Cursor.HAND);

        playBtnBox.setAlignment(Pos.BOTTOM_RIGHT);
        playBtnBox.getChildren().add(playBtn);

        backBtnBox.setAlignment(Pos.BOTTOM_LEFT);
        backBtnBox.getChildren().add(backBtn);

        add(lblName,0, 1);
        add(txtName,1, 1);
        add(lblAge, 0, 2);
        add(txtAge, 1, 2);
        add(playBtnBox, 1, 3);
        add(backBtnBox,0,3);

    }

    TextField getName(){return txtName;}
    TextField getAge(){return txtAge;}
    Button getPlayBtn(){return playBtn;}
    Button getBackBtn(){return backBtn;}
}
