import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT =  3310;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             FileInputStream fileInputStream = new FileInputStream("sendfile.txt");
             OutputStream outputStream = socket.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

