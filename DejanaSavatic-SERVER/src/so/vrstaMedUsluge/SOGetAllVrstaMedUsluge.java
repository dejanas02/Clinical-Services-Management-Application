package so.vrstaMedUsluge;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.VrstaMedUsluge;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

public class SOGetAllVrstaMedUsluge extends AbstractSO {

    private ArrayList<VrstaMedUsluge> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof VrstaMedUsluge)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaMedUsluge!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> usluge = DBBroker.getInstance().select(ado);
        lista = (ArrayList<VrstaMedUsluge>) (ArrayList<?>) usluge;
    }

    public List<VrstaMedUsluge> getLista() {
        return lista;
    }
}
