/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

/*
 * TableListSelectionDemo.java requires no other files.
 */

import database.DbUtil;
import database.Pomocni;
import static database.DBQueries.izTabeleSaKlucevima;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static database.Constants.FieldsForTable;
import static database.Constants.ImezaTabelu;
import static database.Constants.SCRH;
import static database.Constants.SCRW;
import static database.Constants.setTabelaFKKljuceva;
import static database.Constants.setTabelaKljuceva;
import static database.Constants.svetabele;
import static java.awt.SystemColor.menu;

public class Azuriranje extends JPanel {
 JPopupMenu menu = new JPopupMenu("Pop---Up");
    JTextArea output;
    JList list;
    JTable table;
    String newline = "\n";
    ListSelectionModel listSelectionModel;
    String dbtabela;
    SharedListSelectionHandler cikica;

    Vector<String> columnNames;
    HashMap<String, String> kljucevi;
    JScrollPane StavkePane;
    JPanel btnPanel;
    Vector<String> primki;
    Vector<String> spisaktabela;
    Vector<String> bb;

    public <T> Azuriranje(String dbtabela) throws InvocationTargetException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException {
        super(new BorderLayout());
       
        HashMap<Vector<String>, Vector<T>> podpoc = izTabeleSaKlucevima(dbtabela);
        Vector<T> podaci;
        Vector<T> tableData;
        for (Vector<String> key : podpoc.keySet()) {
            columnNames = key;
        }
        podaci = podpoc.get(columnNames);

        kljucevi = setTabelaFKKljuceva.get(dbtabela);

        primki = setTabelaKljuceva.get(dbtabela);

        tableData = podaci;
        table = new JTable(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                /*   pogledati da li je kolona kljuc-ako jeste nije editabilna - onemogucijeees*/
                
                return column > 1 ? true : false;
            }
        };;

        spisaktabela = svetabele;

        //  JList lista = new JList(spisaktabela.toArray());
        JList lista = new JList(ImezaTabelu.keySet().toArray());
        lista.setSelectedIndex(1);
        JButton uzadButton = new JButton("Izaberite tabelu za obradu ");
        lista.addListSelectionListener((ListSelectionListener) -> {
            int newMode = lista.getSelectedIndex();

            output.append("-"
                    + "Mode--: " + lista.getSelectedValue() + newMode
                    + "-" + newline);
            uzadButton.setText("Unesi nov:" + (String) lista.getSelectedValue());
        });
        DefaultListModel<String> model = new DefaultListModel<>();

        listSelectionModel = table.getSelectionModel();
        ListSelectionModel listSelectionModel1 = lista.getSelectionModel();
        cikica = new SharedListSelectionHandler();
        listSelectionModel.addListSelectionListener(cikica);
        table.setSelectionModel(listSelectionModel);
        lista.setSelectionModel(listSelectionModel1);

        StavkePane = new JScrollPane(table);

        JScrollPane TabelePane = new JScrollPane(lista);

