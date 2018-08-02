package top.additional.Socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SocketWThreadManage{
    private ConcurrentHashMap<Integer,SocketWThread> manageMap = new ConcurrentHashMap<Integer, SocketWThread>();
    private HashSet<SocketWThread> set = new HashSet<SocketWThread>();

    SocketWThreadManage(){
    }

    public void add(SocketWThread socketWThread){
        set.add(manageMap.put(socketWThread.getSocketHashCode(),socketWThread));
    }

    public synchronized void writeToSocket(Integer key,String msg) throws IOException {
        SocketWThread socketWThread = manageMap.get(key);
        socketWThread.setMsg(msg);
    }

    public void remove(int socketcode){
        set.remove(manageMap.remove(socketcode));
    }

}
