/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.chess;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TableroController implements Initializable {

    @FXML
    private GridPane gridPane;
    private Scene tableroScene;

    public void setScene(Scene scene) {
        tableroScene = scene;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Aquí puedes agregar la lógica para inicializar las piezas en sus respectivas casillas
    }
}
