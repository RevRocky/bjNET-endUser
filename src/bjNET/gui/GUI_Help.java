package bjNET.gui;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * A class containing many useful helper functions that I can see fit for a a good many GUI environmenrs!
 */
public class GUI_Help {

    /**
     * Returns the amount of lines in a given text box.
     * @param target
     * @return
     */
    public static int textboxLineCount(TextArea target) {
        return target.getText().split("\n").length;
    }

    /**
     * Returns the length of a given line in a text field. Should the line not exist it will return a zero.
     * @param lineNo The line we want the length of. Zero would refer to the first line and so on!
     * @return Length of a given line in a textfield. Zero, if the line does not exist.
     */
    public static int textFieldLineLength(TextField target, int lineNo) {
        String[] targetText = target.getText().split("\n");

        if (targetText.length <= lineNo) {
            return 0;
        }
        // Implicit else
        return target.getText().split("\n")[lineNo].length();
    }
}
