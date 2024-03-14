import java.io.*;
import java.net.*;

public class HammingSender {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3310);
            System.out.println("Connected to Receiver");

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter ASCII data to send: ");
            String inputData = br.readLine();
            out.println(inputData);

            socket.close();
            System.out.println("Data sent to Receiver");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
