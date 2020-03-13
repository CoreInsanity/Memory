package view.MemoryScreen;

import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
    private Image topSelImg;
    private BorderPane playField;
    private MenuBar menuBar;
    private MenuItem menu1;
    private MenuItem menu2;
    private MenuItem menu3;
    private MenuItem menu4;
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
            loadImgs();
        } catch (Exception ex) {
            Game.showPopup("Oopsie woopsie, sumting went vewwy vewwy wong", ex.getMessage(), Alert.AlertType.ERROR, false);
        }

        //Define all the Menu Items
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        menu1 = new MenuItem("Main menu");
        menu2 = new MenuItem("ScoreBoard");
        menu3 = new MenuItem("Exit");
        menu4 = new MenuItem("Restart");


        //Define the Timer label
        timer = new Label();

        //Define the playField pane
        playField = new BorderPane();
    }
    private void loadImgs() throws FileNotFoundException{
        topImg = new Image(new FileInputStream("resources\\top.png"));
        topSelImg = new Image(new FileInputStream("resources\\top_sel.png"));
        menulogo = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));
    }
    private void layoutNodes() {
        var timerBox = new VBox();
        timer.setTextFill(Color.YELLOW);
        timer.setPrefWidth(75);
        timer.setFont(new Font(20));
        timer.setMaxHeight(20);
        timer.setAlignment(Pos.CENTER);
        timerBox.getChildren().addAll(new Rectangle(0, 7), timer);

        menuBar.getMenus().add(menu);
        menu.getItems().addAll(menu1, menu4, menu2, menu3);
        menu.setGraphic(menulogo);

        menulogo.setFitWidth(25);
        menulogo.setFitHeight(25);

        for (int i = 0; i < 20; i++) {
            var imgView = new ImageView(topImg);
            imgView.setCursor(Cursor.HAND);
            imgView.setTranslateX(128 * (i % 4));
            imgView.setTranslateY(128 * (int) (i / 4));
            imgView.setStyle("-fx-border-color: Yellow;-fx-border-width: 5;");
            playField.getChildren().add(imgView);
        }

        setBackground(new Background(new BackgroundFill(Color.rgb(42, 45, 54), CornerRadii.EMPTY, Insets.EMPTY)));
        setMargin(playField, new Insets(10));
        positionInArea(playField,10,10,10,10,10, HPos.CENTER, VPos.CENTER);
        setPrefSize(1000, 1000);

        setTop(menuBar);
        setCenter(playField);
        setLeft(timerBox);
    }

    public MenuItem getMenu1() {return menu1;}
    public MenuItem getMenu2() {return menu2;}
    public MenuItem getMenu3() {return menu3;}
    public MenuItem getMenu4() {return menu4;}
    public BorderPane getPlayField() {return (BorderPane) getCenter();}
    public Label getTimer() {return timer;}
    public Image getTopImg(){return topImg;}
    public Image getTopSelImg(){return topSelImg;}
}
