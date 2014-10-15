package com.example.rpcosta.ejercicio3;

import java.io.Serializable;


public class Cliente implements Serializable {

    private static final long serialVersionUID = 9092274725284727606L;
    private String nombre;
    private String pais;
    private Integer id;
    private String mail;
    private Integer telefono;

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
}
