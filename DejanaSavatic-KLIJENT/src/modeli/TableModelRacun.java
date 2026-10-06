/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Racun;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author korisnk
 */
public class TableModelRacun extends AbstractTableModel {
    
    String[] kolone = {"ID racuna","Datum izdavanja","Ukupni iznos"};
    List<Racun> racuni = new ArrayList();
    
    @Override
    public int getRowCount() {
        return racuni.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = racuni.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdRacuna();
            case 1:
                return r.getDatumIzdavanja();
            case 2:
                return r.getUkupniIznos();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
            fireTableDataChanged();

    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public Racun getAt(int row) {
        return racuni.get(row);    
    }

    
    
    
}
