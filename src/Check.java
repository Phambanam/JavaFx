import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Check {
    public static Boolean check(Button[][] bt, Button button, Pane gr){
        return CheckHorizontal(bt,button,gr) || CheckDiagonalRight(bt,button,gr)
                ||CheckDiagonalLeft(bt,button,gr) || CheckVertical(bt,button,gr)  ;
    }
    private static Boolean CheckHorizontal(Button[][] bt, Button button, Pane gr){
        Line line = new Line();
        for(int i = 0; i < 15; i++ ) {
            for (int j = 0; j < 12; j++) {
                if (bt[i][j] == button)
                {    //kiem tra so con ben trai
                    int k = j;
                    int count = 0;
                    while (k > 0) {
                        k --;
                        if (button.getText().equals(bt[i][k].getText()))
                            count++;
                        else{
                            break;}
                    }
                    // kiem tra so con ben phai
                    k = j;
                    while (k < 11) {
                        k ++;
                        if (button.getText().equals(bt[i][k].getText())) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    gr.getChildren().add(line);
                    if(count >= 4) return true;
                }


            }
        }

        return false;
    }

    private static Boolean CheckVertical(Button[][] bt, Button button,Pane gr){
        for(int i = 0; i < 15; i++ ) {
            for (int j = 0; j < 12; j++) {
                if (bt[i][j] == button)
                {    //kiem tra doc tren
                    int k = i;
                    int count = 0;
                    while (k > 0) {
                        k --;
                        if (button.getText().equals(bt[k][j].getText()))
                            count++;
                        else break;
                    }
                    // kiem tra doc duoi
                    k = i;
                    while (k < 14) {
                        k ++;
                        if (button.getText().equals(bt[k][j].getText())) {
                            count++;
                        } else break;
                    }
                    if(count >= 4) return true;
                }

            }
        }
        return false;

    }
    private static Boolean CheckDiagonalRight(Button[][] bt, Button button,Pane gr){
        for(int i = 0; i < 15; i++ ) {
            for (int j = 0; j < 12; j++) {
                if (bt[i][j] == button)
                {    //kiem tra cheo trai tren
                    int k = j;
                    int c = i;
                    int count = 0;
                    while (k > 0 && c > 0) {
                        k --;
                        c --;
                        if (button.getText().equals(bt[c][k].getText()))
                            count++;
                        else break;
                    }
                    // kiem tra cheo trai duoi
                    k = j;
                    c = i;
                    while (k < 11 && c < 14) {
                        k ++;
                        c++;
                        if (bt[c][k].getText().equals(button.getText())) {
                            count++;
                        } else break;
                    }
                    if(count >= 4) return true;
                }

            }
        }
        return false;
    }
    private static Boolean CheckDiagonalLeft(Button[][] bt, Button button,Pane gr){
        for(int i = 0; i < 15; i++ ) {
            for (int j = 0; j < 12; j++) {
                if (bt[i][j] == button)
                {    //kiem tra cheo phai tren
                    int k = j;
                    int c = i;
                    int count = 0;
                    while (k < 11 && c > 0) {
                        k ++;
                        c --;
                        if (button.getText().equals(bt[c][k].getText()))
                            count++;
                        else break;
                    }
                    // kiem tra cheo phai duoi
                    k = j;
                    c = i;
                    while (k >0 && c < 14) {
                        k --;
                        c++;
                        if (button.getText().equals(bt[i][k].getText())) {
                            count++;
                        } else break;
                    }
                    if(count >= 4) return true;
                }

            }
        }
        return false;
    }
}
