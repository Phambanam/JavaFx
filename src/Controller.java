import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;




public class Controller {
    @FXML
    private TextField Name1,Name2;
    @FXML
    private Button start;
    public void Submit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CaroBoard.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        scene.getWindow();
        ControllerCaroBoard controller = loader.getController();
        controller.CaroBoard(scene);
        controller.setCaroBoard(Name1.getText(),Name2.getText());
        String name1 = Name1.getText().trim();
        String name2 = Name2.getText().trim();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(name1.isEmpty() || name2.isEmpty()) {alert.setContentText(" Retype the player name ");
        alert.show();} else stage.setScene(scene);
    }
}
