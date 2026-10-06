package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VrstaMedUsluge extends ApstraktniDomenskiObjekat {

    private int idVrstaMedUsluge;
    private String nazivMedUsluge;
    private double cena;

    public VrstaMedUsluge() {}
    public VrstaMedUsluge(int idVrstaMedUsluge, String nazivMedUsluge, double cena) {
        this.idVrstaMedUsluge = idVrstaMedUsluge;
        this.nazivMedUsluge = nazivMedUsluge;
        this.cena = cena;
    }

    public int getIdVrstaMedUsluge() {
        return idVrstaMedUsluge;
    }

    public void setIdVrstaMedUsluge(int idVrstaMedUsluge) {
        this.idVrstaMedUsluge = idVrstaMedUsluge;
    }

    public String getNazivMedUsluge() {
        return nazivMedUsluge;
    }

    public void setNazivMedUsluge(String nazivMedUsluge) {
        this.nazivMedUsluge = nazivMedUsluge;
    }

    

    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }

    @Override public String toString() { return nazivMedUsluge; }

    // tabele snake_case, kolone camelCase
    @Override public String tableName() { return "vrsta_usluge"; }
    @Override public String alijas() { return "vu"; }
    @Override public String join() { return ""; }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idVrstaMedUsluge");
            String naziv = rs.getString("nazivMedUsluge");
            double cena = rs.getDouble("cena");
            lista.add(new VrstaMedUsluge(id, naziv, cena));
        }
        rs.close();
        return lista;
    }

    @Override public String columnsForInsert() { return "(nazivMedUsluge,cena)"; }
    @Override public String requirement() { return "idVrstaMedUsluge=" + idVrstaMedUsluge; }
    @Override public String valuesForInsert() { return "'" + nazivMedUsluge + "'," + cena; }
    @Override public String valuesForUpdate() { return "nazivMedUsluge='" + nazivMedUsluge + "', cena=" + cena; }

    @Override @SuppressWarnings("unchecked")
    public String requirementForSelect(Object o) {
        if (o == null) return "";

        if (o instanceof VrstaMedUsluge) {
            VrstaMedUsluge v = (VrstaMedUsluge) o;

        if (v.getIdVrstaMedUsluge() > 0) {
            return " WHERE vu.idVrstaMedUsluge = " + v.getIdVrstaMedUsluge();
        }

        if (v.getNazivMedUsluge() != null && !v.getNazivMedUsluge().isBlank()) {
            String naziv = v.getNazivMedUsluge().replace("'", "''");
            return " WHERE vu.nazivMedUsluge LIKE '" + naziv + "%'";
        }
    }
        return "";
    }
}
