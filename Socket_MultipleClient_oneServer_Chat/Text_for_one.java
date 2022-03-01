package Socket_MultipleClient_oneServer_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Text_for_one implements Runnable {

    Thread t;
    public Socket socket;
    public BufferedReader bufferedReader;
    ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    ServerSocket serverSocket;

    Text_for_one(Socket socket, ServerSocket serverSocket, ArrayList<ClientHandler> clients) {

        this.socket = socket;
        this.serverSocket = serverSocket;
        this.clients = clients;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msgOut = null;
            try {
                msgOut = bufferedReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            msgOut = "Server : " + msgOut;
            String[] args = msgOut.split(" ", 4);
            String userName = args[2];
            for (int i = 0; i < clients.size(); i++) {
                ClientHandler tmpClient = clients.get(i);

                if (tmpClient.userName.equals(userName)) {
                    msgOut = args[0] + " : " + args[3];
                    try {
                        tmpClient.dataOutputStream.writeUTF(msgOut);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        tmpClient.dataOutputStream.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

            }
        }
    }

}
