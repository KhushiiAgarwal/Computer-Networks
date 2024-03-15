import java.io.*;
import java.net.*;

public class GoBackNSender {
    public static void main(String[] args) {
        try {
DatagramSocketsenderSocket = new DatagramSocket();
InetAddressreceiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 3039;

            int windowSize = 4; 
            int totalPackets = 10;
            int base = 0;
            int nextSeqNum = 0;

            while (base <totalPackets) {
                while (nextSeqNum< base + windowSize&&nextSeqNum<totalPackets) {
                    String data = "Packet " + nextSeqNum;
byte[] sendData = data.getBytes();
DatagramPacket packet = new DatagramPacket(sendData, sendData.length, receiverAddress, receiverPort);
senderSocket.send(packet);
System.out.println("Sent: " + data);
nextSeqNum++;
                }

                try {
Thread.sleep(2000); 

byte[] receiveData = new byte[1024];
DatagramPacketackPacket = new DatagramPacket(receiveData, receiveData.length);
senderSocket.receive(ackPacket);
                    String ackMessage = new String(ackPacket.getData(), 0, ackPacket.getLength());
                    int ackNum = Integer.parseInt(ackMessage);

System.out.println("Received ACK for: " + ackNum);
                    base = ackNum + 1;
                } catch (SocketTimeoutException e) {
System.out.println("Timeout occurred. Resending window.");
nextSeqNum = base;
                }
            }

senderSocket.close();
System.out.println("Sender finished.");

        } catch (Exception e) {
e.printStackTrace();
        }
    }
}

