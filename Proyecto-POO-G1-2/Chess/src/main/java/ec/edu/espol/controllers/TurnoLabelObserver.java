/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.controllers;

/**
 *
 * @author Zambrano
 */
import ec.edu.espol.util.Observer;
import javafx.scene.control.Label;

public class TurnoLabelObserver implements Observer {
    private Label turnoLabel;

    public TurnoLabelObserver(Label turnoLabel) {
        this.turnoLabel = turnoLabel;
    }

    @Override
    public void update(String message) {
        turnoLabel.setText(message);
    }
}
