package com.example.rpcosta.ejercicio4.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import com.example.rpcosta.ejercicio4.Activitys.MyActivity2;
import com.example.rpcosta.ejercicio4.DataBase.DBHelper;
import com.example.rpcosta.ejercicio4.Dominio.Item;
import com.example.rpcosta.ejercicio4.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by rpcosta on 27/10/14.
 */
public class MyService extends IntentService {
    private DBHelper helper;
    private Dao dao;
    private Handler handler;
    private ArrayList<Item> lista;


    public MyService() {
        super("MyService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        handler = new Handler();
//        updateData.run();
    }

    private Runnable updateData = new Runnable() {
        public void run() {
            checkear();
            handler.postDelayed(updateData, 1000*5);
        }
    };

    private void checkear() {
        helper = OpenHelperManager.getHelper(MyService.this, DBHelper.class);
        try {
            dao = helper.getItemDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            lista = (ArrayList<Item>) dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Avisar si el item finaliza el día de hoy
        Calendar c = Calendar.getInstance();
        for(int i=0;i<lista.size();i++){
            if (c.getTime() == lista.get(i).getStop_time()) {
                //avisar que estamos en el dia en que finaliza el item
                int hour = c.get(Calendar.HOUR);
                int itemHour = lista.get(i).getStop_time().getHours();
                if (hour == itemHour - 1) {
                    //Creo notificación
                    Intent inte = new Intent(MyService.this, MyActivity2.class);
                    inte.putExtra("ID", lista.get(i).getId());
                    PendingIntent pIntent = PendingIntent.getActivity(this, 0, inte, 0);
                    Notification n = new Notification.Builder(this)
                            .setContentTitle("Su favorito:  " + lista.get(i).getTitle() + " finaliza en 1hs, no lo dejes pasar!")
                            .setSmallIcon(R.drawable.notificacion)
                            .setContentIntent(pIntent)
                            .setAutoCancel(true).build();
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    notificationManager.notify(0, n);
                    n.defaults |= Notification.DEFAULT_LIGHTS;


                }
            }
        }
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        while(true){
            checkear();
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
