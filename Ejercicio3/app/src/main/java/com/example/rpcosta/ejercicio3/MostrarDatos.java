package com.example.rpcosta.ejercicio3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MostrarDatos extends Activity {
    private TextView nombre, mail, telefono, pais;
    private Button agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        Bundle b = getIntent().getExtras();
        Cliente c1 = (Cliente)b.getSerializable("Cliente");

        System.out.println(c1.getNombre());

        nombre = (TextView)findViewById(R.id.textView3);
        nombre.setText(c1.getNombre());
        mail = (TextView)findViewById(R.id.textView4);
        telefono = (TextView)findViewById(R.id.textView5);
        pais = (TextView)findViewById(R.id.textView6);


        mail.setText(c1.getMail());
        telefono.setText(String.valueOf(c1.getTelefono()));
        pais.setText(c1.getPais());

        agregar= (Button)findViewById(R.id.button2);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MostrarDatos.this,Registro.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mostrar_datos, menu);
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
