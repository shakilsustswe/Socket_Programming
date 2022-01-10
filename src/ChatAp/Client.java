/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatAp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String args[]) throws Exception {
        try (Socket socket = new Socket("localhost", 1)) {
            System.out.println("Client connected...");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String s1 = "", s2 = "";
                while (!s2.equals("stop")) {
                    
                    s2 = bufferedReader.readLine();
                    dataOutputStream.writeUTF(s2);
                    dataOutputStream.flush();
                    
                    s1 = dataInputStream.readUTF();
                    System.out.println("Server says: " + s1);
                    
                    
                    
                }
                dataOutputStream.close();
                socket.close();
            }
        }

    }
}
