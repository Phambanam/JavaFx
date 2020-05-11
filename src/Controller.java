import com.sun.deploy.security.SelectableSecurityManager;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    public TextField name1,name2;
    @FXML
    private Button start;

    public static Pane gr ;

    public void checkName1(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(name1.getText().trim().isEmpty() ) {
            alert.setContentText(" Retype the player1 name ");
            alert.show();
        }
        else name1.setOnAction(event1 -> name2.requestFocus());
    }

    public  void checkName2(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(name2.getText().trim().isEmpty() ) {
            alert.setContentText(" Retype the player2 name ");
            alert.show();
        }
        else {
            name2.setOnAction(event1 -> start.requestFocus());
        }
    }

    public  void btStart(ActionEvent event) throws IOException {
        gr = new Pane();
        gr.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        Scene scene = new Scene(gr);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        stage.setResizable(false);
        loader.setLocation(getClass().getResource("CaroBoard.fxml"));
        Parent parent = loader.load();
        gr.getChildren().addAll(parent);
        ControllerCaroBoard controller = loader.getController();
        new draw().caroBoard();
        controller.setPaneNewGame(scene,name1.getText(),name2.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(name1.getText().isEmpty() || name2.getText().isEmpty() ) {
            alert.setContentText(" Retype the player name ");
            alert.show();
        } else  stage.setScene(scene);
        stage.setTitle("GAME C-A-R-O");
        stage.getIcons().add(new Image("/image/iconGame.jfif"));

    }

    public void exitGame(ActionEvent event){
        System.exit(0);
    }

    public void tutorialPlayGame(ActionEvent event){
        Alert alert = new Alert( AlertType.INFORMATION);
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
