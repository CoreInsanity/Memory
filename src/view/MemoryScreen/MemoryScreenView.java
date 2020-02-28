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
    private Image checkmark;
    private Pane playField;
    private MenuBar menuBar;
    private MenuItem menu1;
    private MenuItem menu2;
    private MenuItem menu3;
    private Menu menu;
    private Label timer;
    private Label score;
    private ImageView menulogo;
    private List<ImageView> checkmarkview = new ArrayList<>();


    public MemoryScreenView() {
        initNodes();
        layoutNodes();
    }

    private void initNodes() {
        try {
            loadImgs();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Platform.exit();
        }

        //Define all the Menu Items
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        menu1 = new MenuItem("Main menu");
        menu2 = new MenuItem("ScoreBoard");
        menu3 = new MenuItem("Exit");
        menuBar.getMenus().add(menu);
        menu.getItems().addAll(menu1, menu2, menu3);
        menu.setGraphic(menulogo);

        //Define the Timer and the Score labels
        timer = new Label("Timer: ");
        score = new Label("Score: ");

        //Define the playField pane
        playField = new Pane();
    }

    private void loadImgs() throws FileNotFoundException {
        topImg = new Image(new FileInputStream("resources\\top.png"));
        checkmark = new Image(new FileInputStream("resources\\checkmark.png"));
        menulogo = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));

    }

    private void layoutNodes() {
        timer.setTextFill(Color.YELLOW);
        timer.setPrefWidth(50);
        timer.setMaxHeight(20);
        timer.setAlignment(Pos.CENTER_RIGHT);

        score.setTextFill(Color.YELLOW);
        score.setPrefWidth(50);
        score.setMaxHeight(20);
        score.setAlignment(Pos.CENTER_LEFT);

        menulogo.setFitWidth(25);
        menulogo.setFitHeight(25);

        for (int i = 0; i <20; i++) {
            var imgView = new ImageView(topImg);
            imgView.setCursor(Cursor.HAND);
            imgView.setTranslateX(128 * (i % 4));
            imgView.setTranslateY(128 * (int) (i / 4));
            playField.getChildren().add(imgView);
        }
        for (int i = 0; i < 20; i++) {
            var check = new ImageView(checkmark);
            check.setVisible(false);
            checkmarkview.add(check);
            check.setFitWidth(64);
            check.setFitHeight(64);
            check.setTranslateX(128 * (i % 4));
            check.setTranslateY(128 * (int) (i / 4));
            playField.getChildren().add(check);
        }


        setBackground(new Background(new BackgroundFill(Color.rgb(42, 45, 54), CornerRadii.EMPTY, Insets.EMPTY)));
        setMargin(playField, new Insets(10));
        positionInArea(playField, 10, 10, 10, 10, 10, HPos.CENTER, VPos.CENTER);
        setPrefSize(1000, 1000);

        setTop(menuBar);
        setCenter(playField);
        setLeft(timer);
        setRight(score);
    }

    public MenuItem getMenu1() {
        return menu1;
    }

    public MenuItem getMenu2() {
        return menu2;
    }

    public MenuItem getMenu3() {
        return menu3;
    }

    public Pane getPlayField() {
        return (Pane) getCenter();
    }

    public List<ImageView> getCheckmarkview() {
        return checkmarkview;
    }
}
