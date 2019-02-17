/*
 * Klasa Bazni model sadrzi polja kojanasledjuju sve klasekoje zele da se pojave na webu
 *  Ima metode koje obezbedjuju pravljenje forme za Web.
 *  Svaki objekat moze da se razlicito opise na webu i u aplikaciji
 *  getimenapolja  Klasa sama nalazi imena svojih polja i polja svoje nadklase
 *  getFields  Klasa sama dohvati svoja polja 
 */
package model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import static database.Constants.FieldsForTable;
import static database.Constants.setTabelaPoljaTipova;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author D
 */
public class BazniModel {

    private Integer id;
    private String name;
    private String webime;
    private String slika;
    private String webslika;
    private String webopis;
    private Integer ind;
    private Integer webind;

    public BazniModel() {

    }

    public BazniModel(Integer id, String name, String webime, String slika, String webslika,String webopis, Integer ind, Integer webind) {
        this.id = id;
        this.name = name;
        this.webime = webime;
        this.slika = slika;
       this.webopis=webopis;
        this.webslika = webslika;
        this.webind = webind;
    }

    public BazniModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BazniModel(String name) {
        this.name = name;
    }

    public BazniModel(String name, String webopis, String webslika) {
        this.name = name;
        this.webslika = webslika;
        this.webopis = webopis;
    }

    public BazniModel(Integer id) {
        this.id = id;
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
    /*  Metod dohvata sva polja neke klase ukljucujuci i polja iz nasledjenih klasa ,sve do nivoa Object klase */
    /*
    */
    public static <T> ArrayList<Field> getFields(T t) {
        ArrayList<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        
        return fields;
    }
    
    /*  Metod dohvata sve metode neke klase ukljucujuci i metode iz nasledjenih klasa ,sve do nivoa Object klase */
    /*
    */
     public static <T> ArrayList<Method> getMethode(T t) {
        ArrayList<Method> meth = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            meth.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
        
        return meth;
    }
    

/*  dovata sva polja iz tabele koja odgovara klasi koja poziva metodu
     dohvata imena i tipove polja tabele 
     pravi vektor 
     */
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
        
        for (Field polje:this.getFields(this)){
           
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {  System.out.println("-----------------------------------------");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("-----------------------------------------");
        this.name = name;
    }

    public String getWebime() {
        return webime;
    }

    public void setWebime(String webime) {
        this.webime = webime;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getWebslika() {
        return webslika;
    }

    public void setWebslika(String webslika) {
        this.webslika = webslika;
    }

    public String getWebopis() {
        return webopis;
    }

    public void setWebopis(String webopis) {
        this.webopis = webopis;
    }

    public Integer getInd() {
        return ind;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }

    public Integer getWebind() {
        return webind;
    }

    public void setWebind(Integer webind) {
        this.webind = webind;
    }

    @Override
    public String toString() {
        return "BazniModel{" + "id=" + id + ", name=" + name + ", webime=" + webime + ", slika=" + slika + ", webslika=" + webslika + ", webopis=" + webopis + ", ind=" + ind + ", webind=" + webind + '}';
    }
/*   za klasu koja gapoyiva pravi handler */
public void Handler(){
    
    

}
  
}
