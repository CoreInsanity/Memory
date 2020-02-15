package view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import java.util.List;

public class Tile extends StackPane {
    public Tile(Image img) throws FileNotFoundException {
        var viewer = new ImageView(new Image(new FileInputStream("src\\imgs\\top\\top.png")));

        viewer.setX(128);
        viewer.setY(128);
        viewer.hoverProperty().addListener(m->getScene().setCursor(Cursor.HAND));
        viewer.setOnMouseClicked(c -> viewer.setImage(img));

        setAlignment(Pos.CENTER);
        getChildren().add(viewer);
    }

    public static Parent vul() throws FileNotFoundException{
        Pane panel = new Pane();

        List<Tile> tiles = new ArrayList<>();
        for (var file: new File("src\\imgs\\bottom").listFiles()) {
            tiles.add(new Tile(new Image(new FileInputStream(file.getAbsolutePath()))));
            tiles.add(new Tile(new Image(new FileInputStream(file.getAbsolutePath()))));
        }

        Collections.shuffle(tiles);

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(128 * (i % 4));
            tile.setTranslateY(128 * (i / 4));
            panel.getChildren().add(tile);
        }

        return panel;
    }
}
