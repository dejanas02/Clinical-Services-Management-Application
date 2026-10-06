package controller;

import domain.Drzavljanstvo;
import domain.Pacijent;
import domain.Racun;
import domain.Lekar;
import domain.StrucnaSprema;
import domain.VrstaMedUsluge;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import operacije.Operacije;
import operacije.Status;
import session.Session;               
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class KlijentController {

    private static KlijentController instance;

    private KlijentController() {}

    public static KlijentController getInstance() {
        if (instance == null) instance = new KlijentController();
        return instance;
    }

    private Object sendRequest(Operacije op, Object data) throws Exception {
        KlijentskiZahtev req = new KlijentskiZahtev(op, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        ServerskiOdgovor resp = (ServerskiOdgovor) in.readObject();

        if (resp.getResponseStatus() == Status.Error) {
            throw resp.getExc();
        }
        return resp.getOdgovor();
    }


    public Lekar login(Lekar creds) throws Exception {
        return (Lekar) sendRequest(Operacije.LOGIN, creds);
    }

    public ArrayList<Lekar> getAllLekar() throws Exception {
        return (ArrayList<Lekar>) sendRequest(Operacije.LEKAR_GET_ALL, null);
    }

    public void addPacijent(Pacijent p) throws Exception {
        sendRequest(Operacije.PACIJENT_KREIRAJ, p);
    }

    public ArrayList<Pacijent> getAllPacijent() throws Exception {
        return (ArrayList<Pacijent>) sendRequest(Operacije.PACIJENT_PRETRAZI, null);
    }
   
    public ArrayList<Pacijent> searchPacijent(Pacijent filter) throws Exception {
        return (ArrayList<Pacijent>) sendRequest(Operacije.PACIJENT_PRETRAZI, filter);
    }

    public void updatePacijent(Pacijent p) throws Exception {
        sendRequest(Operacije.PACIJENT_IZMENI, p);
    }

    public void deletePacijent(Pacijent p) throws Exception {
        sendRequest(Operacije.PACIJENT_OBRISI, p);
    }


    public void addRacun(Racun r) throws Exception {
        sendRequest(Operacije.RACUN_KREIRAJ, r);
    }

    public ArrayList<Racun> getAllRacun(Racun filter) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operacije.RACUN_PRETRAZI, filter);
    }

    public void updateRacun(Racun r) throws Exception {
        sendRequest(Operacije.RACUN_IZMENI, r);
    }

    public void addStrucnaSprema(StrucnaSprema ss) throws Exception {
        sendRequest(Operacije.STRUCNA_SPREMA_UNESI, ss);
    }

    public ArrayList<VrstaMedUsluge> getAllVrstaUsluge() throws Exception {
        return (ArrayList<VrstaMedUsluge>) sendRequest(Operacije.USLUGA_PRETRAZI, null);
    }

    public ArrayList<Drzavljanstvo> getAllDrzavljanstvo() throws Exception {
            return (ArrayList<Drzavljanstvo>) sendRequest(Operacije.DRZAVLJANSTVO_GET_ALL, null);
    }

    public void unesiUslugu(VrstaMedUsluge vu) throws Exception {
    sendRequest(Operacije.USLUGA_UNESI, vu);
    }

     public void logout(Lekar ulogovani) throws Exception {
        sendRequest(Operacije.LOGOUT, ulogovani);
    }
     
    public Racun getRacunById(int id) throws Exception {
        return (Racun) sendRequest(Operacije.RACUN_GET_ONE, id);
    }

}
