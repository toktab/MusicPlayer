package Network.Server;

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

public class ThreadServer extends Thread {
    private Socket socket;
    private ArrayList<Socket> clients;
    private HashMap<Socket, User> socketClientList;

    public ThreadServer(Socket socket, ArrayList<Socket> clients, HashMap<Socket, User> socketClientList) {
        this.socket = socket;
        this.clients = clients;
        this.socketClientList = socketClientList;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String outputString = input.readLine();
                if (outputString.equals("exit")) {
                    throw new SocketException();
                }
                System.out.println(outputString);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

