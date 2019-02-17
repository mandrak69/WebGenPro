/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Vector;

/**
 * abstraktna klasa . definise moguca prikazivanja svih klasa iz db. prikaz na
 * ekran , na web, uzi,siri format, format pri unosu novog
 *
 * Obrisi se, upisi se, promeni se
 *
 * @author D
 */
public interface PrikaziMe {

    public Object UnosForm();/* metod koji pravi formu sa poljima objekta za unos preko desktop aplikacije*/
    public Object UnosFormWeb();/*  metod koji pravi formu sa poljima objekta za unos preko weba*/
    public boolean izbrisiSeizDb();/*  metod brisanja  objekta iz tabele u bazi */
    public String ispisiSe();/*  metod javljanja objekta za desktop*/
    public String ispisiNaWeb();/*  metod javljanja objekta za web*/
    
    public Object GUcitaj();
    public int GSnimaj();/*  metod snimanja  objekta u bazu u odgovarajucu tabelu - */
    public int GObrisi();/*  metod koji updejtuje zapis u tabeli na vrednosti objekta koji poziva metod */
    public <T> Vector<T> GUcitaj(Object objod,Object objdo);

}
