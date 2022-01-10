//
package FileTransfer;
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//
//public class Client {
//
//    public static void main(String srgs[]) throws IOException {
//        Socket socket = null;
//        BufferedReader get = null;
//        PrintWriter put = null;
//        try {
//            socket = new Socket("localhost", 1);
//            get = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            put = new PrintWriter(socket.getOutputStream(), true);
//        } catch (Exception e) {
//            System.exit(0);
//        }
//        
//        String u, f;
//        System.out.println("Enter the file name to transfer from server:");
//        DataInputStream dataInputStream = new DataInputStream(System.in);
//        f = dataInputStream.readLine();
//        put.println(f);
//        File f1 = new File("E:\\All Courses\\bus\\2018831072.pdf");
//        FileOutputStream fileOutputStream = new FileOutputStream(f1);
//        while ((u = get.readLine()) != null) {
//            byte jj[] = u.getBytes();
//            fileOutputStream.write(jj);
//        }
//        fileOutputStream.close();
//        System.out.println("File received");
//        socket.close();
//    }
//}

