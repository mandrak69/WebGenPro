package Controll;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author D
 */
import database.Biblos;
import static database.Biblos.GObrisi;
import static database.Biblos.GSnimaj;
import static database.Biblos.GUcitaj;

import database.Constants;
import database.PasswordCheck;
import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BazniModel;
import model.C1;
import model.C11;

public class Main extends Application {

    Stage window;
    Scene scene;
    Button button;

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SQLException, ClassNotFoundException, InstantiationException, InvocationTargetException {

        Set<String> too = Constants.setTabelaFKKljuceva.keySet();

        BazniModel dr = new C11("Austrija");
        BazniModel gr = new C1("Beograd");
        System.out.println("klasa"+gr.getClass().getSimpleName());
        C1 be = new C1("Nis");
        gr.setId(4);
        be.veliki();
        ((C1) gr).veliki();
        ((C1) dr).veliki();

       
        // String ss = DBQueries.getGlavniMeni(9);
        // System.out.println(""+ss);
        launch(args);
    }
 
        
    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("WebGen generator");

        Button button1 = new Button("Click me");
        Button button2 = new Button("Logovanje");
        Button button3 = new Button("Logovanje");
        Button button4 = new Button("Click me");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        //getItems returns the ObservableList object which you can add items to
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Bananas");
        choiceBox.getItems().addAll("Bacon", "Ham", "Meatballs");

        //Set a default value
        choiceBox.setValue("Apples");

        button1.setOnAction(e -> getChoice(choiceBox));
        button2.setOnAction(e -> logovanje());
        button3.setOnAction(e -> {
            try {
                getChoice3();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(40, 40, 20, 20));
        layout.getChildren().addAll(choiceBox, button1, button2, button3, button4);

        scene = new Scene(layout, 500, 550);
        window.setScene(scene);
        window.show();

    }

    //To get the value of the selected item
    private void getChoice(ChoiceBox<String> choiceBox) {
        String food = choiceBox.getValue();
        System.out.println(food);

        System.out.println("pritsiosi 11 menuitem");
        //  RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
        //  dialog.setVisible(true);;
    }

    private void logovanje() {
        //  String food = choiceBox.getValue();
        // System.out.println(food);
        // SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        //       //Turn off metal's use of bold fonts
        //       UIManager.put("swing.boldMetal", Boolean.FALSE);
        PasswordCheck.createAndShowGUI();
        //     }
        // });
        System.out.println("pritsiosi 11 logovanje");
        // RadniProzor dialog = new RadniProzor(new javax.swing.JFrame(), true);
        //  dialog.setVisible(true);;
    }

    private void getChoice3() throws SQLException {

        try {
            System.out.println("pritsiosi 3 menuitem");
            Azuriranje dialog = new Azuriranje("glavnimeni");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class ReversibleArrayList<T> extends ArrayList<T> {

        public ReversibleArrayList(Collection<T> c) {
            super(c);
        }

        public Iterable<T> obrnuto() {
            return new Iterable<T>() {
                @Override
                public Iterator<T> iterator() 
                {
                    return new Iterator<T>() {
                        int tekuci = size() - 1;

                        @Override
                        public boolean hasNext() {
                            return tekuci > -1;
                        }

                        @Override
                        public T next() {
                           
                            return get(tekuci); 
                        }
                        @Override
                        public void remove() { // Nije realizovano throw new UnsupportedOperationException();
                        }
                    ;
                }
            ;
        }
    };
};
}
    
    
    
    
}
