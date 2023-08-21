/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Jonathan
 */
public class TableroMController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        int numRows = 8; // Número de filas en la matriz
        int numCols = 8; // Número de columnas en la matriz

        // Crear una matriz de botones
        Button[][] buttons = new Button[numRows][numCols];
        
        
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numCols; j++) {
//                if(i==0 || i==1 || i==6 || i==7 ){
//                    Image img = new Image("fichas/logo.png");
//                    ImageView view = new ImageView(img);
//                    view.setFitHeight(80);
//                    view.setPreserveRatio(true);
//                    Button button = new Button();
//                buttons[i][j] = button;
//
//                // Agregar el botón a la cuadrícula en la posición (i, j)
//                gridPane.add(button, j, i);
//
//                }
                
                // Manejar eventos de clic en el botón
//                button.setOnAction(event -> {
//                    System.out.println("Botón clickeado: " + button.getText());
//                });
            }
        }
    }    
    
}
