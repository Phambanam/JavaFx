package Model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class FinalValue {
    public static int btX = 45;
    public static int btY = 45;
    public static int btLine = 15;
    public static int btRow = 15;

    public static void playerX(Button btn) {
        Image image = new Image("/image/x.png");
        ImageView imageView = new ImageView(image);
        btn.setId("x");
        btn.setGraphic(imageView);
    }

    public static void playerO(Button btn) {
        Image image = new Image("/image/o.png");
        ImageView imageView = new ImageView(image);
        btn.setId("o");
        btn.setGraphic(imageView);
    }
}