package Model;

import GUI.LineWin;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.Objects;


public class CheckWin {

    public Boolean check(Button[][] bt, Button button, Pane gr) {
        return CheckHorizontal(bt, button, gr) || CheckDiagonalRight(bt, button, gr)
                || CheckDiagonalLeft(bt, button, gr) || CheckVertical(bt, button, gr);
    }

    public Boolean CheckHorizontal(Button[][] bt, Button button, Pane gr) {
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for (int i = 0; i < FinalValue.btRow; i++) {
            for (int j = 0; j < FinalValue.btLine; j++) {
                if (bt[i][j] == button) {    //kiem tra so con ben trai
                    int k = j;
                    int count = 0;
                    while (k > 0) {
                        k--;
                        if (button.getId().equals(bt[i][k].getId())) {
                            count++;
                            button1 = bt[i][k];
                        } else {
                            if (count == 0) {
                                button1 = button;
                            }
                            break;
                        }
                    }
                    // kiem tra so con ben phai
                    int count1 = 0;
                    k = j;
                    while (k < FinalValue.btLine - 1) {
                        k++;
                        if (button.getId().equals(bt[i][k].getId())) {
                            {
                                count1++;
                                button2 = bt[i][k];
                            }
                        } else {
                            if (count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if (count + count1 >= 4) {
                        new LineWin(button1, button2, line, gr);
                        return true;
                    }
                }


            }
        }

        return false;
    }

    public Boolean CheckVertical(Button[][] bt, Button button, Pane gr) {
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for (int i = 0; i < FinalValue.btRow; i++) {
            for (int j = 0; j < FinalValue.btLine; j++) {
                if (bt[i][j] == button) {    //kiem tra doc tren
                    int k = i;
                    int count = 0;
                    while (k > 0) {
                        k--;
                        if (Objects.equals(button.getId(), bt[k][j].getId())) {
                            count++;
                            button1 = bt[k][j];
                        } else {
                            if (count == 0) button1 = button;
                            break;
                        }
                    }
                    // kiem tra doc duoi
                    k = i;
                    int count1 = 0;
                    while (k < FinalValue.btRow - 1) {
                        k++;
                        if (button.getId().equals(bt[k][j].getId())) {
                            {
                                count1++;
                                button2 = bt[k][j];
                            }
                        } else {
                            if (count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if (count + count1 >= 4) {
                        new LineWin(button1, button2, line, gr);
                        return true;
                    }
                }

            }
        }
        return false;

    }

    public Boolean CheckDiagonalRight(Button[][] bt, Button button, Pane gr) {
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for (int i = 0; i < FinalValue.btRow; i++) {
            for (int j = 0; j < FinalValue.btLine; j++) {
                if (bt[i][j] == button) {    //kiem tra cheo trai tren
                    int k = j;
                    int c = i;
                    int count = 0;
                    while (k > 0 && c > 0) {
                        k--;
                        c--;
                        if (button.getId().equals(bt[c][k].getId())) {
                            count++;
                            button1 = bt[c][k];
                        } else {
                            if (count == 0) button1 = button;
                            break;
                        }
                    }
                    // kiem tra cheo trai duoi
                    k = j;
                    c = i;
                    int count1 = 0;
                    while (k < FinalValue.btLine - 1 && c < FinalValue.btRow - 1) {
                        k++;
                        c++;
                        if (button.getId().equals(bt[c][k].getId())) {
                            {
                                count1++;
                                button2 = bt[c][k];
                            }
                        } else {
                            if (count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if (count + count1 >= 4) {
                        new LineWin(button1, button2, line, gr);
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public Boolean CheckDiagonalLeft(Button[][] bt, Button button, Pane gr) {
        Button button1 = new Button();
        Button button2 = new Button();
        Line line = new Line();
        for (int i = 0; i < FinalValue.btRow; i++) {
            for (int j = 0; j < FinalValue.btLine; j++) {
                if (bt[i][j] == button) {    //kiem tra cheo phai tren
                    int k = j;
                    int c = i;
                    int count = 0;
                    while (k < FinalValue.btLine - 1 && c > 0) {
                        k++;
                        c--;
                        if (button.getId().equals(bt[c][k].getId())) {
                            count++;
                            button1 = bt[c][k];
                        } else {
                            if (count == 0) button1 = button;

                            break;
                        }
                    }
                    // kiem tra cheo phai duoi
                    k = j;
                    c = i;
                    int count1 = 0;
                    while (k > 0 && c < FinalValue.btRow - 1) {
                        k--;
                        c++;
                        if (button.getId().equals(bt[c][k].getId())) {
                            {
                                count1++;
                                button2 = bt[c][k];

                            }
                        } else {
                            if (count1 == 0) button2 = button;
                            break;
                        }
                    }
                    if (count + count1 >= 4) {
                        new LineWin(button1, button2, line, gr);
                        return true;
                    }
                }

            }
        }
        return false;
    }

}


