/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Biblos;
import static database.Constants.FieldsForTable;
import static database.Constants.setTabelaPoljaTipova;
import database.PrikaziMe;
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

/**
 *
 * @author D
 */
public class Zaposleni {

    private Integer id;

    private String name;

    private String last_name;

    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation

    private String email;

    private String address;

    private String username;

    private String password;

    private int grad_id;
    private int nivokorisnika_id;

    public Zaposleni() {
    }

    public Zaposleni(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Zaposleni(String name, String last_name, String phone, String email, String address, String username, String password, int grad_id, int nivokorisnika_id) {
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.grad_id = grad_id;
        this.nivokorisnika_id = nivokorisnika_id;
    }

    public Zaposleni(String name, String last_name, String email, String address, String username, String password, int grad_id, int nivokorisnika_id) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.grad_id = grad_id;
        this.nivokorisnika_id = nivokorisnika_id;
    }

    public Zaposleni(String name, String last_name, String username, String password, int grad_id, int nivokorisnika_id) {
        this.name = name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.grad_id = grad_id;
        this.nivokorisnika_id = nivokorisnika_id;
    }

   

    

    public Zaposleni(Integer id) {
        this.id = id;
    }

    public Zaposleni(Integer id, String name, String last_name, String phone, String email, String address, String username, String password, int grad_id, int nivokorisnika_id) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.grad_id = grad_id;
        this.nivokorisnika_id = nivokorisnika_id;
    }

   

    public Integer getId() {
        return id;
    }

    public int getNivokorisnika_id() {
        return nivokorisnika_id;
    }

    public void setNivokorisnika_id(int nivokorisnika_id) {
        this.nivokorisnika_id = nivokorisnika_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZaposleniname() {
        return username;
    }

    public void setZaposleniname(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public int getGrad_id() {
        return grad_id;
    }

    public void setGrad_id(int grad_id) {
        this.grad_id = grad_id;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "id=" + id + ", name=" + name + ", last_name=" + last_name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", username=" + username + ", password=" + password + ", grad_id=" + grad_id + ", nivokorisnika_id=" + nivokorisnika_id + '}';
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
         System.out.println("kl"+this.getimenapolja().toString());
        for (Field polje:this.getFields(this)){
            System.out.println("poljeje"+polje.getDeclaringClass());
            JTextField pp=new JTextField();
            
        items.add(pp);
        }
        return items;

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
