package JsonToObject;

import java.util.Vector;
import model.BazniModel;
import model.C1;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandlerC1 extends DefaultHandler {
     private C1 objekat ;
    private Vector<C1> nizobjekata = new Vector<>();
   
    private String data;
    

    public Vector<C1> getBooksList() {
        return nizobjekata;
    }
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Document has been started");
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.startDocument();
        System.out.println("Document has ended");
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        System.out.println("Element started: " + qName);
        
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println("Attribute: "
                    + attributes.getLocalName(i) + ": "
                    + attributes.getValue(i));
        }
        
        if (qName.equalsIgnoreCase("Drzava")) {
             int id = Integer.parseInt(attributes.getValue("id"));
               System.out.println("-----------------------------------------");
            objekat =  new C1();
            
            objekat.setId(id);
            
        } 
        
    }
    
    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        String nodeValue = new String(ch, start, length);
        if (!nodeValue.trim().equals("")) {
            System.out.println("Node value: " + nodeValue);
        }
        
        data = new String(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("Element finished: " + qName);
       
        if (qName.equalsIgnoreCase("name")) {
            System.out.println("---"+data);
            objekat.setName(data);
        
        } else if (qName.equalsIgnoreCase("webname")) {
            objekat.setWebime(data);
        }else if (qName.equalsIgnoreCase("slika")) {
            objekat.setSlika(data);
        }else if (qName.equalsIgnoreCase("webslika")) {
            objekat.setWebslika(data);
        }else if (qName.equalsIgnoreCase("webopis")) {
            objekat.setWebopis(data);
        }else if (qName.equalsIgnoreCase("webind")) {
            objekat.setWebind(Integer.parseInt(data));
        }else if (qName.equalsIgnoreCase("ind")) {
            objekat.setInd(Integer.parseInt(data));
        }
        
        if(qName.equalsIgnoreCase("Drzava")){
     nizobjekata.add(objekat);
        }       
    }
}
