package view.PlayerCreationScreen;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import models.Game;
import view.MainMenuScreen.MainMenuScreenPresenter;
import view.MainMenuScreen.MainMenuScreenView;
import view.MemoryScreen.MemoryScreenPresenter;
import view.MemoryScreen.MemoryScreenView;

public class PlayerCreationScreenPresenter {
    private PlayerCreationScreenView view;
    private Stage stage;
    private String name;
    private int age;
    private MainMenuScreenView mainMenuScreenView;

    public PlayerCreationScreenPresenter(PlayerCreationScreenView plView, Stage curstage){
        view = plView;
        stage = curstage;

        addEventhandlers();
    }
    private void addEventhandlers(){
        view.getPlayBtn().setOnAction(b -> startGame());
        view.getAge().setOnKeyPressed(k -> {if(k.getCode() == KeyCode.ENTER) startGame();});

        view.getBackBtn().setOnAction(b -> {
            var mmView = new MainMenuScreenView();
            new MainMenuScreenPresenter(mmView, stage);
            stage.setScene(new Scene(mmView));
        });
    }
    private void startGame(){
        if(!fieldsCheck()) return;
        var game = new Game(view.getName().getText(), Integer.parseInt(view.getAge().getText()));
        var viewer = new MemoryScreenView();
        new MemoryScreenPresenter(viewer, stage, game);
        stage.setScene(new Scene(viewer));
    }
    private boolean fieldsCheck(){
        var name = view.getName().getText();
        var age = Integer.parseInt(view.getAge().getText());

        if(name == null || name.trim().isEmpty()) return false; //Check if name is null or whitespace(s)
        if(age == 0 || age > 99) return false; //Sorry, old people

        return true;
    }
}
