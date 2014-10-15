package com.example.rpcosta.ejercicio2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Resultados extends Activity implements Datos {


    private AdapterList adapter;
    private ArrayList<Item> lista;
    private ListView miLista;
    private String query;
    Button siguiente, atras;
    private int offset;
    private String paginas="&limit=15";
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offset=0;
        url= "https://api.mercadolibre.com/sites/MLA/search?q=";
        setContentView(R.layout.activity_resultados);
        Bundle b = getIntent().getExtras();
        query = b.getString("query");
        siguiente=(Button)findViewById(R.id.button5);
        atras = (Button)findViewById(R.id.button4);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url= "https://api.mercadolibre.com/sites/MLA/search?q=";
                offset +=15;
                miLista = (ListView) findViewById(R.id.listView1);
                lista = new ArrayList<Item>();
                adapter = new AdapterList(Resultados.this,R.id.listView1,lista);
                miLista.setAdapter(adapter);
                url += query+paginas+"&offset="+offset;
                adapter = new AdapterList(Resultados.this, R.id.listView1, lista);
                new Busqueda(Resultados.this).execute(url);

            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offset>=15) {
                    url= "https://api.mercadolibre.com/sites/MLA/search?q=";
                    offset -= 15;
                    miLista = (ListView) findViewById(R.id.listView1);
                    lista = new ArrayList<Item>();
                    adapter = new AdapterList(Resultados.this,R.id.listView1,lista);
                    miLista.setAdapter(adapter);
                    url += query+paginas+"&offset="+offset;
                    adapter = new AdapterList(Resultados.this, R.id.listView1, lista);
                    new Busqueda(Resultados.this).execute(url);

                }
                else {
                    Toast msj = Toast.makeText(Resultados.this,"No hay más páginas atrás",Toast.LENGTH_SHORT);
                    msj.show();
                }
            }
        });

        if (savedInstanceState != null) {
            EditText texto = (EditText)findViewById(R.id.editText1);
            miLista = (ListView) findViewById(R.id.listView1);
            lista = (ArrayList<Item>) savedInstanceState.getSerializable("Lista");
            adapter = new AdapterList(this, R.id.listView1, this.lista);
            this.miLista.setAdapter(adapter);

        } else {
            url += query+paginas+"&offset="+offset;
            // Probably initialize members with default values for a new instance
            miLista = (ListView) findViewById(R.id.listView1);
            lista = new ArrayList<Item>();
            adapter = new AdapterList(this, R.id.listView1, this.lista);
            this.miLista.setAdapter(adapter);
            adapter = new AdapterList(Resultados.this, R.id.listView1, lista);
            new Busqueda(this).execute(url);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resultados, menu);
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
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("Lista", lista);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void refreshList(final ArrayList<Item> items) {
        lista = items;
        adapter = new AdapterList(Resultados.this, R.id.listView1, items);
        miLista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
