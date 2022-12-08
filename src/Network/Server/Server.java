package Network.Server;

import Database.Models.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {

        ArrayList<Socket> clients = new ArrayList<>();
        HashMap<Socket, User> SocketUserList = new HashMap<Socket, User>();
//
        try (ServerSocket serversocket = new ServerSocket(1234)) {
            System.out.println("Server is started...");

            while (true) {
                Socket socket = serversocket.accept();
                clients.add(socket);
                ThreadServer ThreadServer = new ThreadServer(socket, clients, SocketUserList);
                ThreadServer.start();

                new Thread(() -> {
                    Scanner in = null;
                    try {
                        in = new Scanner(socket.getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Received:" + in.nextLine());
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
