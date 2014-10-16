package com.example.rpcosta.ejercicio2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rpcosta on 13/10/14.
 */
public class Busqueda extends AsyncTask<String, String, ArrayList<Item>> {
    private Datos dto;
    private ArrayList<Item> listaItems;

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
            JSONObject json = new JSONObject(getResponseText(in));
            JSONArray lista = json.getJSONArray("results");
            listaItems = new ArrayList<Item>();
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                listaItems.add(new Item(item.getString("title"), item.getInt("price"), item.getString("subtitle"), item.getInt("available_quantity"),item.getString("thumbnail"),item.getString("id")));

                if (isCancelled()) break;
            }

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return listaItems;
    }

    private String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }



}


