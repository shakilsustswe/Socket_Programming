
//package FileTransfer;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//
//public class Server {
//    
//     public static void main(String args[])throws IOException
//    { 
//        ServerSocket ss=null;
//        try
//        {  
//            ss=new ServerSocket(1);
//        }
//        catch(IOException e)
//        { 
//            System.out.println("couldn't listen");
//            System.exit(0);
//        }
//        Socket cs=null;
//        try
//        { 
//            cs=ss.accept();
//            System.out.println("Connection established"+cs);
//        }
//        catch(Exception e)
//        { 
//            System.out.println("Accept failed");
//            System.exit(1);
//        } 
//        PrintWriter put=new PrintWriter(cs.getOutputStream(),true);
//        BufferedReader st=new BufferedReader(new InputStreamReader(cs.getInputStream()));
//        String s=st.readLine();
//        System.out.println("The requested file is : "+s);
//        File f=new File(s);
//        if(f.exists())
//        { 
//            BufferedReader d=new BufferedReader(new FileReader(s));
//            String line;
//            while((line=d.readLine())!=null)
//            {
//                put.write(line);
//                put.flush();
//            }
//            d.close();
//            System.out.println("File transfered");
//            cs.close();
//            ss.close();
//        }  
//    }  
//}

