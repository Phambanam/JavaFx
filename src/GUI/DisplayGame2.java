package GUI;

import Model.Draw;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayGame2 {
    private FXMLLoader loader = new FXMLLoader();

    public FXMLLoader getLoader() {
        this.loader.setLocation(getClass().getResource("CaroBoard.fxml"));
        return loader;
    }

    public DisplayGame2(Pane gr, Stage stage) throws IOException {
        gr.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        Scene scene = new Scene(gr);
        stage.setResizable(false);
        getLoader();
        Parent parent = loader.load();
        gr.getChildren().addAll(parent);
        new Draw().caroBoard(gr);
        stage.setScene(scene);
        stage.setTitle("GAME C-A-R-O");
        stage.getIcons().add(new Image("/image/iconGame.jfif"));
    }
}