        table.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent
            ) {
                JTable jtable = (JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = jtable.columnAtPoint(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = jtable.getColumnModel().getColumn(index);
                        System.out.println("Double-clicked on: " + o.toString());

                    }
                }
            }
        }
        );

        JPanel controlPane = new JPanel(new GridLayout(1, 15));

        //    btnPanel = new JPanel();
        JScrollPane scrollPane1;
        JButton addButton;
        addButton = new JButton("Une Noviii");
        JButton delButton;
        delButton = new JButton("DELETE");
        JButton updateButton;
        updateButton = new JButton("UPDATE");
        JButton saveButton;
        saveButton = new JButton("SAVE");

       

        addButton.addActionListener(
                (java.awt.event.ActionEvent evt) -> {

                    cikica.getIzbor().forEach(new Consumer() {
                        @Override
                        public void accept(Object _item) {
                            Object bb = tableData.get((int) _item);
                              System.out.println("" + tableData.get((int) _item).getClass().getSimpleName());
                            
                           
                            System.out.println(evt.getActionCommand() + "-----" + lista.getSelectedValue());
                           

                             //   Izvestaji.createAndShowGUI(dbtabela);


                        }
                    });

                }
        );
      

        updateButton.addActionListener((java.awt.event.ActionEvent evt) -> {

                    cikica.getIzbor().forEach(new Consumer() {
                        @Override
                        public void accept(Object _item) {
                            try {
                                /* spario imena polja i vrednostizapisakoji semenja  
                                uz tabelu pozvanimetodzana koji zapisdaucita id a ga UPDATE*/

                                HashMap<String, String> mapaZaIzmenu = new HashMap<>();

                                bb = (Vector<String>) tableData.get((int) _item);
                                for (String pkluc : columnNames) {
                                    System.out.println(pkluc + "----" + bb.get(columnNames.indexOf(pkluc)));
                                    mapaZaIzmenu.put(pkluc, bb.get(columnNames.indexOf(pkluc)));
                                }
                                
                                Controll.IzmenaZapisa.createAndShowGUI(dbtabela, mapaZaIzmenu);
                               
                            } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                                Logger.getLogger(Azuriranje.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Azuriranje.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(Azuriranje.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });

                }
        );
      

        delButton.addActionListener(
                new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt
            ) {
                int ii = Pomocni.AskYesNo();
                if (ii == 0) {
                    Vector<String> sqlniz = new Vector<>();
                    /*izabrana prvaopcija potvrdjen zahtev*/

                    cikica.getIzbor().forEach((Object _item) -> {
                        for (String vv : primki) {
                            Vector kk = (Vector) tableData.get((int) _item);
                            for (String ooo : columnNames) {
                                if (ooo.equals(vv)) {

                                    System.out.println("DELETE FROM " + dbtabela + " WHERE " + ooo + "=" + kk.get(columnNames.indexOf(ooo)) + " ;");
                                    sqlniz.add("DELETE FROM " + dbtabela + " WHERE " + ooo + "=" + kk.get(columnNames.indexOf(ooo)) + " ;");

                                    System.out.println("sqlniz" + sqlniz.size());
                                    int[] pp = DbUtil.viseSQLodjednom(sqlniz);
                                    for (int oop : pp) {
                                        System.out.println("uspelo za " + oop);
                                    }
                                }
                            }
                        }

                    });
                }

            }

        });

     

        saveButton.addActionListener(
                new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt
            ) {
                System.out.println(cikica.getIzbor().toString());

                cikica.getIzbor().forEach((_item) -> {
                    System.out.println(evt.getActionCommand() + tableData.get((int) _item) + primki.toString());
                });
            }
        }
        );

        JButton PaziButton = new JButton("Unesi nov "+dbtabela);

        PaziButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                javax.swing.SwingUtilities.invokeLater(() -> {
                    try {
                        //Turn off metal's use of bold fonts
                        UIManager.put("swing.boldMetal", Boolean.FALSE);
                        
                        Controll.InputEkran.createAndShowGUI(dbtabela);
                        
                    } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                        Logger.getLogger(Azuriranje.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }
        ;

        }
        );
        
        String[] modes = {"SINGLE_SELECTION",
            "SINGLE_INTERVAL_SELECTION",
            "MULTIPLE_INTERVAL_SELECTION"};

        final JComboBox comboBox = new JComboBox(modes);

        comboBox.setSelectedIndex(2);
        comboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String newMode = (String) comboBox.getSelectedItem();
                if (newMode.equals("SINGLE_SELECTION")) {
                    listSelectionModel.setSelectionMode(
                            ListSelectionModel.SINGLE_SELECTION);
                } else if (newMode.equals("SINGLE_INTERVAL_SELECTION")) {
                    listSelectionModel.setSelectionMode(
                            ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                } else {
                    listSelectionModel.setSelectionMode(
                            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                }
                output.append("----------"
                        + "Mode: " + newMode
                        + "----------" + newline);
            }
        });
      
        JLabel jlabel=new JLabel("Selection mode:");
        
        /*  stavljam  popup meni na labelu prazna*/
        
          Test pra=new Test();
          JLabel prazna=pra.label;
  //controlPane.setBackground(Color.blue);
    JComponent[] buttons = {uzadButton,addButton,prazna, saveButton,prazna,jlabel, comboBox, delButton,updateButton , PaziButton};
    for(JComponent butt:buttons){
    controlPane.add(butt);

    }
