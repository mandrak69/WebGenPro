package Controll;


import database.DbUtil;
import database.SpringUtilities;
import static database.DBQueries.izTabeleSaKlucevima;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;
import static database.Constants.FieldsForTable;
import static database.Constants.setTabelaFKKljuceva;
import static database.Constants.setTabelaKljuceva;
import static database.Constants.setTabelaPoljaTipova;
import static database.Constants.tabelePoljaKarakt;
import java.awt.Color;
import java.awt.Container;
import model.BazniModel;

/**
 * TextInputDemo.java uses these additional files: SpringUtilities.java ...
 */
public class InputEkran extends JPanel
        implements ActionListener,
        FocusListener {

    JTextField TekstPolje, cityField;

    Vector<JTextField> nizTekstpolja;
    String radnatabela;
    JFormattedTextField FormatedFld;
    Vector<JTextField> nizFormatField;
    JSpinner stateSpinner;
    boolean boolPolje = false;
    Font regularFont, italicFont;
    JLabel labela;
    Vector<JLabel> nizlabela;
    final static int GAP = 10;

    public InputEkran(String tabela) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalf = new JPanel() {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                        pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf,
                BoxLayout.PAGE_AXIS));
        JComponent aa = createEntryFields(tabela);
        if (aa == null) {
            /*  nema podataka u tabelikoja jeukljucu ove tabele*/
            //labela.setText("Morateprvouneti zapise u tabeli "+tabela);


        } else {
            leftHalf.add(aa);
            leftHalf.add(createButtons());
        }

        add(leftHalf);
        add(createAddressDisplay());
    }

    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        int i;
        JLabel NadjiSliku;
        JButton Snimaj;
        JButton Obrisi;
        JButton Odustani;
        Test  ttr=new Test();
        ttr.label.setText("Nadji sliku");
        
        
        panel.add(ttr.label);
        Snimaj = new JButton("Snimaj");
        Snimaj.addActionListener(this);
        panel.add(Snimaj);
        Obrisi = new JButton("Obrisi");
        Obrisi.addActionListener(this);
        panel.add(Obrisi);
        Odustani = new JButton("Odustani");
        Odustani.addActionListener(this);
        panel.add(Odustani);

        //Match the SpringLayout's gap, subtracting 5 to make
        //up for the default gap FlowLayout provides.
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP - 5, GAP - 5));
        return panel;
    }

    /**
     * Called when the user clicks the dugme or presses Enter in a text field.
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("iz  InputEkranlin100  zoviproveruuneseni stvari pre inserta");

        if ("Snimaj".equals(e.getActionCommand())) {
            try {
                /*  napravi  objekat klase kojise unosi
        setuj sva poljana unete vrednosti
        GSnimaj(tajobjekat)
        ili belezi u tabelu sa insert ako ganema vec
                 */
                String zarez = "";
                String upisiu = " ";
                String upisiizm = " ";
                String upisiSta = " ";
                String namezaprov = "";
                for (JTextField polje : nizTekstpolja) {
                    if (polje.getName() == "name") {
                        /*iztextfield ->nameuzimamo tekst za proveru duplog unosa npr. Leskovac-Leskovac*/
                        namezaprov = polje.getText();
                    }
                    System.out.println("polje-" + polje.getName() + "*" + polje.getText());
                    if (polje.getText().trim().length() == 0) {

                    } else {
                        upisiu = upisiu + zarez + polje.getName();
                        upisiizm = upisiizm + zarez + "'" + polje.getName() + "'";
                        upisiSta = upisiSta + zarez + "'" + polje.getText() + "'";
                        zarez = ",";
                    }
                }

                String upitzatab = "INSERT INTO " + radnatabela + " ( " + upisiu + " ) SELECT" + upisiSta + " FROM (SELECT " + upisiizm + ") AS tmp WHERE NOT EXISTS (  SELECT id  FROM " + radnatabela + " WHERE name = '" + namezaprov + "') LIMIT 1;";
                System.out.println("upit " + upitzatab);

                DbUtil instanca = DbUtil.getInstance();
                Connection baza = instanca.getConn();
                int returnId = 0;

                PreparedStatement st = baza.prepareStatement(upitzatab, PreparedStatement.RETURN_GENERATED_KEYS);
                int affectedRows = st.executeUpdate();

                if (affectedRows == 0) {
                    JOptionPane.showMessageDialog(null, "Opet?  Zapis sa tim imenom vec postoji...",
                            "NEUSPESNO", JOptionPane.CLOSED_OPTION);
                }

                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int noviid = rs.getInt(1);
                        JOptionPane.showMessageDialog(null, "Unet novi zapis pod ID brojem " + noviid, "Uneto", JOptionPane.CLOSED_OPTION);
                        System.out.println(rs.getInt(1));
                    }
                    rs.close();

                }

            } catch (SQLException ex) {
                Logger.getLogger(InputEkran.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            boolPolje = true;
        }
        updateDisplays();
    }

    protected void updateDisplays() {
        labela.setText(formatAddress());
        if (boolPolje) {
            labela.setFont(regularFont);
        } else {
            labela.setFont(italicFont);
        }
    }

    protected JComponent createAddressDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        labela = new JLabel();
        labela.setHorizontalAlignment(JLabel.CENTER);
        regularFont = labela.getFont().deriveFont(Font.PLAIN,
                16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        //Lay out the panel.
        panel.setBorder(BorderFactory.createEmptyBorder(
                GAP / 2, //top
                0, //left
                GAP / 2, //bottom
                0));   //right
        panel.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        panel.add(labela,
                BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }

    protected String formatAddress() {
        /*proc za proveru ispravnostipolja*/

        if (!boolPolje) {

            return "Unosite novi zapis";

        }

        String street = TekstPolje.getText();
        String city = cityField.getText();
        String state = (String) stateSpinner.getValue();
        String zip = FormatedFld.getText();
        String empty = "";

        if ((street == null) || empty.equals(street)) {
            street = "<em>(no street specified)</em>";
        }
        if ((city == null) || empty.equals(city)) {
            city = "<em>(no city specified)</em>";
        }
        if ((state == null) || empty.equals(state)) {
            state = "<em>(no state specified)</em>";
        } else {
            int abbrevIndex = state.indexOf('(') + 1;
            state = state.substring(abbrevIndex,
                    abbrevIndex + 2);
        }
        if ((zip == null) || empty.equals(zip)) {
            zip = "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<html><p align=center>");
        sb.append(street);
        sb.append("<br>");
        sb.append(city);
        sb.append(" ");
        sb.append(state); //should format
        sb.append(" ");
        sb.append(zip);
        sb.append("</p></html>");

        return sb.toString();
    }

    //A convenience method for creating a MaskFormatter.
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    /**
     * Called when one of the fields gets the focus so that we can select the
     * focused field.
     */
    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JFormattedTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField) c).selectAll();
        }
    }

    //Workaround for formatted text field focus side effects.
    protected void selectItLater(Component c) {
        if (c instanceof JFormattedTextField) {
            final JFormattedTextField ftf = (JFormattedTextField) c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    //Needed for FocusListener interface.
    public void focusLost(FocusEvent e) {
    } //ignore

    public JComponent createEntryFields(String tabela) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NullPointerException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        radnatabela = tabela;
        /*polja sa Mapom  karakteristika svakog poljaiz tabele*/

        HashMap<String, HashMap<String, String>> nizzaRegex = tabelePoljaKarakt.get(tabela);
        for (String key1 : nizzaRegex.keySet()) {
            HashMap<String, String> podniz = nizzaRegex.get(key1);
            System.out.println("polja" + podniz + nizzaRegex.get("COLUMN_TYPE"));

        }

        HashMap<String, String> fki = setTabelaFKKljuceva.get(tabela.toLowerCase());
        Vector<String> pki = setTabelaKljuceva.get(tabela.toLowerCase());
        ;
        Class<?> klasa = Class.forName("model." + tabela.substring(0, 1).toUpperCase() + tabela.substring(1));
        Object aa = klasa.newInstance();
        JPanel panel = new JPanel(new SpringLayout());

        Vector<String> polja = FieldsForTable.get(tabela.toLowerCase());
        HashMap<String, String> poljetip = setTabelaPoljaTipova.get(tabela);
        ArrayList<Field> poljazatab = BazniModel.getFields(aa);    

        nizTekstpolja = new Vector<>();
        String[] labelStrings = new String[poljazatab.size()];
       
       
        JLabel[] labels = new JLabel[poljazatab.size()];;

        JComponent[] fields = new JComponent[poljazatab.size()];
        int fieldNum = 0;
        String dbtabela;

        for (Field ff : poljazatab) {

            if (fki.containsKey(tabela.toLowerCase() + "." + ff.getName())) {
//   ima kljuc -moramo vezati unos za neki JList iz tabele na koju ukazuje   kljuc i polje iz kljuca

                String kljuc = fki.get(tabela.toLowerCase() + "." + ff.getName());
                /*posto ima kljuc nadjemo gde pokazuje dohvatimo vrednost polja name i njega ubacujemo u pregled*/

                dbtabela = kljuc.substring(0, kljuc.indexOf("."));
                HashMap<Vector<String>, Vector<Object>> podpoc = izTabeleSaKlucevima(dbtabela);
                Vector<String> columnNames = null;

                for (Vector<String> key : podpoc.keySet()) {
                    columnNames = key;

                }
                Vector<Object> podaci = podpoc.get(columnNames);
                JComboBox<Object> combox = new JComboBox<>(podaci);

                if (podaci.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Skroz prazna tabela..." + dbtabela,
                            "NEOPHODNI PODACI-Kljuc", JOptionPane.CLOSED_OPTION);

                    createAndShowGUI(dbtabela);
                    return null;

                }

                combox.setName(ff.getName());
                labelStrings[fieldNum] = ff.getName();
                JTextField TekstPolje = new JTextField();
                TekstPolje.setColumns(20);
                TekstPolje.setName(ff.getName());
                nizTekstpolja.add(TekstPolje);
                combox.setSelectedIndex(0);
                Vector pocst = (Vector) combox.getSelectedItem();
                TekstPolje.setText((String) pocst.firstElement());

                /*                               */
                ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Vector s = (Vector) combox.getSelectedItem(); //get the selected item
                        nizTekstpolja.forEach(pp -> {
                            if (pp.getName() == combox.getName()) {
                                pp.setText((String) s.firstElement());

                            }

                        });

                    }

                };
                combox.addActionListener(cbActionListener);
                /**
                 * ***************************************************************
                 */

                // nizTekstpolja.add(TekstPolje);
                 Test  tt=new Test();
                 tt.label.setText(labelStrings[fieldNum]);
                  tt.label.setHorizontalAlignment(JLabel.TRAILING);
             //   labels[fieldNum] = new JLabel(labelStrings[fieldNum], JLabel.TRAILING);
             labels[fieldNum]=tt.label;
                labels[fieldNum].setLabelFor(fields[fieldNum]);
                System.out.println(fieldNum + "dodosak" + fieldNum);
                panel.add(labels[fieldNum]);
                panel.add(combox);
                fields[fieldNum++] = TekstPolje;
                if (pki.contains(ff.getName())) {
                    /*  primarni kljuc na ovom polju. onemoguci izbor. */
                    TekstPolje.setEditable(false);
                    System.out.println("imaga primarnikljuc napolju " + ff.getName());
                }
            } else {

                labelStrings[fieldNum] = "" + ff.getName();
                JTextField TekstPolje = new JTextField();
                //formatiranje  tekstpolja
                TekstPolje.setColumns(20);
                TekstPolje.setName(ff.getName());
                nizTekstpolja.add(TekstPolje);

                labels[fieldNum] = new JLabel(labelStrings[fieldNum],
                        JLabel.TRAILING);
                labels[fieldNum].setLabelFor(fields[fieldNum]);
                System.out.println(fieldNum + "dodobezzklu" + fieldNum);
                panel.add(labels[fieldNum]);
                panel.add(TekstPolje);
                fields[fieldNum++] = TekstPolje;
                if (pki.contains(ff.getName())) {
                    /*  primarni kljuc na ovom polju. onemoguci izbor. */
                    TekstPolje.setEditable(false);
                    System.out.println("imaga primarnikljuc napolju " + ff.getName());
                }
            }

        }

        //       TekstPolje.setInputVerifier(inputVerifier);
        //Create the text field and set it up.
        /* FormatedFld = new JFormattedTextField(
                createFormatter("#####"));
        fields[fieldNum++] = FormatedFld;
         */
        //Associate label/field pairs, add everything,
        //and lay it out.
        SpringUtilities.makeCompactGrid(panel, labelStrings.length, 2, GAP, GAP, GAP, GAP / 2);

