package Model;

import GUI.DisplayGame1;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private DisplayGame1 displayGame1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new DisplayGame1(primaryStage);
    }

}
