package com.example.rpcosta.ejercicio4.Asynctask;

import android.os.AsyncTask;
import android.util.Log;
import com.example.rpcosta.ejercicio4.Dominio.APIResults;
import com.example.rpcosta.ejercicio4.Interfaces.Datos;
import com.example.rpcosta.ejercicio4.Dominio.Item;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by rpcosta on 13/10/14.
 */
public class Busqueda extends AsyncTask<String, String, ArrayList<Item>> {
    private Datos dto;
    private ArrayList<Item> listaItems;
    private APIResults resultados;

    public Busqueda(Datos activity) {
        this.dto = activity;
    }

    @Override
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        dto.refreshList(items);

    }

    @Override
    protected ArrayList<Item> doInBackground(String... urlSearch) {

        try {
            URL url = new URL(urlSearch[0]);
            URLConnection conection = url.openConnection();
            InputStream in = new BufferedInputStream(conection.getInputStream());
            ObjectMapper mapper = new ObjectMapper();
            resultados = mapper.readValue(in, APIResults.class);
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return resultados.getResults();
    }




}


