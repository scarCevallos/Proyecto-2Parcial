/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.chess.GamePhase;
import static ec.edu.espol.chess.GamePhase.BATTLE;
import static ec.edu.espol.chess.GamePhase.MAIN;
import static ec.edu.espol.chess.GamePhase.STANDBY;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

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

    private BorderPane borderPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label turnoLabel;

    private ImageView[][] imageViews = new ImageView[8][8];
    private Button[][] buttons = new Button[8][8];
    private int currentRow, currentCol; // Almacena la ubicación actual de la pieza
    private Scene tableroScene;
    private ImageView fichaSeleccionada = null;
    private GamePhase currentPhase = GamePhase.STANDBY;
    private boolean[][] casillasValidas = new boolean[8][8];
    private int turno = 1;
    private String tipoFicha = "Blancas";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablero();
        mensajeTurno();
    }

    public void setScene(Scene scene) {
        tableroScene = scene;
    }

    private void inicializarTablero() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button b = new Button();
                b.setPrefSize(60, 60);
                ImageView im = new ImageView();
                imageViews[row][col] = im;
                b.setGraphic(im);
                b.setStyle("-fx-border-color: black; -fx-border-width: 20px; -fx-padding: 5px;");

                if ((row + col) % 2 == 0) {
                    b.setStyle("-fx-base: white;-fx-border-color: black; -fx-border-width: 1.5px; ");
                } else {
                    b.setStyle("-fx-base: lightgoldenrodyellow;-fx-border-color: black; -fx-border-width: 1.5px; -fx-padding: 0px; -fx-margin: 0px");
                }
                buttons[row][col] = b;
                b.setMinSize(Button.USE_COMPUTED_SIZE, Button.USE_COMPUTED_SIZE);
                b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                gridPane.add(b, col, row);
                if (row == 0 && col == 0) {
                    setImage(im, "/fichas/torreB.png", 40, 50);
                } else if (row == 0 && col == 7) {
                    setImage(im, "/fichas/torreB.png", 40, 50);
                } else if (row == 7 && col == 0) {
                    setImage(im, "/fichas/torreW.png", 40, 50);
                } else if (row == 7 && col == 7) {
                    setImage(im, "/fichas/torreW.png", 40, 50);
                } else if (row == 0 && (col == 1 || col == 6)) {
                    setImage(im, "/fichas/CaballoB.png", 40, 50);
                } else if (row == 7 && (col == 1 || col == 6)) {
                    setImage(im, "/fichas/CaballoW.png", 40, 50);
                } else if (row == 0 && (col == 2 || col == 5)) {
                    setImage(im, "/fichas/AlfilB.png", 40, 50);
                } else if (row == 7 && (col == 2 || col == 5)) {
                    setImage(im, "/fichas/AlfilW.png", 40, 50);
                } else if (row == 0 && col == 3) {
                    setImage(im, "/fichas/reinaB.png", 40, 50);
                } else if (row == 7 && col == 3) {
                    setImage(im, "/fichas/reinaW.png", 40, 50);
                } else if (row == 0 && col == 4) {
                    setImage(im, "/fichas/reyN.png", 40, 50);
                } else if (row == 7 && col == 4) {
                    setImage(im, "/fichas/reyW.png", 40, 50);
                } else if (row == 1) {
                    setImage(im, "/fichas/peonB.png", 40, 50);
                } else if (row == 6) {
                    setImage(im, "/fichas/PeonW.png", 40, 50);
                }
                b.setOnMouseClicked(this::handleButtonClick);
            }
            borderPane.setCenter(gridPane);

        }
    }

    private void setImage(ImageView imageView, String imagePath, double width, double height) {
        InputStream imageStream = getClass().getResourceAsStream(imagePath);
        if (imageStream != null) {
            Image image = new Image(imageStream);
            imageView.setImage(image);
            imageView.setFitWidth(width); // Ajusta el ancho de la imagen
            imageView.setFitHeight(height); // Ajusta la altura de la imagen
        }
    }

    private void mensajeTurno() {
        String mturno = tipoFicha + "Turno N°" + turno;
        turnoLabel.setText(mturno);
    }

    private void iniciarNuevoTurno() {
        turno++;
        if (turno % 2 == 0) {
            tipoFicha = "Negras";
        } else {
            tipoFicha = "Blancas";
        }
        currentPhase = GamePhase.STANDBY;
        mensajeTurno();
    }

    private void handleButtonClick(MouseEvent event) {
        Button boton = (Button) event.getSource();
        ImageView imageView = (ImageView) boton.getGraphic();
        /*if (turno % 2 == 0) {
            mostrarMensaje("Le toca a las negras");
        } else if (turno % 2 != 0) {
            mostrarMensaje("Le toca a las blancas");
        } */
            switch (currentPhase) {
                case STANDBY:

                    if (esImagenFichaValida(imageView)) {
                        fichaSeleccionada = imageView;
                        currentPhase = GamePhase.MAIN;
                        resetearColorCasillas();
                        casillasValidas = calcularCasillasValidas(fichaSeleccionada);
                        resaltarCasillasValidas(casillasValidas);
                    } else {
                        mostrarMensaje("Seleccion no valida");
                    }
                    break;
                case MAIN:
                    if (imageView != fichaSeleccionada && !esImagenFichaValida(imageView)) {
                        moverFicha(imageView);
                        resetearColorCasillas();
                        currentPhase = GamePhase.BATTLE;
                    } else if (esImagenFichaValida(imageView)) {
                        fichaSeleccionada = imageView;
                        resetearColorCasillas();
                        casillasValidas = calcularCasillasValidas(fichaSeleccionada);
                        resaltarCasillasValidas(casillasValidas);
                    }
                    break;
                case BATTLE:
                    iniciarNuevoTurno();
                    break;
            }
        

    }

