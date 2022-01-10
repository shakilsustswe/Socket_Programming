package ChatAp;

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
import java.util.logging.Level;
import java.util.logging.Logger;

class ReaderS implements Runnable {

    DataOutputStream dataOutputStream = null;
    DataInputStream dataInputStream = null;
    Thread t;
    Socket socket = null;

    ReaderS(Socket socket) {
        this.socket = socket;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

//        try {
//            socket = new Socket("localhost", 1);
//        } catch (IOException ex) {
//            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ////System.out.println("Client connected...");
        
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s2 = "";
        while (!s2.equals("stop")) {
            try {
                s2 = dataInputStream.readUTF();
//        for (int i = 0; i < 10; i++) {
//            System.out.println("ReaderS "+i);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//        }
            } catch (IOException ex) {
                Logger.getLogger(ReaderS.class.getName()).log(Level.SEVERE, null, ex);
            }
            File f = new File(s2);
            System.out.println(f.canRead());
            
            if (f.canRead()) {
                try {
                    receiveFile("ClientFile.pdf");
                    /// receiveFile(s2);
                } catch (Exception ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (!s2.equals("")) {
                System.out.println("Client says : " + s2);
            }
        }
    }
     public void receiveFile(String fileName) throws Exception {

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

class WriterS implements Runnable {

    DataOutputStream dataOutputStream = null;
    DataInputStream dataInputStream = null;
    Thread t;
    Socket socket = null;

    WriterS(Socket socket) {
        this.socket = socket;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

//        try {
//            socket = new Socket("localhost", 1);
//        } catch (IOException ex) {
//            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
//        }
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

//        for (int i = 0; i < 10; i++) {
//            System.out.println("WriterS "+i);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//        }
        String s1 = "";
        while (!s1.equals("stop")) {
            try {
                s1 = bufferedReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(WriterS.class.getName()).log(Level.SEVERE, null, ex);
            }
            File f = new File(s1);
            if (f.canRead()) {
                try {
                    sendFile(s1);
                    /// receiveFile(s2);
                } catch (Exception ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    dataOutputStream.writeUTF(s1);
                } catch (IOException ex) {
                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                dataOutputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(WriterS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendFile(String path) throws Exception {

        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4 * 1024];

        while ((bytes = fileInputStream.read(buffer)) != -1) {

            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();

    }
}

public class Server2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1);
        System.out.println(InetAddress.getLocalHost());
        System.out.println("Server started.........");

        Socket socket = serverSocket.accept();
        ///ClientThread clientThread = new ClientThread(socket);
        System.out.println("Server connected......");

        ReaderS reader = new ReaderS(socket);
        WriterS writer = new WriterS(socket);

    }

}
