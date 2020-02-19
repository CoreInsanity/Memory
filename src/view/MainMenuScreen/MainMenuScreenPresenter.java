package view.MainMenuScreen;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.PlayerCreationScreen.PlayerCreationScreenPresenter;
import view.PlayerCreationScreen.PlayerCreationScreenView;
import view.ScoreboardScreen.ScoreboardScreenPresenter;
import view.ScoreboardScreen.ScoreboardScreenView;

/**
 * Maxim Van den Eede
 * 19/02/2020.
 */
public class MainMenuScreenPresenter {
    public MainMenuScreenPresenter(MainMenuScreenView view, Stage stage) {
        Platform.setImplicitExit(true);

        view.getBtnNewGame().setOnMouseClicked(b-> {
            var pcView = new PlayerCreationScreenView();
            new PlayerCreationScreenPresenter(pcView);
            stage.setScene(new Scene(pcView));
        });
        view.getBtnExit().setOnMouseClicked(b -> Platform.exit());
        view.getBtnScoreboard().setOnMouseClicked(b -> {
            var sbView = new ScoreboardScreenView();
            new ScoreboardScreenPresenter(sbView, stage);
            stage.setScene(new Scene(sbView));
        });
    }
}
