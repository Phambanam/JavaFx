package Model;


import GUI.AlertCheckName;
import javafx.event.ActionEvent;


public class CheckName {
    public CheckName(String namePlayer, ActionEvent event) {
        if (namePlayer.trim().isEmpty()) new AlertCheckName();
    }
}

