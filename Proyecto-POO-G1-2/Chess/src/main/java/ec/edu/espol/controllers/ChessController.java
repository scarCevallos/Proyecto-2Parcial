/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.chess.App;
import ec.edu.espol.chess.Chess;
import ec.edu.espol.chess.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ChessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void exitApp(MouseEvent event) {
        Platform.exit();
    }
    @FXML
    private void jugar(MouseEvent event) throws IOException {
        ArrayList<String> pieceTypes = Jugador.randomizarFichas();
        ArrayList<Jugador> players= Jugador.crearJugadores(pieceTypes);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/chess/TipoFichas.fxml"));
        Parent choosePieceTypeParent = loader.load();
        Scene sc = new Scene(choosePieceTypeParent);
        ChoosePieceTypeController choosePieceTypeController = loader.getController();
        choosePieceTypeController.setJugadores(players);
        App.setScene(sc);
        }
}
