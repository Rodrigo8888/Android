package com.example.rpcosta.ejercicio2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity implements Datos {
    private Button buscar;
    private AdapterList adapter;
    private EditText query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        SharedPreferences prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        String busqueda = prefs.getString("query","busqueda");
        query = (EditText) findViewById(R.id.editText1);
        if(!busqueda.equalsIgnoreCase("busqueda")){
            query.setText(busqueda);
        }
        buscar = (Button) findViewById(R.id.button1);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (query.getText().toString().isEmpty()) {
                    Toast msj = Toast.makeText(MyActivity.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT);
                    msj.show();
                } else {
                    editor.putString("query",query.getText().toString());
                    editor.commit();
                    Intent i = new Intent(MyActivity.this, Resultados.class);
                    i.putExtra("query",query.getText().toString());
                    startActivity(i);

                }
            }
        });
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


    @Override
    public void refreshList(ArrayList<Item> items) {

    }
}
