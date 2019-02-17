/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Biblos;
import database.PrikaziMe;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author D
 */
public class Bitkoinzapis extends BazniModel implements PrikaziMe {

    private Integer id;

    private String date;

    private Integer user;

    private Integer item;

    private String approved;

    private double price;

    private String id_origin;

    public Bitkoinzapis() {
    }

    public Bitkoinzapis(Integer id) {
        this.id = id;
    }

    public Bitkoinzapis(Integer id, double price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId_origin() {
        return id_origin;
    }

    public void setId_origin(String id_origin) {
        this.id_origin = id_origin;
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
        if (!(object instanceof Bitkoinzapis)) {
            return false;
        }
        Bitkoinzapis other = (Bitkoinzapis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bitkoinzapis[ id=" + id + " ]";
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
    public int GSnimaj() {
        int w = -1;
        try {
            w = Biblos.GSnimaj(this);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex) {
            Logger.getLogger(Bitkoinzapis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }

    @Override
    public int GObrisi() {
        int w = -1;
        w=Biblos.GObrisi(this);
       
        return w;
    }

    @Override
    public Object GUcitaj() {
        int w = -1;
        try {
            w = Biblos.GUcitaj(this);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Bitkoinzapis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }

    @Override
    public <T> Vector<T> GUcitaj(Object objod, Object objdo) {
        
        try {
           Vector<?> g = Biblos.GUcitaj(objod, objdo);
            
            return  (Vector<T>) g;
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ClassNotFoundException | InstantiationException | InvocationTargetException ex) {
            Logger.getLogger(Bitkoinzapis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
     }

}
