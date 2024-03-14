import java.io.*;
import java.net.*;

public class HammingReceiver {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3310);
            System.out.println("Waiting for Sender...");

            Socket socket = serverSocket.accept();
            System.out.println("Sender Connected");

            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            String receivedData = in.readLine();
            System.out.println("Received Data: " + receivedData);

            String decodedData = decodeHamming(receivedData);
            System.out.println("Decoded Data: " + decodedData);

            socket.close();
            serverSocket.close();
            System.out.println("Connection Closed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decodeHamming(String receivedData) {
        StringBuilder decodedData = new StringBuilder();
        int dataLength = receivedData.length();
        
        for (int i = 0; i < dataLength; i += 12) {
            String hammingBlock = receivedData.substring(i, Math.min(i + 12, dataLength));
            String decodedBlock = decodeHammingBlock(hammingBlock);
            decodedData.append(decodedBlock);
        }
        
        return decodedData.toString();
    }
    
    public static String decodeHammingBlock(String hammingBlock) {
        StringBuilder decodedBlock = new StringBuilder();
        int[] parityPositions = {1, 2, 4, 8};
        int parityBitCount = 4;
        
        for (int i = 0; i < parityBitCount; i++) {
            int parityPosition = parityPositions[i];
            int parityBit = calculateParity(hammingBlock, parityPosition);
            decodedBlock.append(parityBit);
        }
        
        int dataStartIndex = parityBitCount + 1;
        for (int i = dataStartIndex; i <= hammingBlock.length(); i++) {
            decodedBlock.append(hammingBlock.charAt(i - 1));
        }
        
        return decodedBlock.toString();
    }
    
    public static int calculateParity(String hammingBlock, int parityPosition) {
        int count = 0;
        int index = parityPosition - 1;
        
        while (index < hammingBlock.length()) {
            for (int i = 0; i < parityPosition && index < hammingBlock.length(); i++) {
                if (hammingBlock.charAt(index) == '1') {
                    count++;
                }
                index++;
            }
            index += parityPosition;
        }
                return count % 2;
    }
}
