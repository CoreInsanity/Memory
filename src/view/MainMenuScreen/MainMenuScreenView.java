
package view.MainMenuScreen;

import helpers.Popup;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Maxim Van den Eede
 * 19/02/2020.
 */
public class MainMenuScreenView extends BorderPane {
    private Button btnNewGame;
    private Button btnScoreboard;
    private Button btnExit;
    private VBox menuBox;
    private VBox leftBox;
    private HBox webLinks;
    private ImageView tarkovImg;
    private ImageView tarkovTitle;
    private ImageView mTwitterImg;
    private ImageView lTwitterImg;
    private ImageView gitImg;
    private BorderPane topPane;
    private BorderPane botPane;
    private Font font;

    public MainMenuScreenView() {
        initNodes();
        layoutNodes();
        initTooltips();
        setCursors();
    }

    private void initNodes() {
        font = new Font("Segoe UI", 13);
        menuBox = new VBox();
        leftBox = new VBox();
        webLinks = new HBox();
        btnNewGame = new Button("New Game");
        btnScoreboard = new Button("Scoreboard");
        btnExit = new Button("Exit");
        topPane = new BorderPane();
        botPane = new BorderPane();

        try {
            loadImgs();
        } catch (Exception ex) {
            Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
            Platform.exit();
        }

        webLinks.getChildren().addAll(gitImg, new Rectangle(5, 0), lTwitterImg, new Rectangle(5, 0), mTwitterImg);
        topPane.setBottom(tarkovImg);
        botPane.setBottom(webLinks);
        leftBox.getChildren().addAll(topPane, botPane);
        menuBox.getChildren().addAll(tarkovTitle, new Rectangle(0, 20), btnNewGame, btnScoreboard, btnExit);
    }
    private void loadImgs() throws FileNotFoundException{
        tarkovImg = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));
        tarkovTitle = new ImageView(new Image(new FileInputStream("resources\\tarkov_title.png")));
        gitImg = new ImageView(new Image(new FileInputStream("resources\\github.png")));
        mTwitterImg = new ImageView(new Image(new FileInputStream("resources\\twitter.png")));
        lTwitterImg = new ImageView(new Image(new FileInputStream("resources\\twitter.png")));
    }

    private void layoutNodes() {
        btnNewGame.setFont(font);
        btnScoreboard.setFont(font);
        btnExit.setFont(font);

        btnNewGame.setPrefWidth(100);
        btnScoreboard.setPrefWidth(100);
        btnNewGame.setPrefHeight(40);
        btnScoreboard.setPrefHeight(40);
        btnExit.setPrefWidth(100);

        tarkovImg.setFitWidth(100);
        tarkovImg.setFitHeight(70);
        tarkovImg.setOpacity(0.85);

        tarkovTitle.setFitWidth(500);
        tarkovTitle.setFitHeight(50);
        tarkovTitle.setOpacity(0.85);

        gitImg.setFitWidth(25);
        gitImg.setFitHeight(25);
        mTwitterImg.setFitWidth(25);
        mTwitterImg.setFitHeight(25);
        lTwitterImg.setFitWidth(25);
        lTwitterImg.setFitHeight(25);

        botPane.setBackground(new Background(new BackgroundFill(Color.rgb(49, 53, 64), CornerRadii.EMPTY, Insets.EMPTY)));
        botPane.setPadding(new Insets(0, 0, 10, 7));
        botPane.setPrefWidth(100);
        botPane.setPrefHeight(325);

        topPane.setBackground(new Background(new BackgroundFill(Color.rgb(197, 178, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        topPane.setPrefWidth(100);
        topPane.setPrefHeight(75);

        menuBox.setSpacing(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setBackground(new Background(new BackgroundFill(Color.rgb(42, 45, 54), CornerRadii.EMPTY, Insets.EMPTY)));

        setLeft(leftBox);
        setCenter(menuBox);
    }

    private void initTooltips() {
        Tooltip.install(gitImg, new Tooltip("Open the Github page for this project"));
        Tooltip.install(lTwitterImg, new Tooltip("Open the twitter page of L. De Loenen"));
        Tooltip.install(mTwitterImg, new Tooltip("Open the twitter page of M. Van den Eede"));
        Tooltip.install(gitImg, new Tooltip("Open the Github page for this project"));
        Tooltip.install(btnNewGame, new Tooltip("Open the profile creator and start a game"));
        Tooltip.install(btnScoreboard, new Tooltip("Show the scoreboard"));
        Tooltip.install(btnExit, new Tooltip("Close this application"));
    }
    private void setCursors(){
        gitImg.setCursor(Cursor.HAND);
        lTwitterImg.setCursor(Cursor.HAND);
        mTwitterImg.setCursor(Cursor.HAND);
        btnNewGame.setCursor(Cursor.HAND);
        btnExit.setCursor(Cursor.HAND);
        btnScoreboard.setCursor(Cursor.HAND);
    }

    Button getBtnNewGame() {
        return btnNewGame;
    }
    Button getBtnScoreboard() {
        return btnScoreboard;
    }
    Button getBtnExit() {
        return btnExit;
    }
    ImageView getGitImg() {
        return gitImg;
    }
    ImageView getmTwitterImg() {
        return mTwitterImg;
    }
    ImageView getlTwitterImg() {
        return lTwitterImg;
    }
    public VBox getMenuBox() { return menuBox; }
}
