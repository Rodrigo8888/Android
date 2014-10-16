package com.example.rpcosta.ejercicioextra;

/**
 * Created by rpcosta on 15/10/14.
 */
public class Imagen {
    private String texto;
    private Boolean tex;
    private int id;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getTex() {
        return tex;
    }

    public void setTex(Boolean tex) {
        this.tex = tex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Imagen(String texto, Boolean tex, int id) {
        this.texto = texto;
        this.tex = tex;
        this.id = id;
    }
}
