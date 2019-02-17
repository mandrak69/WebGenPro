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

public class Sorteri extends BazniModel implements PrikaziMe {

   
   
   
    private Integer a11_id;
    
    private String characteristics;
   
   
   
    private String namekatalog;
    
    private String from;
    
    private String to;

    public Sorteri() {
    }

    public Sorteri(Integer a11_id, String characteristics, String namekatalog, String from, String to) {
        this.a11_id = a11_id;
        this.characteristics = characteristics;
        this.namekatalog = namekatalog;
        this.from = from;
        this.to = to;
    }

    

    public Sorteri(Integer a11_id, String characteristics, String namekatalog, String from, String to, Integer id, String name) {
        super(id, name);
        this.a11_id = a11_id;
        this.characteristics = characteristics;
        this.namekatalog = namekatalog;
        this.from = from;
        this.to = to;
    }

    public Sorteri(Integer a11_id, String characteristics, String namekatalog, String from, String to, String name) {
        super(name);
        this.a11_id = a11_id;
        this.characteristics = characteristics;
        this.namekatalog = namekatalog;
        this.from = from;
        this.to = to;
    }

    public Sorteri(Integer a11_id, String characteristics, String namekatalog, String from, String to, Integer id) {
        super(id);
        this.a11_id = a11_id;
        this.characteristics = characteristics;
        this.namekatalog = namekatalog;
        this.from = from;
        this.to = to;
    }

    public Integer A11_id() {
        return a11_id;
    }

    public void setA11_id(Integer A11_id) {
        this.a11_id = A11_id;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getNamekatalog() {
        return namekatalog;
    }

    public void setNamekatalog(String namekatalog) {
        this.namekatalog = namekatalog;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    

    @Override
    public String toString() {
        return "Sorteri{" + "a11_id=" + a11_id + ", characteristics=" + characteristics + ", namekatalog=" + namekatalog + ", from=" + from + ", to=" + to + '}';
    }

    @Override
    public String ispisiSe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ispisiNaWeb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public int GObrisi() {
        
        int w = Biblos.GObrisi(this);
        return w;
          }

    @Override
    public Object GUcitaj() { 
        Object w = null; 
    try {
       
         w = Biblos.GUcitaj(this);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
         }

    @Override
    public int GSnimaj() {
        int w=-1;
        try {
             w = Biblos.GSnimaj(this);
            
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
       
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
