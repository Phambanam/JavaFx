
import javafx.scene.control.Button;
public class draw  extends ControllerCaroBoard{
        public  void caroBoard() {
            Button oldButton = new Button();
            oldButton.setLayoutX(205);
            oldButton.setLayoutY(0);
            oldButton.setPrefSize(0,0);
            for(int i = 0; i < finalValue.btRow; i++ ){
                for(int j = 0; j < finalValue.btLine; j++)
                {   Button btn = new Button();
                    btn.setLayoutY(oldButton.getLayoutY());
                    btn.setLayoutX(oldButton.getLayoutX());
                    btn.setMinSize(finalValue.btX,finalValue.btY);
                    btn.setStyle("-fx-background-color: White;-fx-border-color: black;");
                    btn.setId("");
                    bt[i][j] = btn;
                    Controller.gr.getChildren().addAll(btn);
                    Button btn1 = new Button();
                    btn1.setLayoutX(btn.getLayoutX()+finalValue.btX);
                    btn1.setLayoutY(btn.getLayoutY());
                    oldButton = btn1;
                }
                oldButton.setLayoutY(oldButton.getLayoutY()+finalValue.btY);
                oldButton.setLayoutX(205);
            }
        }
}
