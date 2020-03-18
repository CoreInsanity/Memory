package helpers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.ArrayList;
import java.util.Optional;

public class Popup {
    public static Optional<ButtonType> showPopup(String title, String text, Alert.AlertType type, ArrayList<ButtonType>... buttons){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.getButtonTypes().clear();
        if(buttons.length > 0) alert.getButtonTypes().addAll(buttons[0]);
        else alert.getButtonTypes().add(new ButtonType("Ok"));
        return alert.showAndWait();
    }
}
