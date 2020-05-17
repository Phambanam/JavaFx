package Model;

import GUI.DisplayGame2;
import GUI.TutorialDisplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller extends ControllerCaroBoard{
    private static Pane gr;
    @FXML
    private TextField name1,name2;
    @FXML
    private Button start;
    public TextField getName1() {
        return name1;
    }

    public TextField getName2() {
        return name2;
    }

    public static Pane getGr() {
        return gr;
    }

    public static void setGr(Pane gr) {
        Controller.gr = gr;
    }

    public void checkName1(ActionEvent event) {

        if(name1.getText().trim().isEmpty() ) {
            new CheckName(getName1().getText(),event);
        }
        else name1.setOnAction(event1 -> name2.requestFocus());
    }

    public  void checkName2(ActionEvent event){

        if(name2.getText().trim().isEmpty() ) {
            new CheckName(getName2().getText(),event);
        }
        else {
            name2.setOnAction(event1 -> start.requestFocus());
        }
    }

    public  void btStart(ActionEvent event) throws IOException {
        gr = new Pane();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
          if( !getName1().getText().trim().isEmpty()&&!getName2().getText().trim().isEmpty()) {
              DisplayGame2 displayGame2 = new DisplayGame2(gr,stage);
              ControllerCaroBoard controllerCaroBoard =  displayGame2.getLoader().getController();
              controllerCaroBoard.setNamePlayer(name1.getText(),name2.getText(),0,0);

       }
        System.out.println(getName1().getText());
    }
    public void btTutorial(ActionEvent event){
        new TutorialDisplay(event);
    }
    public void exitGame(ActionEvent event){
        System.exit(0);
    }



}
