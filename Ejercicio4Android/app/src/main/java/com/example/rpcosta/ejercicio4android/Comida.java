package com.example.rpcosta.ejercicio4android;

import java.io.Serializable;

/**
 * Created by rpcosta on 13/10/14.
 */
public class Comida implements Serializable {
    String nombre;
    String descripciçon;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripciçon() {
        return descripciçon;
    }

    public void setDescripciçon(String descripciçon) {
        this.descripciçon = descripciçon;
    }
}
