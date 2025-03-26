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
public class ReinaMovementStrategy implements MovementStrategy {
    @Override
    public boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha) {
        int fOrigen = ficha.getFila();
        int cOrigen = ficha.getColumna();
        int fDestino = ((Ficha) destino.getUserData()).getFila();
        int cDestino = ((Ficha) destino.getUserData()).getColumna();
        boolean movValido = false;
        MovementStrategy movTorre = new TorreMovementStrategy();
        MovementStrategy movAlfil = new AlfilMovementStrategy();
        if (movAlfil.mover(destino, imageViews, ficha)) {
            int pasoFila, pasoColumna;
            if (fDestino > fOrigen) {
                pasoFila = 1;
            } else if (fDestino < fOrigen) {
                pasoFila = -1;
            } else {
                pasoFila = 0;
            }
            if (cDestino > cOrigen) {
                pasoColumna = 1;
            } else if (cDestino < cOrigen) {
                pasoColumna = -1;
            } else {
                pasoColumna = 0;
            }
            int filaActual = fOrigen + pasoFila;
            int columnaActual = cOrigen + pasoColumna;

            while (filaActual != fDestino && columnaActual != cDestino) {
                if (imageViews[filaActual][columnaActual].getImage() != null) {
                    return false; // Hay una ficha en el camino diagonal
                }
                filaActual += pasoFila;
                columnaActual += pasoColumna;
            }
        }
        if (movTorre.mover(destino, imageViews, ficha)) {
            int pasoFila = 0;
            int pasoColumna = 0;

            if (fDestino > fOrigen) {
                pasoFila = 1;
            } else if (fDestino < fOrigen) {
                pasoFila = -1;
            }

            if (cDestino > cOrigen) {
                pasoColumna = 1;
            } else if (cDestino < cOrigen) {
                pasoColumna = -1;
            }

            int filaActual = fOrigen + pasoFila;
            int columnaActual = cOrigen + pasoColumna;

            while (filaActual != fDestino || columnaActual != cDestino) {
                if (imageViews[filaActual][columnaActual].getImage() != null) {
                    return false; // Hay una ficha en el camino
                }
                filaActual += pasoFila;
                columnaActual += pasoColumna;
            }
        }
        if (movTorre.mover(destino, imageViews, ficha) || movAlfil.mover(destino, imageViews, ficha)) {
            movValido = true;
        }
        return movValido;
    }
   }

