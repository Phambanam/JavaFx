package view;

import java.io.InputStream;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BoardState;
import model.Player;

public class View implements EventHandler<ActionEvent> {
	public static final int WIDTH_BOARD = 20;
	public static final int HEIGHT_BOARD = 20;
	public static final int WIDTH_PANE = 1200;
	public static final int HEIGHT_PANE = 700;
	private Button btnHuman;
	private Button btnExit;
	private Labeled timePlayer1, timePlayer2;

	// lop dieu khien
	private Controller controller;
	// mang quan co khi danh
	private final Button[][] arrayButtonChess = new Button[WIDTH_BOARD][HEIGHT_BOARD];
	// khung view
	private   Stage primaryStage;

	public View() {
	}

	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			BoardState boardState = new BoardState();
			Player human = new Player(boardState);
			controller = new Controller();
			controller.setView(this);
			controller.setPlayer(human);

			BorderPane borderPane = new BorderPane();
			BorderPane borderPaneRight = new BorderPane();
			menu(borderPaneRight);
			
			GridPane root = new GridPane();
			Scene scene = new Scene(borderPane, WIDTH_PANE, HEIGHT_PANE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			borderPane.setPadding(new Insets(20));
			borderPane.setCenter(root);
			borderPane.setRight(borderPaneRight);
			// mac dinh player 1 di truoc
			controller.setPlayerFlag(1);
			controller.setTimePlayer(timePlayer1, timePlayer2);
			for (int i = 0; i < WIDTH_BOARD; i++) {
				for (int j = 0; j < HEIGHT_BOARD; j++) {
					Button button = new Button();
					button.setPrefSize(40, 40);
					arrayButtonChess[i][j] = button;
					root.add(button, j, i);
					int finalI = i;
					int finalJ = j;
					button.setOnAction(event -> {
						if (!controller.isEnd()) {
							controller.play(finalI, finalJ,button, arrayButtonChess);
						}
					});
				}
			}
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game caro");
			primaryStage.getIcons().add(new Image("/image/iconGame.jfif"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void menu(BorderPane pane) {
		VBox box = new VBox();
		box.setSpacing(10);
		Class<?> clazz = this.getClass();
		AnchorPane anchorPaneLogo = new AnchorPane();
		AnchorPane anchorPaneMenu = new AnchorPane();
		// set logo
		InputStream input = clazz.getResourceAsStream("/image/Logo.png");
		Image image = new Image(input);
		ImageView imgView = new ImageView(image);
		imgView.setFitHeight(230);
		imgView.setFitWidth(260);
		AnchorPane.setTopAnchor(imgView, 10.0);
		AnchorPane.setLeftAnchor(imgView, 30.0);
		AnchorPane.setRightAnchor(imgView, 30.0);
		anchorPaneLogo.getChildren().add(imgView);

		// Human
		btnHuman= new Button("New Game");
		btnHuman.setId("btnMenu");
		btnHuman.setOnAction(this);
		AnchorPane.setTopAnchor(btnHuman, 10.0);
		AnchorPane.setLeftAnchor(btnHuman, 30.0);
		AnchorPane.setRightAnchor(btnHuman, 30.0);
		anchorPaneMenu.getChildren().add(btnHuman);
		

		// exit
		btnExit = new Button("Exit");
		btnExit.setId("btnMenu");
		btnExit.setOnAction(this);
		AnchorPane.setTopAnchor(btnExit, 50.0);
		AnchorPane.setLeftAnchor(btnExit, 30.0);
		AnchorPane.setRightAnchor(btnExit, 30.0);
		anchorPaneMenu.getChildren().add(btnExit);
		//
		box.getChildren().add(anchorPaneLogo);
		box.getChildren().add(anchorPaneMenu);

		// Bottom
		GridPane gridPaneBottom = new GridPane();
		Labeled namePlayer1 = new Label("Player 1");
		namePlayer1.setId("nameplayer");
		Labeled namePlayer2 = new Label("Player 2");
		namePlayer2.setId("nameplayer");
		gridPaneBottom.add(namePlayer1, 0, 0);
		gridPaneBottom.add(namePlayer2, 1, 0);
		box.getChildren().add(gridPaneBottom);
		//
		GridPane gridPaneBottom1 = new GridPane();
		timePlayer1 = new Label("30");
		timePlayer1.setId("timeplayer");
		timePlayer2 = new Label("30");
		timePlayer2.setId("timeplayer");
		gridPaneBottom1.add(timePlayer1, 0, 0);
		gridPaneBottom1.add(timePlayer2, 1, 0);
		box.getChildren().add(gridPaneBottom1);
		//
		pane.setCenter(box);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == btnExit) {
			primaryStage.close();
		}
		if (e.getSource() == btnHuman) {
			newGame();
		}

	}

	// che do 2 nguoi choi
	public void newGame() {
		controller.setEnd(false);
		controller.setTimePlayer(timePlayer1, timePlayer2);
		controller.setPlayer(new Player(new BoardState()));
		controller.setPlayerFlag(1);
		controller.reset(arrayButtonChess);
	}

}
