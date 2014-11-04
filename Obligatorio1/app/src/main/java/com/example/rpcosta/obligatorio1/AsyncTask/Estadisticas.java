package com.example.rpcosta.obligatorio1.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import com.example.rpcosta.obligatorio1.Interfaces.Estadistica;
import com.example.rpcosta.obligatorio1.ObjetoEstadisticas;
import org.json.JSONArray;
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
public class Estadisticas extends AsyncTask<String, String, ArrayList<ObjetoEstadisticas>> {

    private ArrayList<ObjetoEstadisticas> estadisticas;
    private Estadistica intf;

    public Estadisticas(Estadistica intf) {
        this.intf = intf;
    }

    @Override
    protected void onPostExecute(ArrayList<ObjetoEstadisticas> estadisticas) {
        super.onPostExecute(estadisticas);
    }

    @Override
    protected ArrayList<ObjetoEstadisticas> doInBackground(String... params) {
        try {
            URL url = new URL("http://ortapipreguntados.herokuapp.com/users/statistics/?" + "id=" + params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            JSONObject json = new JSONObject(getResponseText(in));
            JSONObject datos = json.getJSONObject("statistics");
            JSONArray categorias = datos.getJSONArray("categories");
            Boolean respuesta = json.getBoolean("success");
            if(respuesta){
              estadisticas = new ArrayList<ObjetoEstadisticas>();
              for(int i=0; i<categorias.length();i++){
                  JSONObject Obj = (JSONObject) categorias.get(i);
                  ObjetoEstadisticas estadistica = new ObjetoEstadisticas();
                  estadistica.setNombreCategoria(Obj.getString("name"));
                  estadistica.setPreguntasCorrectas(Obj.getInt("total_questions_correct"));
                  estadistica.setPreguntasIncorrectas(Obj.getInt("total_questions_incorrect"));
                  estadistica.setTotalPreguntas(Obj.getInt("total_questions"));
                  estadisticas.add(estadistica);
              }


            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return estadisticas;
    }

    private String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
