package com.example.rpcosta.ejercicio3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;

import java.io.Serializable;
import java.util.ArrayList;

public class MyActivity extends Activity {
    private Cliente c1;
    private Cliente c2;
    private Cliente c3;
    private Cliente c4;
    private ArrayList<Cliente> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        lista = new ArrayList<Cliente>();

        c1=new Cliente();
        c1.setNombre("Ramiro");
        c1.setMail("ramiro@gmail.com");
        c1.setId(1);
        c1.setPais("Uruguay");
        c1.setTelefono(12345678);

        c2=new Cliente();
        c2.setNombre("Gonzalo");
        c2.setMail("gonzalo@gmail.com");
        c2.setId(2);
        c2.setPais("Argentina");
        c2.setTelefono(87654321);

        c3=new Cliente();
        c3.setNombre("Pedro");
        c3.setMail("pedro@gmail.com");
        c3.setId(3);
        c3.setPais("Brasil");
        c3.setTelefono(76512348);

        c4=new Cliente();
        c4.setNombre("Lucas");
        c4.setMail("lucas@gmail.com");
        c4.setId(4);
        c4.setPais("Peru");
        c4.setTelefono(876123678);

        lista.add(c1);
        lista.add(c2);
        lista.add(c3);
        lista.add(c4);

        ListView listview = (ListView)findViewById(R.id.listView);
        listAdapter ad = new listAdapter(this, R.id.listView, lista);
        listview.setAdapter(ad);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Intent i = new Intent(MyActivity.this, MostrarDatos.class);
                    Cliente c = lista.get(arg2);
                    i.putExtra("Cliente",c);
                    startActivity(i);
        } });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
