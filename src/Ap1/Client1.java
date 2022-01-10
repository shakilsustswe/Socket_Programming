package Ap1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client1 {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Client1 started...");
        Socket socket = new Socket("localhost", 1);
        System.out.println("Client1 Connected...");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        ///send to server...
        objectOutputStream.writeObject(message);

        try {
            ///receive from server...
            Object from_server = (String) objectInputStream.readObject();
            System.out.println("From server : " + from_server);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////System.out.println("...........");

    }

}
