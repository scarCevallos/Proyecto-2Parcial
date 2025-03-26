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
public class TorreMovementStrategy implements MovementStrategy {
    @Override
    public boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha) {
        int fOrigen = ficha.getFila();
        int cOrigen = ficha.getColumna();
        int fDestino = ((Ficha) destino.getUserData()).getFila();
        int cDestino = ((Ficha) destino.getUserData()).getColumna();

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
}

