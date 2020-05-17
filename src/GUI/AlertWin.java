package GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class AlertWin {
    public AlertWin(Label lbName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notify the winner");
        alert.setHeaderText(null);
        alert.setContentText(lbName.getText() + " win");
        ButtonType buttonCancel = new ButtonType("Cancel", CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonCancel);
        alert.showAndWait();
    }

}
