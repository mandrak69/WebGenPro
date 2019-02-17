
package model;

import database.Biblos;
import database.PrikaziMe;
import java.util.Vector;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * author D
 */

public class C11 extends BazniModel implements PrikaziMe {

   
  
    
    private Integer c1_id;

    public C11() {
    }

    public C11(String name,Integer c1_id ) {
        super(name);
        this.c1_id = c1_id;
    }

    public C11(Integer id, Integer c1_id) {
        super(id);
        this.c1_id = c1_id;
    }

    public C11(Integer id) {
       super(id);
    }

    public C11(String name) {
        super(name);
    }

  

    public Integer getc1_id() {
        return c1_id;
    }

    public void setc1_id(Integer c1_id) {
        this.c1_id = c1_id;
    }

  

    public String getimetabele() {

        String ime = this.getClass().getSimpleName();
        return ime.toLowerCase();
    }

    @Override
    public String toString() {
        return "Grad{"+this.getId() + "c1_id=" + c1_id +"-"+this.getName()+ '}';
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
