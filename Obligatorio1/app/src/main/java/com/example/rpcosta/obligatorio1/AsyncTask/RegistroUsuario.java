package com.example.rpcosta.obligatorio1.AsyncTask;

import android.os.AsyncTask;
import com.example.rpcosta.obligatorio1.Interfaces.RegUsuario;
import com.example.rpcosta.obligatorio1.Jugador;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by rpcosta on 21/10/14.
 */
public class RegistroUsuario extends AsyncTask<Jugador, Jugador, Boolean> {
    String url;
    RegUsuario dto;
    Boolean result;
    String id;

    public RegistroUsuario(RegUsuario activity) {
        dto = activity;
    }

    @Override
    protected Boolean doInBackground(Jugador... params) {

        try {
            url = "http://ortapipreguntados.herokuapp.com/users/new/?";
            String urlParameters = "name=" + params[0].getNombre() + "&mail=" + params[0].getMail() + "&password=" + params[0].getPassword();
            String request = url;
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            JSONObject json = new JSONObject(getResponseText(in));
            JSONObject _id = json.getJSONObject("user");
            boolean respuesta = (Boolean) json.getBoolean("success");
            if (respuesta == true) {
                id = _id.getString("_id");
                result = true;
            } else {
                result = false;
            }
            wr.close();
            connection.disconnect();
        } catch (Exception ex) {

        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        dto.result(s,id);


    }

    private String getResponseText(InputStreamReader inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
