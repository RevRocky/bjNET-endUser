package bjNET.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import bjNET.backRoom.HighRoller;
import bjNET.backRoom.UnexpectedArgumentException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import static java.lang.System.exit;

public class MainScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea chatBox;

    @FXML
    private TextArea actionBox;

    @FXML
    private TextField commandLine;

    @FXML
    private Button sendMessage;

    @FXML
    private TextField creditAmount;

    @FXML
    private TextField betBox;

    private final int MAX_CHAT_LINES        = 800;          //TODO Replace with user defined option
    private final String DEFAULT_FILE_PATH  = "client.hr";
    HighRoller client;

    @FXML
    void sendMessageKeyHandler(KeyEvent event) {

    }

    @FXML
    void sendMessageMouseHandler(MouseEvent event) {

    }

    @FXML
    void betHandler(MouseEvent event) {

    }

    @FXML
    void standHandler(MouseEvent event) {

    }



    @FXML
    void initialize() {
        assert chatBox != null : "fx:id=\"chatBox\" was not injected: check your FXML file 'MainScreenLayout.fxml'.";
        assert commandLine != null : "fx:id=\"commandLine\" was not injected: check your FXML file 'MainScreenLayout.fxml'.";
        assert sendMessage != null : "fx:id=\"sendMessage\" was not injected: check your FXML file 'MainScreenLayout.fxml'.";

        try{
            client = HighRoller.readHighRoller(DEFAULT_FILE_PATH);
        }
        catch (IOException e) {
            Alert connectionErrorWindow = new Alert(Alert.AlertType.ERROR);
            connectionErrorWindow.setTitle("Unexpected Error Reading from Disk");           // We want our title to be the error connection type!
            connectionErrorWindow.setHeaderText(null);
            connectionErrorWindow.setContentText("There was an unexpected error while reading " +
                                                    "game information from the disk!");
            connectionErrorWindow.showAndWait();
            exit(2);
        }
    }

    // Adds listeners that ensure a responsive UI!
    public void addResponsiveListeners(){

    }

    /**
     * Updates the amount of current credits on the screen to reflect how many credits the user currently has.
     * @param newCreditAmount The amount of new credits the player has.
     */
    public void updateCurrentCredits(int newCreditAmount) {
    }


    // Ensures the bet entered is a legal integer
    private void legalBet() {


    }

    // TODO test to ensure that I can attach the controller to the Message Listener class.
    // TODO add logging support.
    // TODO set cap on
    /**
     * Writes a message recieved from the server to the correct tab.
     * @param newMessage The message received from the server. This message is already fully processed!
     * @param destination A string describing the destination. Either "Chat" or "Action".
     */
    public void writeText(String newMessage, String destination) throws UnexpectedArgumentException {
        TextArea destinationBox;
        int lineCount;

        switch (destination) {
            case "Chat":
                destinationBox = chatBox;
                break;
            case "Action":
                destinationBox = actionBox;
                break;
            default:
                throw new UnexpectedArgumentException("Message was sent to an unexpected stream");
        }

        // Now we check to see if we need to remove a line
        lineCount = GUI_Help.textboxLineCount(destinationBox);

        // Writing to the TextField
        destinationBox.setEditable(true);
        if (lineCount > MAX_CHAT_LINES) {                                                                   // If we have more than the max number of lines!
            String[] boxContents = destinationBox.getText().split("\n");                              // Splitting string in to each line
            boxContents = Arrays.copyOfRange(boxContents, 1, boxContents.length);                      // Copying all of the array... bar pos 1 onward
            destinationBox.setText(String.format("%s\n%s", String.join("\n", boxContents), newMessage));     // Joining new away back
        }
        else {                                                                                               // We have not reached the cap
            destinationBox.setText(String.format("%s\n%s", destinationBox.getText(), newMessage));           // Joining new away back
        }
        destinationBox.setEditable(false);
    }



    // This method will draw a card to the play area of the screen!
    public void drawCard() {

    }
}
