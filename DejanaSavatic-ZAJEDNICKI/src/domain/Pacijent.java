/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author korisnk
 */
public class Pacijent extends ApstraktniDomenskiObjekat{
    
    private int idPacijent;
    private String ime;
    private String prezime;
    private String email;
    private Drzavljanstvo drzavljanstvo;

    public Pacijent() {
    }

    public Pacijent(int idPacijent, String ime, String prezime, String email, Drzavljanstvo drzavljanstvo) {
        this.idPacijent = idPacijent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.drzavljanstvo = drzavljanstvo;
    }

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
        this.idPacijent = idPacijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Drzavljanstvo getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(Drzavljanstvo drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }
    
   

    @Override
    public String tableName() {
        return "pacijent";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return "JOIN drzavljanstvo d ON p.drzavljanstvo = d.idDrzavljanstvo";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idPacijent = rs.getInt("idPacijent");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");

            int idDrzavljanstvo = rs.getInt("idDrzavljanstvo");
            String drzava = rs.getString("drzava");
            Drzavljanstvo d = new Drzavljanstvo(idDrzavljanstvo, drzava);

            Pacijent p = new Pacijent(idPacijent, ime, prezime, email, d); 
                 lista.add(p);
        }
        rs.close();
        return lista;  
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String columnsForInsert() {
        return "(ime,prezime,email, drzavljanstvo)";
    }

    @Override
    public String requirement() {
        return "idPacijent=" + idPacijent;
    }

    @Override
    public String valuesForInsert() {
        return "'"+ ime + "','" + prezime + "','" +  email + "'," + drzavljanstvo.getIdDrzavljanstvo();
    }

    @Override
    public String valuesForUpdate() {
            return "ime='" + ime + "', prezime='" + prezime + "', email='" + email
            + "', drzavljanstvo=" + drzavljanstvo.getIdDrzavljanstvo();   
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Pacijent)) return "";
        Pacijent p = (Pacijent) o;

        if (p.getIdPacijent() > 0) return " WHERE p.idPacijent = " + p.getIdPacijent();

        String ime = p.getIme() == null ? "" : p.getIme().trim().replace("'", "''");
        String prezime = p.getPrezime() == null ? "" : p.getPrezime().trim().replace("'", "''");
        Integer idDrz = (p.getDrzavljanstvo() != null) ? p.getDrzavljanstvo().getIdDrzavljanstvo() : null;

        StringBuilder where = new StringBuilder();
        boolean has = false;

        if (!ime.isBlank() && !prezime.isBlank()) {
            where.append(" WHERE p.ime LIKE '").append(ime).append("%'")
                 .append(" AND p.prezime LIKE '").append(prezime).append("%'");
            has = true;
        } else {
            if (!ime.isBlank()) {
                where.append(" WHERE (p.ime LIKE '").append(ime).append("%' OR p.prezime LIKE '").append(ime).append("%')");
                has = true;
            }
            if (!prezime.isBlank()) {
                where.append(has ? " AND " : " WHERE")
                     .append(" (p.prezime LIKE '").append(prezime).append("%' OR p.ime LIKE '").append(prezime).append("%')");
                has = true;
            }
        }

        if (idDrz != null && idDrz > 0) {
            where.append(has ? " AND " : " WHERE").append(" p.drzavljanstvo = ").append(idDrz);
        }
        return where.toString();
    }


}
    
    
    
    

