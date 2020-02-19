package scenes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;
import view.MemoryScreen.MemoryScreenView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Memory {
    public static void showScene(Stage window, Game game){
        configureWindow(window);

        List<MemoryScreenView> tiles = new ArrayList<>();
        for (var file: new File("src\\imgs\\bottom").listFiles()) {
            try {
                var img = new Image(new FileInputStream(file.getAbsolutePath())); //ONLY CREATE ONE INSTANCE OF THE IMAGE! Otherwise we get different hashcodes
                //tiles.add(new MemoryScreenView(img));
                //tiles.add(new MemoryScreenView(img));
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        Collections.shuffle(tiles);

        Pane playField = new Pane();
        for (int i = 0; i < tiles.size(); i++) {
            MemoryScreenView tile = tiles.get(i);
            tile.setTranslateX(128 * (i % 4));
            tile.setTranslateY(128 * (i / 4));
            playField.getChildren().add(tile);
        }
        window.setScene(new Scene(playField));
    }
    private static void configureWindow(Stage window){
        //Configure the window here

        window.setHeight(675);
        window.setWidth(525);
    }
}
