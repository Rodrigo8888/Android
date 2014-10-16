package com.example.rpcosta.ejercicioextra;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MyActivity extends Activity {
    FragmentVista f;
    ArrayList<Imagen>lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        lista = new ArrayList<Imagen>();
        Imagen imagen1 = new Imagen("Imagen1",true,R.drawable.unnamed);
        Imagen imagen2 = new Imagen("Imagen2",true,R.drawable.unnamed1);
        Imagen imagen3 = new Imagen(null,false,R.drawable.unnamed2);
        Imagen imagen4 = new Imagen(null,false,R.drawable.unnamed3);
        Imagen imagen5 = new Imagen(null,false,R.drawable.unnamed4);
        Imagen imagen6 = new Imagen("Imagen1",true,R.drawable.unnamed5);
        lista.add(imagen1);
        lista.add(imagen2);
        lista.add(imagen3);
        lista.add(imagen4);
        lista.add(imagen5);
        lista.add(imagen6);
        f = new FragmentVista(this,lista);
        getFragmentManager().beginTransaction().add(R.id.receptor, f).commit();
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
