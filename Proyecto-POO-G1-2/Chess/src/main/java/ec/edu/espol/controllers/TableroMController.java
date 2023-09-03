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
                else{
                im.setUserData(new Ficha(row,col));
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

    private void iniciarNuevoTurno() {
        turno++;
        currentPhase = GamePhase.STANDBY;
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
        if(userData.getColor()==null){
            return false;
        }
        else{
            return userData!=null && (userData.getColor()).equals("blanco");
        }
    }
    private boolean fichaNegra(ImageView imv){
        Ficha userData= (Ficha) imv.getUserData();
        if(userData.getColor()==null){
            return false;
        }
        else{
            return userData!=null && (userData.getColor()).equals("negro");
        }
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
                if(imageView!=fichaSeleccionada && !esImagenFichaValida2(imageView,fichaSeleccionada)){
                    Ficha f=(Ficha) fichaSeleccionada.getUserData();
                    if(f.getTipo().equals("peon")){
                        if(moverPeon(imageView)){
                            moverFicha(imageView);
                            iniciarNuevoTurno();
                        }
                    }
                    else if(f.getTipo().equals("torre")){
                        if(moverTorre(imageView)){
                            moverFicha(imageView);
                            iniciarNuevoTurno();
                        }
                    }
                    else if(f.getTipo().equals("caballo")){
                        if(moverCaballo(imageView)){
                            moverFicha(imageView);
                            iniciarNuevoTurno();
                        }
                    }
                    else{
                        moverFicha(imageView);
                        iniciarNuevoTurno();
                    }
                    resetearColorCasillas();
                    
                }
                else if(esImagenFichaValida2(imageView,fichaSeleccionada)){
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
        return (image != null);
    }
    private boolean esImagenFichaValida2(ImageView imageView, ImageView imv) {
        Image image = imageView.getImage();
        Ficha f= (Ficha) imageView.getUserData();
        Ficha f2= (Ficha) imv.getUserData();
        if(f.getColor()!=null){
            String color= f.getColor();
            String color2= f2.getColor();
            return(color.equals(color2));
        }
        return (image != null);
    }

    private boolean moverPeon(ImageView destino){
        Ficha origen = (Ficha) fichaSeleccionada.getUserData();
        Ficha destinop =(Ficha) destino.getUserData();
        int fOrigen= origen.getFila();
        int cOrigen= origen.getColumna();
        int fDestino= destinop.getFila();
        int cDestino = destinop.getColumna();
        System.out.println(fOrigen);
        System.out.println(fDestino);
        String colorOrigen = origen.getColor();
        String colorDestino= null;
        if(destinop.getColor()!=null){
            colorDestino= destinop.getColor();
        }
        boolean movValido= false;
        int fmedia= (fOrigen+fDestino)/2;
        boolean casillaIntermediaVacia = imageViews[fmedia][cOrigen].getImage() == null;
        if(colorOrigen.equals("blanco")){
            if(fDestino==fOrigen-1 && cDestino == cOrigen){
                if((destino.getImage()==null)){
                    movValido= true;
                }
            }
        }
        else if(!colorOrigen.equals("blanco")){
            if(fDestino==fOrigen+1 && cDestino==cOrigen){
                if((destino.getImage()==null)){
                    movValido= true;
                }
            }
        }
        if(colorOrigen.equals("blanco") && fOrigen==6){
            if(fDestino==fOrigen-2 && cDestino == cOrigen){
                if((destino.getImage()==null)&&(casillaIntermediaVacia)){
                    movValido= true;
                }
            }
        }
        else if(colorOrigen.equals("negro") && fOrigen==1){
            if(fDestino==fOrigen+2 && cDestino == cOrigen){
                if((destino.getImage()==null)&&(casillaIntermediaVacia)){
                    movValido= true;
                }
            }
        }
        if(colorDestino!=null){
            if(Math.abs(cDestino-cOrigen)==1){
                if(colorOrigen.equals("blanco")&& fDestino==fOrigen-1 && colorDestino.equals("negro")){
                    movValido=true;
                }
                else if(colorOrigen.equals("negro")&& fDestino==fOrigen+1 && colorDestino.equals("blanco")){
                    movValido=true;
                }
            }
        }
        return movValido;
    }
    private boolean moverTorre(ImageView destino){
         Ficha origen = (Ficha) fichaSeleccionada.getUserData();
    Ficha destinop = (Ficha) destino.getUserData();
    int fOrigen = origen.getFila();
    int cOrigen = origen.getColumna();
    int fDestino = destinop.getFila();
    int cDestino = destinop.getColumna();

    // Verificar si la torre se mueve horizontalmente
    if (fOrigen == fDestino && cOrigen != cDestino) {
        int pasoColumna;
        if (cDestino - cOrigen > 0) {
            pasoColumna = 1;
        } else {
            pasoColumna = -1;
        }

        int columnaActual = cOrigen + pasoColumna;
        while (columnaActual != cDestino) {
            ImageView casillaIntermedia = imageViews[fOrigen][columnaActual];
            if (casillaIntermedia.getImage() != null) {
                return false; // Movimiento no válido si hay una casilla ocupada
            }
            columnaActual += pasoColumna;
        }
        return true; // Movimiento válido en línea horizontal
    }

    // Verificar si la torre se mueve verticalmente
    if (cOrigen == cDestino && fOrigen != fDestino) {
        int pasoFila;
        if (fDestino - fOrigen > 0) {
            pasoFila = 1;
        } else {
            pasoFila = -1;
        }

        int filaActual = fOrigen + pasoFila;
        while (filaActual != fDestino) {
            ImageView casillaIntermedia = imageViews[filaActual][cOrigen];
            if (casillaIntermedia.getImage() != null) {
                return false; // Movimiento no válido si hay una casilla ocupada
            }
            filaActual += pasoFila;
        }
        return true; // Movimiento válido en línea vertical
    }

    return false;
    }
    private boolean moverCaballo(ImageView destino) {
    Ficha origen = (Ficha) fichaSeleccionada.getUserData();
    Ficha destinop = (Ficha) destino.getUserData();
    int fOrigen = origen.getFila();
    int cOrigen = origen.getColumna();
    int fDestino = destinop.getFila();
    int cDestino = destinop.getColumna();

    // Verificar si el movimiento del caballo es válido
    int filaDif = Math.abs(fDestino - fOrigen);
    int colDif = Math.abs(cDestino - cOrigen);

    return (filaDif == 2 && colDif == 1) || (filaDif == 1 && colDif == 2);
}

    private void moverFicha(ImageView destino){
        if (fichaSeleccionada!=null && destino != null){
            Ficha f=(Ficha) fichaSeleccionada.getUserData();
            Ficha d= (Ficha) destino.getUserData();
            Image image= fichaSeleccionada.getImage();
            destino.setImage(image);
            destino.setFitWidth(40);
            destino.setFitHeight(50);
            destino.setUserData(new Ficha(f.getTipo(),f.getColor(),d.getFila(),d.getColumna()));
            fichaSeleccionada.setUserData(new Ficha(f.getFila(),f.getColumna()));
            fichaSeleccionada.setImage(null);
            fichaSeleccionada = null;
        }
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
        Ficha ficha = (Ficha) im.getUserData();
        if(ficha.getTipo().equals("peon")){
            casillasValidas=calcularCasillasValidasPeon(im);
        }
        else if(ficha.getTipo().equals("torre")){
            casillasValidas=calcularCasillasValidasTorre(im);
        }
        else if(ficha.getTipo().equals("caballo")){
            casillasValidas=calcularCasillasValidasCaballo(im);
        }
        else{
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Button casilla = buttons[row][col];
                    ImageView ic = imageViews[row][col];
                    Image img = ic.getImage();
                    casillasValidas[row][col] = (img == null);
                }
            }
        }
        return casillasValidas;
    }
    private boolean[][] calcularCasillasValidasPeon(ImageView im) {
    boolean[][] casillasValidas = new boolean[8][8];
    Ficha ficha = (Ficha) im.getUserData();
    int row = ficha.getFila();
    int col = ficha.getColumna();

    // Para peones blancos
    if (ficha.getColor().equals("blanco")) {
        // Movimiento hacia adelante
        if (row > 1 && imageViews[row - 1][col].getImage() == null) {
            casillasValidas[row - 1][col] = true;
        }
        if(row==6 && imageViews[row-2][col].getImage()==null && imageViews[row - 1][col].getImage() == null){
            casillasValidas[row - 2][col] = true;
        }
        // Movimiento diagonal izquierda para comer
        if (row > 0 && col > 0 && imageViews[row - 1][col - 1].getImage() != null && !fichaBlanca(imageViews[row - 1][col - 1])) {
            casillasValidas[row - 1][col - 1] = true;
        }
        // Movimiento diagonal derecha para comer
        if (row > 0 && col < 7 && imageViews[row - 1][col + 1].getImage() != null && !fichaBlanca(imageViews[row - 1][col + 1])) {
            casillasValidas[row - 1][col + 1] = true;
        }
    }

    // Para peones negros
    if (ficha.getColor().equals("negro")) {
        // Movimiento hacia adelante
        if (row < 6 && imageViews[row + 1][col].getImage() == null) {
            casillasValidas[row + 1][col] = true;
        }
        if(row==1 && imageViews[row+2][col].getImage()==null&& imageViews[row + 1][col].getImage() == null){
            casillasValidas[row + 2][col] = true;
        }
        // Movimiento diagonal izquierda para comer
        if (row > 0 && col > 0 && imageViews[row + 1][col - 1].getImage() != null && !fichaNegra(imageViews[row + 1][col - 1])) {
            casillasValidas[row + 1][col - 1] = true;
        }
        // Movimiento diagonal derecha para comer
        if (row > 0 && col < 7 && imageViews[row + 1][col + 1].getImage() != null && !fichaNegra(imageViews[row + 1][col + 1])) {
            casillasValidas[row + 1][col + 1] = true;
        }
    }

    return casillasValidas;
}
    private boolean[][] calcularCasillasValidasTorre(ImageView im) {
    boolean[][] casillasValidas = new boolean[8][8];
    Ficha ficha = (Ficha) im.getUserData();
    int row = ficha.getFila();
    int col = ficha.getColumna();

    // Movimiento hacia arriba
    for (int i = row - 1; i >= 0; i--) {
        if (imageViews[i][col].getImage() == null) {
            casillasValidas[i][col] = true;
        } else {
            // La torre no puede pasar a través de fichas
            Ficha f2 = (Ficha) imageViews[i][col].getUserData();
            if (!ficha.getColor().equals(f2.getColor())) {
                casillasValidas[i][col] = true;
            }
            break;
        }
    }

    // Movimiento hacia abajo
    for (int i = row + 1; i < 8; i++) {
        if (imageViews[i][col].getImage() == null) {
            casillasValidas[i][col] = true;
        } else {
            // La torre no puede pasar a través de fichas
            Ficha f2 = (Ficha) imageViews[i][col].getUserData();
            if (!ficha.getColor().equals(f2.getColor())) {
                casillasValidas[i][col] = true;
            }
            break;
        }
    }

    // Movimiento hacia la izquierda
    for (int j = col - 1; j >= 0; j--) {
        if (imageViews[row][j].getImage() == null) {
            casillasValidas[row][j] = true;
        } else {
            // La torre no puede pasar a través de fichas
            Ficha f2 = (Ficha) imageViews[row][j].getUserData();
            if (!ficha.getColor().equals(f2.getColor())) {
                casillasValidas[row][j] = true;
            }
            break;
        }
    }

    // Movimiento hacia la derecha
    for (int j = col + 1; j < 8; j++) {
        if (imageViews[row][j].getImage() == null) {
            casillasValidas[row][j] = true;
        } else {
            // La torre no puede pasar a través de fichas
            Ficha f2 = (Ficha) imageViews[row][j].getUserData();
            if (!ficha.getColor().equals(f2.getColor())) {
                casillasValidas[row][j] = true;
            }
            break;
        }
    }

    return casillasValidas;
}
private boolean[][] calcularCasillasValidasCaballo(ImageView im) {
    boolean[][] casillasValidas = new boolean[8][8];
    Ficha ficha = (Ficha) im.getUserData();
    int row = ficha.getFila();
    int col = ficha.getColumna();

    // Movimientos válidos del caballo
    int[][] movimientosCaballo = {
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1},
        {-1, -2},
        {1, -2},
        {2, -1}
    };

    for (int[] movimiento : movimientosCaballo) {
        int newRow = row + movimiento[0];
        int newCol = col + movimiento[1];

        // Verificar si la nueva posición está dentro del tablero
        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            // Verificar si la casilla está vacía o tiene una ficha del color opuesto
            ImageView casilla = imageViews[newRow][newCol];
            if (casilla.getImage() == null || !esMismaFichaColor(ficha, casilla)) {
                casillasValidas[newRow][newCol] = true;
            }
        }
    }

    return casillasValidas;
}

// Método para verificar si dos fichas tienen el mismo color
private boolean esMismaFichaColor(Ficha ficha1, ImageView ficha2) {
    Ficha fichaEnCasilla = (Ficha) ficha2.getUserData();
    return ficha1.getColor().equals(fichaEnCasilla.getColor());
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

