import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.concurrent.atomic.AtomicBoolean;


public class CaroBoard extends Controller {
    int k = 0;
    int count = 0;

    Button[][] bt = new Button[15][15];

    public  void caroBoard(Pane group, String name1, String name2) {


        Button oldButton = new Button();
        oldButton.setLayoutX(150);
        oldButton.setLayoutY(0);
        oldButton.setPrefSize(0,0);


        for(int i = 0; i < 15; i++ ){
            for(int j = 0; j < 12; j++)
            {   Button btn = new Button();


                btn.setLayoutY(oldButton.getLayoutY());
                btn.setLayoutX(oldButton.getLayoutX());
                btn.setMinSize(25,25);
                bt[i][j] = btn;

                btn.setOnAction(event -> {
                    if(count!=0) return;// sau khi win khong the danh
                    if(!btn.getText().equals("")) return;// kiem tra xem o do da danh chua
                    if(k%2 == 0) btn.setText("X");
                    else{
                        btn.setText("O");
                    }

                    if( Check.check(bt,btn,group)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Notify the winner");
                        alert.setHeaderText("winner is " );
                        if(btn.getText().equals("X")) {

                            alert.setContentText(name1);
                        }
                        else{

                            alert.setContentText(name2);
                        }
                        // new CaroBoard().caroBoard(group,name1,name2);

                        alert.show();
                        count++;
                    }

                    k++;
                    if( k == 15*12) new CaroBoard().caroBoard(group,name1,name2);

                });
                group.getChildren().addAll(btn);
                Button btn1 = new Button();
                btn1.setLayoutX(btn.getLayoutX()+25);
                btn1.setLayoutY(btn.getLayoutY());
                oldButton = btn1;
            }
            oldButton.setLayoutY(oldButton.getLayoutY()+25);
            oldButton.setLayoutX(150);
        }
    }

}
