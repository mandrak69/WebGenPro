/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/*
 *
 * Ova klasa sadrzi upite koji se salju u bazu radi izmene ili dohvatanja
 * podataka.
 */
import static JsonToObject.getJSONFromR.getJSONFromResultSet;
import static database.Biblos.GUcitaj;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.A11;

import model.Item;
import model.B11;
import static database.Constants.setTabelaFKKljuceva;
import static database.Constants.setTabelaKljuceva;
import static java.lang.Integer.parseInt;
import model.BazniModel;
import model.C1;
import model.Transaction;
import model.User;

public class DBQueries {

    //Kreira ArrayList za stampanje stranice sa proizvodima pomocu ProductServleta,
    public static <T> ArrayList<T> getAllProductsFromTab(String tabela, int id) throws InstantiationException {
        ArrayList<T> nizitema;
        nizitema = new ArrayList<>();
        try {
            Class<?> klasa = Class.forName("model." + tabela.substring(0, 1).toUpperCase() + tabela.substring(1));

            Object obj;

            Field[] polja = klasa.getFields();
            String strzadodaj = " ";
            String dodupit = "";
            String zarez = "";
            String zvezdica = "";
            for (Field polje : polja) {

                zvezdica = zvezdica + zarez + polje.getName();
                zarez = ",";

            }

            dodupit = dodupit + ("id =" + id + " ;");
            strzadodaj = " and ";

            String upit = "select " + zvezdica + " from " + tabela;
            upit = upit + " where " + dodupit + " ;";

            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            System.out.println("-----" + upit);
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            Field declaredField;
            while (rs.next()) {
                obj = klasa.newInstance();;
                for (int i = 1; i <= columns; i++) {
                    String type = md.getColumnTypeName(i);
                    declaredField = klasa.getField(polja[i - 1].getName());
                    boolean accessible = declaredField.isAccessible();
                    declaredField.setAccessible(true);
                    System.out.println(type + "iii" + i + declaredField.getName());

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
                /*  nov objekat popunjen rezultatima  dodaj ga u listu*/
                nizitema.add((T) obj);
                System.out.println("sada je  " + obj.toString());
            }

        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nizitema;

    }

    //Kreira ArrayList za stampanje stranice sa proizvodima pomocu ProductServleta,
    public static ArrayList<Item> getAllProductsFromItemforCat(String tabela, int cat_id) throws InstantiationException, ClassNotFoundException {
        ArrayList<Item> nizitema;
        nizitema = new ArrayList<>();
        try {
            Item uu = new Item();
            Class<?> klasa = Class.forName("model.Item");
            ArrayList<Field> polja = Item.getFields(uu);
            String strzadodaj = " ";
            String dodupit = "";
            String zarez = "";
            String zvezdica = "";
            for (Field polje : polja) {

                zvezdica = zvezdica + zarez + polje.getName();
                zarez = ",";

            }

            dodupit = dodupit + ("a11_id =" + cat_id + " ;");
            strzadodaj = " and ";

            String upit = "select " + zvezdica + " from " + tabela;
            upit = upit + " where " + dodupit + " ;";

            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            System.out.println("-----" + upit);
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            Item obj;
            Field declaredField;
            while (rs.next()) {
                obj = new Item();
                for (int i = 1; i <= columns; i++) {
                    String type = md.getColumnTypeName(i);

                    declaredField = polja.get(i - 1);
                    boolean accessible = polja.get(i - 1).isAccessible();
                    polja.get(i - 1).setAccessible(true);
                    System.out.println(type + "iii" + i + polja.get(i - 1).getName());

                    switch (type) {
                        case "FLOAT":
                            polja.get(i - 1).set(obj, rs.getFloat(i));

                            break;
                        case "DOUBLE":
                            declaredField.set(obj, rs.getDouble(i));

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
                /*  nov objekat popunjen rezultatima  dodaj ga u listu*/
                nizitema.add(obj);
                System.out.println("sada je  " + obj.toString());
            }

        } catch (SecurityException | SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nizitema;

    }

    //Konvertuje id kategorije u ime kategorije za getAllProducsFromCategory
    public static String getA11(int cat_id) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SQLException, ClassNotFoundException {
        String upit = "select name from a1 where id='" + cat_id + "';";
        int id = 0;
        String ime = "Nepoznata kategorija";
        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);
            rs.next();
            ime = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ime;

    }

    //Za zadati id i imeklase(ilitabele)  pronalazizapissa timid ivraca imepod tim id  */
    public static String getNamebuIdfromTab(String tab, int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IllegalArgumentException, SQLException, NoSuchMethodException, InvocationTargetException {
        String upit = "select name from " + tab + " where id=" + id + ";";
        String name = "";

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);
            rs.next();
            name = rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    //Salje update u bazu sa podacima novog korisnika iz RegisterServleta
    public static int RegisterUser(User u) {
        String upit = "insert into user (name, last_name, phone, email, address, username, password) values "
                + "('?', '?', '?', '?', '?', '?','?');";
     
     int rrs = 0;
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement st = baza.prepareStatement(upit);
             st.setString(1, u.getName());
              st.setString(2, u.getLast_name());
               st.setString(3, u.getPhone());
                st.setString(4, u.getEmail());
                 st.setString(5, u.getAddress());
                 
            st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                rrs = rs.getInt(1);
            } else {

                // throw an exception from here
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rrs;
    }

    //Konvertuje ime proizvodjaca u id proizvodjaca za CreateNewProduct
    public static int getManufacturerId(String name) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        try {
            B11 ct = new B11(name);
            System.out.println("--" + ct.toString());
            GUcitaj(ct);
            System.out.println("--" + ct.toString());
            return ct.getId();

        } catch (NoSuchFieldException | SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    //Konvertuje ime kategorije u id kategorije za CreateNewProduct
    public static int getA1Id(String name) {
        String upit = "select a1 from a11 where name=?;";
        int id = 0;

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            PreparedStatement st = baza.prepareStatement(upit);
            st.setString(1, name);
            ResultSet rs = st.executeQuery(upit);
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    //Salje insert u bazu sa podacima novog proizvoda.
    public static void CreateNewProduct(Item p) {
        String upit = "insert into item (name, a11, manufacturer, price, description, image) values "
                + "('" + p.getName() + "', '" +p.getA11_id() + "', '" + p.getB11_id()
                + "', '" + p.getCena() + "', '" + p.getWebopis() + "', '" + p.getSlika() + "');";
        System.out.println("upitje " + upit);
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            Statement st = baza.createStatement();
            st.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Proverava da li username vec postoji u bazi prilikom registracije korisnika u RegisterServletu
    public static boolean doesUserExist(String username) {
        boolean exists = false;
        String upit = "select * from user where username =?;";
        
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    //Proverava da li proizvod vec postoji u bazi prilikom dodavanja novih proizvoda.
    public static boolean doesProductExist(String name) {
        boolean exists = false;
        String upit = "select * from item where name =?;";
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    //Proverava da li korisnik postoji pri logovanju u LoginServletu
    public static boolean isUserValid(String username, String password) {
        boolean isValid = false;

        String upit = "select * from user where username = ? and password = ?";
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isValid;
    }

    //Vraca id korisnika za atribut sesije u LoginServletu
    public static int isUserValidReturnId(String username, String password) {
        String upit = "select id,nivokorisnika_id from user where username = ? and password = ?";
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    //Dohvata podatke o korisniku za prikaz na stranici account.jsp
    public static User getUser(int id) {
        String upit = "select id,name,last_name,phone,email,address,username,password,c11_id,nivokorisnika_id from user where id=?;";
        User u = new User();
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, ""+id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            u.setId(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setLast_name(rs.getString(3));
            u.setPhone(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setAddress(rs.getString(6));
            u.setUsername(rs.getString(7));
            u.setPassword(rs.getString(8));
            u.setGrad_id(rs.getInt(9));
            u.setNivokorisnika_id(rs.getInt(10));
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    //Upit koji upisuje kupljeni proizvod u tabelu transaction iz PurchaseServleta
    public static void PurchaseProduct(String date, int user_id, int item_id, double cena) {
        String upit = "insert into transaction (date, user, item, approved,price) values (?,,?,?, 'no' ,? );";
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
           
            PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, date);
            ps.setString(2, ""+user_id);
             ps.setString(3, ""+item_id);
              ps.setString(4, ""+cena);
               
               
            ps.executeUpdate(upit);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Dohvata username iz user tabele u formi Stringa za getTransactionListUser metodu ispod
    public static String getUsernameForTransactionList(int user_id) {
        String upit = "select username from user where id=?;";
        String username = "";

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
             PreparedStatement ps = baza.prepareStatement(upit);
            ps.setString(1, ""+user_id);
            ResultSet rs = ps.executeQuery(upit);
            rs.next();
            username = rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username;
    }

    //Dohvata ime proizvoda iz item tabele u formi Stringa za getTransactionListUser metodu ispod
    public static String getItemNameForTransactionList(int item_id) {
        String upit = "select name from item where id=" + item_id + ";";
        String item = "";

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);
            rs.next();
            item = rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    //Pravi ArrayListu transakcija iz tabele transaction za TransactionListServlet za *korisnika*
    public static ArrayList<Transaction> getTransactionList(int user_id, int nivo) {
        String upit = "select * from transaction where user_id=" + user_id + ";";
        if (nivo > 7) {
            /*  ako je admin daj sve*/
            upit = "select * from transaction ;";
        } else {

        }

        ArrayList<Transaction> transakcije
                = new ArrayList<>();

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                Transaction t = new Transaction(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));

                transakcije.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transakcije;
    }

    public static String getKorpuforUser(int user_id) {
        String upit = "select price,kol from transaction where user_id=" + user_id + ";";

        int brtr = 0;
        int iznos = 0;

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);
            double pr;
            int kl;
            while (rs.next()) {
                pr = rs.getDouble(1);
                kl = rs.getInt(2);
                brtr = brtr + kl;
                iznos = (int) (iznos + pr * kl);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "*" + brtr + "/" + iznos;
    }

    public static Vector<A11> getA1() throws SQLException {
        Vector<A11> categories = new Vector<>();

        String upit = "select id,webime,a1_id from a11 ;";
        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        PreparedStatement pst = baza.prepareStatement(upit);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            A11 c = new A11(rs.getInt(1), rs.getString(2), rs.getInt(3));
            categories.add(c);

        }

        return categories;

    }

    //Dohvata podatke iz tabele item i pravi Proizvod za TransactionListItemServlet
    public static Item getProductForTransactionList(String name) {
        String upit = "select * from item where name ='" + name + "';";
        Item p = new Item();
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);
            rs.next();

            p.setId(rs.getInt(1));
            p.setCena(rs.getDouble(5));
            p.setName(rs.getString(2));
            p.setWebopis(rs.getString(6));
            p.setA11_id(rs.getInt(3));
            p.setB11_id(rs.getInt(4));
            p.setSlika(rs.getString(7));
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    //Dohvata podatke i pravi Usera za TransactionListUserServlet
    public static User getUserForTransactionList(String username) {
        String upit = "select * from user where username='" + username + "';";
        User u = new User();
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            PreparedStatement ps = baza.prepareStatement(upit);
            ResultSet rs = ps.executeQuery();
            rs.next();

            u.setId(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setLast_name(rs.getString(3));
            u.setPhone(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setAddress(rs.getString(6));
            u.setUsername(rs.getString(7));
            u.setPassword(rs.getString(8));
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
// Validira -  prebacuje zapis iz tabele transaction u tabelu trans
    //Validira transakciju tako sto u tabeli transaction updateuje kolonu approved na 'yes'

    public static void ValidatePurchase(int transaction_id) {
        String upit = "update transaction set approved='yes' where id=" + transaction_id + ";";
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            Statement st = baza.createStatement();
            st.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Dohvata korisnike i pravi listu za prikaz na UserListServlet za administratora
    public static ArrayList<User> getUsersForUserList() {

        String strr = " id,name,last_name,phone,email,address,username,password,grad_id,nivokorisnika_id ";

        String upit = "select " + strr + " from user;";
        ArrayList<User> korisnici
                = new ArrayList<>();

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                User u
                        = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));

                korisnici.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return korisnici;
    }

    public static List<HashMap<?, ?>> getAllizTab(Object model) {
        List<HashMap<?, ?>> list = new ArrayList<>();
        try {
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();

            String upit = "select * from " + model.getClass().getSimpleName() + "; ";

            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

            try {
                list = convertResultSetToList(rs);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /*  metod getTabela   prima model,prepoznaje tabelu,vadi metapodatke za sva polja 
     *   vraca u obliku HashMape podatke o objektima 
     *   kljuc je ime polja.
     */
    public static List<HashMap<?, ?>> getTabela(Object model, String kljuc) throws SQLException, NoSuchMethodException {
        Connection baza = DbUtil.getInstance().getConn();
        if (kljuc == null || "".equals(kljuc)) {

            ResultSet pkColumns = baza.getMetaData().getPrimaryKeys(null, null, model.getClass().getSimpleName());
            SortedSet<String> pkColumnSet = new TreeSet<>();
            while (pkColumns.next()) {
                String pkColumnName = pkColumns.getString("COLUMN_NAME");
                Integer pkPosition = pkColumns.getInt("KEY_SEQ");

                pkColumnSet.add(pkColumnName);
            }
            
            kljuc = pkColumnSet.first();
        }
        String upit = "Select *" + " from " + model.getClass().getSimpleName() + " order by " + kljuc + " ;";
        Statement st = baza.createStatement();
        ResultSet rs = st.executeQuery(upit);

        List<HashMap<?, ?>> list = new ArrayList<>();

        list = convertResultSetToList(rs);

        return list;
    }

    /*  metod getTabela   prima model,prepoznaje tabelu,vadi metapodatke za sva polja 
     *   vraca u obliku HashMape podatke o objektima 
     *   kljuc je ime polja. samo gde jekljuc>0  tj.  ukljucen za web
     */
    public static List<HashMap<?, ?>> webTabela(Object model, String kljuc) throws SQLException, NoSuchMethodException {
        Connection baza = DbUtil.getInstance().getConn();
        if (kljuc == null || kljuc == "") {

            ResultSet pkColumns = baza.getMetaData().getPrimaryKeys(null, null, model.getClass().getSimpleName());
            SortedSet<String> pkColumnSet = new TreeSet<>();
            while (pkColumns.next()) {
                String pkColumnName = pkColumns.getString("COLUMN_NAME");
                Integer pkPosition = pkColumns.getInt("KEY_SEQ");

                pkColumnSet.add(pkColumnName);
            }
            System.out.println("---" + pkColumnSet);
            Vector<String> kljucpr = setTabelaKljuceva.get(model.getClass().getSimpleName().toLowerCase());
            kljuc = kljucpr.firstElement();
        }
        String upit = "Select *" + " from " + model.getClass().getSimpleName() + " where (" + kljuc + ">0)" + " order by " + kljuc + " ASC ,parent ASC ;";
        Statement st = baza.createStatement();
        ResultSet rs = st.executeQuery(upit);

        List<HashMap<?, ?>> list = new ArrayList<>();

        list = convertResultSetToList(rs);

        return list;
    }

    /* ocita tabelu i pretvori zapise pomocu metadata u formu imepolja,podaci kao u objektu */
 /* zadati ResultSet pretvara u Listu (naziv polja , Vrednosti iscitane iz baze=objekat u formi JSON zapisa  */
    public static List<HashMap<?, ?>> convertResultSetToList(ResultSet rs) throws SQLException, NoSuchMethodException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();

        List<HashMap<?, ?>> list = new ArrayList<>();

        while (rs.next()) {
            HashMap row;
            row = new HashMap();
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));

            }
            row.put("kljuc", rs.getObject(columns));

            list.add(row);

        }

        return list;
    }

    public static String getGlavniMeni(int nivousera) {
        String kraj = "";
        String drvo1 = "";
        int red = 0;
        String[] MENU_HEADER1 = new String[]{"Tabele", "Podaci o firmi", "Katalozi", "Izvestaji", "Stampa", "Help"};
        String[] WEBMENU_HEADER1 = new String[]{"AKCIJA DANA ", "Katalozi", "Prodajan mesta"};
        /* prvi SUBMENU sadrzi  tabele pa treba biti prazan ili  dodaje te stavjke na dno*/
        String[][] SUBMENU1 = new String[][]{
            {"aa", "bb"},
            {"Akcijskecene", "Apple Inc. popusti", "Samo sada "},
            {"Vesti iz Srbije", "Vestiiz belogsveta", "Vesti o nama"},
            {"Januar 2019", "Decembar 2018", "Novembar 2018"},
            {"Uslovi", "Garancija"},
            {"a1", "a2", "a3", "a4"},
            {"Prod 1", "Prod 1", "Prod 1", "Prod 1", "Prod 1"},
            {}};
        /* prvi SUBMENU sadrzi  tabele pa treba biti prazan ili  dodaje te stavjke na dno*/

        //  database.Constants.getInstance();
        for (String glmen : WEBMENU_HEADER1) {
            if (SUBMENU1[red].length > 0) {
                drvo1 = drvo1 + "<div class='navdropdown'>" + "<button class='navdropbtn'>" + glmen + "</button><div class='navdropdown-content'>";

                for (String stavka : SUBMENU1[red]) {
                    drvo1 = drvo1 + "<a href='" + "#" + "'>" + stavka + "</a>";

                }
                drvo1 = drvo1 + "</div>" + "</div>";
            } else {
                drvo1 = drvo1 + "<div class='navdropdown'>" + "<button class='navdropbtn'>" + glmen + "</button>";
                drvo1 = drvo1 + "</div>";
            }
            red++;
        }

       
        return drvo1;

    }

//Kreira ArrayList za stampanje stranice sa proizvodima pomocu ProductServleta,
    public static String getMenuTree(int nivousera) {
        String upit = "select parent,webime,id,controler,nivokorisnika_id,webind,tabela from  (select * from (select * from glavnimeni where nivokorisnika_id<=" + nivousera + ")as b ) as a order by "
                + "      case when parent = 0 then id else parent end, "
                + "      case when parent = 0 then 0 else id end";

        // String upit = "select parent,webime,id,controler,nivokorisnika_id from  (select * from glavnimeni where nivokorisnika_id<=" + nivousera + ") as a order by "
        //        + "      case when parent = 0 then id else parent end, "
        //       + "      case when parent = 0 then 0 else id end";
        System.out.println("upit" + upit);
        String drvo = "<div class='drvo'>";
        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        try {
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

            String kraj = "";
            while (rs.next()) {
                // System.out.println("--" + rs.getString(1) + rs.getString(2) + rs.getString(3));
              String  rr=rs.getString(1);
                
System.out.println("-*-"+rr);
                if (parseInt(rr)==0) {
                    drvo = drvo + kraj + kraj + "<div class='navdropdown'>" + "<button class='navdropbtn'>" + rs.getString(2) + "</button><div class='navdropdown-content'>";
                    kraj = "</div>";
                    System.out.println("--**--"+drvo);
                } else {
                    String ctrlr = rs.getString(4);
                    drvo = drvo + "<a href='" + rs.getString(4) + "?izb=" + rs.getString(7) + "'>" + rs.getString(2) + "</a>";
                    System.out.println("----"+drvo);
                }
            }
            drvo = drvo + "</div>" + "</div>";
               System.out.println("drvo" + drvo);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        drvo = drvo + getGlavniMeni(nivousera);
        return drvo;
    }

    /*  ----------------------------- pravimo  objekte iztabele-------------------------
    
    dohvata polja    
     */
    public static <T> ArrayList<T> getiztabele(Object model) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        String upit = "select * from " + model.getClass().getSimpleName() + " ;";
        System.out.println("------" + upit);
        ArrayList<T> ResList = new ArrayList<>();

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        Statement st = baza.createStatement();
        Class c = model.getClass();
        Object cat;

        // returns the array of Method objects
        //  Field[] m = c.getDeclaredFields();
        ArrayList<Field> m = BazniModel.getFields(model);
        ResultSet rs = st.executeQuery(upit);
        while (rs.next()) {
            cat = c.newInstance();
            for (int i = 1; i < m.size(); i++) {
                // System.out.println("polja = " + m[i].getName()+rs.getObject(i, m[i].getType()));
                m.get(i).setAccessible(true);
                m.get(i).set(cat, rs.getObject(i, m.get(i).getType()));
            }
            ResList.add((T) cat);

           
        }
      
        return ResList;
    }

    /*    Preko vectroa izvlaciizbaze trazenutabeluipravi JTable objekat spreman za prikaz
    * CASE za tippodataka ga razdvaja oddrugih pa imami i zvorni tip svakog polja
    *
     */
 /*  proverava kljuceve za poljaidovlaciumestokljucepolje name za zadati kljuc*/
    public static <T> HashMap<Vector<String>, Vector<T>> izTabeleSaKlucevima(String tabela) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        Vector<String> columnNames = new Vector<>();

        Vector<T> data = new Vector<>();

        DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();

        String sql = "Select * from " + tabela + " ;";

        Statement stmt = baza.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData md = rs.getMetaData();

        int columns = md.getColumnCount();

// The column count starts from 1
        for (int i = 1; i <= columns; i++) {
            System.out.println("iiiii" + i);

            columnNames.add(md.getColumnName(i));
        }
        // System.out.println("kljucevidohve" + setTabelaFKKljuceva.get(tabela.toLowerCase()));
        HashMap<String, String> fki = setTabelaFKKljuceva.get(tabela.toLowerCase());

        while (rs.next()) {
            Vector<T> v = new Vector<>(columns);

            for (int i = 1; i <= columns; i++) {
                String type = md.getColumnTypeName(i);

                System.out.println("imagakljuc" + fki.get(tabela + "." + md.getColumnName(i)));

                if (fki.containsKey(tabela + "." + md.getColumnName(i))) {

                    String kljuc = fki.get(tabela + "." + md.getColumnName(i));
                    /*postoima kljucnadjemogde pokazuje dohvatimo vrednost polja name i njega ubacujemo u pregled*/
                    System.out.println("---" + kljuc.substring(0, kljuc.indexOf(".")));
                    String namePoId = getNamebuIdfromTab(kljuc.substring(0, kljuc.indexOf(".")), rs.getInt(i));
                    type = "STRING";
                    v.add((T) namePoId);

                } else {

                    switch (type) {
                        case "FLOAT":
                            v.add((T) Float.toString(rs.getFloat(i)));
                            break;
                        case "COUNTER":
                            v.add((T) Integer.toString(rs.getInt(i)));
                            break;
                        case "VARCHAR":
                            v.add((T) rs.getString(i));
                            break;
                        case "INTEGER":
                            v.add((T) Integer.toString(rs.getInt(i)));
                            break;
                        case "DATETIME":
                            v.add((T) rs.getString(i));
                            break;
                        case "MEDIUMINT":
                            v.add((T) Integer.toString(rs.getInt(i)));
                            break;
                        case "LONGBLOB":
                            v.add((T) rs.getTime(i));
                            break;
                        default:
                            v.add((T) rs.getString(i));
                    }
                }

            }

            data.addElement((T) v);

        }

        rs.close();
        stmt.close();
        HashMap<Vector<String>, Vector<T>> tabIimena = new HashMap<>();

        tabIimena.put(columnNames, data);
        return tabIimena;
    }

    public static void printHeader(ResultSet rs, char symbol, int width) throws Exception {
        Vector<String> TABELE = new Vector<>();
        Vector<String> POLJA = new Vector<>();
        Vector<String> TIPOVI = new Vector<>();
        Vector<String> PRIMKLJUC = new Vector<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int nCols = rsmd.getColumnCount();
        while (rs.next()) {
            TABELE.add(rs.getString("TABLE_NAME"));
            POLJA.add(rs.getString("COLUMN_NAME"));
            TIPOVI.add(rs.getString("DATA_TYPE"));
            PRIMKLJUC.add(rs.getString("COLUMN_KEY"));

        }

        for (String dd : TABELE) {
            System.out.println("" + dd);
        }
        for (String dd : POLJA) {
            System.out.println("" + dd);
        }
        for (String dd : TIPOVI) {
            System.out.println("" + dd);
        }
    }

//call <br>
//printHeader(rs,'-',70);
    public static String KlasaNaWeb(String tabela) {
        try {
            /*pravi pravougaonik saslikomi tekstom .treba samozameniti linkka sliciiteks cenu
             */
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();

            String sql = "Select id,webslika,webime,name from " + tabela + " ;";

            Statement stmt = baza.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();

            int columns = md.getColumnCount();
            String srvlt = "OpstiServlet";
            if (tabela == "a11") {
                srvlt = "ProductServlet";
            }

            int idd;
            String oo;
            String a = "<div class=\"categories\">";
            String dod = " </div>";
            while (rs.next()) {

                idd = rs.getInt(1);

                a = a + "<div class='catlink'><a href='" + srvlt + "?tab=" + tabela + "&a11_id=" + idd + "' target='_blank'><img class='catimage' src='images/" + rs.getString(2) + "'/></a>";
                a = a + " <a href='" + srvlt + "?tab=" + tabela + "&a11_id=" + idd + "'><span>" + rs.getString(3) + "</span></a></div>";
                oo = rs.getString(4);

            }
            //    String   st1= " <li><img src='' style=' background-image: url('images/Digitech Delay.jpg'); z-index: -5; max-width: 10%;max-height: 10%;transform: scale(10);'>";

            //    String   st2= "<a href='' style='color:white'><h4>North Sky Resort Sogod</h4><p>Sogod, Cebu</p></a></li>";
            return a + dod;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    public String FromRsToJson(){
        try {
            /*  pravi JSON format string zapis iz resulset upit SQL */
            String upit="Select * from c1;";
            
            //      String upit = "SELECT TABLE_NAME,COLUMN_NAME,ORDINAL_POSITION,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE,NUMERIC_PRECISION,COLUMN_TYPE,COLUMN_KEY,EXTRA FROM INFORMATION_SCHEMA.columns where table_SCHEMA='roland_webshop';";
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            System.out.println("-----" + upit);
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);
            ResultSetMetaData md = rs.getMetaData();
            String xx = getJSONFromResultSet(rs,"Drzave");
            System.out.println(xx);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
