/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.chess;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author HP
 */
public class Jugador {
    private String tipoFicha;
    private int id;
    public String getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador(String tipoFicha, int id) {
        this.tipoFicha = tipoFicha;
        this.id = id;
    }
    public static ArrayList<String> randomizarFichas(){
        ArrayList<String> pieceTypes = new ArrayList<>();
        pieceTypes.add("Blancas");
        pieceTypes.add("Negras");
        Collections.shuffle(pieceTypes);
        return pieceTypes;
    }
    public static ArrayList<Jugador> crearJugadores(ArrayList<String> tipofichas){
        ArrayList<Jugador> players= new ArrayList<>();
        for(int i=0;i<tipofichas.size();i++){
            Jugador j= new Jugador(tipofichas.get(i),(i+1));
            players.add(j);
        }
        return players;
    }
}
