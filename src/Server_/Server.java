package Server_;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(1);
        Socket socket = serverSocket.accept();
        System.out.println("Server connected...");
        
        FileInputStream fileInputStream = new FileInputStream("Send.txt");
        DataOutputStream dataOutputStream = new DataOutputStream((socket.getOutputStream()));
        
        int r;
        while ((r = fileInputStream.read()) != -1) {
            dataOutputStream.write(r);
        }
        System.out.println("\n File transfer Comleted");
        socket.close();
        serverSocket.close();
    }
}
