package com.example.rpcosta.ejercicio4.Asynctask;

import android.os.AsyncTask;
import android.util.Log;
import com.example.rpcosta.ejercicio4.Interfaces.DatosItems;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by rpcosta on 29/10/14.
 */
public class Descripcion  extends AsyncTask<String, String, String>{


    private DatosItems dto;
    private String descripcion;


    public Descripcion(DatosItems activity) {
        this.dto = activity;
    }

    @Override
    protected void onPostExecute(String desc) {
        super.onPostExecute(desc);
        dto.refreshDescripcion(desc);
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            URLConnection conection = url.openConnection();
            InputStream in = new BufferedInputStream(conection.getInputStream());
            JSONObject json = new JSONObject(getResponseText(in));
            Log.v("respuesta",json.toString());
            descripcion = json.getString("text");
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return descripcion;
    }
    private String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }


}
