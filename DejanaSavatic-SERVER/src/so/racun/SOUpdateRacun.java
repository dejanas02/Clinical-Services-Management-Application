package so.racun;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import so.AbstractSO;

public class SOUpdateRacun extends AbstractSO {

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;
        if (r.getIdRacuna() <= 0) {
            throw new Exception("Racun mora imati vazeci ID za izmenu.");
        }
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku.");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        Racun r = (Racun) ado;

        
        
        DBBroker.getInstance().update(r);
        
       

        ArrayList<StavkaRacuna> stareStavke=(ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(new StavkaRacuna(r.getIdRacuna(), 0, null, 0, 0, 0));
        
        HashMap<Integer, StavkaRacuna> mapaStarih=new HashMap<>();
        for(StavkaRacuna sr: stareStavke){
            mapaStarih.put(sr.getRb(), sr);
        }
        
        HashMap<Integer, StavkaRacuna> mapaNovih=new HashMap<>();
        for(StavkaRacuna novaStavka: r.getStavke()){
            mapaNovih.put(novaStavka.getRb(), novaStavka);
        }
        
        for(StavkaRacuna staraStavka: stareStavke){
            if(!mapaNovih.containsKey(staraStavka.getRb())){
                DBBroker.getInstance().delete(staraStavka);
            }
        }
        
        for(StavkaRacuna novaStavka: r.getStavke()){
            if(mapaStarih.containsKey(novaStavka.getRb())){
                DBBroker.getInstance().update(novaStavka);
            }
        }
        
        for(StavkaRacuna novaStavka : r.getStavke()){
            if(!mapaStarih.containsKey(novaStavka.getRb())){
                DBBroker.getInstance().insert(novaStavka);
            }
        }
        
    }
}
