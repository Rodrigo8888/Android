package com.example.rpcosta.ejercicio2;

import android.media.Image;
import java.io.Serializable;

/**
 * Created by rpcosta on 13/10/14.
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String image;
    private int price;
    private String subtitle;
    private int quantity;
    private String identificador;

    public String getIdentificador() {
         return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(String titulo, int precio, String subtitulo, int cantidad, String imagen, String ident) {
        this.title = titulo;
        this.price = precio;
        this.subtitle = subtitulo;
        this.quantity = cantidad;
        this.image = imagen;
        this.identificador= ident;
    }
}
