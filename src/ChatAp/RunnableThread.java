package ChatAp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RunnableThread {

    public static void main(String[] args) {
        System.out.println("Main Thread Started...");

        Reader reader = new Reader();
        Writer writer = new Writer();

        System.out.println("Reader is alive: " + reader.t.isAlive());
        System.out.println("Writer is alive: " + writer.t.isAlive());

        try {
            reader.t.join();
            writer.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Reader is alive: " + reader.t.isAlive());
        System.out.println("Writer is alive: " + writer.t.isAlive());

        System.out.println("Main Thread Exited...");
    }
}
