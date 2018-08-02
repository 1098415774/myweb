package top.additional;

import top.additional.Socket.SocketThread;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class TCPServerListener implements ServletContextListener {
    private SocketThread socketThread;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        if (socketThread == null){
            socketThread = SocketThread.getInstance();
            socketThread.setContext(context);
            socketThread.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (socketThread != null && !socketThread.isInterrupted()){
            try {
                socketThread.closeSocketServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socketThread.interrupt();
        }
    }
}
