package Network.Server;

import Database.DAO.IsFriendOfDao;
import Database.DAO.UserDao;
import Database.Models.User;
import Service.IsFriendOfService;
import Service.UserService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {



    private ArrayList<Socket> socketList = new ArrayList<>();
    private ServerSocket serverSocket;
    private int numberOfUsers;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Server Started");

        while (true) {
            Socket newUser = serverSocket.accept();
            socketList.add(newUser);

            //new SendInfo(socketList.get(socketList.size() - 1), socketList).start();
            User usr=new User(1,"","");
            new SendInfo(socketList.get(socketList.size()-1),socketList,usr).start();
        }

    }

}
