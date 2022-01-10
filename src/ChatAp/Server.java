// To do
// 
//
package ChatAp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) throws Exception {

        ServerSocket serverSocket = new ServerSocket(1);
        System.out.println(InetAddress.getLocalHost());
        System.out.println("Server started.........");
        while (true) {

            try (Socket socket = serverSocket.accept()) {
                System.out.println("Server connected......");
                try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String s1 = "", s2 = "";
                    while (!s1.equals("stop")) {
                        
                        s1 = dataInputStream.readUTF();
                        System.out.println("Client says: " + s1);
                        
                        s2 = bufferedReader.readLine();
                        dataOutputStream.writeUTF(s2);
                        dataOutputStream.flush();
                        
                        
                        
                    }
                    dataInputStream.close();
                    socket.close();
                    serverSocket.close();
                }
            }
            
            

        }

    }
}
