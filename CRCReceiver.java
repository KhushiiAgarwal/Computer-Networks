import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3310);
            System.out.println("Waiting for sender...");

            Socket socket = serverSocket.accept();
            System.out.println("Sender connected");

            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            String receivedData = in.readLine();
            String divisor = in.readLine();

            int errorDetected = crcCheck(receivedData, divisor);

            if (errorDetected != 0) {
                System.out.println("Error detected in the received data.");
            } else {
                System.out.println("No errors detected in the received data.");
            }

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int crcCheck(String data, String divisor) {
        int dataLength = data.length();
        int divisorLength = divisor.length();

        char[] dataChars = data.toCharArray();
        char[] remainder = new char[dataLength];

        System.arraycopy(dataChars, 0, remainder, 0, divisorLength - 1);

        for (int i = 0; i < dataLength - divisorLength + 1; i++) {
            if (remainder[i] == '1') {
                xorOperation(remainder, divisor, i);
            }
        }

        for (char c : remainder) {
            if (c == '1')
                return 1; 
        }

        return 0; 
    }

    public static void xorOperation(char[] a, String b, int startIndex) {
        for (int i = 0; i < b.length(); i++) {
            if (a[i + startIndex] == b.charAt(i))
                a[i + startIndex] = '0';
            else
                a[i + startIndex] = '1';
        }
    }
}
