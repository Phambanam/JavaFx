import com.sun.prism.paint.Stop;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.YES;

public class ControllerCaroBoard implements Runnable {

    @FXML
    private Button NewGame,exit;
    @FXML
    private Label lbName1,lbName2,lbPlaying;
    @FXML
    private Label second,lbScore1,lbScore2;


    private Button start;
    private static int k = 0;
    private static Boolean checkNewGame = false;
    private static Button[][] bt = new Button[finalValue.btRow][finalValue.btLine];
    public static Boolean checkStart = false;
    private static int Second = 30;
    private static Boolean RUN_TIMER = false;
    private int Score1 = 0,Score2 = 0;

    public  void  SetScore(){
        lbScore1.setText("0");
        lbScore2.setText("0");
    }
    public void setNamePlayer(String name1, String name2) {
        lbName1.setText(name1);
        lbName2.setText(name2);
    }


    public void StartGame(ActionEvent event) {
        RUN_TIMER = true;
        lbPlaying.setText(lbName1.getText());
        Thread th = new Thread(this);
        th.start();
        checkStart = true;
    }

    public  void StopTimer(){
        RUN_TIMER = false;
    }

    public void reset(){
        Second = 30;
        second.setText(Second + " second");
    }

    public void CaroBoardExit(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        primaryStage.setResizable(false);
        loader.setLocation(getClass().getResource("/sample.fxml"));
        Parent parent = loader.load();
        Pane gr = new Pane();
        gr.getChildren().addAll(parent);
        Scene scene = new Scene(gr);
        primaryStage.setTitle("GAME C-A-R-O");
        primaryStage.getIcons().add(new Image("/image/iconGame.jfif"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void NewGame(ActionEvent event) throws IOException {
         k =0 ;
        if (!checkNewGame) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You are in the game");
            alert.showAndWait();
            return;
        }
        caroBoard();
        checkNewGame = false;
    }
     // create chess board
    public  void caroBoard() {
        final int[] count = {0};

        Button oldButton = new Button();
        oldButton.setLayoutX(150);
        oldButton.setLayoutY(0);
        oldButton.setPrefSize(0,0);

        for(int i = 0; i < finalValue.btRow; i++ ){
            for(int j = 0; j < finalValue.btLine; j++)
            {   Button btn = new Button();
                btn.setLayoutY(oldButton.getLayoutY());
                btn.setLayoutX(oldButton.getLayoutX());
                btn.setMinSize(finalValue.btX,finalValue.btY);
                bt[i][j] = btn;

                btn.setOnAction(event -> {
                    if(!checkStart) return;
                    if(count[0] !=0) return;// After the win can not go on to the remaining cells
                    if(!btn.getText().equals("")) return;// Check this box has a chess piece or not?
                    reset();// when going to a new one, the time is initialized to 30 s
                   if((Score1+Score2)%2 ==0) //Change players first after each game won
                   {if(k%2 == 0) {

                        Image image = new Image("/image/x.png");
                        ImageView imageView =new ImageView(image);
                       btn.setId("x");
                        btn.setGraphic(imageView);

                    }
                    else {

                       Image image = new Image("/image/o.png");
                       ImageView imageView =new ImageView(image);
                       btn.setId("o");
                       btn.setGraphic(imageView);
                   }}
                    else
                   {
                       if(k%2 == 0) {

                           Image image = new Image("/image/o.png");
                           ImageView imageView =new ImageView(image);
                           btn.setId("o");
                           btn.setGraphic(imageView);


                       }
                       else {

                           Image image = new Image("/image/x.png");
                           ImageView imageView =new ImageView(image);
                           btn.setGraphic(imageView);
                           btn.setId("x");
                       }
                   }

                    if( Check.check(bt,btn,Controller.gr)) { //Check who has 5 pieces first will win
                        StopTimer();
                        System.out.println("win");
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Notify the winner");
                        alert.setHeaderText(null);
                        if(btn.getId().equals("x")){
                            alert.setContentText(lbName1.getText() + " win");
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
                            checkStart = false;
                        }

                        count[0]++;
                    }

                    k++;
                    if( k == finalValue.btLine*finalValue.btRow) caroBoard(); //If the board is full, it will create a new board

                });
                Controller.gr.getChildren().addAll(btn);
                Button btn1 = new Button();
                btn1.setLayoutX(btn.getLayoutX()+finalValue.btX);
                btn1.setLayoutY(btn.getLayoutY());
                oldButton = btn1;
            }
            oldButton.setLayoutY(oldButton.getLayoutY()+finalValue.btY);
            oldButton.setLayoutX(150);
        }
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

    }






