package Network.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ThreadReader extends Thread{
    private Socket socket;
    private BufferedReader cin;
    @Override
    public void run(){
        try {
            while (true) {
                String message = cin.readLine();
                System.out.println(message);
            }
        } catch (SocketException e) {
            System.out.println("You left the server");
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                cin.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
