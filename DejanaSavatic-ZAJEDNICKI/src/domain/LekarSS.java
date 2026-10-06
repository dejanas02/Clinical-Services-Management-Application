/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author korisnk
 */
public class LekarSS extends ApstraktniDomenskiObjekat{
    
    private int idLekar;
    private int idStrucnaSprema;
    private Date datumSticanja;
    private int id;
    
    public LekarSS() {
    }

    public LekarSS(int idLekar, int idStrucnaSprema, Date datumSticanja, int id) {
        this.idLekar = idLekar;
        this.idStrucnaSprema = idStrucnaSprema;
        this.datumSticanja = datumSticanja;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public int getIdLekar() {
        return idLekar;
    }

    public void setIdLekar(int idLekar) {
        this.idLekar = idLekar;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    @Override
    public String toString() {
        return "LekarSS{" + "idLekar=" + idLekar + ", idStrucnaSprema=" + idStrucnaSprema + ", datumSticanja=" + datumSticanja + '}';
    }

    
    
    public String tableName() {
        return "lekar_ss"; 
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

            int idLekar = rs.getInt("idLekar");
            int idStrucnaSprema = rs.getInt("idStrucnaSprema");
            Date datumSticanja = rs.getDate("datumSticanja");
            int id = rs.getInt("id");
            LekarSS lekarSS = new LekarSS(idLekar, idStrucnaSprema, datumSticanja, id);
            lista.add(lekarSS);
        }

        rs.close();
        return lista;
        
    }

    @Override
    public String columnsForInsert() {
        return "(idLekar,idStrucnaSprema,datumSticanja)";
    }

    public String requirement() {
        return "idLekar=" + idLekar + " AND idStrucnaSprema=" + idStrucnaSprema;
    }

    @Override
    public String valuesForInsert() {
        return idLekar + "," + idStrucnaSprema + ",'" +
           new java.sql.Date(datumSticanja.getTime()) + "'";
    }

    @Override
    public String valuesForUpdate() {
        return "datumSticanja='" + new java.sql.Date(datumSticanja.getTime()) + "'";
    }

     @Override
    public String requirementForSelect(Object o) {
        if (o == null) return "";
        if (o instanceof Integer) {
            int idLekar = (Integer) o;
            return " WHERE idLekar=" + idLekar;
        }
        if (o instanceof LekarSS) {
            LekarSS key = (LekarSS) o;
            return " WHERE idLekar=" + key.idLekar +
                   " AND idStrucnaSprema=" + key.idStrucnaSprema;
        }
        return "";
    }
    
    
    
    
}
