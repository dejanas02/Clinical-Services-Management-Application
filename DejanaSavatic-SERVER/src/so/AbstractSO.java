package so;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;

/**
 *
 * @author korisnik
 */
import java.sql.SQLException;

public abstract class AbstractSO {
    protected abstract void validate(ApstraktniDomenskiObjekat ado) throws Exception;
    protected abstract void execute(ApstraktniDomenskiObjekat ado) throws Exception;

    public void templateExecute(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
    
}
