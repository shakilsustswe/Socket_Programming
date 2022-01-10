package Client_;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1)) {

            ///String f = null;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enter the file name to transfer from server:");

            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            
            try {
                File file = new File(str);
                ///file.createNewFile();
                System.out.println("Is directory? " + file.canRead());
                if(file.canRead())sendFile(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //sendFile("C:\\Users\\Asus\\Desktop\\English.pdf");
            
            
            ///sendFile("C:\\Users\\Asus\\Desktop\\book.pdf");

            dataInputStream.close();
            dataOutputStream.close();

        } catch (Exception e) {

            System.out.println("/////////////");
            e.printStackTrace();
        }
    }

    private static void sendFile(String path) throws Exception {

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
