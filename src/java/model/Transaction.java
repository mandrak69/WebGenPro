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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


public class Transaction{

    
   
    private Integer id;
    
    private String date;
    
    private Integer user;
    
    private Integer item;
    
    private String approved;
    
    private double price;
private Integer kol;

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Transaction(Integer id, String date, Integer user, Integer item, String approved, double price) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.item = item;
        this.approved = approved;
        this.price = price;
    }

    public Transaction(Integer id, double price) {
        this.id = id;
        this.price = price;
    }

    public Transaction(Integer id, String date, Integer user, Integer item, String approved, double price,int kol) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.item = item;
        this.approved = approved;
        this.price = price;
        this.kol=kol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKol() {
        return kol;
    }

    public void setKol(Integer kol) {
        this.kol = kol;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Transaction[ id=" + id + " ]";
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
     public static <T> ArrayList<Field> getFields(T t) {
        ArrayList<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        
        return fields;
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
        
        for (Field polje:this.getdeklarisanapolja()){
           
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

