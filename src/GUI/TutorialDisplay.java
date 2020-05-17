package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class TutorialDisplay {
    public TutorialDisplay(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(" instructions for playing caro games");
        alert.setHeaderText(null);
        alert.setContentText("How to Play Caro Playing Caro is pretty straightforward." +
                "\nIt is played using the X and O symbol. The player representing the “X” symbol " +
                "\ngets to take the first turn followed by the player representing the “O” symbol. " +
                "\nPlayers take turns placing their symbol on an open intersection on the board." +
                "\nIt is very much a game of strategy, as players have to race to create an unbroken " +
                "\nrow of five symbols while blocking their opponent to prevent them from creating five in a row. " +
                "\nThe player that manages to create five in a row first wins the round." +
                "\nThe row can be horizontal, vertical, or diagonal as long as it is unbroken." +
                "\nEach turn plays for 30 seconds");
        alert.show();
    }
}
