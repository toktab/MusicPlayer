package Network.Client;

import Database.Models.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
    public HashMap<Integer,Integer> getCurrentActivity() {

        // get the input stream from the connected socket
        InputStream inputStream = null;
        try {
            inputStream = s.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // read the list of messages from the socket
        try {
            HashMap<Integer, Integer> act = (HashMap<Integer, Integer>) objectInputStream.readObject();
//            System.out.println(act);
            return act;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//        HashMap<Integer,Integer> activty = new HashMap<>();
//        class Info{
//            HashMap<Integer,Integer> act = new HashMap<>();
//
//            public Info(HashMap<Integer, Integer> act) {
//                this.act = act;
//            }
//        }
//        try {
//            OutputStream outputStream = s.getOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject(activty);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    public void sendActivityToServer(int id, int userId,boolean listening){
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
    public void sendRequestForActivity(){
        try {

            PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
            printWriter.println("!");
            System.out.println("Sent ! - client");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendRequestForActivityForFriend(int friendId){
        try {

            PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
            printWriter.println("!" + friendId);
            System.out.println("Sent friendID ! - client");

        }
        catch(Exception e){
            e.printStackTrace();
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
