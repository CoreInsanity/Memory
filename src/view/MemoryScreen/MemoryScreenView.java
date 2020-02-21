package view.MemoryScreen;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Game;

import javax.print.DocFlavor;
import java.awt.*;
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
    }
    private void layoutNodes(){
    }
    public void initPlayfield(Pane pane){
        setCenter(pane);
        setPrefSize(1000,1000);
    }

    List<ImageView> getTiles(){return tiles;}
    ImageView getTopImg() {return topImg;}
}
