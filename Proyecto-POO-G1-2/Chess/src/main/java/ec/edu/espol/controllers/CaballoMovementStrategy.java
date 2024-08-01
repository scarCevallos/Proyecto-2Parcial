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
public class CaballoMovementStrategy implements MovementStrategy {
    @Override
    public boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha) {
        int fOrigen = ficha.getFila();
        int cOrigen = ficha.getColumna();
        int fDestino = ((Ficha) destino.getUserData()).getFila();
        int cDestino = ((Ficha) destino.getUserData()).getColumna();

        // Verificar si el movimiento del caballo es válido
        int filaDif = Math.abs(fDestino - fOrigen);
        int colDif = Math.abs(cDestino - cOrigen);

        return (filaDif == 2 && colDif == 1) || (filaDif == 1 && colDif == 2);
    }
}

