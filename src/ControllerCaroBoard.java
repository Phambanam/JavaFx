import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCaroBoard {
    @FXML
    Label lbName1;
    @FXML
    Label lbName2;
    @FXML
    Label lbScore1;
    @FXML
    Label lbScore2;
    @FXML
    private Button pause;
    @FXML
    private Button resume;
    @FXML
    private Button exit;


    public void setCaroBoard(String name1, String name2)  {
    lbName1.setText(name1);
    lbName2.setText(name2);
    }
    public void CaroBoardExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
            stage.setScene(scene);
        }

    public void CaroBoard(Scene scene){

        Button oldButton = new Button();
        oldButton.setLayoutX(144);
        oldButton.setLayoutY(30);
        oldButton.setPrefSize(0,0);

    }

}

