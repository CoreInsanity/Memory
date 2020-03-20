package view.MemoryScreen;

import helpers.Popup;
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
    private MenuItem mainMenu;
    private MenuItem restart;
    private MenuItem scoreboard;
    private MenuItem exit;
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
            Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
            Platform.exit();
        }

        //Define all the Menu Items
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        mainMenu = new MenuItem("Main menu");
        restart = new MenuItem("Restart");
        scoreboard = new MenuItem("Scoreboard");
        exit = new MenuItem("Exit");

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
        menu.getItems().addAll(mainMenu, restart, scoreboard, exit);
        menu.setGraphic(menulogo);

        menulogo.setFitWidth(25);
        menulogo.setFitHeight(25);

        for (int i = 0; i < 20; i++) {
            var imgView = new ImageView(topImg);
            imgView.setCursor(Cursor.HAND);
            imgView.setTranslateX(135 * (i % 4));
            imgView.setTranslateY(135 * (int) (i / 4));
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

    public MenuItem getMainMenu() {return mainMenu;}
    public MenuItem getRestart() {return restart;}
    public MenuItem getScoreboard() {return scoreboard;}
    public MenuItem getExit() {return exit;}
    public BorderPane getPlayField() {return (BorderPane) getCenter();}
    public Label getTimer() {return timer;}
    public Image getTopImg(){return topImg;}
    public Image getTopSelImg(){return topSelImg;}
}
