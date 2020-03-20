package view.ScoreboardScreen;

import helpers.Popup;
import helpers.Scoreboard;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import models.Game;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ScoreboardScreenView extends BorderPane {
    private TableView<Game> scoreboardTable;
    private ObservableList<Game> gameModels;
    private MenuBar menuBar;
    private MenuItem mainMenu;
    private MenuItem exit;
    private MenuItem delete;
    private Menu menu;
    private Menu scoreboard;
    private ImageView menulogo;

    public ScoreboardScreenView() {
        initNodes();
        initTable();
        layoutNodes();
    }

    private void initNodes() {
        scoreboardTable = new TableView<>();

        try {
            loadImgs();
            gameModels = FXCollections.observableList(Scoreboard.readScoreBoard("scoreboard.json"));
        } catch (Exception ex) {
            Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
            Platform.exit();
        }

        //Define all the Menu Items
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        scoreboard = new Menu("Scoreboard");
        mainMenu = new MenuItem("Main menu");
        exit = new MenuItem("Exit");
        delete = new MenuItem("Delete scoreboard");
    }

    private void loadImgs() throws FileNotFoundException {
        menulogo = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));
    }
    private void initTable(){
        scoreboardTable.setItems(gameModels);

        TableColumn colGames = new TableColumn("Games");

        TableColumn<Game, String> colName = new TableColumn<>("Name");
        TableColumn<Game, String> colAge = new TableColumn<>("Age");
        TableColumn<Game, String> colGameDate = new TableColumn<>("GameDate");
        TableColumn<Game, String> colGameTime = new TableColumn<>("GameTime");
        TableColumn<Game, String> colClicks = new TableColumn<>("Clicks");

        colName.setMinWidth(150);
        colAge.setMinWidth(100);
        colGameDate.setMinWidth(120);
        colGameTime.setMinWidth(100);
        colClicks.setMinWidth(100);

        colName.setCellValueFactory(new PropertyValueFactory<Game, String>("Name"));
        colAge.setCellValueFactory(new PropertyValueFactory<Game, String>("Age"));
        colGameDate.setCellValueFactory(new PropertyValueFactory<Game, String>("GameDate"));
        colGameTime.setCellValueFactory(new PropertyValueFactory<Game, String>("GameTime"));
        colClicks.setCellValueFactory(new PropertyValueFactory<Game, String>("ClickAmount"));

        colGames.getColumns().addAll(colGameDate, colName, colAge, colGameTime, colClicks);

        scoreboardTable.getColumns().add(colGames);
    }
    private void layoutNodes() {
        menulogo.setFitWidth(25);
        menulogo.setFitHeight(25);
        menuBar.getMenus().addAll(menu, scoreboard);
        menu.getItems().addAll(mainMenu, exit);
        menu.setGraphic(menulogo);

        scoreboard.getItems().addAll(delete);
        scoreboard.setGraphic(new Rectangle(0, 25));

        setCenter(scoreboardTable);
        setTop(menuBar);
    }

    public MenuItem getMainMenu() { return mainMenu; }
    public MenuItem getExit() { return exit; }
    public MenuItem getDelete() { return delete; }
}
