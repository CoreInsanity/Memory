package view.ScoreboardScreen;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;

/**
 * Maxim Van den Eede
 * 19/02/2020.
 */
public class ScoreboardScreenPresenter {
    public ScoreboardScreenPresenter(ScoreboardScreenView view, Stage stage){

        Platform.setImplicitExit(false);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                var maView = new MainMenuScreenView();
                new MainMenuScreenPresenter(maView, stage);
                stage.setScene(new Scene(maView));
                event.consume();
            }
        });


    }
}
