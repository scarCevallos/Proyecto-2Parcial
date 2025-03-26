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
public class PeonMovementStrategy implements MovementStrategy {
    @Override
    public boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha) {
        int fOrigen = ficha.getFila();
        int cOrigen = ficha.getColumna();
        int fDestino = ((Ficha) destino.getUserData()).getFila();
        int cDestino = ((Ficha) destino.getUserData()).getColumna();
        String colorOrigen = ficha.getColor();
        String colorDestino = ((Ficha) destino.getUserData()).getColor();
        boolean movValido = false;
        int fmedia = (fOrigen + fDestino) / 2;
        boolean casillaIntermediaVacia = imageViews[fmedia][cOrigen].getImage() == null;
        if (colorOrigen.equals("blanco")) {
            if (fDestino == fOrigen - 1 && cDestino == cOrigen) {
                if ((destino.getImage() == null)) {
                    movValido = true;
                }
            }
        } else if (!colorOrigen.equals("blanco")) {
            if (fDestino == fOrigen + 1 && cDestino == cOrigen) {
                if ((destino.getImage() == null)) {
                    movValido = true;
                }
            }
        }
        if (colorOrigen.equals("blanco") && fOrigen == 6) {
            if (fDestino == fOrigen - 2 && cDestino == cOrigen) {
                if ((destino.getImage() == null) && (casillaIntermediaVacia)) {
                    movValido = true;
                }
            }
        } else if (colorOrigen.equals("negro") && fOrigen == 1) {
            if (fDestino == fOrigen + 2 && cDestino == cOrigen) {
                if ((destino.getImage() == null) && (casillaIntermediaVacia)) {
                    movValido = true;
                }
            }
        }
        if (colorDestino != null) {
            if (Math.abs(cDestino - cOrigen) == 1) {
                if (colorOrigen.equals("blanco") && fDestino == fOrigen - 1 && colorDestino.equals("negro")) {
                    movValido = true;
                } else if (colorOrigen.equals("negro") && fDestino == fOrigen + 1 && colorDestino.equals("blanco")) {
                    movValido = true;
                }
            }
        }
        return movValido;
    }
}

