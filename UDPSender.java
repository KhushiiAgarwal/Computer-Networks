import java.net.*;

public class UDPSender {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();
        byte[] sendData = new byte[1024];
        String message = "Hello from Sender!";
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 3310);
        socket.send(sendPacket);
        socket.close();
    }
}

