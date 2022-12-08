package Network.Server;

import Database.Models.Music;
import Database.Models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreadServer extends Thread {
    private Socket socket;
    private ArrayList<Socket> clients;
    private HashMap<Socket, User> socketClientList;
    private static HashMap<Integer, Integer> activity = new HashMap<>();

    private static HashMap<User, Music> info = new HashMap<>();

    public static HashMap<Integer, Integer> getActivity() {
        System.out.println("inside activity");
        System.out.println(activity.toString());
        return activity;
    }

    public static void setActivity(HashMap<Integer, Integer> activity) {
        ThreadServer.activity = activity;
    }

    public static HashMap<User, Music> getInfo() {
        return info;
    }

    public static void setInfo(HashMap<User, Music> info) {
        ThreadServer.info = info;
    }

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
                System.out.println("input:\n" + outputString);
                if(outputString.charAt(0)=='`'){
                    setActivity(saveInfo(outputString));

                }else if(outputString.charAt(0)=='~'){
                    setActivity(removeInfo(outputString,getActivity()));
                }
                System.out.println("Receveid9: \n" + outputString);
                System.out.println("Current:" + getActivity().toString());
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<Integer, Integer> removeInfo(String outputString,HashMap<Integer,Integer> hash) {
        String [] str = outputString.split("@");
        str[0] = str[0].substring(1,str[0].length());

        hash.remove(Integer.parseInt(str[0]));

        System.out.println("Removed Act:\n" + hash.toString());
        return hash;
//        UserDao userDao = new UserDao();
//        List<User> userList = userDao.getAll();
//        User user = new User(-1,null,null);
//        for(int i = 0; i < userList.size(); i++){
//            if(userList.get(i).getId()==Integer.parseInt(str[0])){
//                user = userList.get(i);
//                break;
//            }
//        }
//
//        info.remove(user);
//        System.out.println("+removedinfo\n"+info);
    }

    private HashMap<Integer, Integer> saveInfo(String outputString) {

        String [] str = outputString.split("@");
        str[0] = str[0].substring(1,str[0].length());

        activity.put(Integer.parseInt(str[0]),Integer.parseInt(str[1]));

        System.out.println("Added Act:\n" + activity.toString());
        return activity;


//        UserDao userDao = new UserDao();
//        MusicDao musicDao = new MusicDao();
//        List<User> userList = userDao.getAll();
//        List<Music> musicList = musicDao.getAll();
//        User user = new User(-1,null,null);
//        Music music = new Music(-1,null);
//
////        System.out.println("1 " + str[0]);
////        System.out.println("2 " + str[1]);
//
//        for(int i = 0; i < userList.size(); i++){
//            if(userList.get(i).getId()==Integer.parseInt(str[0])){
//                user = userList.get(i);
//                break;
//            }
//        }
//        for(int i = 0; i < musicList.size(); i++){
//            if(musicList.get(i).getId()==Integer.parseInt(str[1])){
//                music = musicList.get(i);
//                break;
//            }
//        }
//        if(info.containsKey(user)){
//            info.remove(user);
//        }
//        info.put(user,music);
//        System.out.println("+addedinfo\n" + info);

    }
}