//Pravi beli deoekrana za Output area.
    output  = new JTextArea(11, 20);

    output.setEditable (
    false);
        JScrollPane outputPane = new JScrollPane(output,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    //Do the layout.
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    add(splitPane, BorderLayout.CENTER);

    JPanel topHalf = new JPanel();

    topHalf.setLayout (

    new BoxLayout(topHalf, BoxLayout.LINE_AXIS));

        // JButton ucitaj = new JButton("Ucitaj");
    uzadButton.addActionListener ( 
        new java.awt.event.ActionListener() {
            @Override
        public void actionPerformed
        (java.awt.event.ActionEvent evt
        
            ) {
                try {
                String izbor = null;


                /* iz izabranoh  name ttreba povratiti key tabela*/
                izbor = ImezaTabelu.get(lista.getSelectedValue());

                InputEkran.createAndShowGUI(izbor);
                // cikica.getIzbor().forEach((_item) -> {
                //      System.out.println("2"+evt.getActionCommand() + tableData.get((int) _item));
                //    });
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                Logger.getLogger(Azuriranje.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Azuriranje.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    );

        JPanel tableContainer = new JPanel(new GridLayout(1, 3));

    tableContainer.setBorder (BorderFactory.createTitledBorder

    ("Tabele - izbor"));
    TabelePane.setSize (

    new Dimension(120, 130));
    tableContainer.add (TabelePane);

    StavkePane.setPreferredSize (new Dimension(420, 130));

    topHalf.setBorder (BorderFactory.createEmptyBorder(5, 5, 0, 5));
    
    tableContainer.add (StavkePane);

    topHalf.add (tableContainer);

    topHalf.setMinimumSize (

    new Dimension(SCRW - 300, SCRH - 300));
        //  topHalf.setPreferredSize(new Dimension(SCRW-300,SCRH-300));
    splitPane.add (topHalf);

    JPanel bottomHalf = new JPanel(new BorderLayout());

    bottomHalf.add (controlPane, BorderLayout.PAGE_START);

    bottomHalf.add (outputPane, BorderLayout.CENTER);
    //XXX: next line needed if bottomHalf is a scroll pane:
    //bottomHalf.setMinimumSize(new Dimension(400, 50));

    bottomHalf.setPreferredSize (

    new Dimension(250, 110));
    splitPane.add (bottomHalf);
}

/**
 * Create the GUI and show it. For thread safety, this method should be invoked
 * from the event-dispatching thread.
 *
 * @param <error>
 */
public static void createAndShowGUI(String tab) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        //Create and set up the window.
        JFrame frame = new JFrame("Azuriranje - Glavnimenu-" + tab);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setPreferredSize(new Dimension(SCRW, SCRH));
        frame.setSize(SCRW, SCRH);
        //    Constants probni = model.Constants.getInstance();
        //Create and set up the content pane.
        Azuriranje demon = new Azuriranje(tab);
        demon.setOpaque(true);
        frame.setContentPane(demon);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
        public void run() {

                //  Probni   probni= model.Probni.getInstance();
                String tab = "c1";
                try {
                    createAndShowGUI(tab);
                

} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                    Logger.getLogger(Azuriranje.class
.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    

}

    class SharedListSelectionHandler implements ListSelectionListener {

    private int firstIndex;
    private int lastIndex;
    private final ArrayList<Integer> izbor = new ArrayList<>();

    public ArrayList getIzbor() {
        return izbor;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public boolean isIsAdjusting() {
        return isAdjusting;
    }
    private boolean isAdjusting;

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        firstIndex = e.getFirstIndex();
        lastIndex = e.getLastIndex();
        isAdjusting = e.getValueIsAdjusting();
        output.append("Event for indexes "
                + firstIndex + " - " + lastIndex
                + "; isAdjusting is " + isAdjusting
                + "; selected indexes:");

        izbor.clear();
        if (lsm.isSelectionEmpty()) {
            output.append(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    izbor.add(i);
                    output.append("--- " + i);
                }
            }
        }
        output.append(newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

}
    /*   za desniklik meni  */
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
        
      //  System.out.println("Menu item Test2");
      }
    });
    menu.add(item);

    getContentPane().add(label);
    pack();
    setSize(300, 100);
  }

  
}
}
