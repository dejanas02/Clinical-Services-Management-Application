package so.racun;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetRacun extends AbstractSO {

    private ArrayList<Racun> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        
        ArrayList<ApstraktniDomenskiObjekat> racuniADO = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Racun>) (ArrayList<?>) racuniADO;

        
        for (Racun r : lista) {
            StavkaRacuna sFilter = new StavkaRacuna();
            sFilter.setIdRacuna(r.getIdRacuna()); 
            ArrayList<ApstraktniDomenskiObjekat> stavkeADO = DBBroker.getInstance().select(sFilter);
            ArrayList<StavkaRacuna> stavke = (ArrayList<StavkaRacuna>) (ArrayList<?>) stavkeADO;
            r.setStavke(stavke);
        }
        
        
        
        
    }

    public ArrayList<Racun> getLista() {
        return lista;
    }
}
