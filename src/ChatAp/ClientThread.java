
package ChatAp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

class ClientThread implements Runnable{

    private Socket socket = null;
    public ClientThread(Socket socket){
        try{
            this.socket = socket;
        }
        catch(Exception e){
            
        }
    }
    
    
    @Override
    public void run() {
        
        
         try{
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello client");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientInput = input.readLine();
            System.out.println(clientInput);

            input.close();
            out.close();
            socket.close();
        }
        catch (Exception e){

        }
        
        
    }
    
    
    public void start()
    {
        Thread thread = new Thread(this);
        thread.start();
    }
    
    
    
}
