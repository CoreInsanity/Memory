package view.MemoryScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class MemoryScreenView extends BorderPane {
    private ImageView topImg;

    private List<ImageView> tiles;
    private Image image;
    private StackPane playFieldStack;
    private MenuBar menuBar;
    private MenuItem menu1 = new MenuItem("Main menu");
    private MenuItem menu2 = new MenuItem("ScoreBoard");
    private MenuItem menu3 = new MenuItem("Exit");
    private Menu menu = new Menu("Menu");
    private Label timer;
    private Label score;



    public MemoryScreenView() {
        initNodes();
        layoutNodes();
    }
    private void initNodes(){
        try {
            topImg = new ImageView(new Image(new FileInputStream("resources\\top.png")));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        menu.getItems().addAll(menu1,menu2,menu3);
        timer = new Label("Timer: ");
        score = new Label("Score: ");
    }
    private void layoutNodes(){
        setTop(menuBar);

        setLeft(timer);
        timer.setTextFill(Color.YELLOW);
        timer.setPrefWidth(50);
        timer.setMaxHeight(20);


        setRight(score);
        score.setTextFill(Color.YELLOW);
        score.setPrefWidth(50);
        score.setMaxHeight(20);


    }
    public void initPlayfield(Pane pane){
        setBackground(new Background(new BackgroundFill(Color.rgb(42, 45, 54),CornerRadii.EMPTY,Insets.EMPTY)));
        setCenter(pane);
        setPrefSize(1000,1000);
    }

    List<ImageView> getTiles(){return tiles;}
    ImageView getTopImg() {return topImg;}

    public MenuItem getMenu1() {
        return menu1;
    }

    public MenuItem getMenu2() {
        return menu2;
    }

    public MenuItem getMenu3() {
        return menu3;
    }
}
