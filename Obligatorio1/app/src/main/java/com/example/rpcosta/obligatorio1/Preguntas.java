package com.example.rpcosta.obligatorio1;

import AsyncTask.PreguntasApi;
import Interfaces.APIPreguntas;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Preguntas extends Activity implements APIPreguntas {
    private TextView timmer, preguntas;
    private Button resp1, resp2, resp3, resp4;
    private String url;
    private String idP, idU;
    private Boolean isClicked;
    private Desafios desafio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        preguntas = (TextView) findViewById(R.id.textView2);
        resp1 = (Button) findViewById(R.id.button);
        resp2 = (Button) findViewById(R.id.button2);
        resp3 = (Button) findViewById(R.id.button3);
        resp4 = (Button) findViewById(R.id.button4);
        isClicked = false;
        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        idU = b.getString("idUser");
        final String tipo = b.getString("tipo");
        final String versus = b.getString("versus");
        final String challenge = b.getString("idChallenge");
        desafio = (Desafios)b.getSerializable("desafio");
        String params = "/questions/get/?" + "id_category=" + id;
        url = "http://ortapipreguntados.herokuapp.com" + params;

        new PreguntasApi(this).execute(url);
        resp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preguntas.this, ResultadoPreguntas.class);
                i.putExtra("idUser", idU);
                i.putExtra("idP", idP);
                i.putExtra("idO", "0");
                i.putExtra("tipo",tipo);
                if(versus!=null) {
                    i.putExtra("versus", versus);
                    i.putExtra("idChallenge",challenge);
                }
                if(desafio!=null){
                    i.putExtra("desafio",desafio);
                }
                isClicked = true;
                i.putExtra("time", isClicked);
                startActivity(i);
            }
        });
        resp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preguntas.this, ResultadoPreguntas.class);
                i.putExtra("idP", idP);
                i.putExtra("idO", "1");
                i.putExtra("idUser", idU);
                i.putExtra("tipo",tipo);
                if(versus!=null) {
                    i.putExtra("versus", versus);
                    i.putExtra("idChallenge",challenge);
                }
                if(desafio!=null){
                    i.putExtra("desafio",desafio);
                }
                isClicked = true;
                i.putExtra("time", isClicked);
                startActivity(i);

            }
        });

        resp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preguntas.this, ResultadoPreguntas.class);
                i.putExtra("idP", idP);
                i.putExtra("idO", "2");
                i.putExtra("idUser", idU);
                i.putExtra("tipo",tipo);
                if(versus!=null) {
                    i.putExtra("versus", versus);
                    i.putExtra("idChallenge",challenge);
                }
                if(desafio!=null){
                    i.putExtra("desafio",desafio);
                }
                isClicked = true;
                i.putExtra("time", isClicked);
                startActivity(i);

            }
        });

        resp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preguntas.this, ResultadoPreguntas.class);
                i.putExtra("idP", idP);
                i.putExtra("idO", "3");
                i.putExtra("idUser", idU);
                i.putExtra("tipo",tipo);
                if(versus!=null) {
                    i.putExtra("versus", versus);
                    i.putExtra("idChallenge",challenge);
                }
                if(desafio!=null){
                    i.putExtra("desafio",desafio);
                }
                isClicked = true;
                i.putExtra("time", isClicked);
                startActivity(i);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preguntas, menu);
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
    public void refreshPreguntas(EstructuraPreguntas pregunta) {

        if (pregunta != null) {
            idP = pregunta.getIdPregunta();
            preguntas.setText(pregunta.getPreguntas());
            resp1.setText(pregunta.getOpciones().get(0));
            resp2.setText(pregunta.getOpciones().get(1));
            resp3.setText(pregunta.getOpciones().get(2));
            resp4.setText(pregunta.getOpciones().get(3));
            timmer = (TextView) findViewById(R.id.textView);

            class MyCount extends CountDownTimer {

                public MyCount(long millisInFuture, long countDownInterval) {
                    super(millisInFuture, countDownInterval);
                }

                @Override
                public void onFinish() {
                    if (!isClicked) {
                        //set time out to the image
                        Intent i = new Intent(Preguntas.this, ResultadoPreguntas.class);
                        i.putExtra("time", isClicked);
                        i.putExtra("idUser", idU);
                        startActivity(i);

                    }
                    else{
                        
                    }

                }

                @Override
                public void onTick(long millisUntilFinished) {
                    timmer.setText(getResources().getString(R.string.time) + ": " + millisUntilFinished / 1000);
                }
            }
            MyCount counter = new MyCount(25000, 1000);
            counter.start();
        }


    }


    @Override
    public void onBackPressed() {

    }
}
