/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.espol.util;

import ec.edu.espol.model.Ficha;
import javafx.scene.image.ImageView;

/**
 *
 * @author Zambrano
 */
public interface MovementStrategy {
    boolean mover(ImageView destino, ImageView[][] imageViews, Ficha ficha);
}
