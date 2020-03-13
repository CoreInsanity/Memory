package view.ScoreboardScreen;

import helpers.Scoreboard;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import models.Game;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class ScoreboardScreenView extends BorderPane {
    private TableView<Game> table;
    private MenuBar menuBar;
    private MenuItem menu1;
    private MenuItem menu2;
    private Menu menu;
    private ImageView menulogo;

    public ScoreboardScreenView() {
        initNodes();
        initTable();
        layoutNodes();

    }

    private void initNodes() {
        table = new TableView<>();

        try {
            loadImgs();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Define all the Menu Items
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        menu1 = new MenuItem("Main menu");
        menu2 = new MenuItem("Exit");
    }

    private void loadImgs() throws FileNotFoundException {
        menulogo = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));
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

        menuBar.getMenus().add(menu);
        menu.getItems().addAll(menu1, menu2);
        menu.setGraphic(menulogo);

        menulogo.setFitWidth(25);
        menulogo.setFitHeight(25);

        setTop(menuBar);

    }

    public MenuItem getMenu1() {
        return menu1;
    }

    public MenuItem getMenu2() {
        return menu2;
    }
}
