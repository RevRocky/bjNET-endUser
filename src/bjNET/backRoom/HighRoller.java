package bjNET.backRoom;

import java.io.*;

import java.net.*;
import java.util.StringTokenizer;

/**
 * Created by mafia_000 on 07-Dec-16.
 */
public class HighRoller implements Serializable {


    private static final long serialVersionUID = 2059951317615006879L;
    private DatagramSocket serverConnection;
    private final InetAddress serverAddy;
    private final int port;
    private static final String DEFAULTPORT = "15000";

    private String handle;
    private long availableCredits;                                  // Let's make the user feel /BIG/
    private long currentBet;

    private final String MSG_DELIMITER = "&";
    private final int MESSAGELENGTH = 2048;

    public HighRoller(String userHandle, String IPAddy, String port) throws IOException, ConnectionError {
        handle = userHandle;                                           // Setting the user's handle!
        serverAddy = InetAddress.getByName(IPAddy);
        this.port = Integer.parseInt(port);

        connectAndVerify();
    }

    // TODO: Eventually Saved data (w.r.t monies and wotnot will be saved)
    public HighRoller(String userHandle, String IPAddy) throws IOException, ConnectionError {
        this(userHandle, IPAddy, HighRoller.DEFAULTPORT);
    }

    // This connects to the server, and waits to see if the connection was successful.
    // It flags the appropriate exception should something go wrong!
    private void connectAndVerify() throws IOException, ConnectionError {
        serverConnection = new DatagramSocket();
        byte[] helloMSG = packageMessage("jo", String.format("%s", handle));
        sendMessage(helloMSG);

        // Now we will ready to receive the incoming message. This may be best suited for a static method in
        // message listener
        boolean recieved = false;                                                    // Tracks whether or not we have received a message yet.
        int timer = 30;                                                       // 30 seconds seems a reasonable countdown length.
        byte[] buffer = new byte[MESSAGELENGTH];
        DatagramPacket packet = new DatagramPacket(buffer, MESSAGELENGTH);
        String receivedMSG;

        // It is important to note that this is the only message in the protocol where we only get a numeric code.
        serverConnection.setSoTimeout(0);                                                   // Will time out after 5 seconds
        try {
            receivedMSG = new String(packet.getData(), 0, packet.getLength());
            serverConnection.receive(packet);                                               // Receiving the message into the current packet
        } catch (SocketTimeoutException e) {                                                   // Not entirely the best style right here
            throw new ConnectionError(String.format("Server Timeout. The bjNET server at %s is not responding.",
                    serverAddy.getHostAddress()));                                          // But I want control of the message!
        }

        // If the user name is already in use we also want to flag an error.
        if (receivedMSG.equals("409")) {
            throw new ConnectionError(String.format("The user name '%s' is already in use. Please select a new user name.", handle));
        }
        // Implicit else

    }

    // A short little function that packages a message before it gets sent out over the connection!
    private byte[] packageMessage(String prefix, String messageBody) {
        String outGoingString = prefix + MSG_DELIMITER + messageBody;
        return outGoingString.getBytes();                           // Ensure this is UTF-8
    }

    // A little wrapper method that sends a message to the server!
    private void sendMessage(byte[] message) throws IOException {
        DatagramPacket packet = new DatagramPacket(message, message.length, serverAddy, port);
        serverConnection.send(packet);
    }

/**
     * Returns the HighRoller object stored at filePath
     * @param filePath the path to the file where the high roller object is stored!
     * @return The HighRoller object stored at location filePath.
     * @throws IOException should an error occur when reading the file from disk.
     */
    // TODO make sure that these errors are being handled in the best possible manner!
    public static HighRoller readHighRoller(String filePath) throws IOException {
        try(ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(filePath))) {
            return (HighRoller)oIn.readObject();
        }
        catch (Exception e) {
            throw new IOException("Unexpected behaviour when reading player information from disk");
        }
    }


    /**
     * Writes the highRoller class to the disk in a binary format.
     * @param filePath The location where the file will be written to disk.
     * @throws IOException should any issue happen while writing the file to disk. Will prompt a popup window
     * in the user interface.
     */

    public void writeToDisk(String filePath) throws IOException {
        // Declaring some of our output streams
        FileOutputStream fOut   = new FileOutputStream(filePath);
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(this);

        // Closing our object and file streams. Any errors will be tossed up to the GUI (for now)
        if (fOut != null) {
            fOut.close();
        }
        if (oOut != null) {
            oOut.close();
        }
    }
}

