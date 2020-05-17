package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayGame1 {
    public DisplayGame1(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        primaryStage.setResizable(false);
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent parent = loader.load();
        Pane gr = new Pane();
        gr.getChildren().addAll(parent);
        Scene scene = new Scene(gr);
        primaryStage.setTitle("GAME C-A-R-O");
        primaryStage.getIcons().add(new Image("/image/iconGame.jfif"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
