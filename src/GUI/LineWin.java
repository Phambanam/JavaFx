package GUI;

import Model.FinalValue;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineWin {
    public LineWin(Button button1, Button button2, Line line, Pane gr) {
        line.setStartX(button1.getLayoutX() + FinalValue.btX / 2);
        line.setEndX(button2.getLayoutX() + FinalValue.btX / 2);
        line.setStartY(button1.getLayoutY() + FinalValue.btY / 2);
        line.setEndY(button2.getLayoutY() + FinalValue.btY / 2);
        line.setStroke(Color.GREEN);
        gr.getChildren().add(line);
    }
}
