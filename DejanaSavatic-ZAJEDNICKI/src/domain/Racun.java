/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author korisnk
 */
public class Racun extends ApstraktniDomenskiObjekat{
    
    private int idRacuna;
    private Date datumIzdavanja;
    private double ukupniIznos;
    private Lekar lekar;
    private Pacijent pacijent;
    private ArrayList<StavkaRacuna> stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(int idRacuna, Date datumIzdavanja, double ukupniIznos, Lekar lekar, Pacijent pacijent, ArrayList<StavkaRacuna> stavke) {
        this.idRacuna = idRacuna;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupniIznos = ukupniIznos;
        this.lekar = lekar;
        this.pacijent = pacijent;
        this.stavke = stavke;
    }
    
    

    public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public double getUkupniIznos() {
        return ukupniIznos;
    }

    public void setUkupniIznos(double ukupniIznos) {
        this.ukupniIznos = ukupniIznos;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public ArrayList<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String tableName() {
        return "racun";
    }

    @Override
    public String alijas() {
        return "rac";
    }

    @Override
    public String join() {
         return " JOIN lekar l ON rac.lekar = l.idLekar " +
           " JOIN pacijent p ON rac.pacijent = p.idPacijent " +
           " JOIN drzavljanstvo d ON p.drzavljanstvo = d.idDrzavljanstvo ";

    }

   @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
    ArrayList<ApstraktniDomenskiObjekat> list = new ArrayList<>();
    while (rs.next()) {
        Lekar l = new Lekar(
            rs.getInt("l.idLekar"),
            rs.getString("l.ime"),
            rs.getString("l.prezime"),
            rs.getString("l.korisnickoIme"),
            rs.getString("l.email"),
            rs.getString("l.sifra")
        );

        Drzavljanstvo d = new Drzavljanstvo(
        rs.getInt("d.idDrzavljanstvo"),
        rs.getString("d.drzava")
        );

        Pacijent p = new Pacijent(
            rs.getInt("p.idPacijent"),
            rs.getString("p.ime"),
            rs.getString("p.prezime"),
            rs.getString("p.email"),
            d
        );

        Racun rac = new Racun();
        rac.setIdRacuna(rs.getInt("rac.idRacuna"));
        rac.setDatumIzdavanja(rs.getDate("rac.datumIzdavanja"));
        rac.setUkupniIznos(rs.getDouble("rac.ukupniIznos"));
        rac.setLekar(l);
        rac.setPacijent(p);
        list.add(rac);
    }
    rs.close();
    return list;
}

    @Override
    public String valuesForInsert() {
    return "'" + new java.sql.Date(datumIzdavanja.getTime()) + "', "
         + ukupniIznos + ", "
         + lekar.getIdLekar()+ ", "
         + pacijent.getIdPacijent();
    }



    @Override
    public String valuesForUpdate() {
    return " datumIzdavanja = '" + new java.sql.Date(datumIzdavanja.getTime()) + "', "
         + "ukupniIznos = " + ukupniIznos + ", "
         + "lekar = " + lekar.getIdLekar()+ ", "
         + "pacijent = " + pacijent.getIdPacijent();
    }

     @Override
    public String columnsForInsert() {
            return "(datumIzdavanja, ukupniIznos, lekar, pacijent)";    
    }

    
    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Racun)) return "";
        Racun r = (Racun) o;

        if (r.getIdRacuna() > 0) {
            return " WHERE rac.idRacuna = " + r.getIdRacuna();
        }
        if (r.getPacijent()!= null && r.getPacijent().getIdPacijent()> 0) {
            return " WHERE rac.pacijent = " + r.getPacijent().getIdPacijent();
        }
        return "";
    }

   
    @Override
    public String requirement() {
        return "idRacuna=" + idRacuna;
    }
    
}