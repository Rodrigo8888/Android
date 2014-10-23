package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpcosta.obligatorio1.Interfaces.RegUsuario;


public class VentanaRegistroPassword extends Activity implements RegUsuario {
    //Controles
    private Button continuar;
    private EditText pass;
    private Toast mensaje;
    //Variables
    private String contraseña;
    private String nombre;
    private String mail;
    private String error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro_password);
        pass = (EditText)findViewById(R.id.editText);

        Bundle b = getIntent().getExtras();
        nombre = b.getString("Nombre");
        mail = b.getString("Mail");
        continuar=(Button)findViewById(R.id.button);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contraseña=pass.getText().toString();
                error = getResources().getString(R.string.mensajeErrorEmpty);
                if(contraseña.isEmpty()){
                    mensaje=Toast.makeText(VentanaRegistroPassword.this,error,Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                else{
                    Jugador j = new Jugador();
                    j.setMail(mail);
                    j.setNombre(nombre);
                    j.setPassword(contraseña);
                    new RegistroUsuario(VentanaRegistroPassword.this).execute(j);
                    Intent i = new Intent(VentanaRegistroPassword.this,MyActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ventana_registro_password, menu);
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
    public void result(String results) {
        error=results;
        mensaje=Toast.makeText(VentanaRegistroPassword.this,error,Toast.LENGTH_SHORT);
        mensaje.show();
    }
}
