import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ControllerCaroBoard  {

    @FXML
    private Button newGAme;
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

    public void CaroBoardExit(ActionEvent event) {
        System.exit(0);
    }


}

