package TestGame;

import Model.FinalValue;
import javafx.scene.control.Button;
import org.junit.jupiter.api.Test;

class CheckWinTest {
    Button[][] bt = new Button[FinalValue.btRow][FinalValue.btLine];


    @Test
    void check() {
    }

    @Test
    void checkHorizontal() {


        for(int i = 0;i<FinalValue.btRow; i++)
    {
        for (int j = 0; j < FinalValue.btLine; j++) {
            bt[5][j] = new Button("nam");
            bt[5][j].setId("x");
            System.out.println(bt[5][j].getId());
        }
    }

}

    @Test
    void checkVertical() {
    }

    @Test
    void checkDiagonalRight() {
    }

    @Test
    void checkDiagonalLeft() {
    }
}