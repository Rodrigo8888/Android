package com.example.rpcosta.obligatorio1;

import AsyncTask.ObtenerCategorias;
import DataBase.BaseDatos;
import Interfaces.ObtenerListaCategorias;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class VentanaInicio extends Activity implements ObtenerListaCategorias {
    private int progreso = 0;
    private int paso = 500;
    private ProgressBar mProgressBar;
    private BaseDatos db;
    private static final long REPEAT_TIME = 1000 * 40;
    private ArrayList<Categorias> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_inicio);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        db = new BaseDatos(this);
        listaCategorias = new ArrayList<Categorias>();
        new ObtenerCategorias(this).execute();
        AlarmManager service = (AlarmManager) VentanaInicio.this
                .getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(VentanaInicio.this, MyReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(VentanaInicio.this, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar cal = Calendar.getInstance();
        // Start 30 seconds after boot completed
        cal.add(Calendar.SECOND, 30);
        //
        // Fetch every 30 seconds
        // InexactRepeating allows Android to optimize the energy consumption
        service.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                cal.getTimeInMillis(), REPEAT_TIME, pending);

    }

    private void cuentaAtras(long milisegundos) {

        CountDownTimer mCountDownTimer;
        mProgressBar.setMax((int) milisegundos);
        mProgressBar.setProgress(paso);

        mCountDownTimer = new CountDownTimer(milisegundos, paso) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress" + progreso + millisUntilFinished);
                progreso += paso;
                mProgressBar.setProgress(progreso);
            }

            @Override
            public void onFinish() {
                progreso += paso;
                mProgressBar.setProgress(progreso);
                mProgressBar.setVisibility(View.INVISIBLE);
                Intent i = new Intent(VentanaInicio.this, MyActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), getString(R.string.bienvenido), Toast.LENGTH_SHORT).show();
                finish();
            }
        };

        mCountDownTimer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ventana_inicio, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        cuentaAtras(3000);   //3 sec.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void obteneRespuesta(ArrayList<Categorias> respuesta) {
        ArrayList<Categorias> lista = db.listaCategorias();
        if (respuesta != null && lista.size() == 0) {
            listaCategorias = respuesta;
            for (int i = 0; i < listaCategorias.size(); i++) {
                db.insertarCategoria(listaCategorias.get(i).getNombre(), listaCategorias.get(i).getId());
            }
        } else {
            if (respuesta != null) {
                listaCategorias = respuesta;
                for (int i = 0; i < listaCategorias.size(); i++) {
                    Boolean already = false;
                    for (int j = 0; (j < lista.size())&&(!already); j++) {
                        if (listaCategorias.get(i).getId().equalsIgnoreCase(lista.get(j).getId())) {
                             already = true;
                        }
                    }
                    if(!already){
                        db.insertarCategoria(listaCategorias.get(i).getNombre(), listaCategorias.get(i).getId());
                    }
                }

            }
        }
    }


}
