import java.io.*;
import java.net.*;

public class SelectiveRepeatSender {
    public static void main(String[] args) {
        try {
DatagramSocket socket = new DatagramSocket();

InetAddressreceiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 3309;
            int windowSize = 4;
            int totalPackets = 10;

byte[] data = "Hello, Receiver!".getBytes();

            int base = 0;
            int nextSeqNum = 0;

            while (base <totalPackets) {
                for (int i = base; i<Math.min(nextSeqNum + windowSize, totalPackets); i++) {
DatagramPacket packet = new DatagramPacket(data, data.length, receiverAddress, receiverPort);
packet.setData(("Packet " + i).getBytes());

socket.send(packet);
System.out.println("Sent: " + new String(packet.getData()));

nextSeqNum++;
                }

byte[] ackData = new byte[1024];
DatagramPacketackPacket = new DatagramPacket(ackData, ackData.length);
socket.receive(ackPacket);
                int ack = Integer.parseInt(new String(ackPacket.getData()).trim());

System.out.println("Received acknowledgment for packet " + ack);
                base = ack + 1;
            }

socket.close();
        } catch (Exception e) {
e.printStackTrace();
        }
    }
}
import java.io.*;
import java.net.*;

public class S4Sender {
    public static void main(String[] args) {
        try {
DatagramSocket socket = new DatagramSocket();

InetAddressreceiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 3309;
            int windowSize = 4;
            int totalPackets = 10;

byte[] data = "Hello, Receiver!".getBytes();

            int base = 0;
            int nextSeqNum = 0;

            while (base <totalPackets) {
                for (int i = base; i<Math.min(nextSeqNum + windowSize, totalPackets); i++) {
DatagramPacket packet = new DatagramPacket(data, data.length, receiverAddress, receiverPort);
packet.setData(("Packet " + i).getBytes());

socket.send(packet);
System.out.println("Sent: " + new String(packet.getData()));

nextSeqNum++;
                }

byte[] ackData = new byte[1024];
DatagramPacketackPacket = new DatagramPacket(ackData, ackData.length);
socket.receive(ackPacket);
                int ack = Integer.parseInt(new String(ackPacket.getData()).trim());

System.out.println("Received acknowledgment for packet " + ack);
                base = ack + 1;
            }

socket.close();
        } catch (Exception e) {
e.printStackTrace();
        }
    }
}
