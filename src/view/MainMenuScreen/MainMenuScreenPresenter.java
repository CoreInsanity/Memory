package view.MainMenuScreen;

import helpers.Scene;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;
import view.PlayerCreationScreen.PlayerCreationScreenPresenter;
import view.PlayerCreationScreen.PlayerCreationScreenView;


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

        handleEvents();
    }
    private void handleEvents(){
        view.getBtnNewGame().setOnMouseClicked(b-> startNewGame());
        view.getBtnNewGame().setOnKeyPressed(k -> {if (k.getCode() == KeyCode.ENTER) startNewGame();});

        view.getBtnExit().setOnMouseClicked(b -> Platform.exit());
        view.getBtnExit().setOnKeyPressed(k -> {if(k.getCode() == KeyCode.ENTER) Platform.exit();});

        view.getBtnScoreboard().setOnMouseClicked(b -> Scene.showScoreboard(stage));
        view.getBtnScoreboard().setOnKeyPressed(k -> {if(k.getCode() == KeyCode.ENTER) Scene.showScoreboard(stage);});

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

        view.setOnMouseDragged(m -> {
            stage.setX(m.getScreenX() - 0);
            stage.setY(m.getScreenY() - 0);
        });

    }
    private void startNewGame(){
        var pcView = new PlayerCreationScreenView();
        new PlayerCreationScreenPresenter(pcView, stage);
        view.setCenter(pcView);
    }
}
