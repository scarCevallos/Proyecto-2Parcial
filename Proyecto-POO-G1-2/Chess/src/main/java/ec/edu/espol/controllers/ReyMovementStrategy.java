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
public class ReyMovementStrategy implements MovementStrategy {
    @Override
    public boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha) {
        int fOrigen = ficha.getFila();
        int cOrigen = ficha.getColumna();
        int fDestino = ((Ficha) destino.getUserData()).getFila();
        int cDestino = ((Ficha) destino.getUserData()).getColumna();
        // Verificar si el movimiento es válido para el rey
        int filaDif = Math.abs(fDestino - fOrigen);
        int colDif = Math.abs(cDestino - cOrigen);

        // El rey puede moverse una casilla en cualquier dirección
        if ((filaDif == 1 && colDif == 0) || (filaDif == 0 && colDif == 1) || (filaDif == 1 && colDif == 1)) {
            // Verificar si la casilla de destino está vacía o tiene una ficha del color opuesto
            ImageView casillaDestino = imageViews[fDestino][cDestino];
            if (casillaDestino.getImage() == null || !esMismaFichaColor(ficha, casillaDestino)) {
                return true; // Movimiento válido para el rey
            }
        }

        return false; // Movimiento inválido para el rey
    }
        private boolean esMismaFichaColor(Ficha ficha1, ImageView ficha2) {
        Ficha fichaEnCasilla = (Ficha) ficha2.getUserData();
        return ficha1.getColor().equals(fichaEnCasilla.getColor());
    }
}

