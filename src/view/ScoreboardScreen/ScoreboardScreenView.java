package view.ScoreboardScreen;

import helpers.Json;
import helpers.Scoreboard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.Game;

import javax.imageio.metadata.IIOMetadataNode;
import java.io.IOException;


public class ScoreboardScreenView extends BorderPane {
    private TextArea scores;
    private Scoreboard scoreboard;
    private Json json;
    private String yeet;

    public ScoreboardScreenView() {
        initNodes();
    }

    private void initNodes() {
        TableView tableView = new TableView();
        TableColumn<String, Game> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, Game> lastNameColumn = new TableColumn<>("Age");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

    private void layoutNodes() {


//        try {
//            for (var game:scoreboard.readScoreBoard("scoreboard.json")
//            ) {
//                yeet = new String(game.getPlayer().getName() + " " + game.getPlayer().getAge() + " " + game.getGameTime() + " " + game.getClickAmount());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
////        }
//        setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//    }
    }
}
