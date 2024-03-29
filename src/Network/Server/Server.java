package Network.Server;

import Database.Models.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    private static Socket socket;
    public static void main(String[] args) throws Exception {

        HashMap<Integer,Integer> activity = new HashMap<>();
        ArrayList<Socket> clients = new ArrayList<>();
        HashMap<Socket, User> SocketUserList = new HashMap<Socket, User>();
//
        try (ServerSocket serversocket = new ServerSocket(1234)) {
            System.out.println("Server is started...");

            while (true) {
                socket = serversocket.accept();
                clients.add(socket);

                ThreadServer ThreadServer = new ThreadServer(socket, clients, SocketUserList,activity);
                ThreadServer.start();

//                new Thread(() -> {
//                    Scanner in = null;
//                    try {
//                        in = new Scanner(socket.getInputStream());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("Received:" + in.nextLine());
//                    Network.Server.ThreadServer.sendActivity(socket);
//                }).start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendActivity() {
        try {
            ThreadServer.sendActivity(new Socket("localhost",1234));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
