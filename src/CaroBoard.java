import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.w3c.dom.ls.LSOutput;


public class CaroBoard extends Controller {
    int k = 0;

    public  void caroBoard(Pane group, String name1,String name2) {
        Button[][] bt = new Button[15][15];

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

                int finalI = i;
                int finalJ = j;

                btn.setOnAction(event -> {
                    if(!btn.getText().equals("")) return;// kiem tra xem o do da danh chua
                    if(k%2 == 0) btn.setText("X");
                    else{
                        btn.setText("O");
                    }
                    if( Check.check(bt,bt[finalI][finalJ],group)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Notify the winner");
                        alert.setHeaderText("winner is " );
                        if(btn.getText().equals("X")) alert.setContentText(name1);
                                else alert.setContentText(name2);
                        alert.show();
                    }
                    k++;
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
