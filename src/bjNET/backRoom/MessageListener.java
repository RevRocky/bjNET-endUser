package bjNET.backRoom;

import java.net.DatagramSocket;
import bjNET.gui.MainScreenController;

/**
 * This class is an agent that simply listens for new messages and then forks off a new process that correctly
 * handles each message
 */
public class MessageListener {

    private MainScreenController parent;            // The MainScreenController

    /**
     *
     * @param sock
     * @return
     */
    public static String recieveOneOff(DatagramSocket sock) {
        return "";
    }
}
