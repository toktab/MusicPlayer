package Network.Client;

import Database.Models.User;
import Network.Server.Server;
import Network.Server.ThreadServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client {

    Socket s;
    User user;

    public Client(User user) {
        this.user = user;
        try{
            this.s = new Socket("localhost",1234);
            this.user = user;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect() throws Exception {

        ThreadClient threadClient = new ThreadClient(this.s,user);
        threadClient.start();
    }

    public void updateCurrentMusic(int id,int userId,boolean listening){
        new Thread(() -> {
            ThreadClient threadClient = new ThreadClient(this.s,user);
            threadClient.sendToServer(id,userId,listening);
        }).start();


    }
    public HashMap<Integer, Integer> getActivity(){
        HashMap<Integer,Integer> ac = new HashMap<>();
        Server server = new Server();
        server.sendActivity();
        ThreadClient threadClient = new ThreadClient(this.s,user);
        return threadClient.getCurrentActivity();
    }

}
