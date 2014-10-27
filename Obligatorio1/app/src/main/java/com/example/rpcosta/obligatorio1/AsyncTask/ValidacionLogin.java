package com.example.rpcosta.obligatorio1.AsyncTask;

import android.os.AsyncTask;
import com.example.rpcosta.obligatorio1.Interfaces.ValUsuario;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rpcosta on 21/10/14.
 */
public class ValidacionLogin extends AsyncTask<ArrayList<String>, String, Boolean> {
    boolean result;
    private String url;
    private ValUsuario dto;
    private String id;
    private String name;
    private String mail;

    public ValidacionLogin(ValUsuario activity) {
        dto = activity;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dto.validacion(aBoolean,id,name,mail);
    }


    @Override
    protected Boolean doInBackground(ArrayList<String>... params) {

        try {
            url = "http://ortapipreguntados.herokuapp.com/users/login/?";
            String urlParameters = "mail=" + params[0].get(0) + "&password=" + params[0].get(1);
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
                name = _id.getString("name");
                mail = _id.getString("mail");
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

    private String getResponseText(InputStreamReader inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
