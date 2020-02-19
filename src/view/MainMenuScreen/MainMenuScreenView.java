package view.MainMenuScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

/**
 * Maxim Van den Eede
 * 19/02/2020.
 */
public class MainMenuScreenView extends VBox {
    private ImageView tarkovLogo;
    private Button btnNewGame;
    private Button btnScoreboard;
    private Button btnExit;

    public MainMenuScreenView(){
        initNodes();
        layoutNodes();
    }
    private void initNodes(){
        try {
            tarkovLogo = new ImageView(new Image(new FileInputStream("resources\\tarkov.png")));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        btnNewGame = new Button("New Game");
        btnScoreboard = new Button("Scoreboard");
        btnExit = new Button("Exit");

        getChildren().addAll(tarkovLogo, btnNewGame, btnScoreboard, btnExit);
    }
    private void layoutNodes(){
        tarkovLogo.setFitWidth(364);
        tarkovLogo.setFitHeight(178);

        btnNewGame.setPrefWidth(100);
        btnScoreboard.setPrefWidth(100);
        btnNewGame.setPrefHeight(40);
        btnScoreboard.setPrefHeight(40);
        btnExit.setPrefWidth(100);

        setSpacing(20);
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    Button getBtnNewGame(){return btnNewGame;}
    Button getBtnScoreboard(){return btnScoreboard;}
    Button getBtnExit(){return  btnExit;}
}
