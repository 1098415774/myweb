package top.additional.Socket;

import top.additional.ContextUtil;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketWThread{
    private Socket socket;
    private BufferedWriter writer;
    private String msg = "";
    private List<Socket> socketList = (ArrayList<Socket>) ContextUtil.getBean("socketList");

    SocketWThread(Socket socket){
        this.socket = socket;
        init();
    }

    private void init(){
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            writer.write("success\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMsg(String msg) throws IOException {
        this.msg = msg + "\n";
        if (!socketList.contains(socket)){
            writer.close();
            return;
        }
        writer.write(this.msg);
        writer.flush();
    }

    public int getSocketHashCode(){
        return socket.hashCode();
    }
}
