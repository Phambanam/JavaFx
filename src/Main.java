import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        primaryStage.setResizable(false);
        loader.setLocation(getClass().getResource("/sample.fxml"));
        Parent parent = loader.load();
        Pane gr = new Pane();
        gr.getChildren().add(parent);
        Scene scene = new Scene(gr);
        primaryStage.setTitle("GAME C-A-R-O");
        primaryStage.getIcons().add(new Image("/image/iconGame.jfif"));
        primaryStage.setScene(scene);
        primaryStage.show();
}
}
