package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tile extends StackPane {
    Text text = new Text();
    private static final int NUM_PER_ROW = 4;
    private static final int HOER = 8;


    public Tile(String waarde) {
        Rectangle vakje = new Rectangle(75, 75);
        vakje.setFill(null);
        vakje.setStroke(Color.BLACK);

        text.setText(waarde);
        text.setFont(Font.font(30));

        setAlignment(Pos.CENTER);
        getChildren().addAll(vakje, text);
    }

    public static Parent vul() {
        Pane paneel = new Pane();
        char c = 'A';
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < HOER; i++) {
            tiles.add(new Tile(String.valueOf(c)));
            tiles.add(new Tile(String.valueOf(c)));
            c++;
        }

        Collections.shuffle(tiles);

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(75 * (i % NUM_PER_ROW));
            tile.setTranslateY(75 * (i / NUM_PER_ROW));
            paneel.getChildren().add(tile);
        }
        return paneel;
    }
}
