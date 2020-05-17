package Model;

import GUI.AlertCheckNewGame;
import GUI.AlertWin;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static Model.Controller.getGr;
import static Model.Controller.setGr;

public class ControllerCaroBoard implements Runnable {


    @FXML
    private Label lbName1, lbName2, lbPlaying;
    @FXML
    private Label second, lbScore1, lbScore2;
    private final CheckWin check = new CheckWin();
    private int k = 0;
    private Boolean checkNewGame = false;
    private final Button[][] bt = Draw.bt;
    private int secondPlaying = 30;
    private Boolean blRUN_TIMER = false;
    private int score1 = 0, score2 = 0;
    private int checkBtStart = 0;
    private int count = 0;

    public void setNamePlayer(String name1, String name2, int score1, int score2) {
        lbName1.setText(name1);
        lbName2.setText(name2);
        lbScore1.setText(String.valueOf(score1));
        lbScore2.setText(String.valueOf(score2));
    }

    public void startGame(ActionEvent event) {
        handlePiece();
        blRUN_TIMER = true;
        if (checkBtStart == 0) {
            new Thread(this).start();
        } else return;
        checkBtStart++;
    }

    public void stopTimer() {
        blRUN_TIMER = false;
    }

    public void resetTimer() {
        secondPlaying = 30;
        second.setText(secondPlaying + " second");
    }

    public void caroBoardExit(ActionEvent event) throws Exception {
        setGr(new Pane());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new Main().start(primaryStage);
    }

    public void newGame(ActionEvent event) {
        if (!checkNewGame && secondPlaying != 30) {
            new AlertCheckNewGame();
            return;
        }
        blRUN_TIMER = true;
        k = 0;
        checkBtStart = 0;
        count = 0;
        startGame(event);
        new Draw().caroBoard(getGr());
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
                        new Draw().caroBoard(getGr()); //If the board is full, it will create a new board
                });
            }
    }

    public void checkChangeFirstPlayer(Button bt) {
        if ((score1 + score2) % 2 == 0) //Change players first after each game won
            if (k % 2 == 0) FinalValue.playerX(bt);
            else FinalValue.playerO(bt);
        else if (k % 2 == 0)
            FinalValue.playerO(bt);
        else FinalValue.playerX(bt);
    }

    public void messagePlayerWin(Button button) {
        if (check.check(bt, button, getGr())) { //Check who has 5 pieces first will win
            stopTimer();
            if (button.getId().equals("x")) {
                new AlertWin(lbName1);
                score1++;//Increase points for player2 when winning
                lbScore1.setText(String.valueOf(score1));
            } else {
                new AlertWin(lbName2);
                score2++;//Increase points for player2 when winning
                lbScore2.setText(String.valueOf(score2));
            }

            checkNewGame = true;
            count++;
        }
    }

    @Override
    public void run() {
        while (blRUN_TIMER && secondPlaying > 0) {
            secondPlaying--;

            Platform.runLater(() -> {
                //write who is playing in lbPlaying
                if (secondPlaying % 2 == 1) {
                    lbPlaying.setText("");
                } else if ((score1 + score2) % 2 == 0) {
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
                second.setText(secondPlaying + " second");
                //write win
                if (secondPlaying == 0) {

                    if (lbPlaying.getText().equals(lbName1.getText())) {
                        new AlertWin(lbName2);
                        score2++;
                        lbScore2.setText(String.valueOf(score2));
                    } else {
                        new AlertWin(lbName1);
                        score1++;
                        lbScore1.setText(String.valueOf(score1));
                    }
                    checkNewGame = true;
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



