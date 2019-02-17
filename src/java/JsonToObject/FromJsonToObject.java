/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import model.C1;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Aleksandra
 */
public class FromJsonToObject {

    String fajl;
    String tab;

    public FromJsonToObject(String fajl, String tab) throws InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException {
        try {
            this.fajl = fajl;

            this.tab = tab;

            //Vector<Object> spisak = new Vector<>();
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxFactory.newSAXParser();
            DefaultHandler handler = null;
              switch (tab) {
                case "c1":
                     
            handler = new MyHandlerC1();
                    break;
                case "c11":
                     handler = new MyHandlerC11();
                     
                    break;
                case "a1":
                     handler = new MyHandlerC1();
                    break;
            }
          

          //  MyHandlerC1 handler = new MyHandlerC1();

            parser.parse(FromJsonToObject.class.getResourceAsStream(fajl),
                    handler
            );
           spisak =  handler.getBooksList();
           
           /* spisak = (Vector<C1>) handler.getBooksList();
            for (C1 b : spisak) {
            System.out.println("-"+b.getId()+b.getName());
            b.GSnimaj();
            }*/
        } catch (IOException ex) {
            Logger.getLogger(FromJsonToObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
