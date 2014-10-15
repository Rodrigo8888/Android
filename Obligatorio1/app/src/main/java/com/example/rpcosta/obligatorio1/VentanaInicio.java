package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class VentanaInicio extends Activity {
    private int progreso=0;
    private int paso = 500;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_inicio);
        mProgressBar=(ProgressBar) findViewById(R.id.progressbar);
    }

    private void cuentaAtras(long milisegundos){

        CountDownTimer mCountDownTimer;
        mProgressBar.setMax((int)milisegundos);
        mProgressBar.setProgress(paso);

        mCountDownTimer=new CountDownTimer(milisegundos, paso) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ progreso+ millisUntilFinished);
                progreso+=paso;
                mProgressBar.setProgress(progreso);
            }

            @Override
            public void onFinish() {
                progreso+= paso;
                mProgressBar.setProgress(progreso);
                mProgressBar.setVisibility(View.INVISIBLE);
                Intent i = new Intent (VentanaInicio.this,MyActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), getString(R.string.bienvenido), Toast.LENGTH_SHORT).show();
                finish();}
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
}
