package com.example.rpcosta.ejercicio4android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultadosComida extends Activity {
    private TextView nombre,descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_comida);
        nombre = (TextView)findViewById(R.id.textView);
        descripcion = (TextView)findViewById(R.id.textView2);
        Bundle b = getIntent().getExtras();
        Comida c = (Comida)b.getSerializable("Comida");
        nombre.setText(c.getNombre());
        descripcion.setText(c.getDescripciçon());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resultados_comida, menu);
        return true;
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
