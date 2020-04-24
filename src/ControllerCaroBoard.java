import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCaroBoard {

    @FXML
    Label lbScore1;
    @FXML
    Label lbScore2;
    @FXML
    private Button newGAme;
    @FXML
    private Button resume;
    @FXML
    private Button exit;
    @FXML
    Label lbName1;
    @FXML
    Label lbName2;

    public void  setNamePlayer1(String name1)  {
        lbName1.setText(name1);
    }
    public void setNamePlayer2(String name2){
        lbName2.setText(name2);
    }


    public void CaroBoardExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setResizable(false);
        stage.setScene(scene);
        }
    public void CaroBoardNewGame(ActionEvent event)  throws IOException  {
      new Controller().Start(event);
    }




}

