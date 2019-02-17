/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BazniModel;
import static database.Constants.setTabelaFKKljuceva;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dragan Markovic
 */
public class Biblos {

    private Object donja;
    private Object gornja;
    private Object model;

    public Biblos() {

    }

    public Biblos(Object donja, Object gornja, Object model) {
        this.donja = donja;
        this.gornja = gornja;
        this.model = model;
    }

    public static <T> int GObrisi(T obj) {

        int obrisan = -1;
        int brojid;
        String upit = "";
        try {
            Class<?> klasa = obj.getClass();
            ArrayList<Field> nizz = BazniModel.getFields(obj);
//Field[] nizz = obj.getClass().getFields();
          
            for (Field pop : nizz) {
                if ("id".equals(pop.getName())) {
                    pop.setAccessible(true);
                    brojid = (int) pop.get(obj);
                    DbUtil instanca = DbUtil.getInstance();
                    Connection baza = instanca.getConn();
                    upit = "DELETE FROM " + klasa.getSimpleName().toLowerCase() + " WHERE ID= ? ;";
                    PreparedStatement st = baza.prepareStatement(upit);
                    //    st.setString(1,"user");
                    st.setInt(1, brojid);
                    obrisan = st.executeUpdate();
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | SQLException ex) {
            Logger.getLogger(Biblos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obrisan;
    }
/*   doraditi u delu umetanja objkta */
    public static <T> T GUmetni(BazniModel obj) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SQLException {
        Vector<String> pki = Constants.setTabelaKljuceva.get(obj.getClass().getSimpleName());

        HashMap<String, String> kludr = setTabelaFKKljuceva.get(obj.getClass().getSimpleName());
        /* prvo proveritidalisusvikljucevipostavljeni i postoje u odgovarajucim tabelama*/
 ArrayList<Field> polja = BazniModel.getFields(obj);
      //  Field[] polja = obj.getClass().getFields();
        String strzadodaj = " ";
        String dodupit = "";
        String zarez = "";
        String zvezdica = "";
        for (Field polje : polja) {

            boolean accessible = polje.isAccessible();
            zvezdica = zvezdica + zarez + polje.getName();
            zarez = ",";
            polje.setAccessible(true);

            if (polje.get(obj) == null) {

            } else {
                String yy = "1" + polje.get(obj);
                if (yy.equals("10")) {

                } else {

                    dodupit = dodupit + (strzadodaj + polje.getName() + "='" + polje.get(obj) + "'");
                    strzadodaj = " and ";
                }

            }
            polje.setAccessible(accessible);
        }
        String upit = "select " + zvezdica + " from " + obj.getClass().getSimpleName() + " where ";
        upit = upit + dodupit + " ;";

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        try (Statement st = baza.createStatement(); 
                ResultSet rs = st.executeQuery(upit)) {

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            Class<?> kla = obj.getClass();
            Field declaredField;
            while (rs.next()) {

                for (int i = 1; i <= columns; i++) {
                    String type = md.getColumnTypeName(i);
                    declaredField = polja.get(i - 1);
                    boolean accessible = declaredField.isAccessible();
                    declaredField.setAccessible(true);
                    

                    switch (type) {
                        case "FLOAT":
                            declaredField.set(obj, rs.getFloat(i));

                            break;
                        case "COUNTER":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "VARCHAR":
                            declaredField.set(obj, rs.getString(i));

                            break;
                        case "INT":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "INTEGER":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "DATETIME":
                            declaredField.set(obj, rs.getString(i));

                            break;
                        case "MEDIUMINT":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "LONGBLOB":
                            declaredField.set(obj, rs.getTime(i));

                            break;
                        default:
                            declaredField.set(obj, rs.getString(i));

                    }
                    declaredField.setAccessible(accessible);
                }
                rs.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(Biblos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (T) obj;

    }

    /*   od objekta  do objekta ucitaj niz objekata */
    public static <T> Vector<T> GUcitaj(T objod, T objdo) throws
            IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
            SQLException, ClassNotFoundException, InstantiationException, InvocationTargetException {

        Vector<T> rezultNiz = new Vector<>();

        Vector<String> pki = Constants.setTabelaKljuceva.get(objod.getClass().getSimpleName());
        int imanesto = 0;
       ArrayList<Field> polja = BazniModel.getFields(objod);

       
        String strzadodaj = " ";
        String dodupit = "";
        String zarez = "";
        String zvezdica = "";
        for (Field polje : polja) {

            boolean accessible = polje.isAccessible();

            zvezdica = zvezdica + zarez + polje.getName();
            zarez = ",";
            polje.setAccessible(true);

            if (polje.get(objod) == null) {

            } else {
                String yy = "1" + polje.get(objod);
                if (yy.equals("10")) {

                } else {

                    dodupit = dodupit + "(" + (strzadodaj + polje.getName() + ">='" + polje.get(objod) + "' AND ");
                    dodupit = dodupit + (strzadodaj + polje.getName() + "<='" + polje.get(objdo) + "' )");
                    strzadodaj = " and ";
                }

            }
            polje.setAccessible(accessible);
        }
        if (zvezdica.isEmpty()) {

            zvezdica = "*";
        }
        String upit = "select " + zvezdica + " from " + objod.getClass().getSimpleName().toLowerCase();
        /*prazan where   uslov  */
        if (dodupit == "") {
            upit = upit + " ;";
        } else {
            upit = upit + " where " + dodupit + " ;";

        }

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        try (
                Statement st = baza.createStatement();
                ResultSet rs = st.executeQuery(upit)) {

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            Field declaredField;

            Class c = objod.getClass();

            while (rs.next()) {
                /*rezultat treba obraditi red po red objekatpo objekat*/
                Object uu = objod.getClass().cast(c.newInstance());
                imanesto = 1;
                for (int i = 1; i <= columns; i++) {

                    String type = md.getColumnTypeName(i);
   declaredField = polja.get(i - 1);

                    // declaredField = polja[i - 1];
                    boolean accessible = declaredField.isAccessible();
                    declaredField.setAccessible(true);

                    switch (type) {
                        case "FLOAT":
                            declaredField.set(uu, rs.getFloat(i));

                            break;
                        case "COUNTER":
                            declaredField.set(uu, rs.getInt(i));

                            break;
                        case "VARCHAR":
                            declaredField.set(uu, rs.getString(i));

                            break;
                        case "INT":
                            declaredField.set(uu, rs.getInt(i));

                            break;
                        case "INTEGER":
                            declaredField.set(uu, rs.getInt(i));

                            break;
                        case "DATETIME":
                            declaredField.set(uu, rs.getString(i));

                            break;
                        case "MEDIUMINT":
                            declaredField.set(uu, rs.getInt(i));

                            break;
                        case "LONGBLOB":
                            declaredField.set(uu, rs.getTime(i));

                            break;
                        default:
                            declaredField.set(uu, rs.getString(i));

                    }
                    declaredField.setAccessible(accessible);
                    
                }

                rezultNiz.add((T) uu);

            }
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Biblos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        //    System.out.println("sada je  " + obj.toString());

        if (imanesto == 0) {
            return null;

        } else {
            return rezultNiz;

        }

    }

    /*
    za objekat sa parametrima koje posedujepretraziodgovarajucu tabelu koju nadje po objektu
    Ako nadje prvi  takav primerak setuje ostala polja i vraca ga .
    Posle metode original ima  setovana polja .
    Ako ne nadje vraca null/
    Originalni objekat je isti
    Metod moze da se poziva sa a=GUcitaj(Objekat)   ili samo sa GUcitaj(Objekat)
     */
    public static <T> T GUcitaj(Object obj) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SQLException, ClassNotFoundException {

        Vector<String> pki = Constants.setTabelaKljuceva.get(obj.getClass().getSimpleName());
        int imanesto = 0;
        ArrayList<Field> polja = BazniModel.getFields(obj);

        String strzadodaj = " ";
        String dodupit = "";
        String zarez = "";
        String zvezdica = "";
        for (Field polje : polja) {

            boolean accessible = polje.isAccessible();

            zvezdica = zvezdica + zarez + polje.getName();
            zarez = ",";
            polje.setAccessible(true);

            if (polje.get(obj) == null) {

            } else {
                String yy = "1" + polje.get(obj);
                if (yy.equals("10")) {

                } else {

                    dodupit = dodupit + (strzadodaj + polje.getName() + "='" + polje.get(obj) + "'");
                    strzadodaj = " and ";
                }

            }
            polje.setAccessible(accessible);
        }
        if (zvezdica.isEmpty()) {

            zvezdica = "*";
        }
        String upit = "select " + zvezdica + " from " + obj.getClass().getSimpleName().toLowerCase();
        /*prazan where   uslov  */
        if (dodupit == "") {
            upit = upit + " ;";
        } else {
            upit = upit + " where " + dodupit + " ;";

        }

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        try (
                Statement st = baza.createStatement();
                ResultSet rs = st.executeQuery(upit)) {

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            Field declaredField;

            while (rs.next()) {
                imanesto = 1;

                for (int i = 1; i <= columns; i++) {

                    String type = md.getColumnTypeName(i);
                    declaredField = polja.get(i - 1);

                    // declaredField = polja[i - 1];
                    boolean accessible = declaredField.isAccessible();
                    declaredField.setAccessible(true);

                    switch (type) {
                        case "FLOAT":
                            declaredField.set(obj, rs.getFloat(i));

                            break;
                        case "COUNTER":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "VARCHAR":
                            declaredField.set(obj, rs.getString(i));

                            break;
                        case "INT":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "INTEGER":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "DATETIME":
                            declaredField.set(obj, rs.getString(i));

                            break;
                        case "MEDIUMINT":
                            declaredField.set(obj, rs.getInt(i));

                            break;
                        case "LONGBLOB":
                            declaredField.set(obj, rs.getTime(i));

                            break;
                        default:
                            declaredField.set(obj, rs.getString(i));

                    }
                    declaredField.setAccessible(accessible);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Biblos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        //    System.out.println("sada je  " + obj.toString());
        if (imanesto == 0) {
            return null;
        } else {
            return (T) obj;

        }
    }

    /*  
    **    Gsnimaj  po objektu pronalazi  tabelu,metod za dohvatanje svihpolja klase
    **   proverava ima li ID nekuvrednost. 
    **  Ako nema generise upit INSERT i izvrsava.
    **  Dobija nazad ID pod kojim je ubacen zapis.
    **  Setuje ID objekta na tu vrednost
    **  Vraca id generisanod strane MySQL 
    **  Ako ima definisan ID moramo proveriti da li ima zapisa satimID-UPDATE
    **  Ako nema onda je to i dalje  INSERT sa timID.-
     */
    public static int GSnimaj(Object obj) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        int noviid = -1;
        try { 
            BazniModel bm=new BazniModel();
            ArrayList<Field> polja = BazniModel.getFields(obj);
        //    boolean imaga = polja.contains(bm.getClass().getField("id"));
           
            Field uu = bm.getClass().getDeclaredField("id");
            uu.setAccessible(true);
            Object oo = uu.get(obj);
            Class<?> klasa = obj.getClass();
            String tabela = klasa.getSimpleName().toLowerCase();
            Vector<String> pki = Constants.setTabelaKljuceva.get(obj.getClass().getSimpleName());

           
          
            String strzadodaj = " ";
            String dodupit = "";
            String dodupit1 = "";
            boolean imaga = false;
            System.out.println("idzaovajobjekat" + oo);
            if (oo != null) {

                /*proveri  dali ima zapisa sa tim ID*/
                String up = "SELECT 1 FROM " + tabela + " WHERE ID =" + (int) oo + " ;";
                System.out.println("---------"+up);
                DbUtil instanca = DbUtil.getInstance();
                Connection baza = instanca.getConn();
                PreparedStatement ps = baza.prepareStatement(up);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("----"+rs.getString(1));
                    imaga = true;
                    /*  znaci ima ga ID -radi se UPDATE tog zapisa  */
                    System.out.println(rs.getString(1)+"nasaozapissa id "+(int) oo);
                    for (Field polje : polja) {

                        try {
                            boolean accessible = polje.isAccessible();

                            //       zarez = ",";
                            polje.setAccessible(true);

                            if (polje.getName() == "id" || polje.get(obj)==null) {
                            } else {
                                dodupit = dodupit + strzadodaj + polje.getName() + "='" + polje.get(obj) + "'";
                                dodupit1 = dodupit1 + "";
                                strzadodaj = " , ";
                            }

                            polje.setAccessible(accessible);
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(Biblos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    String upit = "UPDATE  " + tabela + " SET " + dodupit + " WHERE  id=" + (int) oo + ";";
                    System.out.println(dodupit + "/" + dodupit1 + "upitzaGINSERT " + upit);

                    //          String upitzatab = "INSERT INTO "+tabela+"(" + dodupit + ")" + " SELECT * FROM (SELECT " + dodupit1 + ") AS tmp WHERE NOT EXISTS (  SELECT tabela FROM "+tabela+" WHERE tabela = '" + tabela + "') LIMIT 1;";
                    int returnId = 0;

                    PreparedStatement st = baza.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
                    int affectedRows = st.executeUpdate();

                    if (affectedRows == 0) {
                        JOptionPane.showMessageDialog(null, "Opet?  Zapis sa tim imenom vec postoji...",
                                "NEUSPESNO", JOptionPane.CLOSED_OPTION);
                    }

                    rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        noviid = rs.getInt(1);
                        JOptionPane.showMessageDialog(null, "Unet novi zapis pod ID brojem " + noviid, "Uneto", JOptionPane.CLOSED_OPTION);
                        System.out.println(noviid);

                    }
                    rs.close();

                } else {
                    /*  nema zapisa sa tim ID  - obican INSERT u nastavku */

                }

            }

            if (imaga == false) {

               
                strzadodaj = " ";
                dodupit = "";
                dodupit1 = "";
                // String zarez = "";

                for (Field polje : polja) {

                    try {
                        boolean accessible = polje.isAccessible();

                        //       zarez = ",";
                        polje.setAccessible(true);

                        if (polje.get(obj) == null) {

                        } else {

                            dodupit = dodupit + strzadodaj + polje.getName();
                            dodupit1 = dodupit1 + strzadodaj + "'" + polje.get(obj) + "'";
                            strzadodaj = " , ";
                        }

                        polje.setAccessible(accessible);
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(Biblos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                String upit = "INSERT into " + tabela + "(" + dodupit + ")" + " VALUES ( " + dodupit1 + ");";
                System.out.println(dodupit + "/" + dodupit1 + "upitzaGINSERT " + upit);

                //          String upitzatab = "INSERT INTO "+tabela+"(" + dodupit + ")" + " SELECT * FROM (SELECT " + dodupit1 + ") AS tmp WHERE NOT EXISTS (  SELECT tabela FROM "+tabela+" WHERE tabela = '" + tabela + "') LIMIT 1;";
                DbUtil instanca = DbUtil.getInstance();
                Connection baza = instanca.getConn();
                int returnId = 0;

                PreparedStatement st = baza.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
                int affectedRows = st.executeUpdate();

                if (affectedRows == 0) {
                    JOptionPane.showMessageDialog(null, "Opet?  Zapis sa tim imenom vec postoji...",
                            "NEUSPESNO", JOptionPane.CLOSED_OPTION);
                }

                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    noviid = rs.getInt(1);
                    JOptionPane.showMessageDialog(null, "Unet novi zapis pod ID brojem " + noviid, "Uneto", JOptionPane.CLOSED_OPTION);
                    System.out.println(noviid);

                }
                rs.close();
            } else {

            }

        } catch (SQLException ex) {
            Logger.getLogger(Biblos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return noviid;
    }

    public Object getDonja() {
        return donja;
    }

    public void setDonja(Object donja) {
        this.donja = donja;
    }

    public Object getGoenja() {
        return gornja;
    }

    public void setGoenja(Object gornja) {
        this.gornja = gornja;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

}