// Método para verificar si la imagen es la imagen de una ficha válida
    private boolean esImagenFichaValida(ImageView imageView) {
        Image image = imageView.getImage();
        return image != null;
    }

    private void moverFicha(ImageView destino) {
        if (fichaSeleccionada != null && destino != null) {
            Image image = fichaSeleccionada.getImage();
            destino.setImage(image);
            destino.setFitWidth(40);
            destino.setFitHeight(50);
            fichaSeleccionada.setImage(null);
        }
        fichaSeleccionada = null;
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void resaltarCasillasValidas(boolean[][] casillas) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (casillas[row][col]) {
                    buttons[row][col].setStyle("-fx-base: lightblue;-fx-border-color: black; -fx-border-width: 1.5px;");
                }
            }
        }
    }

    private void resetearColorCasillas() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button casilla = buttons[row][col];
                if ((row + col) % 2 == 0) {
                    casilla.setStyle("-fx-base: white;-fx-border-color: black; -fx-border-width: 1.5px;");
                } else {
                    casilla.setStyle("-fx-base: lightgoldenrodyellow;-fx-border-color: black; -fx-border-width: 1.5px;");
                }
            }
        }
    }

    private boolean[][] calcularCasillasValidas(ImageView im) {
        boolean[][] casillasValidas = new boolean[8][8];
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button casilla = buttons[row][col];
                ImageView ic = imageViews[row][col];
                Image img = ic.getImage();
                casillasValidas[row][col] = (img == null);
            }
        }
        return casillasValidas;
    }

}
//    @FXML
//    private void handlePieceClick(MouseEvent event) {
//        ImageView piece = (ImageView) event.getSource();
//        int row = GridPane.getRowIndex(piece);
//        int col = GridPane.getColumnIndex(piece);
//
//        // Implementa la lógica para mover las piezas según sea necesario
//        if (selectedPiece == null) {
//            selectedPiece = piece;
//            currentRow = row;
//            currentCol = col;
//        } else {
//            // Mueve la pieza seleccionada a la nueva ubicación
//            GridPane.setColumnIndex(selectedPiece, col);
//            GridPane.setRowIndex(selectedPiece, row);
//
//            // Limpia la selección
//            selectedPiece = null;
//        }
//    }

