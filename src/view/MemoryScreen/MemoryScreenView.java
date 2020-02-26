package view.MemoryScreen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import models.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class MemoryScreenView extends BorderPane {
    private Image topImg;
    private Image image;
    private Pane playField;
    private MenuBar menuBar;
    private MenuItem menu1;
    private MenuItem menu2;
    private MenuItem menu3;
    private Menu menu;
    private Label timer;
    private Label score;
    private ImageView menulogo;


    public MemoryScreenView() {
        initNodes();
        layoutNodes();
    }

    private void initNodes() {
        try {
            topImg = new Image(new FileInputStream("resources\\top.png"));
            menulogo = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));
        } catch (Exception ex) {}

        menuBar = new MenuBar();
        menu = new Menu("Menu");
        menu1 = new MenuItem("Main menu");
        menu2 = new MenuItem("ScoreBoard");
        menu3 = new MenuItem("Exit");
        menuBar.getMenus().add(menu);
        menu.getItems().addAll(menu1, menu2, menu3);
        menu.setGraphic(menulogo);
        timer = new Label("Timer: ");
        score = new Label("Score: ");
        playField = new Pane();
    }

    private void layoutNodes() {
        timer.setTextFill(Color.YELLOW);
        timer.setPrefWidth(50);
        timer.setMaxHeight(20);

        score.setTextFill(Color.YELLOW);
        score.setPrefWidth(50);
        score.setMaxHeight(20);

        menulogo.setFitWidth(25);
        menulogo.setFitHeight(25);

        for (int i = 0; i < new File("resources\\bottom").listFiles().length*2; i++) {
            var imgView = new ImageView(topImg);
            imgView.setCursor(Cursor.HAND);
            imgView.setTranslateX(128 * (i % 4));
            imgView.setTranslateY(128 * (i / 4));
            playField.getChildren().add(imgView);
        }

        setBackground(new Background(new BackgroundFill(Color.rgb(42, 45, 54), CornerRadii.EMPTY, Insets.EMPTY)));
        setMargin(playField, new Insets(10));
        positionInArea(playField,10,10,10,10,10, HPos.CENTER, VPos.CENTER);
        setPrefSize(1000, 1000);

        setTop(menuBar);
        setCenter(playField);
        setLeft(timer);
        setRight(score);

        ImageView firstTile = ((ImageView) playField.getChildren().get(0));

        //try {
        //    firstTile.setImage(new Image(new FileInputStream("resources\\tarkov.png")));
       // }catch (Exception ex){

        //}
    }

    public MenuItem getMenu1() {return menu1;}
    public MenuItem getMenu2() {return menu2;}
    public MenuItem getMenu3() {return menu3;}
}
