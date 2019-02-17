/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Jovan
 *
 * Ova klasa sadrzi metode pomocu kojih se prilikom slanja upita u bazu
 * uspostavlja konekcija sa bazom.
 */
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DbUtil {
    //  private String dbname="roland_webshop";
    //  private String port="3307";
    //   private String user = "root";
    //  private String password = "";
    //  private String url = "jdbc:mysql://localhost:"+port+"/"+dbname;

    private static Connection conn = null;
    private static DbUtil instance = null;

    private DbUtil() {

    }

    public static DbUtil getInstance() {
        if (instance == null) {
            instance = new DbUtil();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }

    static {
        String dbname = "webgenbaza";
        String port = "3306";
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:" + port + "/" + dbname;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final <E> List<E> getInstanceList(Class<E> cls, ResultSet rs) {
        List<E> resultList = new ArrayList();
        try {
            E object = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            while (rs.next()) {
                object = cls.newInstance();
                for (Field field : fields) {
                    String fieldName = field.getName();

                    //      PropertyUtils.setProperty(object, fieldName, rs.getObject(fieldName));
                }
                resultList.add(object);
            }
        } catch (Exception e) {

        }
        return resultList;
    }

    public static int[] viseSQLodjednom(Vector<String> params) {
    //    String[] oo = params;
        try { 
            System.out.println("Creating statement...");
            DbUtil instanca = DbUtil.getInstance();
            Connection baza = instanca.getConn();
            Statement stmt = baza.createStatement();
            baza.setAutoCommit(false);
            // Set auto-commit to false
            for (String SQL:params){
            stmt.addBatch(SQL);
            }
       
          // Create an int[] to hold returned values
            int[] count = stmt.executeBatch();
           //Explicitly commit statements to apply changes
            baza.commit();
            // Clean-up environment
            stmt.close();
           

            return count;

        } catch (SQLException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
