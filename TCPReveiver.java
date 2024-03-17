import java.io.*;
import java.net.*;

public class TCPReceiver {
    private static final int SERVER_PORT = 3310;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
             Socket socket = serverSocket.accept();
             FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
             InputStream inputStream = socket.getInputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
