
package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;

import java.lang.reflect.Array;

public class Main extends Application {
// Bat dau khoi tao stage
	@Override
	public void start(Stage primaryStage) {
		try {
			View view = new View();
			view.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
