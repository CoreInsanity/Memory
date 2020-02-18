import javafx.application.Application;
import javafx.stage.Stage;
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
        PlayerCreationScreenView.showScene(stage); //Display the profile generator
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}