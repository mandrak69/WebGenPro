/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Biblos;
import static database.Constants.FieldsForTable;
import static database.Constants.setTabelaPoljaTipova;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author D
 */

public class Transaction_head  {

    
    private Integer id;
    
    private Date datum_kup;
    
    private String approved;
    private Double suma;
  
    private String napomena;
   
    private Integer user_id;
    
    private Integer zaposleni_id;

    public Transaction_head() {
    }

    public Transaction_head(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatum_kup() {
        return datum_kup;
    }

    public void setDatum_kup(Date datum_kup) {
        this.datum_kup = datum_kup;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getZaposleni_id() {
        return zaposleni_id;
    }

    public void setZaposleni_id(Integer zaposleni_id) {
        this.zaposleni_id = zaposleni_id;
    }

  

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
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
        if (!(object instanceof Transaction_head)) {
            return false;
        }
        Transaction_head other = (Transaction_head) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TransactionHead[ id=" + id + " ]";
    }

    
    public ArrayList<String> getimenapolja() {

      
          Field[] a12 = this.getClass().getDeclaredFields();
        ArrayList<String> imena = new ArrayList<>();
        for (Field a : a12) {
            imena.add(a.getName());
        }
         Field[] a11 = this.getClass().getFields();
       
        for (Field a : a11) {
            imena.add(a.getName());
        }

        return imena;

    }
    public ArrayList<Field> getpoljaklase() {

      
          Field[] a12 = this.getClass().getFields();
        ArrayList<Field> polja = new ArrayList<>();
        for (Field a : a12) {
            polja.add(a);
        }
         Field[] a11 = this.getClass().getDeclaredFields();
       
        for (Field a : a11) {
            polja.add(a);
        }

        return polja;

    }

    public  Vector<JTextField> unesiMe() throws ClassNotFoundException {
        String tabela = this.getimetabele();
        
        Vector<String> columnNames = FieldsForTable.get(tabela);
        HashMap<String, String> poletip = setTabelaPoljaTipova.get(tabela);
              
        /*
        SELECT COLUMN_NAME,
        DATA_TYPE,
        CHARACTER_MAXIMUM_LENGTH
        FROM information_schema.columns
        WHERE TABLE_NAME = 'item';
         */
        JFormattedTextField.AbstractFormatterFactory factory = null;
        
         Vector<JTextField> items = new Vector<>();
         System.out.println("kl"+this.getimenapolja().toString());
        for (Field polje:this.getpoljaklase()){
            System.out.println("poljeje"+polje.getDeclaringClass());
            JTextField pp=new JTextField();
            
        items.add(pp);
        }
        return items;

    }

   

  

    public Field[] getdeklarisanapolja() {
        Field[] a12 = this.getClass().getDeclaredFields();
        return a12;
    }

    public String getimetabele() {

        String ime = this.getClass().getSimpleName();
        return ime.toLowerCase();
    }

    public String ImeKlasetoLowerString() {

        String ime = this.getClass().getSimpleName();
        return ime.toLowerCase();
    }

    public String toLongWebString() {

        String ime = this.getClass().getSimpleName();
        return ime.toLowerCase();
    }

  
 
   

    public int GObrisi() {
        
        int w = Biblos.GObrisi(this);
        return w;
          }

    public Object GUcitaj() { 
        Object w = null; 
    try {
       
         w = Biblos.GUcitaj(this);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
         }

    public int GSnimaj() {
        int w=-1;
        try {
             w = Biblos.GSnimaj(this);
            
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
       
    }

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
