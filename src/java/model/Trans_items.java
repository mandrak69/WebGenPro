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

public class Trans_items  extends BazniModel implements PrikaziMe {

    
    
    private Integer id;
    
    private Integer col;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private Double price;
  
    private String napomena;
    
    private Integer transaction_head_id;

    public Trans_items() {
    }

    public Trans_items(Integer col, Double price, String napomena, Integer transaction_head_id) {
        this.col = col;
        this.price = price;
        this.napomena = napomena;
        this.transaction_head_id = transaction_head_id;
    }

    public Trans_items(Integer id, Integer col, Double price, String napomena, Integer transaction_head_id) {
        this.id = id;
        this.col = col;
        this.price = price;
        this.napomena = napomena;
        this.transaction_head_id = transaction_head_id;
    }

    public Trans_items(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Integer getTransaction_head_id() {
        return transaction_head_id;
    }

    public void setTransaction_head_id(Integer transaction_head_id) {
        this.transaction_head_id = transaction_head_id;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trans_items)) {
            return false;
        }
        Trans_items other = (Trans_items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TransItems[ id=" + id + " ]";
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
