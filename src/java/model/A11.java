/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Biblos;
import database.PrikaziMe;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author D
 */
public class A11 extends BazniModel implements PrikaziMe {

    private Integer a1_id;
    private Integer akcija;
    private Integer minim_kol;
   

    public A11() {
    }

   

    public A11(Integer id, String webime,Integer a1_id) {
        super(id, webime);
        this.a1_id = a1_id;
    }

   

   

    

    public Integer getA1_id() {
        return a1_id;
    }

    public void setA1_id(Integer a1_id) {
        this.a1_id = a1_id;
    }

   

    public Integer getAkcija() {
        return akcija;
    }

    public void setAkcija(Integer akcija) {
        this.akcija = akcija;
    }

   

    public Integer getMinim_kol() {
        return minim_kol;
    }

    public void setMinim_kol(Integer minim_kol) {
        this.minim_kol = minim_kol;
    }

   
    

   


    @Override
    public String toString() {
        return "a11{" + "a1_id=" + a1_id + ", akcija=" + akcija + ", minim_kol=" + minim_kol + '}';
    }




    @Override
    public String ispisiSe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ispisiNaWeb() {
        
        
        
        
        return null;
       
         }

   

    @Override
    public boolean izbrisiSeizDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public Object UnosFormWeb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object UnosForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object GUcitaj() {
         
    try {
       
        Object w = this.GUcitaj();
        return w;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   }

    @Override
    public int GSnimaj() {
        this.GSnimaj();     
        return 0;
    }

    @Override
    public int GObrisi() {
        int p = this.GObrisi();
        return p;
    }

    @Override
    public <T> Vector<T> GUcitaj(Object objod, Object objdo) {
      try {
            Vector<Object> niz = Biblos.GUcitaj(objod, objdo);
            return (Vector<T>) niz;
            
           
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ClassNotFoundException | InstantiationException | InvocationTargetException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


   

}
