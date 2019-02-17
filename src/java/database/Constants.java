/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.DbUtil;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author D
 */
public class Constants {
//public static final Object[] MENU_HEADER;

    public static Vector<String> svetabele;
    public static HashMap<String, Vector<String>> setTabelaKljuceva;
    /* set tabela sa stranim kljucevima*/
    public static HashMap<String, HashMap<String, String>> setTabelaFKKljuceva;
    public static HashMap<String, HashMap<String, String>> setTabelaPoljaTipova;
    /* set tabela sa primarnim indeksima*/
    public static Vector<String> prkizatab;
    public static HashMap<String, Vector<String>> FieldsForTable;
    public static  String[] MENU_HEADER;
     public static  String[] WEBMENU_HEADER;
    public static  String[][] SUBMENU;
    private static Constants instancaKonstanti;
    public  static int ONE_SECOND;
    public static Map<String, String> ImezaTabelu;
    public static Map<String, String> TabelazaWebIme;
    public static Map<String, String> WebImeZaIme;
    public  static int SCRH;
    public  static int SCRW;
    public static HashMap<String, HashMap<String, HashMap<String, String>>> tabelePoljaKarakt;
    public static HashMap<String, HashMap<String, String>> poljeKarakteristike;
public final static String APP_DIR;
public final static String IMG_DIR;
    private Constants() {

    }

    public static Constants getInstance() {
        return instancaKonstanti;
    }

