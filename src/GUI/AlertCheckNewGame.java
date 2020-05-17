package GUI;

import javafx.scene.control.Alert;

public class AlertCheckNewGame {
    public AlertCheckNewGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You are in the game");
        alert.showAndWait();
    }
}
