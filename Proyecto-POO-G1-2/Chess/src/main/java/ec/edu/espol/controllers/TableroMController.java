/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private ImageView selectedPiece; // Almacena la pieza seleccionada
    private int currentRow, currentCol; // Almacena la ubicación actual de la pieza
    private GridPane gridpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int numRows = 8;
        int numCols = 8;

        Button[][] buttons = new Button[numRows][numCols]; // Inicializa la matriz de botones

        // Configurar botones con imágenes para representar el tablero
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if(i==1 || i ==0 || i==6 || i==7){
                Button button = new Button();
                button.setMinSize(50, 50); // Tamaño de los botones
                button.setMaxSize(50, 50);

                // Crear un ImageView y configurar una imagen para el botón
                ImageView imageView = new ImageView(new Image("fichas/"+i+j+".png")); // Reemplaza con la ruta correcta

                // Configurar clic para seleccionar pieza
                button.setOnMouseClicked(e -> handlePieceClick(button.getOnMouseClicked()));

                // Limpiar el contenido anterior del botón y agregar el ImageView
                button.setGraphic(imageView);

                // Agregar el botón a la cuadrícula en la posición (i, j)
                gridPane.add(button, j, i);
                buttons[i][j] = button; // Agregar el botón a la matriz
            }
            }
        }
    } 
    @FXML
    private void handlePieceClick(MouseEvent event) {
        ImageView piece = (ImageView) event.getSource();
        int row = GridPane.getRowIndex(piece);
        int col = GridPane.getColumnIndex(piece);

        // Implementa la lógica para mover las piezas según sea necesario
        if (selectedPiece == null) {
            selectedPiece = piece;
            currentRow = row;
            currentCol = col;
        } else {
            // Mueve la pieza seleccionada a la nueva ubicación
            GridPane.setColumnIndex(selectedPiece, col);
            GridPane.setRowIndex(selectedPiece, row);

            // Limpia la selección
            selectedPiece = null;
        }
    }

        }
}
