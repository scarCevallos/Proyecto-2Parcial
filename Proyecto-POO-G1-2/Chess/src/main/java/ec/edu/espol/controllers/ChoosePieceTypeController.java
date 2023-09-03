/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.chess.Jugador;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class ChoosePieceTypeController implements Initializable {
    private ArrayList<Jugador> jugadores;
    @FXML
    private BorderPane bp;
    @FXML
    private VBox vb;

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        definirJugadores(jugadores);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bp.setCenter(vb);
    }
    @FXML
    private void continuar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/chess/TableroM.fxml"));
        Parent tableroParent = loader.load();
        Scene tableroScene = new Scene(tableroParent,680,480);
        TableroMController tableroController = loader.getController();
        tableroController.setScene(tableroScene);
        tableroController.setPlayers(jugadores);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableroScene);
        window.show();
    }
    public void definirJugadores(ArrayList<Jugador> players){
        vb.getChildren().clear();
        for(Jugador j: players){
            Label lb = new Label();
            lb.setText("Jugador "+j.getId()+": "+j.getTipoFicha());
            lb.setAlignment(Pos.CENTER);
            vb.getChildren().add(lb);
        }
    }

    // ...
}