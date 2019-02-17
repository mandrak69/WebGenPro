/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToObject;


import static JsonToObject.getJSONFromR.getJSONFromResultSet;
import database.DbUtil;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import model.C1;
import org.xml.sax.SAXException;

/**
 *
 * @author Aleksandra
 */
public class FromJsonToObject1 {

    

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
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
         
        //  ubacujem dryave   FromJsonToObject gfd=new FromJsonToObject("myDrzava.xml","c1");
        //  ubacujem gradove     FromJsonToObject gfd=new FromJsonToObject("myGrad.xml","c11");
        
    }

}
