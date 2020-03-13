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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import models.Player;

import javax.imageio.metadata.IIOMetadataNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScoreboardScreenView extends BorderPane {
    private TableView<Game> table;

    public ScoreboardScreenView() {
        initNodes();
        initTable();
        layoutNodes();
    }

    private void initNodes() {
        table = new TableView<>();
    }
    private void initTable(){
        List<Game> gameModels = new ArrayList<>();
        try {
            gameModels = Scoreboard.readScoreBoard("scoreboard.json");
        }catch (Exception ex)
        {}

        ObservableList<Game> games = FXCollections.observableList(gameModels);
        table.setItems(games);

        TableColumn colGames = new TableColumn("Games");

        TableColumn<Game, String> colName = new TableColumn<>("Name");
        TableColumn<Game, String> colAge = new TableColumn<>("Age");
        TableColumn<Game, String> colGameTime = new TableColumn<>("GameTime");
        TableColumn<Game, String> colClicks = new TableColumn<>("Clicks");

        colName.setCellValueFactory(new PropertyValueFactory<Game, String>("Name"));
        colAge.setCellValueFactory(new PropertyValueFactory<Game, String>("Age"));
        colGameTime.setCellValueFactory(new PropertyValueFactory<Game, String>("GameTime"));
        colClicks.setCellValueFactory(new PropertyValueFactory<Game, String>("ClickAmount"));

        colGames.getColumns().addAll(colName, colAge, colGameTime, colClicks);

        table.getColumns().add(colGames);
    }
    private void layoutNodes() {
        setCenter(table);
    }
}
