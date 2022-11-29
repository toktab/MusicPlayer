package Network.Server;

import java.io.IOException;

public class MyServer {
    public static void main(String[] args) {

        try{
            Server server = new Server(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
