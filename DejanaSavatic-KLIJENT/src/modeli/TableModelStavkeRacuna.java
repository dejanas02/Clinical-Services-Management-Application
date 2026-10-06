/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import controller.KlijentController;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author korisnk
 */
public class TableModelStavkeRacuna extends AbstractTableModel {
    
    String[] kolone = {"Redni broj", "Usluga", "Kolicina", "Cena", "Iznos"};
    List<StavkaRacuna> stavke;
    private int rb;
    
    
     public TableModelStavkeRacuna() {
         stavke = new ArrayList<>();
         rb = 0;
    }

    public TableModelStavkeRacuna(Racun racun) {
        try {
             this.stavke = racun.getStavke() != null ? racun.getStavke() : new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkeRacuna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna sr = stavke.get(rowIndex);
        

        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getVrstaMedUsluge().toString();
            case 2:
                return sr.getKolicina();
            case 3:
                return sr.getCenaUsluge();
            case 4:
                return sr.getIznos();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    private void reindex() {
        for (int i = 0; i < stavke.size(); i++) {
            stavke.get(i).setRb(i + 1); 
        }
    }

    public void dodajStavku(StavkaRacuna nova) {
        if (nova == null || nova.getVrstaMedUsluge() == null) return;

        int idUslugeNove = nova.getVrstaMedUsluge().getIdVrstaMedUsluge();

        for (int i = 0; i < stavke.size(); i++) {
            StavkaRacuna postojeca = stavke.get(i);
            if (postojeca.getVrstaMedUsluge() != null &&
                postojeca.getVrstaMedUsluge().getIdVrstaMedUsluge() == idUslugeNove) {

                int novaKolicina = postojeca.getKolicina() + nova.getKolicina();
                postojeca.setKolicina(novaKolicina);

                double cena = postojeca.getCenaUsluge();  
                postojeca.setIznos(novaKolicina * cena);

                reindex();
                fireTableRowsUpdated(i, i);
                return;
            }
        }

        stavke.add(nova);
        reindex();
        fireTableRowsInserted(stavke.size() - 1, stavke.size() - 1);
    }
    public void obrisiStavku(int row) {
        if (row >= 0 && row < stavke.size()) {
            stavke.remove(row);

            rb = 0;
            for (StavkaRacuna s : stavke) {
                s.setRb(++rb);
            }
            fireTableDataChanged();
        }
    }

    public double vratiUkupanIznos() {
        double ukupno = 0;
        for (StavkaRacuna s : stavke) {
            ukupno += s.getIznos();
        }
        return ukupno;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public StavkaRacuna getStavka(int row) {
        if (row >= 0 && row < stavke.size()) {
            return stavke.get(row);
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        return columnIndex==2 || columnIndex==3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaRacuna sr = stavke.get(rowIndex);
        try {
            if (columnIndex == 2) {
                int k = (aValue instanceof Number) ? ((Number) aValue).intValue()
                                                    : Integer.parseInt(aValue.toString().trim());
                if (k < 0) k = 0;
                sr.setKolicina(k);
            } else if (columnIndex == 3) { 
                double c = (aValue instanceof Number) ? ((Number) aValue).doubleValue()
                                                       : Double.parseDouble(aValue.toString().trim().replace(',', '.'));
                if (c < 0) c = 0;
                sr.setCenaUsluge(c);
            } else {
                return;
            }

            sr.setIznos(sr.getKolicina() * sr.getCenaUsluge());

            fireTableCellUpdated(rowIndex, columnIndex); 
            fireTableCellUpdated(rowIndex, 4);        
        } catch (NumberFormatException ex) {
            fireTableRowsUpdated(rowIndex, rowIndex);
        }

    }
    
    
    
}
