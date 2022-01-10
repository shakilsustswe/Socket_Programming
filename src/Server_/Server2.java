package Server_;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1)) {

            System.out.println("listening to port:1");
            Socket clientSocket = serverSocket.accept();

            System.out.println(clientSocket + " connected.");

            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            receiveFile("ClientFile.pdf");
            ///receiveFile("NewFile2.pdf");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
//            
        } catch (Exception e) {
            System.out.println("******");
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName) throws Exception {

        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4 * 1024];

        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // read upto file size
            
        }
        fileOutputStream.close();

    }

}
