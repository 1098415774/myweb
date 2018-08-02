package top.additional.Socket;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class SocketWThreadMap<K,V> extends ConcurrentHashMap<K,V>{
    private HashSet<SocketWThread> set = new HashSet<SocketWThread>();

    @Override
    public V put(K key, V value) {

        return super.put(key, value);
    }
}
