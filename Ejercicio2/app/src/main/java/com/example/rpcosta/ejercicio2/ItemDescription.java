package com.example.rpcosta.ejercicio2;

import android.content.Context;
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
 * Created by rpcosta on 16/10/14.
 */
public class ItemDescription extends AsyncTask<String, String, Item> {
    private DatosItems dto;
    private Item it;


    public ItemDescription(DatosItems activity) {
        this.dto = activity;
    }

    @Override
    protected void onPostExecute(Item item) {
        super.onPostExecute(item);
        dto.refreshdatos(item);
    }

    @Override
    protected Item doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            URLConnection conection = url.openConnection();
            InputStream in = new BufferedInputStream(conection.getInputStream());
            JSONObject item = new JSONObject(getResponseText(in));
            String titulo = item.getString("title");
            int precio = item.getInt("price");
            String subtitulo = item.getString("subtitle");
            int cantidad = item.getInt("available_quantity");
            String numeroItem = item.getString("id");
            JSONObject image = (JSONObject) item.optJSONArray("pictures").get(0);
            if(image!=null){
                it = new Item(titulo,precio,subtitulo,cantidad,image.getString("url"),numeroItem);
            }
            else{
                it = new Item(titulo,precio,subtitulo,cantidad,item.getString("thumbnail"),numeroItem);
            }

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return it;
    }

    private String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
