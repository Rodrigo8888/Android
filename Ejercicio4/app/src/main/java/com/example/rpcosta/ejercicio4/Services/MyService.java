package com.example.rpcosta.ejercicio4.Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import com.example.rpcosta.ejercicio4.DataBase.DBHelper;

/**
 * Created by rpcosta on 27/10/14.
 */
public class MyService extends IntentService {
    private Context ctx;
    private DBHelper helper;
    private MyService miService;

    private MyService(Context context) {
        super("MyService");
        this.ctx=context;
    }

    public Service getInstance(){
        if(miService == null){
           miService = new MyService(ctx);
        }
        return miService;
    }


    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
