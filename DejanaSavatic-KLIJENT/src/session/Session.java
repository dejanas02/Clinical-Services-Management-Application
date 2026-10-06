/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domain.Lekar;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author korisnk
 */
public class Session {
    
    private static Session instance;
    private Socket socket;
    private Lekar ulogovani;
    
    private Session() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    } 

    public Lekar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Lekar ulogovani) {
        this.ulogovani = ulogovani;
    }

    
    
    
    
}
