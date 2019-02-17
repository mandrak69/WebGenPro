package JsonToObject;

import java.util.ArrayList;
import java.util.List;
import model.C11;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandlerC11 extends DefaultHandler {
    
    private List<C11> nizobjekata = new ArrayList<>();
    private C11 objekat = null;
    private String data = "";
    

    public List<C11> getBooksList() {
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
        
        if (qName.equalsIgnoreCase("Grad")) {
            int id = Integer.parseInt(attributes.getValue("id"));
            objekat = new C11();
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
            objekat.setName(data);
        } else if (qName.equalsIgnoreCase("drzava_id")) {
            objekat.setId(Integer.parseInt(data));
        } else if (qName.equalsIgnoreCase("webime")) {
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
        
        if(qName.equalsIgnoreCase("Grad")){
                   nizobjekata.add(objekat);
}
    }
}
