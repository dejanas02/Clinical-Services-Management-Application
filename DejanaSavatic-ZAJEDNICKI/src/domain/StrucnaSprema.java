/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author korisnk
 */
public class StrucnaSprema extends ApstraktniDomenskiObjekat{
    
    private int idStrucnaSprema;
    private String naziv;
    private String stepenObrazovanja;

    public StrucnaSprema() {
    }

    public StrucnaSprema(int idStrucnaSprema, String naziv, String stepenObrazovanja) {
        this.idStrucnaSprema = idStrucnaSprema;
        this.naziv = naziv;
        this.stepenObrazovanja = stepenObrazovanja;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getStepenObrazovanja() {
        return stepenObrazovanja;
    }

    public void setStepenObrazovanja(String stepenObrazovanja) {
        this.stepenObrazovanja = stepenObrazovanja;
    }
    
    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String tableName() {
        return "strucna_sprema";
    }

    @Override
    public String alijas() {
        return "";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idStrucnaSprema = rs.getInt("idStrucnaSprema");
            String naziv = rs.getString("naziv");
            String stepenObrazovanja = rs.getString("stepenObrazovanja");
            StrucnaSprema ss = new StrucnaSprema(idStrucnaSprema, naziv, stepenObrazovanja);
            lista.add(ss);
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(naziv,stepenObrazovanja)";
    }

    @Override
    public String valuesForInsert() {
        return "'" + naziv + "','" + stepenObrazovanja + "'";
    }

    @Override
    public String valuesForUpdate() {
    return "naziv='" + naziv + "', stepenObrazovanja='" + stepenObrazovanja + "'";
    }

@Override
    public String requirement() {
    return "idStrucnaSprema=" + idStrucnaSprema;
    }

    @Override
    public String requirementForSelect(Object o) {
        return "";
    }

   
    
}
