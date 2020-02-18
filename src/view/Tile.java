package view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import java.util.List;

public class Tile extends StackPane {
    private static Integer lastClickedHash;
    private static boolean secondClick;

    public Tile(Image img) throws FileNotFoundException {
        var topImg = new Image(new FileInputStream("src\\imgs\\top\\top.png"));

        var viewer = new ImageView(topImg);

        viewer.setX(128);
        viewer.setY(128);
        viewer.hoverProperty().addListener(m->getScene().setCursor(Cursor.HAND));

        viewer.setOnMouseClicked(c -> onClick(viewer, img, topImg));

        setAlignment(Pos.CENTER);
        getChildren().add(viewer);
    }

    private static void onClick(ImageView viewer, Image botImage, Image topImage){
        viewer.setImage(botImage);

        if(lastClickedHash == null){
            lastClickedHash = botImage.hashCode();
            secondClick = true;
        }
        else if(secondClick && lastClickedHash.equals(botImage.hashCode())) {
            System.out.println("You found a match cyka");
            lastClickedHash = null;
            secondClick = false;
        }
        else if (secondClick){
            System.out.println("njet blyat");

            viewer.setImage(topImage);
            secondClick = false;
            lastClickedHash = null;
        }
        else secondClick = true;

        lastClickedHash = botImage.hashCode();
    }
}
