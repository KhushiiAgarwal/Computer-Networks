import java.io.*;
import java.net.*;

public class SelectiveRepeatReceiver {
    public static void main(String[] args) {
        try {
DatagramSocket socket = new DatagramSocket(3309);
            int windowSize = 4;
            int totalPackets = 10;
            int base = 0;
            int nextSeqNum = 0;

            while (base <totalPackets) {
byte[] data = new byte[1024];
DatagramPacket packet = new DatagramPacket(data, data.length);
socket.receive(packet);
                String message = new String(packet.getData()).trim();
                int seqNum = Integer.parseInt(message.split(" ")[1]);

                if (Math.random() < 0.3) {
System.out.println("Packet " + seqNum + " lost.");
                    continue;
                }

                if (seqNum>= base &&seqNum< base + windowSize) {
                    if (seqNum == base) {
System.out.println("Received: " + message);
                        base++;
                    } else {
System.out.println("Received out-of-order packet: " + message);
                    }

byte[] ackData = (seqNum + "").getBytes();
DatagramPacketackPacket = new DatagramPacket(ackData, ackData.length, packet.getAddress(), packet.getPort());
socket.send(ackPacket);
                }
            }
socket.close();
        } catch (Exception e) {
e.printStackTrace();
        }
    }
}
