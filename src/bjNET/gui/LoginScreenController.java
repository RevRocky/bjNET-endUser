package bjNET.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bjNET.backRoom.HighRoller;
import bjNET.backRoom.ConnectionError;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField handleEntry;

    @FXML
    private ComboBox<String> serverEntry;

    @FXML
    private Button connectButton;

    public final String DEFAULT_FILE_LOCATION = "client.hr";

    @FXML
    void openConnectionClick(MouseEvent event) {
        openConnection();
    }

    @FXML
    void openConnectionKey(KeyEvent event) {
       KeyCode code = event.getCode();
        if (code.equals(KeyCode.SPACE) || code.equals(KeyCode.ENTER)) {
            openConnection();
        }
    }

    // Opening a connection to the server using logic in the HighRoller class. If connection is successful, we will
    // close the connection window and open up the main application.
    // TODO Add support for ports other than 5000!
    // TODO This may not be the best implimentation of a multi windowed programme...
    private void openConnection() {
        HighRoller client;                                               // Declaring our instance of the HighRoller class.

        // Attempting to connect to the server
        try {
            client = new HighRoller(handleEntry.getText(), serverEntry.getValue());
            client.writeToDisk(DEFAULT_FILE_LOCATION);
        }
        catch (IOException | ConnectionError e) {
            Alert connectionErrorWindow = new Alert(Alert.AlertType.ERROR);
            connectionErrorWindow.setTitle(e.getClass().toString());     // We want our title to be the error connection type!
            connectionErrorWindow.setHeaderText(null);
            connectionErrorWindow.setContentText(e.getMessage());
            connectionErrorWindow.showAndWait();
            return;
        }

        closeWindow();
        new bjNET_Main().start(new Stage());

    }

    // Little method to close the current window.
    private void closeWindow() {
        Stage thisWindow = (Stage) connectButton.getScene().getWindow();
        thisWindow.close();
    }
    @FXML
    void initialize() {
    }
}
