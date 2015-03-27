package com.example.rpcosta.obligatorio1;

import AsyncTask.Estadisticas;
import DataBase.BaseDatos;
import Interfaces.Estadistica;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;


public class DesafioIndividual extends Activity implements Estadistica {
    private RelativeLayout rotar;
    private ImageView start, fotoPerfil,fotoversus;
    private RotateAnimation rotation;
    private String[] ids;
    private String id;
    private String tipo;
    private BaseDatos db;
    private int degree;
    private SharedPreferences prefs;
    private String versus, challenge;
    private Desafios desafio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio_individual);
        rotar = (RelativeLayout) findViewById(R.id.relativeLayout1);
        fotoPerfil = (ImageView) findViewById(R.id.imageView10);
        fotoversus =  (ImageView) findViewById(R.id.imageView11);
        ids = new String[6];
        Bundle b = getIntent().getExtras();
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        id = b.getString("ids");
        tipo = b.getString("tipo");
        versus = b.getString("versus");
        desafio = (Desafios) b.getSerializable("desafio");
        challenge = b.getString("idChallenge");
        if (id == null) {
            id = prefs.getString("id", null);
        }
        if(desafio!=null){
            if(desafio.getUrl()!=null&&!desafio.getUrl().equalsIgnoreCase("")) {
                fotoversus.setBackground(null);
                Picasso.with(DesafioIndividual.this).load(desafio.getUrl()).into(fotoversus);
            }
        }
        db = new BaseDatos(this);
        String image = db.urlImagen();
        if (!image.equalsIgnoreCase("")) {
            fotoPerfil.setBackground(null);
            Picasso.with(DesafioIndividual.this).load(image).into(fotoPerfil);
        }
        new Estadisticas(this).execute(id);
        start = (ImageView) findViewById(R.id.imageView9);
        final int[] posiciones = {360, 840, 605, 660, 415, 540};
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degree = new Random().nextInt(posiciones.length);
                rotation = new RotateAnimation(0f, posiciones[degree], Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                rotation.setDuration(1600);
                rotation.setRepeatCount(0);
                rotation.setInterpolator(new DecelerateInterpolator());
                rotation.setFillAfter(true);
                rotation.setFillEnabled(true);
                rotar.startAnimation(rotation);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ArrayList<Categorias> lista = db.listaCategorias();
                        switch (posiciones[degree]) {
                            case 360:
                                //Geografía
                                for (int i = 0; i < lista.size(); i++) {
                                    if (lista.get(i).getNombre().equalsIgnoreCase("Geografía")) {
                                        ids[5] = lista.get(i).getId();
                                    }
                                }
                                Intent i = new Intent(DesafioIndividual.this, Geografia.class);
                                String idg = ids[5];
                                i.putExtra("id", idg);
                                i.putExtra("idUser", id);
                                i.putExtra("tipo", tipo);
                                if (versus != null) {
                                    i.putExtra("versus", versus);
                                    i.putExtra("idChallenge", challenge);
                                }
                                if (desafio != null) {
                                    i.putExtra("desafio", desafio);
                                }
                                startActivity(i);
                                rotation.setFillAfter(false);
                                rotation.setFillEnabled(false);
                                break;
                            case 840:
                                // Entretenimiento
                                for (int t = 0; t < lista.size(); t++) {
                                    if (lista.get(t).getNombre().equalsIgnoreCase("Entretenimiento")) {
                                        ids[3] = lista.get(t).getId();
                                    }
                                }
                                Intent in = new Intent(DesafioIndividual.this, Entretenimiento.class);
                                String ide = ids[3];
                                in.putExtra("id", ide);
                                in.putExtra("idUser", id);
                                in.putExtra("tipo", tipo);
                                if (versus != null) {
                                    in.putExtra("versus", versus);
                                    in.putExtra("idChallenge", challenge);
                                }
                                if (desafio != null) {
                                    in.putExtra("desafio", desafio);
                                }
                                startActivity(in);
                                rotation.setFillAfter(false);
                                rotation.setFillEnabled(false);
                                break;
                            case 605:
                                //Deporte
                                for (int t = 0; t < lista.size(); t++) {
                                    if (lista.get(t).getNombre().equalsIgnoreCase("Deportes")) {
                                        ids[0] = lista.get(t).getId();
                                    }
                                }
                                Intent inten = new Intent(DesafioIndividual.this, Deporte.class);
                                String idss = ids[0];
                                inten.putExtra("id", idss);
                                inten.putExtra("idUser", id);
                                inten.putExtra("tipo", tipo);
                                if (versus != null) {
                                    inten.putExtra("versus", versus);
                                    inten.putExtra("idChallenge", challenge);
                                }
                                if (desafio != null) {
                                    inten.putExtra("desafio", desafio);
                                }
                                startActivity(inten);
                                rotation.setFillAfter(false);
                                rotation.setFillEnabled(false);
                                break;
                            case 660:
                                //Historia
                                for (int t = 0; t < lista.size(); t++) {
                                    if (lista.get(t).getNombre().equalsIgnoreCase("Historia")) {
                                        ids[2] = lista.get(t).getId();
                                    }
                                }
                                Intent intent = new Intent(DesafioIndividual.this, Historia.class);
                                String idh = ids[2];
                                intent.putExtra("id", idh);
                                intent.putExtra("idUser", id);
                                intent.putExtra("tipo", tipo);
                                if (versus != null) {
                                    intent.putExtra("versus", versus);
                                    intent.putExtra("idChallenge", challenge);
                                }
                                if (desafio != null) {
                                    intent.putExtra("desafio", desafio);
                                }
                                startActivity(intent);
                                rotation.setFillAfter(false);
                                rotation.setFillEnabled(false);
                                break;
                            case 415:
                                //Ciencia
                                for (int t = 0; t < lista.size(); t++) {
                                    if (lista.get(t).getNombre().equalsIgnoreCase("Ciencia y Tecnología")) {
                                        ids[1] = lista.get(t).getId();
                                    }
                                }
                                Intent c = new Intent(DesafioIndividual.this, Ciencia.class);
                                String idc = ids[1];
                                c.putExtra("id", idc);
                                c.putExtra("idUser", id);
                                c.putExtra("tipo", tipo);
                                if (versus != null) {
                                    c.putExtra("versus", versus);
                                    c.putExtra("idChallenge", challenge);
                                }
                                if (desafio != null) {
                                    c.putExtra("desafio", desafio);
                                }
                                startActivity(c);
                                rotation.setFillAfter(false);
                                rotation.setFillEnabled(false);
                                break;
                            case 540:
                                //Arte
                                for (int t = 0; t < lista.size(); t++) {
                                    if (lista.get(t).getNombre().equalsIgnoreCase("Arte y Literatura")) {
                                        ids[4] = lista.get(t).getId();
                                    }
                                }
                                Intent inte = new Intent(DesafioIndividual.this, Arte.class);
                                String ida = ids[4];
                                inte.putExtra("id", ida);
                                inte.putExtra("idUser", id);
                                inte.putExtra("tipo", tipo);
                                if (versus != null) {
                                    inte.putExtra("versus", versus);
                                    inte.putExtra("idChallenge", challenge);
                                }
                                if (desafio != null) {
                                    inte.putExtra("desafio", desafio);
                                }
                                startActivity(inte);
                                rotation.setFillAfter(false);
                                rotation.setFillEnabled(false);
                                break;
                        }
                    }
                }, 2000);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.desafio_individual, menu);
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
    public void onBackPressed() {
        Intent i = new Intent(DesafioIndividual.this, MiPerfil.class);
        startActivity(i);
    }

    @Override
    public void refreshEstadistics(ArrayList<ObjetoEstadisticas> lista) {

        for (int i = 0; i < lista.size(); i++) {
            String cat = lista.get(i).getNombreCategoria();
            if (cat.equalsIgnoreCase("Deportes")) {
                ids[0] = lista.get(i).getId();


            } else if (cat.equalsIgnoreCase("Ciencia y Tecnología")) {
                ids[1] = lista.get(i).getId();

            } else if (cat.equalsIgnoreCase("Historia")) {
                ids[2] = lista.get(i).getId();

            } else if (cat.equalsIgnoreCase("Entretenimiento")) {
                ids[3] = lista.get(i).getId();

            } else if (cat.equalsIgnoreCase("Arte y Literatura")) {
                ids[4] = lista.get(i).getId();

            } else if (cat.equalsIgnoreCase("Geografía")) {
                ids[5] = lista.get(i).getId();

            }
        }

    }
}
