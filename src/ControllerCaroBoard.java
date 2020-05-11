import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class ControllerCaroBoard extends Controller implements Runnable {

    @FXML
    private Pane ControllerPane;
    @FXML
    private Label lbName1, lbName2, lbPlaying;
    @FXML
    private Label second, lbScore1, lbScore2;
    private Button newGame;
    private int k = 0;
    private Boolean checkNewGame = false;
    private Button[][] bt = draw.bt;
    private Boolean checkStart = false;
    private int Second = 30;
    private Boolean blRUN_TIMER = false;
    private int Score1 = 0, Score2 = 0;
    private int checkBtStart = 0;
    private int count = 0;

    public void setPaneNewGame(Scene scene, String name1, String name2) {
        ControllerPane.setPrefHeight(FinalValue.btLine * 45);
        ControllerPane.setStyle("-fx-background-color: White;-fx-border-color: blue;");
        lbScore1.setText("0");
        lbScore2.setText("0");
        lbName1.setText(name1);
        lbName2.setText(name2);
    }


    public void startGame(ActionEvent event) {
        handlePiece();
        blRUN_TIMER = true;

        if (checkBtStart == 0) {
            new Thread(this).start();
            lbPlaying.setText(lbName1.getText());
        } else return;
        checkBtStart++;
    }

    public void stopTimer() {
        blRUN_TIMER = false;
    }

    public void resetTimer() {
        Second = 30;
        second.setText(Second + " second");
    }

    public void caroBoardExit(ActionEvent event) throws Exception {
        Controller.gr = new Pane();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new Main().start(primaryStage);
    }

    public void newGame(ActionEvent event) {
        if (!checkNewGame) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You are in the game");
            alert.showAndWait();
            return;
        }
        k = 0;
        checkBtStart = 0;
        count = 0;
        startGame(event);
        new draw().caroBoard();
        handlePiece();
    }

    public void handlePiece() {

        for (int i = 0; i < FinalValue.btRow; i++)
            for (int j = 0; j < FinalValue.btLine; j++) {
                int finalJ = j;
                int finalI = i;
                bt[i][j].setOnAction(event -> {
                    if (count != 0) return;// After the win can not go on to the remaining cells
                    if (bt[finalI][finalJ].getId().equals("x") || bt[finalI][finalJ].getId().equals("o"))
                        return;// Check this box has a chess piece or not?
                    resetTimer();// when going to a new one, the time is initialized to 30 s
                    checkChangeFirstPlayer(bt[finalI][finalJ]);
                    messagePlayerWin(bt[finalI][finalJ]);
                    k++;
                    if (k == FinalValue.btLine * FinalValue.btRow)
                        new draw().caroBoard(); //If the board is full, it will create a new board
                });
            }
    }
     public void checkChangeFirstPlayer (Button bt){
         if ((Score1 + Score2) % 2 == 0) //Change players first after each game won
             if (k % 2 == 0) FinalValue.playerX(bt);
             else FinalValue.playerO(bt);
         else
         if (k % 2 == 0)
             FinalValue.playerO(bt);
         else FinalValue.playerX(bt);
     }
     public  void messagePlayerWin(Button button){
         if (Check.check(bt, button, Controller.gr)) { //Check who has 5 pieces first will win
             stopTimer();
             System.out.println("win");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Notify the winner");
             alert.setHeaderText(null);
             if (button.getId().equals("x")) {
                 alert.setContentText(lbName1.getText() + " win");
                 Score1++;// Increase points for player 1 when winning
                 lbScore1.setText(String.valueOf(Score1));
             } else {
                 alert.setContentText(lbName2.getText() + " win");
                 Score2++;//Increase points for player2 when winning
                 lbScore2.setText(String.valueOf(Score2));
             }


             ButtonType buttonCancel = new ButtonType("Cancel", CANCEL_CLOSE);
             alert.getButtonTypes().setAll(buttonCancel);
             Optional<ButtonType> result = alert.showAndWait();

             if (result.get().getButtonData() == CANCEL_CLOSE) {
                 checkNewGame = true;
                 checkStart = true;
             }
             count++;
         }
     }
    @Override
    public void run() {
        while (blRUN_TIMER && Second > 0) {
            Second--;

            Platform.runLater(() -> {
                //write who is playing in lbPlaying
                if (Second % 2 == 1) {
                    lbPlaying.setText("");
                } else if ((Score1 + Score2) % 2 == 0) {
                    if (k % 2 == 0) {
                        lbPlaying.setText(lbName1.getText().toUpperCase() + "'S" + "\nTURN");
                        lbPlaying.setStyle("-fx-text-fill: Red;");
                    } else {
                        lbPlaying.setText(lbName2.getText().toUpperCase() + "'S" + "\nTURN");
                        lbPlaying.setStyle("-fx-text-fill: Green;");
                    }
                } else if (k % 2 == 1) {
                    lbPlaying.setText(lbName1.getText().toUpperCase() + "'S" + "\nTURN");
                    lbPlaying.setStyle("-fx-text-fill: Red;");
                } else {
                    lbPlaying.setText(lbName2.getText().toUpperCase() + "'S" + "\nTURN");
                    lbPlaying.setStyle("-fx-text-fill: Green;");
                }
                //write timer thinking
                second.setText(Second + " second");
                //write win
                if (Second == 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Notify the winner");
                    alert.setHeaderText(null);
                    if (lbPlaying.getText().equals(lbName1.getText())) {
                        alert.setContentText(lbName2.getText() + " win");
                        Score2++;
                        lbScore2.setText(String.valueOf(Score2));
                    } else {
                        alert.setContentText(lbName1.getText() + " win");
                        Score1++;
                        lbScore1.setText(String.valueOf(Score1));
                    }
                    ButtonType buttonCancel = new ButtonType("Cancel", CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonCancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get().getButtonData() == CANCEL_CLOSE) {
                        stopTimer();
                        checkStart = false;
                        checkNewGame = true;
                    }
                    count++;
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



