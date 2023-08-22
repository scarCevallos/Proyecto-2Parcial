/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class ChoosePieceTypeController implements Initializable {
    @FXML
    private Label player1Label;
    
    @FXML
    private Label player2Label;
    
    private final StringProperty pieceType1 = new SimpleStringProperty();
    private final StringProperty pieceType2 = new SimpleStringProperty();

    public StringProperty pieceType1Property() {
        return pieceType1;
    }

    public StringProperty pieceType2Property() {
        return pieceType2;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1Label.textProperty().bind(Bindings.concat("Jugador 1: Tus fichas son ", pieceType1));
        player2Label.textProperty().bind(Bindings.concat("Jugador 2: Tus fichas son ", pieceType2));
    }
    @FXML
    private void continuar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/chess/TableroM.fxml"));
        Parent tableroParent = loader.load();
        Scene tableroScene = new Scene(tableroParent,680,480);
        TableroMController tableroController = loader.getController();
        tableroController.setScene(tableroScene);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableroScene);
        window.show();
    }

    // ...
}