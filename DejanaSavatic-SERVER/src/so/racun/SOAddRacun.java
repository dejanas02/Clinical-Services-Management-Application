package so.racun;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

public class SOAddRacun extends AbstractSO {

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;

        if (r.getLekar()== null || r.getPacijent()== null) {
            throw new Exception("Racun mora imati lekara i pacijenta.");
        }
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku.");
        }
        if (r.getDatumIzdavanja() == null) {
            throw new Exception("Datum izdavanja je obavezan.");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int idRacuna = keys.getInt(1);

        Racun r = (Racun) ado;
        r.setIdRacuna(idRacuna);

        int rb = 1;
        for (StavkaRacuna s : r.getStavke()) {
            s.setIdRacuna(idRacuna); 
            s.setRb(rb++);
            DBBroker.getInstance().insert(s);
        }
    }
}
