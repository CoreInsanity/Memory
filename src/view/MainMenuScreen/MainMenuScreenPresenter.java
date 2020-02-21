package view.MainMenuScreen;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.stage.StageStyle;
import view.PlayerCreationScreen.PlayerCreationScreenPresenter;
import view.PlayerCreationScreen.PlayerCreationScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

/**
 * Maxim Van den Eede
 * 19/02/2020.
 */
public class MainMenuScreenPresenter {
    private MainMenuScreenView view;
    private Stage stage;

    public MainMenuScreenPresenter(MainMenuScreenView mmView, Stage curStage) {
        Platform.setImplicitExit(true);
        view = mmView;
        stage = curStage;

        stage.initStyle(StageStyle.UNDECORATED);

        setCursors();
        handleEvents();

        stage.setHeight(400);
        stage.setWidth(600);
    }
    private void handleEvents(){
        view.getBtnNewGame().setOnMouseClicked(b-> {
            var pcView = new PlayerCreationScreenView();
            new PlayerCreationScreenPresenter(pcView, stage);
            stage.setScene(new Scene(pcView));
        });

        view.getBtnExit().setOnMouseClicked(b -> Platform.exit());

        view.getBtnScoreboard().setOnMouseClicked(b -> {
            var sbView = new ScoreboardScreenView();
            new ScoreboardScreenPresenter(sbView, stage);
            stage.setScene(new Scene(sbView));
        });

        view.getGitImg().setOnMouseClicked(b -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/CoreInsanity/Memory"));
            } catch (Exception ex) {}
        });
        view.getmTwitterImg().setOnMouseClicked(b -> {
            try {
                Desktop.getDesktop().browse(new URI("https://twitter.com/maximvdeede"));
            } catch (Exception ex) {}
        });
        view.getlTwitterImg().setOnMouseClicked(b -> {
            try {
                Desktop.getDesktop().browse(new URI("https://twitter.com/larskedl"));
            } catch (Exception ex) {}
        });
    }
    private void setCursors(){
        view.getGitImg().setOnMouseEntered(m -> stage.getScene().setCursor(Cursor.HAND));
        view.getGitImg().setOnMouseExited(m -> stage.getScene().setCursor(Cursor.DEFAULT));

        view.getmTwitterImg().setOnMouseEntered(m -> stage.getScene().setCursor(Cursor.HAND));
        view.getmTwitterImg().setOnMouseExited(m -> stage.getScene().setCursor(Cursor.DEFAULT));

        view.getlTwitterImg().setOnMouseEntered(m -> stage.getScene().setCursor(Cursor.HAND));
        view.getlTwitterImg().setOnMouseExited(m -> stage.getScene().setCursor(Cursor.DEFAULT));

        view.getBtnNewGame().setOnMouseEntered(m -> stage.getScene().setCursor(Cursor.HAND));
        view.getBtnNewGame().setOnMouseExited(m -> stage.getScene().setCursor(Cursor.DEFAULT));

        view.getBtnScoreboard().setOnMouseEntered(m -> stage.getScene().setCursor(Cursor.HAND));
        view.getBtnScoreboard().setOnMouseExited(m -> stage.getScene().setCursor(Cursor.DEFAULT));

        view.getBtnExit().setOnMouseEntered(m -> stage.getScene().setCursor(Cursor.HAND));
        view.getBtnExit().setOnMouseExited(m -> stage.getScene().setCursor(Cursor.DEFAULT));
    }
}
