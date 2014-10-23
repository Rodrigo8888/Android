package com.example.rpcosta.ejercicio2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rpcosta.ejercicio2.Adapter.AdapterList;
import com.example.rpcosta.ejercicio2.Asynctask.Busqueda;
import com.example.rpcosta.ejercicio2.Interfaces.Datos;

import java.util.ArrayList;


public class Resultados extends Activity implements Datos {


    private AdapterList adapter;
    private ArrayList<Item> lista;
    private ListView miLista;
    private String query;
    private int offset;
    private static final int max =15;
    private String paginas = "&limit=15";
    private static String listQuery = "Lista";
    private String url;
    ProgressDialog dialogo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        offset = 0;
        url = "https://api.mercadolibre.com/sites/MLA/search?q=";
        setContentView(R.layout.activity_resultados);
        Bundle b = getIntent().getExtras();
        lista = new ArrayList<Item>();
        query = b.getString("query");
        miLista = (ListView) findViewById(R.id.listView1);
        adapter = new AdapterList(Resultados.this, R.id.listView1, lista);
        miLista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= offset + max) {
                    dialogo = ProgressDialog.show(Resultados.this, "", "Buscando...", true);
                    url = "https://api.mercadolibre.com/sites/MLA/search?q=";
                    offset += max;
                    if(miLista.getAdapter()==null) {
                        miLista.setAdapter(adapter);
                    }
                    url += query + paginas + "&offset=" + offset;
                    new Busqueda(Resultados.this).execute(url);
                }
            }
        });

        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Resultados.this,Descripcion.class);
                i.putExtra("id",lista.get(position).getIdentificador());
                startActivity(i);
            }
        });

        if (savedInstanceState != null) {
            EditText texto = (EditText) findViewById(R.id.editText1);
            miLista = (ListView) findViewById(R.id.listView1);
            lista = (ArrayList<Item>) savedInstanceState.getSerializable(listQuery);
            adapter = new AdapterList(Resultados.this, R.id.listView1, lista);
            miLista.setAdapter(adapter);

        } else {
            lista = new ArrayList<Item>();
            adapter = new AdapterList(Resultados.this, R.id.listView1, lista);
            url += query + paginas + "&offset=" + offset;
            miLista = (ListView) findViewById(R.id.listView1);
            if(miLista.getAdapter()==null) {
                miLista.setAdapter(adapter);
            }
            dialogo = ProgressDialog.show(Resultados.this, "", "Buscando...", true);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(listQuery, lista);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void refreshList(final ArrayList<Item> items) {
        dialogo.dismiss();
        if (lista != null) {
            lista.addAll(items);
        }
        else{
            lista = items;
        }
        if(miLista.getAdapter()==null) {
            miLista.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

}
