package com.example.rpcosta.ejercicio1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyActivity extends Activity {
    private Button search;
    private EditText texto;
    private String buscar;
    private Toast mensaje;
    private ListView lista;
    private ListAdapter adapter;
    private ArrayList<Item>listaItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        search = (Button) findViewById(R.id.button);
        lista = (ListView)findViewById(R.id.listView);
        texto = (EditText) findViewById(R.id.editText);
        listaItems=new ArrayList<Item>();
        adapter = new ListAdapter(this, R.id.listView, this.listaItems);
        this.lista.setAdapter(adapter);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);

        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().
                heightPixels)
        {
            Toast.makeText(this,"Screen switched to Landscape mode",Toast.LENGTH_SHORT).
                    show();
        }
        else
        {
            Toast.makeText(this,"Screen switched to Portrait mode",Toast.LENGTH_SHORT).
                    show();
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar = texto.getText().toString();
                if (buscar.isEmpty()) {//Agregar validación numérica
                    mensaje = Toast.makeText(MyActivity.this, "No puede estar vacío el campo", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                else{
                    GetList list = new GetList(MyActivity.this,buscar);
                    list.start();
                }

            }
        });

    }



    //Metodo que refresca la vista
    public void refreshList(final ArrayList<Item> items)
    {
        this.listaItems = items;
        runOnUiThread(new Runnable() {
            public void run() {
                adapter = new ListAdapter(MyActivity.this, R.id.listView, items);
                lista.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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
}
