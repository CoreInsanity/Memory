package view.MemoryScreen;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private Tile tile;
    private Stage stage;

    public MemoryScreenPresenter(MemoryScreenView memoryScreenView, Stage curStage) {
        stage = curStage;
        stage.setHeight(675);
        stage.setWidth(525);

        this.memoryScreenView = memoryScreenView;
    }
}
