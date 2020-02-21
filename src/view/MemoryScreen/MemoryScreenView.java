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
        playfield();
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
    private void playfield(){
        tiles = new ArrayList<ImageView>();

        for (var file : new File("resources\\bottom").listFiles()) {
            try {
                image = new Image(new FileInputStream(file.getAbsolutePath()));//ONLY CREATE ONE INSTANCE OF THE IMAGE! Otherwise we get different hashcodes
                tiles.add(new ImageView(image));
                tiles.add(new ImageView(image));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        Collections.shuffle(tiles);

        Pane playField = new Pane();
        for (int i = 0; i < tiles.size(); i++) {
            ImageView tile = tiles.get(i);
            tile.setTranslateX(128 * (i % 4));
            tile.setTranslateY(128 * (i / 4));
            playField.getChildren().add(tile);
        }
        setCenter(playField);
        setPrefSize(1000,1000);
    }

    List<ImageView> getTiles(){return tiles;}
    ImageView getTopImg() {return topImg;}
}
