package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpcosta.obligatorio1.Interfaces.ChangePass;

import java.util.ArrayList;


public class OlvidoPass extends Activity implements ChangePass {
    private Button enviar;
    EditText newPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvido_pass);
        enviar = (Button) findViewById(R.id.button);
        newPass = (EditText) findViewById(R.id.editText);
        Bundle b = getIntent().getExtras();
        final String id = b.getString("id");
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = newPass.getText().toString();
                if (pass.isEmpty()) {
                    String error = getResources().getString(R.string.mensajeErrorEmpty);
                    Toast msj = Toast.makeText(OlvidoPass.this, error, Toast.LENGTH_SHORT);
                    msj.show();
                } else {
                    ArrayList<String> lista = new ArrayList<String>();
                    lista.add(id);
                    lista.add(pass);
                    new CambioPass(OlvidoPass.this).execute(lista);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.olvido_pass, menu);
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
    public void result(Boolean results) {
        if(results){
            String mensaje = getResources().getString(R.string.cambioPass);
            Toast msj = Toast.makeText(OlvidoPass.this,mensaje,Toast.LENGTH_SHORT);
            msj.show();
            finish();
        }
        else{
            String mensaje = getResources().getString(R.string.nuevoIntento);
            Toast msj = Toast.makeText(OlvidoPass.this,mensaje,Toast.LENGTH_SHORT);
            msj.show();
        }
    }
}
