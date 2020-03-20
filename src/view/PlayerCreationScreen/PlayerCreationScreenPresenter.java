package view.PlayerCreationScreen;

import helpers.Popup;
import helpers.Scene;
import javafx.application.Platform;
import javafx.scene.control.Alert;
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
        view.getBackBtn().setOnAction(b -> Scene.showMainMenu(stage));
    }
    private void startGame(){
        if(!fieldsCheck()){
            Popup.showPopup("Incorrect data", "Please make sure all fields are correct", Alert.AlertType.WARNING);
            return;
        }
        try {
            var game = new Game(view.getName().getText(), Integer.parseInt(view.getAge().getText()));
            helpers.Scene.showMemory(stage, game);
        }catch (Exception ex){
            Popup.showPopup("Something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
            Platform.exit();
        }
    }
    private boolean fieldsCheck(){
        try{
            Integer.parseInt(view.getAge().getText());
        }catch (Exception ex){
            return false;
        }

        var name = view.getName().getText();
        var age = Integer.parseInt(view.getAge().getText());

        if(name == null || name.trim().isEmpty()) return false; //Check if name is null or whitespace(s)
        if(age == 0 || age > 99) return false; //Sorry, old people

        return true;
    }
}
