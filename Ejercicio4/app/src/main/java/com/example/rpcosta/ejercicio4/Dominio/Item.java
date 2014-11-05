package com.example.rpcosta.ejercicio4.Dominio;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Created by rpcosta on 13/10/14.
 */
@DatabaseTable(tableName = "FAVORITOS")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;


    public static final String ID = "_id";
    public static final String NUM_ITEM = "id";
    public static final String DATA_END = "stop_time";

    @DatabaseField(generatedId = true, columnName = ID)
    private int _id;

    private String title;

    private String thumbnail;

    private Boolean notif;

    private float price;

    @DatabaseField(columnName = DATA_END)
    private Date stop_time;

    private String subtitle;

    private int available_quantity;

    @DatabaseField(columnName = NUM_ITEM)
    private String id;

    private ArrayList<Pictures> pictures;

    public Item() {

    }

    public Date getStop_time() {
        return stop_time;
    }

    public Boolean getNotif() {
        return notif;
    }

    public void setNotif(Boolean notif) {
        this.notif = notif;
    }

    public void setStop_time(Date stop_time) {
        this.stop_time = stop_time;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Item(String titulo, float precio, String subtitulo, int cantidad, String image, String ident, ArrayList<Pictures> lista, Date fecha) {
        this.title = titulo;
        this.price = precio;
        this.notif = false;
        this.subtitle = subtitulo;
        this.available_quantity = cantidad;
        this.thumbnail = image;
        this.id = ident;
        this.pictures = lista;
        this.stop_time = fecha;
    }
}
