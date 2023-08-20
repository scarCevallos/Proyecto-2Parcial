/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author HP
 */
public class Chess {
    public static ArrayList<String> randomizarFichas(){
        ArrayList<String> pieceTypes = new ArrayList<>();
        pieceTypes.add("Blancas");
        pieceTypes.add("Negras");
        Collections.shuffle(pieceTypes);
        return pieceTypes;
    }
}
