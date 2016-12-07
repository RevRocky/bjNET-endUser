package bjNET.backRoom;

import java.io.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by mafia_000 on 07-Dec-16.
 */
public class HighRoller implements Serializable{


    private static final long serialVersionUID = 2059951317615006879L;
    private DatagramSocket serverConnection;
    private final InetAddress serverAddy;
    private final int port;
    private static final String DEFAULTPORT              = "5000";


    private String handle;
    private long availableCredits;                                  // Let's make the user feel /BIG/
    private long currentBet;

    private final String MSG_DELIMITER                   = "%"

    public HighRoller(String userHandle, String IPAddy, String port) throws IOException {
        handle      = userHandle;                                           // Setting the user's handle!
        serverAddy  = InetAddress.getByName(IPAddy);
        this.port   = Integer.parseInt(port);

        connectAndVerify();
    }

    // TODO: Eventually Saved data (w.r.t monies and wotnot will be saved)
    public HighRoller(String userHandle, String IPAddy) throws IOException {
        this(userHandle, IPAddy, HighRoller.DEFAULTPORT);
    }

    // This connects to the server, and waits to see if the connection was successful.
    // It flags the appropriate exception should something go wrong!
    private void connectAndVerify() throws IOException {
        serverConnection        = new DatagramSocket(port, serverAddy);
        byte[] helloMSG = packageMessage("jo", String.format("%s", handle));
        sendMessage(helloMSG);
    }

    // A short little function that packages a message before it gets sent out over the connection!
    private byte[] packageMessage(String prefix, String messageBody) {
        String outGoingString = prefix + MSG_DELIMITER + messageBody;
        return outGoingString.getBytes();                           // Ensure this is UTF-8
    }

    // A little wrapper method that sends a message to the server!
    private void sendMessage(byte[] message) throws IOException {
        DatagramPacket packet = new DatagramPacket(message, message.length);
        serverConnection.send(packet);
    }

    /**
     * Returns the HighRoller object stored at filePath
     * @param filePath the path to the file where the high roller object is stored!
     * @return The HighRoller object stored at location filePath.
     */
    public static HighRoller readHighRoller(String filePath) {
        return
    }

    /**
     * Writes the highRoller class to the disk.
     * @param filePath The location where the file will be written to disk.
     */
    public void writeToDisk(String filePath) {

    }
}
