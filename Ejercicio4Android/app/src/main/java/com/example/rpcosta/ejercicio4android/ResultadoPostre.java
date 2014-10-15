package com.example.rpcosta.ejercicio4android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultadoPostre extends Activity {
    private TextView nombre,descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resultado_postre);
            nombre = (TextView)findViewById(R.id.textView);
            descripcion = (TextView)findViewById(R.id.textView2);
            Bundle b = getIntent().getExtras();
            Postre p = (Postre)b.getSerializable("Postre");
            nombre.setText(p.getNombre());
            descripcion.setText(p.getDescripcion());

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resultado_postre, menu);
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
