/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.chess.Ficha;
import ec.edu.espol.chess.GamePhase;
import ec.edu.espol.chess.Jugador;
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
    private ImageView fichaSeleccionada= null;
    private GamePhase currentPhase= GamePhase.STANDBY;
    private boolean[][] casillasValidas= new boolean[8][8];
    private int turno=1;
    private String tipoFicha="Blancas";
    private ArrayList<Jugador> players;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    public void setScene(Scene scene) {
        tableroScene = scene;
    }
    public void setPlayers(ArrayList<Jugador> players) {
        this.players = players;
        inicializarTablero();
        mensajeTurno();
    }
    
    private void inicializarTablero(){
        for(int row=0; row<8;row++){
            for(int col=0; col<8; col++){
                Button b= new Button();
                b.setPrefSize(60, 60);
                ImageView im= new ImageView();
                imageViews[row][col]= im;
                b.setGraphic(im);
                b.setStyle("-fx-border-color: black; -fx-border-width: 20px; -fx-padding: 5px;");
                
                if((row+col)%2==0){
                    b.setStyle("-fx-base: white;-fx-border-color: black; -fx-border-width: 1.5px; ");
                }
                else{
                    b.setStyle("-fx-base: lightgoldenrodyellow;-fx-border-color: black; -fx-border-width: 1.5px; -fx-padding: 0p; -fx-margin: 0px");
                }
                buttons[row][col]=b;
                b.setMinSize(Button.USE_COMPUTED_SIZE, Button.USE_COMPUTED_SIZE);
                b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                gridPane.add(b,col,row);
                if (row == 0 && (col == 0 || col==7)) {
                setImage(im, "/fichas/torrenegro.png",40,50);
                im.setUserData(new Ficha("torre","negro",row,col));
                } 
                else if (row == 7 && (col == 0 || col==7)) {
                setImage(im, "/fichas/torreblanco.png",40,50);
                im.setUserData("blanco");
                im.setUserData(new Ficha("torre","blanco",row,col));
                } 
                else if (row == 0 && (col == 1 || col == 6)) {
                setImage(im, "/fichas/Caballonegro.png",40,50);
                im.setUserData(new Ficha("caballo","negro",row,col));
                } 
                else if (row == 7 && (col == 1 || col == 6)) {
                setImage(im, "/fichas/Caballoblanco.png",40,50);
                im.setUserData(new Ficha("caballo","blanco",row,col));
                } 
                else if (row == 0 && (col == 2 || col == 5)) {
                setImage(im, "/fichas/Alfilnegro.png",40,50);
                im.setUserData(new Ficha("alfil","negro",row,col));
                } 
                else if (row == 7 && (col == 2 || col == 5)) {
                setImage(im, "/fichas/Alfilblanco.png",40,50);
                im.setUserData(new Ficha("alfil","blanco",row,col));
                } 
                else if (row == 0 && col == 3) {
                setImage(im, "/fichas/reinanegro.png",40,50);
                im.setUserData(new Ficha("reina","negro",row,col));
                } 
                else if(row==7 && col==3){
                setImage(im, "/fichas/reinablanco.png",40,50);
                im.setUserData(new Ficha("reina","blanco",row,col));
                }
                else if (row == 0 && col == 4) {
                setImage(im, "/fichas/reynegro.png",40,50);
                im.setUserData(new Ficha("rey","negro",row,col));
                } 
                else if (row == 7 && col == 4) {
                setImage(im, "/fichas/reyblanco.png",40,50);
                im.setUserData(new Ficha("rey","blanco",row,col));
                } 
                else if (row == 1) {
                setImage(im, "/fichas/peonnegro.png",40,50);
                im.setUserData(new Ficha("peon","negro",row,col));
                } 
                else if (row == 6) {
                setImage(im, "/fichas/Peonblanco.png",40,50);
                im.setUserData(new Ficha("peon","blanco",row,col));
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
        Jugador jugadoractual=null;
        for(Jugador j: players){
            if(turno%2==0 && (j.getTipoFicha().equalsIgnoreCase("Negras"))){
                jugadoractual=j;
                break;
            }
            else if(turno%2!=0 && (!j.getTipoFicha().equalsIgnoreCase("Negras"))){
               jugadoractual=j;
               break;
            }
        }
        String mturno= "Jugador "+ jugadoractual.getId()+ " "+"Turno N° "+turno;
        turnoLabel.setText(mturno);
    }
    private void iniciarNuevoTurno(){
        turno++;
        if(turno%2==0){
            tipoFicha="Negras";
        }
        else{
            tipoFicha="Blancas";
        }
        currentPhase=GamePhase.STANDBY;
        mensajeTurno();
    }
    private boolean turnoJugador(ImageView imv){
        Jugador jugadoractual=null;
        Jugador jblanco= null;
        Jugador jnegro= null;
        for(Jugador j: players){
            if(turno%2==0 && (j.getTipoFicha().equalsIgnoreCase("Negras"))){
                jugadoractual=j;
                jnegro=j;
                break;
            }
            else if(turno%2!=0 && (!j.getTipoFicha().equalsIgnoreCase("Negras"))){
               jugadoractual=j;
               jblanco=j;
               break;
            }
        }
        return (jugadoractual==jblanco && fichaBlanca(imv))||(jugadoractual==jnegro && fichaNegra(imv));
    }
    private boolean fichaBlanca(ImageView imv){
        Ficha userData= (Ficha) imv.getUserData();
        return userData!=null && (userData.getColor()).equals("blanco");
    }
    private boolean fichaNegra(ImageView imv){
        Ficha userData= (Ficha) imv.getUserData();
        return userData!=null && (userData.getColor()).equals("negro");
    }
    private void handleButtonClick(MouseEvent event) {
    Button boton = (Button) event.getSource();
    ImageView imageView = (ImageView) boton.getGraphic();
        switch(currentPhase){
            case STANDBY:
                if(turnoJugador(imageView)){
                    if(esImagenFichaValida(imageView)){
                        fichaSeleccionada=imageView;
                        currentPhase= GamePhase.MAIN;
                        resetearColorCasillas();
                        casillasValidas= calcularCasillasValidas(fichaSeleccionada);
                        resaltarCasillasValidas(casillasValidas);
                    }
                }
                break;
            case MAIN:
                System.out.println("Hola");
                if(imageView!=fichaSeleccionada && !esImagenFichaValida(imageView)){
                    moverFicha(imageView);
                    resetearColorCasillas();
                    iniciarNuevoTurno();
                }
                else if(esImagenFichaValida(imageView)){
                    fichaSeleccionada= imageView;
                    resetearColorCasillas();
                    casillasValidas= calcularCasillasValidas(fichaSeleccionada);
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
    private void moverFicha(ImageView destino){
        if (fichaSeleccionada!=null && destino != null){
            Ficha f=(Ficha) fichaSeleccionada.getUserData();
            Image image= fichaSeleccionada.getImage();
            destino.setImage(image);
            destino.setFitWidth(40);
            destino.setFitHeight(50);
            destino.setUserData(f);
            fichaSeleccionada.setImage(null);
        }
        fichaSeleccionada=null;
    }
    private void mostrarMensaje(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Información");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
    }
    private void resaltarCasillasValidas(boolean[][] casillas){
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                if(casillas[row][col]){
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

    private boolean[][] calcularCasillasValidas(ImageView im){
        boolean[][] casillasValidas= new boolean[8][8];
        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++){
                Button casilla= buttons[row][col];
                ImageView ic= imageViews[row][col];
                Image img= ic.getImage();
                casillasValidas[row][col]=(img==null);
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
    
