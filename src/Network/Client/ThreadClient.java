package Network.Client;

import Database.Models.User;

import java.io.IOException;
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

    public void sendToServer(int id, int userId,boolean listening){
        if(listening) {//LISTENING
            try {
                PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
                printWriter.println("`" + userId + "@" + id);//3 aq gaigzaavna
                System.out.println("Sent Info! - client");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {//NOT LISTENING
            try {
                PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
                printWriter.println("~" + userId + "@" + id);//3 aq gaigzaavna
                System.out.println("Sent Info not listening ! - client");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
