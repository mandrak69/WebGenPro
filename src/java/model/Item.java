/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Biblos;
import database.PrikaziMe;
import java.util.Vector;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


 

public class Item extends BazniModel implements PrikaziMe {

  
    
  
    private Double cena;
       
    private Double webcena1;
    
    private Double webcena2;
   
    private Double webcena3;
    
    private Integer webakcija;
    
  
    
    private Integer a11_id;
   
    private Integer b11_id;
   

    public Item() {
    }

    public Item(Integer id, String name,  Integer a11_id, Integer b11_id) {
       super(id,name);
        
        this.a11_id = a11_id;
        this.b11_id = b11_id;
    }

    public Item(double tb_price, String tb_productName, String tb_description, int a11_id, int b11_id, String imagepath) {
      super(tb_productName,tb_description,imagepath);
    this.cena=tb_price;
     this.a11_id = a11_id;
     this.b11_id =b11_id;
    }

    


    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }




  

    public Double getWebcena1() {
        return webcena1;
    }

    public void setWebcena1(Double webcena1) {
        this.webcena1 = webcena1;
    }

    public Double getWebcena2() {
        return webcena2;
    }

    public void setWebcena2(Double webcena2) {
        this.webcena2 = webcena2;
    }

    public Double getWebcena3() {
        return webcena3;
    }

    public void setWebcena3(Double webcena3) {
        this.webcena3 = webcena3;
    }

    public Integer getWebakcija() {
        return webakcija;
    }

    public void setWebakcija(Integer webakcija) {
        this.webakcija = webakcija;
    }

   
    public Integer getA11_id() {
        return a11_id;
    }

    public void setA11_id(Integer a11_id) {
        this.a11_id = a11_id;
    }

    public Integer getB11_id() {
        return b11_id;
    }

    public void setB11_id(Integer b11_id) {
        this.b11_id = b11_id;
    }

    @Override
    public String toString() {
        return super.toString()+"Item{" + "cena=" + cena + ", webcena1=" + webcena1 + ", webcena2=" + webcena2 + ", webcena3=" + webcena3 + ", webakcija=" + webakcija + ", a11_id=" + a11_id + ", b11_id=" + b11_id + '}';
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