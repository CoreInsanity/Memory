import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.Memory;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.PlayerCreationScreen.PlayerCreationScreenPresenter;
import view.PlayerCreationScreen.PlayerCreationScreenView;

/**
 * Maxim Van den Eede
 * 13/12/2019.
         */
public class Menu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("Tarkov Memory");
        var view = new MainMenuScreenView();
        var presenter = new MainMenuScreenPresenter(view, stage);

        stage.setHeight(400);
        stage.setWidth(400);
        stage.setScene(new Scene(view));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}