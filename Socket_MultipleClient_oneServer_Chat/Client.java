package Socket_MultipleClient_oneServer_Chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class SendMessage implements Runnable {

    Thread t;
    public Socket socket;
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public BufferedReader bufferedReader;
    String userName;

    public SendMessage(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                sendMessage();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void sendMessage() throws IOException {
        String msgOut = bufferedReader.readLine();   //read input from terminal
        msgOut = userName + " : " + msgOut;
        dataOutputStream.writeUTF(msgOut);
    }
}

class ReceiveMessage implements Runnable {

    Thread t;
    public Socket socket;
    public DataInputStream dataInputStream;

    ReceiveMessage(Socket socket) {
        this.socket = socket;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {

        while (true) {
            receive();
        }

    }

    private void receive() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

        String msgIn = null;
        try {
            msgIn = dataInputStream.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(msgIn);

    }

}

public class Client {

    public Socket socket;
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public BufferedReader bufferedReader;
    String userName;

    public static void main(String args[]) throws Exception {

        new Client().start();

    }

    private void start() throws IOException {

        socket = new Socket("localhost", 1);
        System.out.println("Server connected......");
        System.out.println("Client connected...");
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
        userName = bufferedReader.readLine();
        dataOutputStream.writeUTF(userName);
        run();

    }

    public void sendMessage() throws IOException {
        String msgOut = bufferedReader.readLine();   //read input from terminal
        msgOut = userName + " : " + msgOut;
        dataOutputStream.writeUTF(msgOut);
    }

    public void receiveMessage() throws IOException {
        String msgIn = dataInputStream.readUTF();
        System.out.println(msgIn);
    }

    private void run() {

        SendMessage sendMess = new SendMessage(socket, userName);
        ReceiveMessage receiveMess = new ReceiveMessage(socket);
    }

}
