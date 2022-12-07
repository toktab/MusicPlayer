package Network.Server;

import Database.DAO.MusicDao;
import Database.DAO.UserDao;
import Database.Models.Music;
import Database.Models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ThreadServer extends Thread {
    private Socket socket;
    private ArrayList<Socket> clients;
    private HashMap<Socket, User> socketClientList;

    public static HashMap<User, Music> info = new HashMap<>();


    public ThreadServer(Socket socket, ArrayList<Socket> clients, HashMap<Socket, User> socketClientList) {
        this.socket = socket;
        this.clients = clients;
        this.socketClientList = socketClientList;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String outputString = input.readLine();//userid musicid
                if (outputString.equals("exit")) {
                    throw new SocketException();
                }
                if(outputString.charAt(0)=='`'){
                    saveInfo(outputString);
                }else if(outputString.charAt(0)=='~'){
                    removeInfo(outputString);
                }
                System.out.println("Receveid9: \n" + outputString);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeInfo(String outputString) {
        String [] str = outputString.split("@");
        str[0] = str[0].substring(1,str[0].length());
        UserDao userDao = new UserDao();
        List<User> userList = userDao.getAll();
        User user = new User(-1,null,null);
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getId()==Integer.parseInt(str[0])){
                user = userList.get(i);
                break;
            }
        }

        info.remove(user);
        System.out.println(info);
    }

    private void saveInfo(String outputString) {

        String [] str = outputString.split("@");
        str[0] = str[0].substring(1,str[0].length());

        UserDao userDao = new UserDao();
        MusicDao musicDao = new MusicDao();
        List<User> userList = userDao.getAll();
        List<Music> musicList = musicDao.getAll();
        User user = new User(-1,null,null);
        Music music = new Music(-1,null);

//        System.out.println("1 " + str[0]);
//        System.out.println("2 " + str[1]);

        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getId()==Integer.parseInt(str[0])){
                user = userList.get(i);
                break;
            }
        }
        for(int i = 0; i < musicList.size(); i++){
            if(musicList.get(i).getId()==Integer.parseInt(str[1])){
                music = musicList.get(i);
                break;
            }
        }
        if(info.containsKey(user)){
            info.remove(user);
        }
        info.put(user,music);
        System.out.println(info);

    }
}

