package GUI;

import javafx.scene.control.Alert;

public class AlertCheckName {
    public AlertCheckName() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(" Retype the player name ");
        alert.setHeaderText(null);
        alert.show();
    }
}
