package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rpcosta.obligatorio1.AsyncTask.Estadisticas;
import com.example.rpcosta.obligatorio1.Interfaces.Estadistica;

import java.util.ArrayList;


public class MiPerfil extends Activity implements Estadistica {
    private ImageView editar,nuevoJuego;
    private TextView nombreJugador, mailJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        nuevoJuego = (ImageView)findViewById(R.id.imageView9);
        nombreJugador = (TextView) findViewById(R.id.textView);
        mailJugador = (TextView) findViewById(R.id.textView2);
        Bundle b = getIntent().getExtras();
        String nombre = b.getString("name");
        final String id = b.getString("id");
        String email = b.getString("mail");
        mailJugador.setText(email);
        nombreJugador.setText(nombre);
        new Estadisticas(this).execute(id);
        editar = (ImageView) findViewById(R.id.imageView2);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiPerfil.this, EditarDatos.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
        nuevoJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiPerfil.this,VentanaDesafios.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mi_perfil, menu);
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

    @Override
    public void refreshEstadistics(ArrayList<String> lista) {

    }
}
