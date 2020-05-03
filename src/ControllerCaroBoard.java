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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.YES;

public class ControllerCaroBoard implements Runnable {

    @FXML
    private Button NewGame;
    @FXML
    private Button exit;
    @FXML
    private Label lbName1;
    @FXML
    private Label lbName2;
    @FXML
    private Label lbPlaying;
    @FXML
    private Label second;
    @FXML
    private Button start;
    private static int k = 0;
    private static Boolean checkNewGame = false;
    private static Button[][] bt = new Button[finalValue.btRow][finalValue.btLine];
    public static Boolean checkStart = false;
    private static int Second = 30;
    private static Boolean RUN_TIMER = false;

    public void setNamePlayer1(String name1) {
        lbName1.setText(name1);
    }

    public void setNamePlayer2(String name2) {
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

    public void CaroBoardExit(ActionEvent event) {
        System.exit(0);
    }

    public void NewGame(ActionEvent event) throws IOException {
        if (!checkNewGame) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You are in the game");
            alert.showAndWait();
            return;
        }

        Pane gr = new Pane();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CaroBoard.fxml"));
        stage.setResizable(false);
        Parent parent = loader.load();

        stage.setTitle("GAME C-A-R-O");
        stage.getIcons().add(new Image("/image/iconGame.jfif"));
        gr.getChildren().addAll(parent, lbName1, lbName2);
        caroBoard(gr);
        stage.setScene(new Scene(gr));
    }



    public  void caroBoard(Pane group) {
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
                    if(count[0] !=0) return;// sau khi win khong the danh
                    if(!btn.getText().equals("")) return;// kiem tra xem o do da danh chua
                    reset();
                    if(k%2 == 0) {
                        btn.setText("X");

                    }
                    else{
                        btn.setText("O");
                    }
                    //Check out time
                    if(Second == 0)
                    {Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                        if(btn.getText().equals("X") )
                            alert.setContentText( lbName2.getText() + "win");
                        else
                            alert.setContentText( lbName2.getText() + "win" );
                    }



                    if( Check.check(bt,btn,group)) {
                        StopTimer();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Notify the winner");
                        alert.setContentText("Do you want to play a new game ");
                        if(btn.getText().equals("X"))
                            alert.setHeaderText(lbName1.getText() + "win");
                        else
                            alert.setHeaderText( lbName2.getText() + "win" );

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
                    if( k == finalValue.btLine*finalValue.btRow) caroBoard(group);

                });
                group.getChildren().addAll(btn);
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
                    alert.setContentText("Do you want to play a new game ");
                    if(lbPlaying.getText().equals(lbName1.getText()))
                        alert.setHeaderText( lbName2.getText()+ "win");
                    else
                        alert.setHeaderText( lbName1.getText() + "win" );
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






