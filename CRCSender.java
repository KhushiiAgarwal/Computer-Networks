import java.io.*;
import java.net.*;
import java.util.*;

public class CRCSender {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket("localhost", 3310);
            
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            
            System.out.print("Enter ASCII data to send: ");
            String data = scanner.nextLine();
            out.println(data);
            
            System.out.print("Enter CRC divisor: ");
            String divisor = scanner.nextLine();
            out.println(divisor);
            
            scanner.close();
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
