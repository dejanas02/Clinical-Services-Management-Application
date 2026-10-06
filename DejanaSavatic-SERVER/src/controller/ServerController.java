package controller;

import domain.Drzavljanstvo;
import domain.Pacijent;
import domain.Racun;
import domain.Lekar;
import domain.StrucnaSprema;
import domain.VrstaMedUsluge;

import java.util.ArrayList;
import so.drzavljanstvo.SOGetAllDrzavljanstvo;

import so.login.SOLogin;

import so.pacijent.SOAddPacijent;
import so.pacijent.SODeletePacijent;
import so.pacijent.SOGetAllPacijent;
import so.pacijent.SOUpdatePacijent;

import so.racun.SOAddRacun;
import so.racun.SOGetRacun;
import so.racun.SOUpdateRacun;

import so.lekar.SOGetAllLekar;

import so.strucna_sprema.SOAddStrucnaSprema;
import so.strucna_sprema.SOGetAllStrucnaSprema;

import so.vrstaMedUsluge.SOGetAllVrstaMedUsluge;

public class ServerController {

    private static ServerController instance;
    private final ArrayList<Lekar> ulogovaniLekari = new ArrayList<>();

    private ServerController() { }

    public static synchronized ServerController getInstance() {
        if (instance == null) instance = new ServerController();
        return instance;
    }

    public ArrayList<Lekar> getUlogovaniLekari() {
        return ulogovaniLekari;
    }

    
    public Lekar login(Lekar kredencijali) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(kredencijali);
        Lekar ulogovani = so.getUlogovaniLekar();
        if (ulogovani != null && !ulogovaniLekari.contains(ulogovani)) {
            ulogovaniLekari.add(ulogovani);
        }
        return ulogovani;
    }

    public void logout(Lekar ulogovani) {
        ulogovaniLekari.remove(ulogovani);
    }

    public Object getAllLekar() throws Exception {
        SOGetAllLekar so = new SOGetAllLekar();
        so.templateExecute(new Lekar());
        return so.getLista();
    }

    
    public void kreirajPacijenta(Pacijent pacijent) throws Exception {
        new SOAddPacijent().templateExecute(pacijent);
    }

    public Object pretraziPacijente(Object filter) throws Exception {
        
        SOGetAllPacijent so = new SOGetAllPacijent();
        if (filter instanceof Pacijent) {
            so.templateExecute((Pacijent) filter);
        } else {
            so.templateExecute(new Pacijent());
        }
        return so.getLista();
    }

    public void izmeniPacijenta(Pacijent pacijent) throws Exception {
        new SOUpdatePacijent().templateExecute(pacijent);
    }

    public void obrisiPacijenta(Pacijent pacijent) throws Exception {
        new SODeletePacijent().templateExecute(pacijent);
    }

    
    public void kreirajRacun(Racun racun) throws Exception {
        new SOAddRacun().templateExecute(racun);
    }

    public Object pretraziRacune(Object filter) throws Exception {
        SOGetRacun so = new SOGetRacun();
        if (filter instanceof Racun) {
            so.templateExecute((Racun) filter);
        } else {
            so.templateExecute(new Racun());
        }
        return so.getLista();
    }

    public void izmeniRacun(Racun racun) throws Exception {
        new SOUpdateRacun().templateExecute(racun);
    }

    
    public void unesiStrucnuSpremu(StrucnaSprema ss) throws Exception {
        new SOAddStrucnaSprema().templateExecute(ss);
    }

    public Object getAllStrucnaSprema() throws Exception {
        SOGetAllStrucnaSprema so = new SOGetAllStrucnaSprema();
        so.templateExecute(new StrucnaSprema());
        return so.getLista();
    }

    

    public Object getAllUsluga() throws Exception {
        SOGetAllVrstaMedUsluge so = new SOGetAllVrstaMedUsluge();
        so.templateExecute(new VrstaMedUsluge());
        return so.getLista();
    }


    public Object getAllDrzavljanstvo() throws Exception {
       SOGetAllDrzavljanstvo so = new SOGetAllDrzavljanstvo();
        so.templateExecute(new Drzavljanstvo()); 
        return so.getLista();
    }
    
    public Racun getRacunById(int id) throws Exception {
    SOGetRacun so = new SOGetRacun();
    Racun filter = new Racun();
    filter.setIdRacuna(id);
    so.templateExecute(filter);

    ArrayList<Racun> lista = so.getLista(); 
    if (lista.isEmpty()) return null;
    return lista.get(0);  
    }
    
    
}
   
