/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Ficha;
import ec.edu.espol.util.MovementStrategy;
import javafx.scene.image.ImageView;

/**
 *
 * @author Zambrano
 */
public class AlfilMovementStrategy implements MovementStrategy {
    @Override
    public boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha) {
        int fOrigen = ficha.getFila();
        int cOrigen = ficha.getColumna();
        int fDestino = ((Ficha) destino.getUserData()).getFila();
        int cDestino = ((Ficha) destino.getUserData()).getColumna();
        int filaDif = Math.abs(fDestino - fOrigen);
        int colDif = Math.abs(cDestino - cOrigen);
        boolean movValido = false;
        if (filaDif == colDif) {
            int pasoFila = (fDestino - fOrigen) / filaDif;
            int pasoColumna = (cDestino - cOrigen) / colDif;
            int filaActual = fOrigen + pasoFila;
            int columnaActual = cOrigen + pasoColumna;
            while (filaActual != fDestino && columnaActual != cDestino) {
                ImageView casillaIntermedia = imageViews[filaActual][columnaActual];
                if (casillaIntermedia.getImage() != null) {
                    movValido = false;
                }
                filaActual += pasoFila;
                columnaActual += pasoColumna;
            }
            movValido = true;
        } else {
            movValido = false;
        }
        return movValido;
    }
}
