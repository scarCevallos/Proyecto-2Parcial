/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
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
    private GridPane gridpane;
    private Button[][] buttons = new Button[8][8];
    private ImageView[][] imageViews = new ImageView[8][8];
    private int i_1;
    private int i_2;
    private int estado = 0;
    private Image backgroundImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablero();
        agregarFichas();
        
    }

    private void inicializarTablero() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button button = new Button();
                button.setMinSize(50, 50);  // Establece el tamaño del botón

                // Agregar ImageView al botón
                ImageView imageView = new ImageView();
                imageViews[row][col] = imageView;
                button.setGraphic(imageView);

                // Alternar colores directamente en el código
                if ((row + col) % 2 == 0) {
                    button.setStyle("-fx-base: white;");
                } else {
                    button.setStyle("-fx-base: black;");
                }

                buttons[row][col] = button;
                gridpane.add(button, col, row); // Agrega el botón al GridPane
            }
        }
    }

    private void agregarFichas() {
        // Ejemplo: Cargar una imagen en la posición (1, 2)
        for(int i = 0; i<8;i++ ){
        Image peonImage = new Image(getClass().getResourceAsStream("/fichas/PeonB.png"));
        imageViews[1][i].setImage(peonImage);}
        for(int i = 0; i<8;i++ ){
        Image peonImage = new Image(getClass().getResourceAsStream("/fichas/PeonW.png"));
        imageViews[6][i].setImage(peonImage);}
 
        // Ejemplo: Cargar otra imagen en la posición (6, 5)
        Image torreImage = new Image(getClass().getResourceAsStream("/fichas/torre.png"));
        imageViews[7][5].setImage(torreImage);
        

        // Continúa cargando imágenes en las posiciones que necesites
    }

    private void moverFicha(){
 
        EventHandler<MouseEvent> clickGetImage = (MouseEvent t)-> {
            //tiene que tener dos estados
            if(estado==0){
                //obtener la posición del boton, y el tipo de ficha
                estado++;
            }
            else{
                //si no es movimiento valido le mandas un alert al usuario
                //limpiar la imagen donde estaba el boton anterior
                //poner la imagen, osea la ficha en la nueva posicion en la matriz 
                Button b = buttons[1][1];
                
            }
        Button clickedButton = (Button) t.getSource();
        Image backgroundImage = clickedButton.getBackground().getImages().get(0).getImage();
        };
        
                
    }
    

 }

