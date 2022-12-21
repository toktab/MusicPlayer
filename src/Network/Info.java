package Network;

import Global.Pair;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;

public class Info implements Serializable {
    HashMap<Integer,Integer> act = null;
    Pair<Integer, Integer> pair = null;

    public Info(HashMap<Integer, Integer> act) {
        this.act = act;
    }
    public Info(Pair<Integer,Integer> pair){
        this.pair = pair;
    }
}
