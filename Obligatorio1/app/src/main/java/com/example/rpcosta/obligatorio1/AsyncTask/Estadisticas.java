package com.example.rpcosta.obligatorio1.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import com.example.rpcosta.obligatorio1.Interfaces.Estadistica;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rpcosta on 24/10/14.
 */
public class Estadisticas extends AsyncTask<String, String, ArrayList<String>> {

    private ArrayList<String> estadisticas;
    private Estadistica intf;

    public Estadisticas(Estadistica intf) {
        this.intf = intf;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        try {
            URL url = new URL("http://ortapipreguntados.herokuapp.com/users/statistics/?"+"id=" + params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            JSONObject json = new JSONObject(getResponseText(in));
           Log.v("respuesta",json.toString());


        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return estadisticas;
    }

    private String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
