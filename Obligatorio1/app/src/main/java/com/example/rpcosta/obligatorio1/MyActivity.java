package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpcosta.obligatorio1.AsyncTask.ValidacionLogin;
import com.example.rpcosta.obligatorio1.Interfaces.ValUsuario;

import java.util.ArrayList;


public class MyActivity extends Activity implements ValUsuario {

    //Controles
    private EditText usuario, contraseña;
    private Button ingresar, registro;
    private Toast mensaje;

    //Variables
    String mail;
    String pass;
    String mensajeError;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        usuario = (EditText) findViewById(R.id.editText);
        contraseña = (EditText) findViewById(R.id.editText2);
        registro = (Button) findViewById(R.id.button3);
        ingresar = (Button) findViewById(R.id.button);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mail = usuario.getText().toString();
                pass = contraseña.getText().toString();
                ArrayList<String> lista = new ArrayList<String>();
                //Validación de datos de ingreso

                if (mail.isEmpty() || pass.isEmpty()) {
                    mensajeError = getResources().getString(R.string.mensajeErrorEmpty);
                    mensaje = Toast.makeText(MyActivity.this, mensajeError, Toast.LENGTH_SHORT);
                    mensaje.show();
                } else {
                    lista.add(mail);
                    lista.add(pass);
                    new ValidacionLogin(MyActivity.this).execute(lista);

                }
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyActivity.this, VentanaRegistroNombre.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
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
    public void validacion(Boolean correcto, String ide, String name, String mail) {
        if (correcto) {
            id=ide;
            Intent i = new Intent(MyActivity.this, MiPerfil.class);
            i.putExtra("name",name);
            i.putExtra("id", id);
            i.putExtra("mail",mail);
            startActivity(i);
            finish();
        } else {
            mensajeError = getResources().getString(R.string.errorLogin);
            mensaje = Toast.makeText(MyActivity.this, mensajeError, Toast.LENGTH_SHORT);
            mensaje.show();
        }
    }
}


