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
public class Drzavljanstvo extends ApstraktniDomenskiObjekat{
    
    private int idDrzavljanstvo;
    private String drzava;

    public Drzavljanstvo() {
    }

    public Drzavljanstvo(int idDrzavljanstvo, String drzava) {
        this.idDrzavljanstvo = idDrzavljanstvo;
        this.drzava = drzava;
    }

    public int getIdDrzavljanstvo() {
        return idDrzavljanstvo;
    }

    public void setIdDrzavljanstvo(int idDrzavljanstvo) {
        this.idDrzavljanstvo = idDrzavljanstvo;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        return drzava;
    }

    
    
    @Override
    public String tableName() {
        return "drzavljanstvo";
    }

    @Override
    public String alijas() {
        return "d";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
                int idDrzavljanstvo = rs.getInt("idDrzavljanstvo");
                String drzava = rs.getString("drzava");
                Drzavljanstvo d = new Drzavljanstvo(idDrzavljanstvo, drzava);
                lista.add(d);
            }

        rs.close();
        return lista;    }

    @Override
    public String columnsForInsert() {
        return "";
    }

    @Override
    public String requirement() {
        return "";
    }

    @Override
    public String valuesForInsert() {
        return "";
    }

    @Override
    public String valuesForUpdate() {
        return "";
    }

    @Override
    public String requirementForSelect(Object o) {
        return "";
    }
    
    
    
    
    
}
