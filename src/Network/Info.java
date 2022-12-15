package Network;

import java.net.Socket;
import java.util.HashMap;

public class Info{
    HashMap<Integer,Integer> act = new HashMap<>();

    Socket socket = new Socket();

    public Info(HashMap<Integer, Integer> act) {
        this.act = act;
    }
    public Info(Socket socket) {
        this.socket = socket;
    }
}
