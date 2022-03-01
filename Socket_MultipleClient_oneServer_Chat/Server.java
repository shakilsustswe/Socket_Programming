package Socket_MultipleClient_oneServer_Chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Client_Add implements Runnable {

    Thread t;
    public Socket socket;
    ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    ServerSocket serverSocket;

    Client_Add(Socket socket, ServerSocket serverSocket, ArrayList<ClientHandler> clients) {

        this.socket = socket;
        this.serverSocket = serverSocket;
        this.clients = clients;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();
                ClientHandler newClient = null;
                newClient = new ClientHandler(socket);
                clients.add(newClient);
                newClient.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}

public class Server {

    public Socket socket;
    public BufferedReader bufferedReader;
    ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) throws IOException {

        new Server().ClientAdd();

    }

    private void ClientAdd() throws IOException {

        ServerSocket serverSocket = new ServerSocket(1);
        System.out.println(InetAddress.getLocalHost());
        System.out.println("Server started.........");

        Client_Add client_add = new Client_Add(socket, serverSocket, clients);/////add new Client
        Text_for_one text_for_one = new Text_for_one(socket, serverSocket, clients);/////text to perticular client

    }

}
