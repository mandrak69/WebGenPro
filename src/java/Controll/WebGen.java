package Controll;



import database.Constants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import static database.Constants.ImezaTabelu;
import static database.Constants.SCRH;
import static database.Constants.SCRW;
import static database.Constants.TabelazaWebIme;
import static database.Constants.WebImeZaIme;
import static database.Constants.svetabele;


/* MenuSelectionManagerDemo.java requires images/middle.gif. */

 /*
 * This class is just like MenuDemo, except every second (thanks
 * to a Timer) the selected path of the menu is printed in the text area.
 */
public class WebGen implements ActionListener, ItemListener {

    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
public final static int ONE_SECOND = 1000;

    public JMenuBar createMenuBar() {

        Vector<String> a = Constants.svetabele;
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        menuBar.setFont(new Font("sans-serif", Font.PLAIN, 20));
        //Build the first menu.
        int glmen = 0;

        for (String gl : Constants.MENU_HEADER) {
            menu = new JMenu(gl);

            menu.setPreferredSize(new Dimension(150, menu.getPreferredSize().height));

            menu.setFont(new Font("sans-serif", Font.PLAIN, 20));
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription(
                    "opisi");
            menuBar.add(menu);
            
            if(glmen==0){
            /*   dodaj   tabele u glani meni 1 i listener za azuriraj tabelu}*/
            for(String tt:svetabele){
                String imezaMeni = WebImeZaIme.get(TabelazaWebIme.get(tt));
                 menuItem = pravimenuitem(imezaMeni, "", 22);
                    //   menuItem.addActionListener(this);
                    menu.add(menuItem);
            }
            
            
            
            }
            if (Constants.SUBMENU[glmen].length > 0) {
                
                for (String su : Constants.SUBMENU[glmen]) {

                    menuItem = pravimenuitem(su, "", 22);
                    //   menuItem.addActionListener(this);
                    menu.add(menuItem);
                }
            }
            glmen++;
        };
        menu = new JMenu("Dodatak");

        menu.setFont(new Font("sans-serif", Font.PLAIN, 20));
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "opis2");
        menuBar.add(menu);
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");

        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi 13 menuitem");
                //    RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //    dialog.setVisible(true);;
            }

        });
        String basePath = new File("").getAbsolutePath();
        ImageIcon icon = createImageIcon(basePath + "\\build\\web\\images\\ggg.gif");
        menuItem = new JMenuItem("SaIkonom", icon);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi 222  menuitem");
                
            }

        });
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(icon);
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi 333  menuitem");
                //    RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //    dialog.setVisible(true);
            }

        });
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");

        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi 15menuitem");
                //    RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //    dialog.setVisible(true);;
            }

        });

        menuItem.addActionListener(this);
        menu.add(menuItem);
        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi 444  menuitem");
                //   RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //   dialog.setVisible(true);
            }

        });
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        menuItem.addActionListener(this);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi Another item  menuitem");
                //    RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //    dialog.setVisible(true);
            }

        });
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");

        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("pritsiosi 21 menuitem");
                //    RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //    dialog.setVisible(true);;
            }

        });

        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Nicemu za sada ne sluyi");

        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //    System.out.println("pritsiosi 22 menuitem");
                //    RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                //    dialog.setVisible(true);;
            }

        });

        menuItem.addActionListener(this);
        menu.add(menuItem);
        Timer timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MenuElement[] path = MenuSelectionManager.defaultManager().
                        getSelectedPath();
                for (int i = 0; i < path.length; i++) {
                    if (path[i].getComponent() instanceof javax.swing.JMenuItem) {
                        JMenuItem mi = (JMenuItem) path[i].getComponent();
                        if ("".equals(mi.getText())) {
                            output.append("ICON-ONLY MENU ITEM > ");
                        } else {
                            output.append(mi.getText() + " > ");
                        }
                    }
                }
                if (path.length > 0) {
                    output.append(newline);
                }
            }
        });
        timer.start();
        return menuBar;
    }

    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        String s = "Action event detected."
                + newline
                + "    Event source: " + source.getText()
                + " (an instance of " + getClassName(source) + ")";
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        String s = "Item event detected."
                + newline
                + "    Event source: " + source.getText()
                + " (an instance of " + getClassName(source) + ")"
                + newline
                + "    New state: "
                + ((e.getStateChange() == ItemEvent.SELECTED)
                ? "selected" : "unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    // Vraca ime klase bez  package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex + 1);
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = WebGen.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("GlavniEkran WebEx");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        frame.setPreferredSize(new Dimension(SCRW,SCRH));
        WebGen demo = new WebGen();
        
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(SCRW,SCRH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
         Constants probni = database.Constants.getInstance();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
             //   Constants probni = model.Constants.getInstance();
                createAndShowGUI();
            }
        });
    }

    private JMenuItem pravimenuitem(String naziv, String proc, int sizefont) {
        JMenuItem menuItem = new JMenuItem(naziv,
                KeyEvent.VK_T);
        menuItem.setFont(new Font("sans-serif", Font.PLAIN, sizefont));
        menuItem.setPreferredSize(new Dimension(170, menuItem.getPreferredSize().height));
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "propratnaporukaod " + naziv);

        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    System.out.println("startaj azurir " + ImezaTabelu.get(naziv)) ;
                   
                    Controll.Azuriranje.createAndShowGUI(ImezaTabelu.get(naziv));
                    //     RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
                    //   Rpp dialog = new Rpp(new JFrame(), true, naziv);
                    //  dialog.setVisible(true);;
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                    Logger.getLogger(WebGen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        return menuItem;

    }

}
