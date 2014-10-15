package com.example.rpcosta.obligatorio1;

public class Jugador {

    private String nombre;
    private String mail;
    private String contraseña;

    public String getMail() {
        return mail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


}
