package top.additional.Socket;

import top.additional.ContextUtil;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketThread extends Thread{
    private static SocketThread socketThread;
    protected int port = 10023;
    private ServletContext context;
    private ThreadPoolExecutor threadPoolRThread;

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
        port = Integer.parseInt(this.context.getInitParameter("port"));
    }

    protected List<Socket> socketList = (ArrayList<Socket>) ContextUtil.getBean("socketList");
    protected SocketWThreadManage socketWThreadManage = (SocketWThreadManage) ContextUtil.getBean("socketWThreadManage");

    public static synchronized SocketThread getInstance(){
        if (socketThread == null){
            socketThread = new SocketThread();
        }
        return socketThread;
    }

    public synchronized List<Socket> getSocketList() {
        return socketList;
    }

    public void delectSocket(Socket socket) {
        socketList.remove(socket);
    }

    private SocketThread(){
        threadPoolRThread = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        threadPoolRThread.setKeepAliveTime(10, TimeUnit.SECONDS);
//        System.out.println(threadPoolRThread.getPoolSize());
    };

    private void RThread(Socket socket){
        threadPoolRThread.execute(new SocketRThread(socket));
        int size = threadPoolRThread.getPoolSize();
    }

    private void WThread(Socket socket){
        SocketWThread socketWThread = new SocketWThread(socket);
        socketWThreadManage.add(socketWThread);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                RThread(socket);
                WThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Iterator<Socket> it = socketList.iterator();
            while (it.hasNext()){
                try {
                    it.next().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketList.clear();
            threadPoolRThread.shutdown();
        }
    }

    public void closeSocketServer() throws IOException {
        Iterator<Socket> it = socketList.iterator();
        while (it.hasNext()){
            it.next().close();
        }
        socketList.clear();
        threadPoolRThread.shutdown();
    }

}