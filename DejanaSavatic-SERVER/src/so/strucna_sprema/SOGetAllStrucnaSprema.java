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
public class SOGetAllStrucnaSprema extends AbstractSO {

    private ArrayList<StrucnaSprema> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StrucnaSprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StrucnaSprema!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> sprema = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StrucnaSprema>) (ArrayList<?>) sprema;
    }

    public ArrayList<StrucnaSprema> getLista() {
        return lista;
    }
    
}
