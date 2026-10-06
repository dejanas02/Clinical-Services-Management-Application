package so.lekar;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Lekar;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllLekar extends AbstractSO {

    private ArrayList<Lekar> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Lekar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lekar!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> lekari = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Lekar>) (ArrayList<?>) lekari;
    }

    public ArrayList<Lekar> getLista() {
        return lista;
    }
}
