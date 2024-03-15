import java.io.*;
import java.net.*;

public class GoBackNReceiver {
    public static void main(String[] args) {
        try {
DatagramSocketreceiverSocket = new DatagramSocket(3039);
            int expectedSeqNum = 0;

            while (true) {
byte[] receiveData = new byte[1024];
DatagramPacketreceivePacket = new DatagramPacket(receiveData, receiveData.length);
receiverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                int packetSeqNum = Integer.parseInt(message.split(" ")[1]);

                if (packetSeqNum == expectedSeqNum) {
System.out.println("Received: " + message);
expectedSeqNum++;

                    String ackMessage = Integer.toString(expectedSeqNum - 1);
byte[] ackData = ackMessage.getBytes();
DatagramPacketackPacket = new DatagramPacket(ackData, ackData.length, receivePacket.getAddress(), receivePacket.getPort());
receiverSocket.send(ackPacket);
System.out.println("Sent ACK for: " + (expectedSeqNum - 1));
                } else {
                    String ackMessage = Integer.toString(expectedSeqNum - 1);
byte[] ackData = ackMessage.getBytes();
DatagramPacketackPacket = new DatagramPacket(ackData, ackData.length, receivePacket.getAddress(), receivePacket.getPort());

receiverSocket.send(ackPacket);
System.out.println("Sent ACK for: " + (expectedSeqNum - 1) + " (Out-of-order packet discarded)");
                }
            }
        } catch (Exception e) {
e.printStackTrace();
        }
    }
}
