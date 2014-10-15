package com.example.rpcosta.ejercicio4android;

import java.io.Serializable;

/**
 * Created by rpcosta on 13/10/14.
 */
public class Postre implements Serializable{
    String nombre;
    String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
