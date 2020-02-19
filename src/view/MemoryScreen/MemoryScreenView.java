package view.MemoryScreen;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import java.util.List;

public class MemoryScreenView extends StackPane {
    private static Integer lastClickedHash;
    private static boolean secondClick;

    public MemoryScreenView(Stage window, Game game) {
        configureWindow(window);

        List<MemoryScreenView> tiles = new ArrayList<>();
        for (var file : new File("src\\imgs\\bottom").listFiles()) {
            try {
                var img = new Image(new FileInputStream(file.getAbsolutePath())); //ONLY CREATE ONE INSTANCE OF THE IMAGE! Otherwise we get different hashcodes
                //tiles.add(new MemoryScreenView(img));
                //tiles.add(new MemoryScreenView(img));
            } catch (Exception ex) {
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

    private static void configureWindow(Stage window) {
        //Configure the window here

        window.setHeight(675);
        window.setWidth(525);
    }

    private void createImg(Image img) throws FileNotFoundException {
        var topImg = new Image(new FileInputStream("src\\imgs\\top\\top.png"));

        var viewer = new ImageView(topImg);

        viewer.setX(128);
        viewer.setY(128);
        viewer.hoverProperty().addListener(m -> getScene().setCursor(Cursor.HAND));

        viewer.setOnMouseClicked(c -> onClick(viewer, img, topImg));

        setAlignment(Pos.CENTER);
        getChildren().add(viewer);
    }

    private static void onClick(ImageView viewer, Image botImage, Image topImage) {
        viewer.setImage(botImage);

        if (lastClickedHash == null) {
            lastClickedHash = botImage.hashCode();
            secondClick = true;
        } else if (secondClick && lastClickedHash.equals(botImage.hashCode())) {
            System.out.println("You found a match cyka");
            lastClickedHash = null;
            secondClick = false;
        } else if (secondClick) {
            System.out.println("njet blyat");

            viewer.setImage(topImage);
            secondClick = false;
            lastClickedHash = null;
        } else secondClick = true;

        lastClickedHash = botImage.hashCode();
    }
}
