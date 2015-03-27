package com.example.rpcosta.obligatorio1;

/**
 * Created by rpcosta on 27/10/14.
 */
public class ObjetoEstadisticas {

    private int preguntasCorrectas;
    private int preguntasIncorrectas;
    private int totalPreguntas;
    private String nombreCategoria,Id;

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getPreguntasCorrectas() {
        return preguntasCorrectas;
    }

    public void setPreguntasCorrectas(int preguntasCorrectas) {
        this.preguntasCorrectas = preguntasCorrectas;
    }

    public int getPreguntasIncorrectas() {
        return preguntasIncorrectas;
    }

    public void setPreguntasIncorrectas(int preguntasIncorrectas) {
        this.preguntasIncorrectas = preguntasIncorrectas;
    }

    public int getTotalPreguntas() {
        return totalPreguntas;
    }

    public void setTotalPreguntas(int totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }
}
