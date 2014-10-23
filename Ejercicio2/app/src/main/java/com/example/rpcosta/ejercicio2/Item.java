package com.example.rpcosta.ejercicio2;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Created by rpcosta on 13/10/14.
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String thumbnail;
    private int price;
    private String subtitle;
    private int available_quantity;
    private String id;
    private ArrayList<Pictures> pictures;

    public Item() {

    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Pictures> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<Pictures> getLista() {
        return pictures;
    }

    public void setLista(ArrayList<Pictures> lista) {
        this.pictures = lista;
    }

    public String getIdentificador() {
        return id;
    }

    public void setIdentificador(String identificador) {
        this.id = identificador;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
    public Item(String titulo, int precio, String subtitulo, int cantidad, String image, String ident, ArrayList<Pictures> lista) {
        this.title = titulo;
        this.price = precio;
        this.subtitle = subtitulo;
        this.available_quantity = cantidad;
        this.thumbnail = image;
        this.id= ident;
        this.pictures=lista;
    }
}
