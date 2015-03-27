package com.example.rpcosta.obligatorio1;

import AsyncTask.Estadisticas;
import AsyncTask.ObtenerDesafio;
import DataBase.BaseDatos;
import Interfaces.Desafio;
import Interfaces.Estadistica;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MiPerfil extends Activity implements Estadistica, Desafio {
    private ImageView editar, nuevoJuego, fotoPerfil;
    private float totalPreguntas;
    private float totalPreguntasCorrectas;
    private float totalPreguntasIncorrectas;
    private String[] iDs;
    private CheckBox notify;
    private ArrayList<Desafios> listaDesafios;
    private ProgressDialog dialogo = null;
    private RatingBar statistics;
    private BaseDatos db;
    private SharedPreferences prefs;
    private TextView nombreJugador, mailJugador, ciencia, arte, deporte, historia, entretenimiento, geografia, ganados, perdidos, empates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        notify = (CheckBox) findViewById(R.id.checkBox);
        nuevoJuego = (ImageView) findViewById(R.id.imageView9);
        nombreJugador = (TextView) findViewById(R.id.textView);
        statistics = (RatingBar) findViewById(R.id.ratingBar);
        ganados = (TextView) findViewById(R.id.textView14);
        fotoPerfil = (ImageView) findViewById(R.id.imageView);
        empates = (TextView) findViewById(R.id.textView15);
        perdidos = (TextView) findViewById(R.id.textView16);
        db = new BaseDatos(this);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        String image = db.urlImagen();
        notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putString("notificaciones", "true");
                    editor.commit();


                }
                else{
                    editor.putString("notificaciones", "false");
                    editor.commit();

                } 
            }
        });

        if(!image.equalsIgnoreCase("")){
            fotoPerfil.setBackground(null);
            Picasso.with(MiPerfil.this).load(image).into(fotoPerfil);
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
        listaDesafios = new ArrayList<Desafios>();
        totalPreguntas = 0;
        totalPreguntasCorrectas = 0;
        totalPreguntasIncorrectas = 0;
        mailJugador = (TextView) findViewById(R.id.textView2);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String nombre = b.getString("name");
            final String id = b.getString("id");
            String email = b.getString("mail");
            editor.putString("name", nombre);
            editor.putString("id", id);
            editor.putString("mail", email);
            editor.commit();
        }
        if (prefs.getString("name", null) != null && prefs.getString("id", null) != null && prefs.getString("mail", null) != null) {
            nombreJugador.setText(prefs.getString("name", null));
            mailJugador.setText(prefs.getString("mail", null));
            new ObtenerDesafio(this).execute(prefs.getString("id", null));
        }

        dialogo = ProgressDialog.show(MiPerfil.this, "",getResources().getString(R.string.actualizando), true);
        new Estadisticas(this).execute(prefs.getString("id", null));
        editar = (ImageView) findViewById(R.id.imageView2);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiPerfil.this, EditarDatos.class);
                i.putExtra("id", prefs.getString("id", null));
                startActivity(i);
            }
        });
        nuevoJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiPerfil.this, VentanaDesafios.class);
                i.putExtra("ids", prefs.getString("id", null));
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
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshEstadistics(ArrayList<ObjetoEstadisticas> lista) {
        dialogo.dismiss();
        geografia = (TextView) findViewById(R.id.textView6);
        deporte = (TextView) findViewById(R.id.textView8);
        arte = (TextView) findViewById(R.id.textView12);
        historia = (TextView) findViewById(R.id.textView9);
        entretenimiento = (TextView) findViewById(R.id.textView10);
        ciencia = (TextView) findViewById(R.id.textView13);
        iDs = new String[6];
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                //Total preguntas de todas las categorías
                totalPreguntas += lista.get(i).getTotalPreguntas();
                totalPreguntasCorrectas += lista.get(i).getPreguntasCorrectas();
                totalPreguntasIncorrectas += lista.get(i).getPreguntasIncorrectas();
            }
            statistics.setRating((totalPreguntasCorrectas * 5) / totalPreguntas);

            for (int i = 0; i < lista.size(); i++) {
                //Asigno los valores
                String cat = lista.get(i).getNombreCategoria();
                if (cat.equalsIgnoreCase("Deportes")) {
                    int total;
                    if (lista.get(i).getTotalPreguntas() != 0) {
                        total = ((lista.get(i).getPreguntasCorrectas()) * 100) / lista.get(i).getTotalPreguntas();
                    } else {
                        total = 0;
                    }
                    iDs[0] = lista.get(i).getId();
                    deporte.setText(total + "%");

                } else if (cat.equalsIgnoreCase("Ciencia y Tecnología")) {
                    int total1;
                    if (lista.get(i).getTotalPreguntas() != 0) {
                        total1 = ((lista.get(i).getPreguntasCorrectas()) * 100) / lista.get(i).getTotalPreguntas();
                    } else {
                        total1 = 0;
                    }
                    iDs[1] = lista.get(i).getId();
                    ciencia.setText(total1 + "%");

                } else if (cat.equalsIgnoreCase("Historia")) {
                    int total2;
                    if (lista.get(i).getTotalPreguntas() != 0) {
                        total2 = ((lista.get(i).getPreguntasCorrectas()) * 100) / lista.get(i).getTotalPreguntas();
                    } else {
                        total2 = 0;
                    }
                    iDs[2] = lista.get(i).getId();
                    historia.setText(total2 + "%");

                } else if (cat.equalsIgnoreCase("Entretenimiento")) {
                    int total3;
                    if (lista.get(i).getTotalPreguntas() != 0) {
                        total3 = ((lista.get(i).getPreguntasCorrectas()) * 100) / lista.get(i).getTotalPreguntas();
                    } else {
                        total3 = 0;
                    }
                    iDs[3] = lista.get(i).getId();
                    entretenimiento.setText(total3 + "%");

                } else if (cat.equalsIgnoreCase("Arte y Literatura")) {
                    int total4;
                    if (lista.get(i).getTotalPreguntas() != 0) {
                        total4 = ((lista.get(i).getPreguntasCorrectas()) * 100) / lista.get(i).getTotalPreguntas();
                    } else {
                        total4 = 0;
                    }
                    iDs[4] = lista.get(i).getId();
                    arte.setText(total4 + "%");

                } else if (cat.equalsIgnoreCase("Geografía")) {
                    int total5;
                    if (lista.get(i).getTotalPreguntas() != 0) {
                        total5 = ((lista.get(i).getPreguntasCorrectas()) * 100) / lista.get(i).getTotalPreguntas();
                    } else {
                        total5 = 0;
                    }
                    iDs[5] = lista.get(i).getId();
                    geografia.setText(total5 + "%");

                }
            }
        }


    }

    @Override
    public void obtenerDesafio(ArrayList<Desafios> lista) {
        if (lista != null) {
            int ganadas = 0;
            int perdidas = 0;
            int empatadas = 0;
            listaDesafios = db.listaDesafios();
            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getWinner().equalsIgnoreCase("")) {
                    if (lista.get(i).getWinner().equalsIgnoreCase(prefs.getString("id", null))) {
                        ganadas += 1;

                    } else {
                        if (lista.get(i).getWinner().equalsIgnoreCase("0")) {
                            empatadas += 1;
                        } else {
                            perdidas += 1;
                        }
                    }
                }
            }

            ganados.setText(": " + String.valueOf(ganadas));
            perdidos.setText(": " + String.valueOf(perdidas));
            empates.setText(": " + String.valueOf(empatadas));

        }

    }



}
