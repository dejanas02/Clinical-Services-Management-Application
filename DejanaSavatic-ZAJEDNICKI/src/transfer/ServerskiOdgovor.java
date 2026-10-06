/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import operacije.Status;

/**
 *
 * @author korisnk
 */
public class ServerskiOdgovor implements Serializable {
    private Object odgovor;
    private Exception exc;
    private Status responseStatus;
    
    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public ServerskiOdgovor(Object odgovor, Exception exc, Status responseStatus) {
        this.odgovor = odgovor;
        this.exc = exc;
        this.responseStatus = responseStatus;
    }

    
    
    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }

    public Status getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Status responseStatus) {
        this.responseStatus = responseStatus;
    }
     
    
      
}
