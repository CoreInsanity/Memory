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

        setCursors();
        handleEvents();

        stage.setHeight(400);
        stage.setWidth(600);
    }
    private void handleEvents(){
        view.getBtnNewGame().setOnMouseClicked(b-> {
            var pcView = new PlayerCreationScreenView();
            new PlayerCreationScreenPresenter(pcView, stage);
            view.setCenter(pcView);
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
        view.getGitImg().setCursor(Cursor.HAND);
        view.getlTwitterImg().setCursor(Cursor.HAND);
        view.getmTwitterImg().setCursor(Cursor.HAND);
        view.getBtnNewGame().setCursor(Cursor.HAND);
        view.getBtnExit().setCursor(Cursor.HAND);
        view.getBtnScoreboard().setCursor(Cursor.HAND);
    }
}
