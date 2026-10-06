/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Lekar;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOLogin extends AbstractSO{
    
    Lekar ulogovaniLekar;
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Lekar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lekar!");
        }
        Lekar l = (Lekar) ado;
        for (Lekar lekar : ServerController.getInstance().getUlogovaniLekari()) {
            if (lekar.getKorisnickoIme().equals(l.getKorisnickoIme())) {
                throw new Exception("Lekar je vec ulogovan!");
            }
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        Lekar l = (Lekar) ado;

        ArrayList<Lekar> lekari
                = (ArrayList<Lekar>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Lekar lekar : lekari) {
            if (lekar.getKorisnickoIme().equals(l.getKorisnickoIme())
                    && lekar.getSifra().equals(l.getSifra())) {
                ulogovaniLekar = lekar;
                ServerController.getInstance().getUlogovaniLekari().add(lekar);
                return;
            }
        }
        throw new Exception("Lekar sa tim kredencijalima ne postoji!");
    }

    public Lekar getUlogovaniLekar() {
        return ulogovaniLekar;
    }
    
    
}
