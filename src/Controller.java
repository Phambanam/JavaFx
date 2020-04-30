import com.sun.deploy.security.SelectableSecurityManager;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


public class Controller {
    @FXML
    public TextField Name1,Name2;
    @FXML
    private Button start;

    public void CheckName1(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Name1.getText().trim().isEmpty() ) {
            alert.setContentText(" Retype the player1 name ");
            alert.show();
        }
        else Name1.setOnAction(event1 -> Name2.requestFocus());
    }
    public  void CheckName2(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Name2.getText().trim().isEmpty() ) {
            alert.setContentText(" Retype the player2 name ");
            alert.show();
        }
        else Name2.setOnAction(event1 -> start.requestFocus());
    }
    public  void Start(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        stage.setResizable(false);
        loader.setLocation(getClass().getResource("CaroBoard.fxml"));
        Parent parent = loader.load();
        Pane gr = new Pane();
        stage.setTitle("GAME C-A-R-O");
        stage.getIcons().add(new Image("/image/iconGame.jfif"));

        gr.getChildren().addAll(parent);
        ControllerCaroBoard controller = loader.getController();
        CaroBoard caroBoard = new CaroBoard();
        caroBoard.caroBoard(gr,Name1.getText(),Name2.getText());
        Scene scene = new Scene(gr);

        controller.setNamePlayer1(Name1.getText());
        controller.setNamePlayer2(Name2.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Name1.getText().isEmpty() || Name2.getText().isEmpty() ) {
            alert.setContentText(" Retype the player name ");
            alert.show();
        } else  stage.setScene(scene);
    }


}
