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

public class A1 extends BazniModel implements PrikaziMe {

  
   
  

    public A1() {
    }

    public A1(Integer id) {
        super(id);
    }

   
    

 

    @Override
    public String ispisiSe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ispisiNaWeb() {
      String dr="<div class=A1>";
       dr=dr+"<p id=A1text>"+ this.getName()+this.getWebime()+"</p>";
       dr=dr+"<a></a>";
      dr=dr+"</div>";
      
      
        return dr;
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
