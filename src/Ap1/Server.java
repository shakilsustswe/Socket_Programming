package Ap1;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1);
        ///ServerSocket serverSocket2 = new ServerSocket(2);
        System.out.println("Server Started...");

        while (true) {

            ///Socket socket2 = serverSocket2.accept();
            Socket socket = serverSocket.accept();
            System.out.println("Client connected...");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

//            ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());
//            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
            try {
                ///read from client.........
                Object ClientMessage = (String) objectInputStream.readObject();
                ///Object ClientMessage2 = (String) objectInputStream2.readObject();

                String Message = "";
                if ("" != ClientMessage) {
                    System.out.println("From Client1 : " + ClientMessage);
                } else {
                    Scanner input = new Scanner(System.in);
                    Message = input.nextLine();
                }

//                if(""!=ClientMessage2) {
//                    System.out.println("From Client2 : " + ClientMessage2);
//                    ClientMessage=ClientMessage2;
//                } 
                String serverMessage = (String) ClientMessage;
                if (serverMessage != "") {
                    serverMessage = Message;
                }
                ///String serverMessage2 = (String) ClientMessage2;
                serverMessage = serverMessage.toUpperCase();
                //serverMessage2 = serverMessage2.toUpperCase();
                ///send to client...
                System.out.println(serverMessage);
                objectOutputStream.writeObject(serverMessage);
                /// objectOutputStream2.writeObject(serverMessage);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
