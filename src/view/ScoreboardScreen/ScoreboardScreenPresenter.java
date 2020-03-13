package view.ScoreboardScreen;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;

/**
 * Maxim Van den Eede
 * 19/02/2020.
 */
public class ScoreboardScreenPresenter {
    private ScoreboardScreenView scoreboardScreenView;
    private Stage stage;
    public ScoreboardScreenPresenter(ScoreboardScreenView view, Stage stage){
        this.scoreboardScreenView =view;
        this.stage = stage;
        addEventHandlers();

    }
    private void addEventHandlers(){
        scoreboardScreenView.getMenu1().setOnAction(b -> {
            var mmView = new MainMenuScreenView();
            new MainMenuScreenPresenter(mmView, stage);
            stage.setScene(new Scene(mmView));
        });
        scoreboardScreenView.getMenu2().setOnAction(b -> {
            Platform.exit();
        });
    }
}
