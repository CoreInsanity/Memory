package view.ScoreboardScreen;

import helpers.Popup;
import helpers.Scene;
import helpers.Scoreboard;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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
        scoreboardScreenView.getMainMenu().setOnAction(b -> Scene.showMainMenu(stage));
        scoreboardScreenView.getExit().setOnAction(b -> {
            Platform.exit();
        });
        scoreboardScreenView.getDelete().setOnAction(b -> {
            var popup = Popup.showPopup("Are you sure?", "You are about to delete your savefile, are you sure?", Alert.AlertType.WARNING);
            if(popup.isPresent() && popup.get().getText() == "Ok"){
                if(Scoreboard.clearScoreBoard("scoreboard.json")) Scene.showMainMenu(stage);
                else Popup.showPopup("¯\\_(ツ)_/¯", "Something went wrong while deleting your savefile\nPlease make sure the file is accessible", Alert.AlertType.ERROR);
            }
        });
    }
}
