package com.example.rpcosta.obligatorio1;

import AsyncTask.ObtenerDesafio;
import DataBase.BaseDatos;
import Interfaces.Desafio;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class VentanaDesafios extends Activity implements Desafio {
    private Button desafioInd, desafioVs;
    private ListView listaDes;
    private BaseDatos bd;
    private String challenge;
    private ArrayList<Desafios> listaDesafios;
    private ArrayList<Desafios> listaBD;
    private String ides;
    private static String TIPO_RANDOM = "random";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_desafios);
        desafioInd = (Button) findViewById(R.id.button);
        desafioVs = (Button) findViewById(R.id.buttons);
        listaDes = (ListView) findViewById(R.id.listView1);
        bd = new BaseDatos(this);
        listaBD = bd.listaDesafios();
        Bundle b = getIntent().getExtras();
        ides = b.getString("ids");
        challenge = b.getString("idChallenge");
        
        new ObtenerDesafio(this).execute(ides);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        for (int i = 0; i < listaBD.size(); i++) {
            ArrayList<Questions> listaPreguntas = bd.obtenerPreguntas(listaBD.get(i));
            listaBD.get(i).setQuestions(listaPreguntas);
        }
        desafioInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VentanaDesafios.this, ElegirDesafio.class);
                i.putExtra("ids", ides);
                startActivity(i);
            }
        });
        listaDes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(VentanaDesafios.this, DesafioIndividual.class);
                i.putExtra("tipo", TIPO_RANDOM);
                i.putExtra("ids", ides);
                i.putExtra("versus", "versus");
                if(challenge!=null){
                    i.putExtra("idChallenge", challenge);  
                }
                else{
                    i.putExtra("idChallenge", listaDesafios.get(position).getId());
                }
                i.putExtra("desafio", listaDesafios.get(position));
                startActivity(i);
            }
        });
        desafioVs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VentanaDesafios.this, JugadorVersus.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ventana_desafios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent i = new Intent(VentanaDesafios.this,MiPerfil.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void obtenerDesafio(ArrayList<Desafios> lista) {
        listaBD = bd.listaDesafios();
        for(int i = 0;i<listaBD.size();i++){
           listaBD.get(i).setQuestions(bd.obtenerPreguntas(listaBD.get(i))); 
        }
        if (lista != null && lista.size()>0) {
            listaDesafios = lista;
            if (listaBD.size() <= listaDesafios.size() && listaDesafios.get(0).getQuestions().size()!=0) {
                if (listaBD.size() < listaDesafios.size()) {
                    ArrayList<Desafios> nuevos = new ArrayList<Desafios>();
                    for (int i = 0; i < listaDesafios.size(); i++) {
                        if (!listaDesafios.get(i).getStatus().equalsIgnoreCase("finished")) {
                            nuevos.add(listaDesafios.get(i));
                        }
                    }
                    AdapterList adapter = new AdapterList(VentanaDesafios.this, R.id.listView1, nuevos);
                    if (listaDes.getAdapter() == null) {
                        listaDes.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Boolean agregar = false;
                    ArrayList<Desafios> listaJugando = new ArrayList<Desafios>();
                    for (int i = 0; i < listaDesafios.size(); i++) {
                        if (!listaDesafios.get(i).getQuestions().get(listaDesafios.get(i).getQuestions().size() - 1).getId_user().equalsIgnoreCase(ides) && listaDesafios.get(i).getStatus().equalsIgnoreCase("pending")) {
                            listaJugando.add(listaDesafios.get(i));
                            agregar = true;
                        }
                    }
                    if (agregar) {
                        AdapterList adapter = new AdapterList(VentanaDesafios.this, R.id.listView1, listaJugando);
                        listaDes.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                }

            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(VentanaDesafios.this, MiPerfil.class);
        startActivity(i);
    }
}
