package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Lars De Loenen
 * 19/02/2020.
 */
public class Tile {
    private static Integer lastClickedHash;
    private static boolean secondClick;

    public static void onClick(ImageView viewer, Image botImage, Image topImage) {
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
