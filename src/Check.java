import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Check {

    public static Boolean check(Button[][] bt, Button button, Pane gr){
        return CheckHorizontal(bt,button,gr) || CheckDiagonalRight(bt,button,gr)
                ||CheckDiagonalLeft(bt,button,gr) || CheckVertical(bt,button,gr)  ;
    }

    private static Boolean CheckHorizontal(Button[][] bt, Button button, Pane gr){
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for(int i = 0; i < finalValue.btRow; i++ ) {
            for (int j = 0; j < finalValue.btLine; j++) {
                if (bt[i][j] == button)
                {    //kiem tra so con ben trai
                    int k = j;
                    int count = 0;
                    while (k > 0) {
                         k--;
                        if (button.getId().equals(bt[i][k].getId()))
                        {
                            count++;
                            button1 = bt[i][k];
                        }
                        else{
                            if(count == 0) {
                                button1 = button;
                            }
                            break;}
                    }
                    // kiem tra so con ben phai
                    int count1 = 0;
                    k = j;
                    while (k < finalValue.btLine - 1) {
                        k ++;
                        if (button.getId().equals(bt[i][k].getId())) {
                            {
                                count1++;
                                button2 = bt[i][k];
                            }
                        } else {
                            if(count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if(count + count1 >= 4) {
                        drawLine(button1,button2,line,gr);
                        return true;
                    }
                }


            }
        }

        return false;
    }


    private static Boolean CheckVertical(Button[][] bt, Button button, Pane gr){
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for(int i = 0; i < finalValue.btRow; i++ ) {
            for (int j = 0; j < finalValue.btLine; j++) {
                if (bt[i][j] == button)
                {    //kiem tra doc tren
                    int k = i;
                    int count = 0;
                    while (k > 0) {
                        k --;
                        if (Objects.equals(button.getId(), bt[k][j].getId()))
                        {
                            count++;
                            button1 = bt[k][j];
                        }
                        else{if(count == 0) button1 = button;
                            break;
                        }
                    }
                    // kiem tra doc duoi
                    k = i;
                    int count1 = 0;
                    while (k < finalValue.btRow - 1) {
                        k ++;
                        if (button.getId().equals(bt[k][j].getId())) {
                            {
                                count1++;
                                button2 = bt[k][j];
                            }
                        } else {
                            if(count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if(count + count1 >= 4){
                        drawLine(button1,button2,line,gr);
                        return true;
                    }
                }

            }
        }
        return false;

    }

    private static Boolean CheckDiagonalRight(Button[][] bt, Button button, Pane gr){
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for(int i = 0; i < finalValue.btRow; i++ ) {
            for (int j = 0; j < finalValue.btLine; j++) {
                if (bt[i][j] == button)
                {    //kiem tra cheo trai tren
                    int k = j;
                    int c = i;
                    int count = 0;
                    while (k > 0 && c > 0) {
                        k --;
                        c --;
                        if (button.getId().equals(bt[c][k].getId())) {
                            count++;
                            button1 = bt[c][k];
                        }
                        else{
                            if(count == 0) button1 = button;
                            break;
                        }
                    }
                    // kiem tra cheo trai duoi
                    k = j;
                    c = i;
                    int count1 = 0;
                    while (k < finalValue.btLine - 1 && c < finalValue.btRow - 1) {
                        k ++;
                        c++;
                        if (button.getId().equals(bt[c][k].getId())) {
                            {
                                count1++;
                                button2 = bt[c][k];
                            }
                        } else {
                            if(count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if(count + count1 >= 4) {
                        drawLine(button1,button2,line,gr);
                        return true;
                    }
                }

            }
        }
        return false;
    }
    private static Boolean CheckDiagonalLeft(Button[][] bt, Button button, Pane gr){
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for(int i = 0; i < finalValue.btRow; i++ ) {
            for (int j = 0; j < finalValue.btLine; j++) {
                if (bt[i][j] == button)
                {    //kiem tra cheo phai tren
                    int k = j;
                    int c = i;
                    int count = 0;
                    while (k < finalValue.btLine - 1 && c > 0) {
                        k ++;
                        c --;
                        if (button.getId().equals(bt[c][k].getId()))
                        {
                            count++;
                            button1 = bt[c][k];
                        }
                        else {
                            if(count == 0) button1 = button;

                            break;
                        }
                    }
                    // kiem tra cheo phai duoi
                    k = j;
                    c = i;
                    int count1 = 0;
                    while (k >0 && c < finalValue.btRow - 1) {
                        k --;
                        c++;
                        if (button.getId().equals(bt[c][k].getId())) {
                            {
                                count1++;
                                button2 = bt[c][k];

                            }
                        } else{
                            if(count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if(count + count1 >= 4){
                        drawLine(button1,button2,line,gr);
                        return  true;
                    }
                }

            }
        }
        return false;
    }
    public static void drawLine( Button button1, Button button2, Line line, Pane gr){
        line.setStartX(button1.getLayoutX()+finalValue.btX/2);
        line.setEndX(button2.getLayoutX()+finalValue.btX/2);
        line.setStartY(button1.getLayoutY()+finalValue.btY/2);
        line.setEndY(button2.getLayoutY()+finalValue.btY/2);
        line.setStroke(Color.GREEN);
        gr.getChildren().add(line );
    }

}
