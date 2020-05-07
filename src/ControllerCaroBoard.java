import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Optional;

import static java.awt.Color.*;
import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class ControllerCaroBoard extends Controller implements Runnable {

    public Button NewGame;
    @FXML
    private Label lbName1,lbName2,lbPlaying;
    @FXML
    private Label second,lbScore1,lbScore2;
    public static int k = 0;
    public static Boolean checkNewGame = false;
    public static Button[][] bt = new Button[finalValue.btRow][finalValue.btLine];
    public static Boolean checkStart = false;
    public static int Second = 30;
    public static Boolean RUN_TIMER = false;
    public static int Score1 = 0,Score2 = 0;

    public  void  SetScore(){
        lbScore1.setText("0");
        lbScore2.setText("0");
    }
    public void setNamePlayer(String name1, String name2) {
        lbName1.setText(name1);
        lbName2.setText(name2);
    }
    int checkBtStart = 0;
    public void StartGame(ActionEvent event) {
        handlePiece();
        RUN_TIMER = true;

        if( checkBtStart == 0){
            new Thread(this).start();
            lbPlaying.setText(lbName1.getText());
        }else  return;
        checkBtStart++;
    }
    public  void StopTimer(){
        RUN_TIMER = false;
    }
    public void resetTimer(){
        Second = 30;
        second.setText(Second + " second");
    }
    public void CaroBoardExit(ActionEvent event) throws Exception {
         Controller.gr = new Pane();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new  Main().start(primaryStage);
    }
    public void NewGame(ActionEvent event) {
        if (!checkNewGame) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You are in the game");
            alert.showAndWait();
            return;
        }
        k =0 ;
        checkBtStart = 0;
        StartGame(event);
        new draw().caroBoard();
        handlePiece();
        checkNewGame = false;
    }
    @Override
    public void run() {
        while(RUN_TIMER && Second > 0) {
            Second--;
            Platform.runLater(() -> {
                if(k%2==0) lbPlaying.setText(lbName1.getText() +
                        "\nis playing");
                else  lbPlaying.setText(lbName2.getText() +"\nis playing");
                second.setText(Second + " second");
                if(Second == 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Notify the winner");
                    alert.setHeaderText(null);
                    if(lbPlaying.getText().equals(lbName1.getText()))
                    {
                        alert.setContentText( lbName2.getText()+ " win");
                        Score2++;
                        lbScore2.setText(String.valueOf(Score2));
                    }
                    else
                    {
                        alert.setContentText( lbName1.getText() + " win" );
                        Score1++;
                        lbScore1.setText(String.valueOf(Score1));
                    }
                    ButtonType buttonCancel= new ButtonType("Cancel", CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonCancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get().getButtonData() == CANCEL_CLOSE)
                    {
                        StopTimer();
                        checkStart = false;
                        checkNewGame = true;
                    }
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void handlePiece(){
        final int[] count = {0};
        for( int i = 0; i < finalValue.btRow; i++)
            for(int j = 0 ; j < finalValue.btLine; j++){
                int finalJ = j;
                int finalI = i;
                bt[i][j].setOnAction(event -> {
                    if(count[0] !=0) return;// After the win can not go on to the remaining cells
                    if(bt[finalI][finalJ].getId().equals("x") || bt[finalI][finalJ].getId().equals("o") ) return;// Check this box has a chess piece or not?
                    resetTimer();// when going to a new one, the time is initialized to 30 s
                    if((Score1+Score2)%2 ==0) //Change players first after each game won
                    {if(k%2 == 0) {

                        Image image = new Image("/image/x.png");
                        ImageView imageView =new ImageView(image);
                        bt[finalI][finalJ].setId("x");
                        bt[finalI][finalJ].setGraphic(imageView);

                    }
                    else {

                        Image image = new Image("/image/o.png");
                        ImageView imageView =new ImageView(image);
                        bt[finalI][finalJ].setId("o");
                        bt[finalI][finalJ].setGraphic(imageView);
                    }}
                    else
                    {
                        if(k%2 == 0) {

                            Image image = new Image("/image/o.png");
                            ImageView imageView =new ImageView(image);

                            bt[finalI][finalJ].setId("o");
                            bt[finalI][finalJ].setGraphic(imageView);


                        }
                        else {

                            Image image = new Image("/image/x.png");
                            ImageView imageView =new ImageView(image);
                            bt[finalI][finalJ].setGraphic(imageView);

                            bt[finalI][finalJ].setId("x");
                        }
                    }

                    if( Check.check(bt,bt[finalI][finalJ],Controller.gr)) { //Check who has 5 pieces first will win
                        StopTimer();
                        System.out.println("win");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Notify the winner");
                        alert.setHeaderText(null);
                        if(bt[finalI][finalJ].getId().equals("x")){
                            alert.setContentText(lbName1.getText()+ " win");
                            Score1++;// Increase points for player 1 when winning
                           lbScore1.setText(String.valueOf(Score1));
                        }
                        else{
                            alert.setContentText( lbName2.getText() + " win" );
                            Score2++;//Increase points for player2 when winning
                            lbScore2.setText(String.valueOf(Score2));
                        }


                        ButtonType buttonCancel= new ButtonType("Cancel", CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(buttonCancel);
                        Optional<ButtonType> result = alert.showAndWait();

                        if(result.get().getButtonData() == CANCEL_CLOSE)
                        {
                            checkNewGame = true;
                            checkStart = true;
                        }
                        count[0]++;
                    }
                    k++;
                    if( k == finalValue.btLine*finalValue.btRow) new draw().caroBoard(); //If the board is full, it will create a new board
                });
            }
    }
    }





