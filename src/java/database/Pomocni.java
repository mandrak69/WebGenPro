/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author D
 */
public class Pomocni {

    public static int AskYesNo() {

        JOptionPane pane = new JOptionPane(
                "Da liste sigurni ?\nPotvrdite Vas izbor.");
        Object[] options = new String[]{"Neka bude", "Cekaj da razmislim.."};
        pane.setOptions(options);
        JDialog dialog = pane.createDialog(new JFrame(), "Pitanje za Vas");
        dialog.show();
        Object obj = pane.getValue();
        int result = -1;
        for (int k = 0; k < options.length; k++) {
            if (options[k].equals(obj)) {
                result = k;
            }
        }
        System.out.println("User's choice: " + result);
        return result;
    }
}
