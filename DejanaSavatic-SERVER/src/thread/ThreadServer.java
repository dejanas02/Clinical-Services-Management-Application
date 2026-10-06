/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author korisnk
 */
public class ThreadServer extends Thread{
    
    private ServerSocket serverSocket;

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ThreadClient th = new ThreadClient(socket);
                th.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    
}
