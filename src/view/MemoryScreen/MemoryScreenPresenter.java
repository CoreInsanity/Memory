package view.MemoryScreen;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Tile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EventListener;

/**
 * Lars De Loenen
 * 19/02/2020.
 */
public class MemoryScreenPresenter {
    private MemoryScreenView memoryScreenView;

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView) {
        this.memoryScreenView = memoryScreenView;
        addEventHandlers();
    }

    private void addEventHandlers() {
        for (var tile : memoryScreenView.getTiles()) {
            tile.hoverProperty().addListener(m -> tile.getScene().setCursor(Cursor.HAND));
            tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Tile.onClick(tile, memoryScreenView.getbotImage(), tile.getImage());

                }
            });

        }
    }
}