    static {
         APP_DIR = "E:\\PROJEKTI SA PREDAVANJA\\WebGenPro\\web";
          IMG_DIR = "E:\\PROJEKTI SA PREDAVANJA\\WebGenPro\\web\\images";
        instancaKonstanti = new Constants();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SCRH = screenSize.height;
        SCRW = screenSize.width;

        MENU_HEADER = new String[]{"Tabele", "Podaci o firmi", "Katalozi", "Izvestaji", "Stampa", "Help"};
        WEBMENU_HEADER = new String[]{"AKCIJA DANA ", "Novosti", "Katalozi", "Poslovanje", "Prodajan mesta"};
        /* prvi SUBMENU sadrzi  tabele pa treba biti prazan ili  dodaje te stavjke na dno*/
        SUBMENU = new String[][]{
            {"Dodaj", "Dodaj meni"},
            {"Akcijskecene", "Apple Inc. popusti","Samo danas "},
            {"Vesti iz Srbije", "Vestiiz belog sveta","Vesti o nama"},
            {"Januar 2019","Decembar 2018","Novembar 2018"},
            {"Uslovi","Garancija"},
            {"a1", "a2", "a3", "a4"},
            {"Prod 1","Prod 1","Prod 1","Prod 1","Prod 1"},
            {}};
        ONE_SECOND = 1000;
        try {
            /*Uvozimo promenljive koje su const pri startuprograma i retko se menjaju a mnogo pozivajy
            Primer polja u tabelama i kljucevi
            SQL upiti
            getAllTables
             */

            svetabele = new Vector<>();
            /*spisaktabela*/
            setTabelaPoljaTipova = new HashMap<>();

            setTabelaKljuceva = new HashMap<>();
            /* set tabela sa stranim kljucevima*/
            setTabelaFKKljuceva = new HashMap<>();
            /* set tabela sa primarnim indeksima*/
            Vector<String> prkizatab = new Vector<>();

            /*  set tabela sa imenima polja redosled iz tabele*/
            FieldsForTable = new HashMap<>();

            //  String upit = "SELECT Table_name FROM INFORMATION_SCHEMA.TABLES where table_SCHEMA=\"roland_webshop\"";
            String upit = "SELECT TABLE_NAME,COLUMN_NAME,ORDINAL_POSITION,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE,NUMERIC_PRECISION,COLUMN_TYPE,COLUMN_KEY,EXTRA FROM INFORMATION_SCHEMA.columns where table_SCHEMA='webgenbaza';";
            Vector<String> TABELE = new Vector<>();
            Vector<String> POLJA = new Vector<>();
            Vector<String> TIPOVI = new Vector<>();
            Vector<String> ColumnTIPOVI = new Vector<>();
            Vector<String> PRIMKLJUC = new Vector<>();
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            DatabaseMetaData metaData = baza.getMetaData();
            Statement st = baza.createStatement();
            ResultSet rs = st.executeQuery(upit);

        
            while (rs.next()) {

                TABELE.add(rs.getString("TABLE_NAME"));
                POLJA.add(rs.getString("COLUMN_NAME"));
                TIPOVI.add(rs.getString("DATA_TYPE"));
                ColumnTIPOVI.add(rs.getString("COLUMN_TYPE"));
                PRIMKLJUC.add(rs.getString("COLUMN_KEY"));

            }
         
            int du;
            String poctab = TABELE.get(0);
            tabelePoljaKarakt = new HashMap<>();
            poljeKarakteristike = new HashMap<>();

            for (du = 0; du < TABELE.size(); du++) {
                
                if (poctab.equals(TABELE.get(du))) {

                    HashMap<String, String> Karakteristike = new HashMap<>();
                    Karakteristike.put("DATA_TYPE", TIPOVI.get(du));
                    Karakteristike.put("COLUMN_TYPE", ColumnTIPOVI.get(du));
                    Karakteristike.put("COLUMN_KEY", PRIMKLJUC.get(du));
                    poljeKarakteristike.put(POLJA.get(du), Karakteristike);

                } else {
                    tabelePoljaKarakt.put(poctab, poljeKarakteristike);
                    poljeKarakteristike = new HashMap<>();

                    HashMap<String, String> Karakteristike = new HashMap<>();
                    Karakteristike.put("DATA_TYPE", TIPOVI.get(du));
                    Karakteristike.put("COLUMN_TYPE", ColumnTIPOVI.get(du));
                    Karakteristike.put("COLUMN_KEY", PRIMKLJUC.get(du));
                    poljeKarakteristike.put(POLJA.get(du), Karakteristike);

                    poctab = TABELE.get(du);

                }

            }
            /*   posledni par u hashmapi ubaciti po kraju petlje*/

            tabelePoljaKarakt.put(poctab, poljeKarakteristike);

            /*   posledni par u hashmapi ubaciti po kraju petlje*/
            Vector<String> allFields;

            for (String key : tabelePoljaKarakt.keySet()) {
                allFields = new Vector<>();
                for (String key1 : tabelePoljaKarakt.get(key).keySet()) {
                    allFields.add(key1);
                   
                }

                FieldsForTable.put(key, allFields);
                svetabele.add(key);
            }


            /*  proveravamodaliimanove tabeleza ubaciti u glavnimeni tabelu  */
            /*   upit presekomtrazi dali postoji vec tabela sa imenom.akojenemadodaje jeu glavnimeni i setuje parent na 0*/
            //dohvata  primarne kljuceve za  tabele
            for (String tab : svetabele) {
                //   Class<?> jj = Class.forName("model." + tab);
                //  Field[] polja = jj.getFields();

                ResultSet pkColumns = metaData.getPrimaryKeys(null, null, tab);
                Vector<String> pkColumnSet = new Vector<>();
                while (pkColumns.next()) {
                    String pkColumnName = pkColumns.getString("COLUMN_NAME");
                    Integer pkPosition = pkColumns.getInt("KEY_SEQ");
                    //    out.println("" + pkColumnName + " is the " + pkPosition + ". column of the primary key of the table " + tab);
                    pkColumnSet.add(pkColumnName);
                }

                setTabelaKljuceva.put(tab, pkColumnSet);
            }

            for (String tab : svetabele) {
                ResultSet foreignKeys = metaData.getImportedKeys(baza.getCatalog(), null, tab);
                HashMap<String, String> fkljucevi = new HashMap<>();
                fkljucevi.clear();
                while (foreignKeys.next()) {
                    String fkTableName = foreignKeys.getString("FKTABLE_NAME");
                    String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                    String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                    String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
                    fkljucevi.put(fkTableName + "." + fkColumnName, pkTableName + "." + pkColumnName);
                    //   System.out.println(fkTableName + "." + fkColumnName + " -> " + pkTableName + "." + pkColumnName);
                }
                setTabelaFKKljuceva.put(tab, fkljucevi);
            }

            /*  INSERT koji proverava da li ima novih tabela dodatih u bazu.
            Ako ima dodaje tu novu tabelu u glavnimeni i setuje ime i webime na
            pocetne vrednosti.
            Stavlja parent na 0- pripadace dod daljnjeg prvom meniju.*/
            Statement st1 = baza.createStatement();
            for (String tab : svetabele) {
                String upitzatab;
                upitzatab = "INSERT INTO glavnimeni ( name,tabela,webime,parent ) SELECT * FROM (SELECT '" + tab.substring(0, 1).toUpperCase() + tab.substring(1) + "-priv', '" + tab + "' , 'Web" + tab + "',0) AS tmp WHERE NOT EXISTS (  SELECT tabela FROM glavnimeni WHERE tabela = '" + tab + "') LIMIT 1;";
                System.out.println("" + upitzatab);
                int rs1 = st1.executeUpdate(upitzatab);
                System.out.println("int " + rs1);
            }
            /*   sastavljam HashMap  ime za menu (name)-> tabela */
            /*  HashMap wrbime->tabela */

            Statement st2 = baza.createStatement();
            String upitzatab = "select name,tabela,webime from glavnimeni ; ";
            ResultSet resultSet = st2.executeQuery(upitzatab);

            ImezaTabelu = new HashMap<>();
            TabelazaWebIme = new HashMap<>();
            WebImeZaIme = new HashMap<>();
            while (resultSet.next()) {
                String im = resultSet.getString(1);
                String im1 = resultSet.getString(2);
                String im2 = resultSet.getString(3);
                ImezaTabelu.put(im, im1);
                TabelazaWebIme.put(im1, im2);
                WebImeZaIme.put(im2, im);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
