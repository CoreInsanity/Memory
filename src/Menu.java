import helpers.Popup;
import helpers.Scene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Game;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;

import java.io.IOException;

/**
 * Maxim Van den Eede
 * 13/12/2019.
         */
public class Menu extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.setResizable(false);
            stage.setTitle("Tarkov Memory");
            stage.initStyle(StageStyle.UNDECORATED);
            Scene.showMainMenu(stage);
            stage.setResizable(true);
            stage.show();
        }catch (Exception ex){
            Popup.showPopup("What have you done?", "This shouldn't happen, please report this issue to the devs\n\nError: "+ex.getMessage()+"\n\nStacktrace: "+ex.getStackTrace().toString(), Alert.AlertType.ERROR);
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}