package bjNET.gui;

import java.net.URL;
import java.util.ResourceBundle;

import bjNET.backRoom.HighRoller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class LoginScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField handleEntry;

    @FXML
    private ComboBox<?> serverEntry;

    @FXML
    private Button connectButton;

    @FXML
    void openConnection(KeyEvent event) {

    }

    @FXML
    void initialize() {
        assert handleEntry != null : "fx:id=\"handleEntry\" was not injected: check your FXML file 'Login_Screen.fxml'.";
        assert serverEntry != null : "fx:id=\"serverEntry\" was not injected: check your FXML file 'Login_Screen.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'Login_Screen.fxml'.";

    }
}
