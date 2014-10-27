package com.example.rpcosta.obligatorio1.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import com.example.rpcosta.obligatorio1.ChangeNameMail;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rpcosta on 23/10/14.
 */
public class CambiarNombreMail extends AsyncTask<ArrayList<String>, String, Boolean> {
    boolean result;
    String url;
    ChangeNameMail dto;

    public CambiarNombreMail(ChangeNameMail activity) {
        dto = activity;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dto.resultado(aBoolean);
    }


    @Override
    protected Boolean doInBackground(ArrayList<String>... params) {

        try {
            url = "http://ortapipreguntados.herokuapp.com/users/edit/?";
            String urlParameters = "id=" + params[0].get(0) + "&name=" + params[0].get(1)+"&mail=" + params[0].get(2);
            Log.v("urlParams= ",urlParameters);
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
            boolean respuesta = (Boolean) json.getBoolean("success");
            if (respuesta == true) {
                result = true;
            } else {
                result = false;
            }
            wr.close();
            connection.disconnect();

        } catch (Exception e){
            Log.e("Error",e.getMessage());
        }

        return result;
    }

    private String getResponseText(InputStreamReader inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
