/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.chess;

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
        List<String> pieceTypes = new ArrayList<>();
        pieceTypes.add("Blancas");
        pieceTypes.add("Negras");
        Collections.shuffle(pieceTypes);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TipoFichas.fxml"));
        Parent choosePieceTypeParent = loader.load();

        ChoosePieceTypeController choosePieceTypeController = loader.getController();
        choosePieceTypeController.pieceType1Property().set(pieceTypes.get(0));
        choosePieceTypeController.pieceType2Property().set(pieceTypes.get(1));

        Scene choosePieceTypeScene = new Scene(choosePieceTypeParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(choosePieceTypeScene);
        window.show();
        }
}
