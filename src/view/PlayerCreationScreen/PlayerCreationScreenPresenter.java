package view.PlayerCreationScreen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import scenes.Memory;
import view.MemoryScreen.MemoryScreenView;

import java.io.FileNotFoundException;

public class PlayerCreationScreenPresenter {
    private PlayerCreationScreenView view;
    private String name;
    private int age;

    public PlayerCreationScreenPresenter(PlayerCreationScreenView plView){
        view = plView;
        view.getPlayBtn().setOnAction(b -> {
            name = view.getName().getText();
            age = Integer.parseInt(view.getAge().getText());
            System.out.println(name + age);
//            stage.setScene(new Scene(MemoryScreenView));
        });
    }
}
