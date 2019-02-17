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

/**
 *
 * @author D
 */

public class C1 extends BazniModel implements PrikaziMe {

 

  

    public C1() {
        super();
        
    }

    public C1(Integer id) {
        super(id);
    }

    public C1(String name) {
        super(name);
    }

   
 

   

    @Override
    public String toString() {
        return "c1{"+this.getId()+this.getName()+this.ImeKlasetoLowerString() + '}';
    }

    @Override
    public String ispisiSe() {
        String s1=this.getName()+"--"+this.ImeKlasetoLowerString();
    return s1;
    
    }

    @Override
    public  String ispisiNaWeb() {
        try {
            /*pravi pravougaonik saslikomi tekstom .treba samozameniti linkka sliciiteks cenu
            */
            C1 df=new C1();
            df.setName("C");
            C1 ef=new C1();
            ef.setName("T");
            Vector<C1> z = Biblos.GUcitaj(df,ef);
            String a="";
          for(C1 drz: z){
          
          a=a+"<div class='catlink' ><a href='ProductServlet?A11_id=1'><img class='catimage' src='images/Gibson Les Paul.jpg'/></a>";
           a=a+" <a href='ProductServlet?A11_id=1'><span>"+drz.getName()+"</span></a></div>";
          
          
          }  
            String   st1= " <li><img src='' style=' background-image: url('images/Digitech Delay.jpg'); z-index: -5; max-width: 10%;max-height: 10%;transform: scale(10);'>";
            
            
            String   st2= "<a href='' style='color:white'><h4>North Sky Resort Sogod</h4><p>Sogod, Cebu</p></a></li>";
            
            return st1+st2;
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ClassNotFoundException | InstantiationException | InvocationTargetException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        
        
    return null;
    
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
public void veliki(){System.out.println("veliki rad");}
}