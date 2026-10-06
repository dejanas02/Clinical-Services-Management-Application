/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Pacijent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author korisnk
 */
public class TableModelPacijent extends AbstractTableModel {
    
    String[] kolone = {"Ime","Prezime","Email","Drzavljanstvo"};
    List<Pacijent> pacijenti = new ArrayList();

    @Override
    public int getRowCount() {
        return pacijenti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pacijent p = pacijenti.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getIme();
            case 1: return p.getPrezime();
            case 2: return p.getEmail();
            case 3: return p.getDrzavljanstvo().getDrzava();
            default: return "";
        }
    }
    
    
      public void setData(List<Pacijent> lista) {
        pacijenti.clear();
        if (lista != null) pacijenti.addAll(lista);
        fireTableDataChanged();
    }

    public Pacijent getPacijentAt(int row) {
        if (row < 0 || row >= pacijenti.size()) return null;
        return pacijenti.get(row);
    }

    public void removeRow(int row) {
        if (row < 0 || row >= pacijenti.size()) return;
        pacijenti.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateRow(int row, Pacijent izmenjen) {
        if (row < 0 || row >= pacijenti.size()) return;
        pacijenti.set(row, izmenjen);
        fireTableRowsUpdated(row, row);
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
}
