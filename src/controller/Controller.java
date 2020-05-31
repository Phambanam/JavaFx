
package controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import view.View;
import java.io.InputStream;
import java.util.Optional;
import java.util.Stack;
import java.util.Timer;

public class Controller {
    private View view = new View();
    private Player player;
    private Class<?> classImg; //  lay anh quan co
    private InputStream o;
    private InputStream x;
    private Image imageO;
    private Image imageX;
    private boolean end;
    private int sumMovie;
    private String playerWin;
    private BoardState boardState = new BoardState();

    public Controller() {
        getComponents();
    }

    public int getSumMovie() {
        return sumMovie;
    }

    private void getComponents() {
        end = false;
        sumMovie = 0;
        playerWin = "";
        classImg = this.getClass();
        o = classImg.getResourceAsStream("/image/o.png");
        x = classImg.getResourceAsStream("/image/x.png");
        imageO = new Image(o);
        imageX = new Image(x);
    }

    public View getView() {
        return view;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlayerFlag() {
        return player.getPlayerFlag();
    }

    public void setPlayerFlag(int playerFlag) {
        player.setPlayerFlag(playerFlag);
    }


    public BoardState getBoardState() {
        return player.getBoardState();
    }


    public boolean isEnd() {
        return end;
    }


    public void play(int x , int y,Button c, Button[][] a) {
            getBoardState();
            if(c.getId() != null) return;
            if (getPlayerFlag() == 1 && boardState.getBoard()[x][y] == 0) {
                newPlay(x, y, 1, a);
                setPlayerFlag(2);
            } else {
                getBoardState();
                if (getPlayerFlag() == 2 && boardState.getBoard()[x][y] == 0) {
                   newPlay(x, y, 2, a);
                    setPlayerFlag(1);
                }
            }

        if (end) {
            timer1.cancel();
            timer2.cancel();
            dialog("Player " + playerWin + " win!");
            return;
        }
        runTimer(getPlayerFlag());
    }


    public void newPlay(int x, int y, int player, Button[][] arrayButtonChess) {
        getBoardState().setPosition(x, y, player);
        if (player == 1) {
            arrayButtonChess[x][y].setGraphic(new ImageView(imageX));
            arrayButtonChess[x][y].setId("x");
            Point point = new Point(x, y);
            point.setPlayer(1);
        } else {
            arrayButtonChess[x][y].setGraphic(new ImageView(imageO));
            arrayButtonChess[x][y].setId("o");
            Point point = new Point(x, y);
            point.setPlayer(2);

        }
        sumMovie++;
        if (getBoardState().checkEnd(x, y) == player) {
            playerWin = player + "";
            end = true;
        }
        if (sumMovie == (getBoardState().height * getBoardState().width)) {
            playerWin = 2 + "";
            end = true;
        }

    }

    public void dialog(String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("GameOver");
        alert.setHeaderText(title);
        alert.setContentText("Do you want play again");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           {
                view.newGame();
            }

        } else {
            // su dung khi chon khong hoac dong hoi thoai
        }
    }


    public void setView(View view) {
        this.view = view;
    }


    public void setEnd(boolean end) {
        this.end = end;
    }

    public void reset(Button[][] arrayButtonChess) {
        sumMovie = 0;
        timer1.cancel();
        timer2.cancel();
        timePlayer1.setText("30");
        timePlayer2.setText("30");
        getBoardState().resetBoard();
        for (int i = 0; i < arrayButtonChess.length; i++) {
            for (int j = 0; j < arrayButtonChess[i].length; j++) {
                arrayButtonChess[i][j].setGraphic(null);
            }
        }
    }

    Labeled timePlayer1, timePlayer2;


    public void setTimePlayer(Labeled timePlayer1, Labeled timePlayer2) {
        this.timePlayer1 = timePlayer1;
        this.timePlayer2 = timePlayer2;
    }

    Timer timer1 = new Timer();
    Timer timer2 = new Timer();


    public void runTimer(int player) {
        if (end) {
            timer1.cancel();
            timer2.cancel();
        } else {
            timer1.cancel();
            timer2.cancel();
            TaskTimer task1 = new TaskTimer(timePlayer1);
            TaskTimer task2 = new TaskTimer(timePlayer2);
            task1.setController(this);
            task2.setController(this);
            if (player == 1) {
                timer2.cancel();
                timer1 = new Timer();
                timer1.schedule(task1, 0, 1000);
            } else {
                timer1.cancel();
                timer2 = new Timer();
                timer2.schedule(task2, 0, 1000);
            }
        }
    }
}
