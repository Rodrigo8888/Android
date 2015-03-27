package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import AsyncTask.ValidacionLogin;
import Interfaces.ValUsuario;

import java.util.ArrayList;


public class MyActivity extends Activity implements ValUsuario {

    //Controles
    private EditText usuario, contraseña;
    private Button ingresar, registro;
    private Toast mensaje;
    private Dialog dialog;

    //Variables
    String mail;
    String pass;
    String mensajeError;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        dialog = null;
        usuario = (EditText) findViewById(R.id.editText);
        contraseña = (EditText) findViewById(R.id.editText2);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(usuario.getWindowToken(), 0);
        usuario.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() ==       KeyEvent.KEYCODE_ENTER) {

                    return false;
                }
                else if(keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK){

                }
                return false;
            }
        });
        InputMethodManager nnm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        nnm.hideSoftInputFromWindow(contraseña.getWindowToken(), 0);
        contraseña.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() ==       KeyEvent.KEYCODE_ENTER) {

                    return false;
                }
                else if(keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK){

                }
                return false;
            }
        });
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
                    dialog = ProgressDialog.show(MyActivity.this, "", "Iniciando...", true);
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
        dialog.dismiss();
        if (correcto) {
            id = ide;
            Intent i = new Intent(MyActivity.this, MiPerfil.class);
            i.putExtra("name", name);
            i.putExtra("id", id);
            i.putExtra("mail", mail);
            startActivity(i);
            finish();
        } else {
            mensajeError = getResources().getString(R.string.errorLogin);
            mensaje = Toast.makeText(MyActivity.this, mensajeError, Toast.LENGTH_SHORT);
            mensaje.show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


