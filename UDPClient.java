import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getLocalHost();
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String message = "Hello from Client!";
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, 3310);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String responseMessage = new String(receivePacket.getData());
        System.out.println("Message from Server: " + responseMessage);
        clientSocket.close();
    }
}
