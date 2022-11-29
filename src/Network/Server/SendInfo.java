package Network.Server;

import Database.DAO.IsFriendOfDao;
import Database.DAO.UserDao;
import Database.Models.User;
import Service.IsFriendOfService;
import Service.UserService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SendInfo extends Thread{

    UserService userService = new UserService(new UserDao());
    IsFriendOfService isFriendOfService = new IsFriendOfService(new IsFriendOfDao(),new UserDao());
//        System.out.println(isFriendOfService.getFriendsByUser(user));


    private DataOutputStream dataOutputStream;
    private Socket user;
    private User currentUser;
    private List<User> allUsers;

    public SendInfo(Socket user, ArrayList<Socket> allUsers, User currentUser) {
        this.user = user;
        this.currentUser = currentUser;
        this.allUsers = isFriendOfService.getFriendsByUser(currentUser);
    }

    @Override
    public void run() {



//        while (true) {
//            String message = "";
//            try {
//                message = (new DataInputStream(user.getInputStream()).readUTF());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            String finalMessage = message;
//            System.out.println(finalMessage);
//            System.out.println(allUsers.size());
//            allUsers.forEach(user -> {
//                try {
//                    dataOutputStream = new DataOutputStream(user.getOutputStream());
//                    dataOutputStream.writeUTF(finalMessage);
//                    dataOutputStream.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            });
//
//        }
    }

}
