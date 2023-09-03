/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.chess;

/**
 *
 * @author HP
 */
public class Ficha {
    private String tipo;
    private String color;
    private int fila;
    private int columna;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Ficha(String tipo, String color, int fila, int columna) {
        this.tipo = tipo;
        this.color = color;
        this.fila = fila;
        this.columna = columna;
    }
    public Ficha(int fila, int columna){
        this.fila=fila;
        this.columna= columna;
    }
     
}
