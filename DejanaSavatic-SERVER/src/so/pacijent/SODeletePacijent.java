/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pacijent;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Pacijent;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SODeletePacijent extends AbstractSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Pacijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pacijent!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