//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad//xpad, ypad
        return panel;
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    public static void createAndShowGUI(String tabela) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        //Create and set up the window.

        JFrame frame = new JFrame("Unos novog zapisa -" + tabela);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Add contents to the window.
        frame.add(new InputEkran(tabela));
        frame.setAlwaysOnTop(true);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public class Test extends JFrame {
  JPopupMenu menu = new JPopupMenu("Popup");

  class MyLabel extends JLabel {
    public MyLabel(String text) {
      super(text);
      addMouseListener(new PopupTriggerListener());
    }

    class PopupTriggerListener extends MouseAdapter {
      public void mousePressed(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
          menu.show(ev.getComponent(), ev.getX(), ev.getY());
        }
      }

      public void mouseReleased(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
          menu.show(ev.getComponent(), ev.getX(), ev.getY());
        }
      }

      public void mouseClicked(MouseEvent ev) {
      }
    }
  }

  JLabel label = new MyLabel("Right-clicK");

  public Test() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    JMenuItem item = new JMenuItem("Test1");
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Menu item Test1");
          
          
      }
    });
    menu.add(item);

    item = new JMenuItem("Test2");
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          
       System.out.println("Menu item Test2");
         
      }
    });
    menu.add(item);

    
    pack();
    setSize(300, 100);
  }

  
}
}
