package com.example.rpcosta.obligatorio1.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rpcosta.obligatorio1.Interfaces.ChangePass;

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
public class CambioPass extends AsyncTask<ArrayList<String> ,Boolean,Boolean >{
    Boolean result;
    ChangePass dto;

    public CambioPass(ChangePass dto) {
        this.dto = dto;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dto.result(aBoolean);

    }


    @Override
    protected Boolean doInBackground(ArrayList<String>... params) {
        try {
            String urL = "http://ortapipreguntados.herokuapp.com/users/changePassword/?";
            String urlParameters = "id=" + params[0].get(0) + "&password=" + params[0].get(1);
            String request = urL;
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
            Log.v("resp",json.toString());
            if (respuesta == true) {
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
