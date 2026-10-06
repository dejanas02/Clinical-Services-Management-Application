/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pacijent;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Pacijent;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOAddPacijent extends AbstractSO {

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Pacijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pacijent!");
        }

        Pacijent p = (Pacijent) ado;

        if (!p.getEmail().contains("@")) {
            throw new Exception("Neispravan format email-a!");
        }
        ArrayList<Pacijent> pacijenti = (ArrayList<Pacijent>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
