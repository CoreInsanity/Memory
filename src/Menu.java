import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;

/**
 * Maxim Van den Eede
 * 13/12/2019.
         */
public class Menu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("Tarkov Memory");

        stage.initStyle(StageStyle.UNDECORATED);

        var view = new MainMenuScreenView();
        new MainMenuScreenPresenter(view, stage);

        stage.setScene(new Scene(view));
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}