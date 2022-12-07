package Network.Client;

import Database.Models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClient extends Thread{

    Socket s;
    User user;

    public ThreadClient(Socket s, User user) {
        this.s = s;
        this.user = user;
    }


    public void sendMessage(String message){
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pr.println(message);
        pr.flush();
    }

    public void run() {
        Socket s;
        try{
            s = new Socket("localhost",1234);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        sendMessage(user.getId() + " " + user.getUsername());
    }
}
