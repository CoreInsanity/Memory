package view.ScoreboardScreen;

import helpers.Scoreboard;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ScoreboardScreenView extends GridPane {
    private Label scores;

    public ScoreboardScreenView() {
        initNodes();
        layoutNodes();
    }

    private void initNodes(){
        scores = new Label();
    }

    private void layoutNodes() {
    }
}
