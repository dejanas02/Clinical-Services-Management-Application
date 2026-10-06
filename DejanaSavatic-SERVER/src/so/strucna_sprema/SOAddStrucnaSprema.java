/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.strucna_sprema;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.StrucnaSprema;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author korisnk
 */
public class SOAddStrucnaSprema  extends AbstractSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StrucnaSprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StrucnaSprema!");
        }

        StrucnaSprema ss = (StrucnaSprema) ado;


        ArrayList<StrucnaSprema> spreme = (ArrayList<StrucnaSprema>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (StrucnaSprema sprema : spreme) {
            if (sprema.getNaziv().equals(ss.getNaziv())) {
                throw new Exception("Strucna sprema sa tim imenom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
