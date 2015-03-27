package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Arte extends Activity {
    private Button jugar;
    private Desafios desafio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arte);
        Bundle b = getIntent().getExtras();
        final String id = b.getString("id");
        final String idU = b.getString("idUser");
        final String tipo = b.getString("tipo");
        final String versus = b.getString("versus");
        final String challenge = b.getString("idChallenge");
        desafio = (Desafios) b.getSerializable("desafio");
        jugar = (Button) findViewById(R.id.button2);
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Arte.this, Preguntas.class);
                i.putExtra("id", id);
                i.putExtra("idUser", idU);
                if (versus != null) {
                    i.putExtra("versus", versus);
                    i.putExtra("idChallenge", challenge);
                }
                if (desafio != null) {
                    i.putExtra("desafio", desafio);
                }
                i.putExtra("tipo", tipo);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.arte, menu);
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

    }
}
