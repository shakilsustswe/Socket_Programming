package Client_;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1);
        if (socket.isConnected()) {
            System.out.println("Client connected.........");
        }
        FileOutputStream fileOutputStream = new FileOutputStream("received.txt");
        DataInputStream dataInputStream = new DataInputStream((socket.getInputStream()));
        
        int r;
        while ((r = dataInputStream.read()) != -1) {
            
            fileOutputStream.write((char) r);

        }
        socket.close();
    } 
}
