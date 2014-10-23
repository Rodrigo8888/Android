package com.example.rpcosta.ejercicio2.Asynctask;

import android.os.AsyncTask;
import android.util.Log;
import com.example.rpcosta.ejercicio2.Interfaces.DatosItems;
import com.example.rpcosta.ejercicio2.Item;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
            ObjectMapper mapper = new ObjectMapper();
            it = mapper.readValue(in, Item.class);

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return it;
    }

    private String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
