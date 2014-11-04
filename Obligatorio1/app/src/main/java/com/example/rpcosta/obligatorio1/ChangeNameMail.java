package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rpcosta.obligatorio1.AsyncTask.CambiarNombreMail;
import com.example.rpcosta.obligatorio1.Interfaces.CambiarNomMail;

import java.util.ArrayList;


public class ChangeNameMail extends Activity implements CambiarNomMail {
    EditText name, mail;
    Button continuar;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name_mail);
        name = (EditText) findViewById(R.id.editText);
        mail = (EditText) findViewById(R.id.editText2);
        continuar = (Button) findViewById(R.id.button);
        Bundle b = getIntent().getExtras();
        final String id = b.getString("id");
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = name.getText().toString();
                String email = mail.getText().toString();
                if (nombre.isEmpty() || email.isEmpty()) {
                    String mensaje = getResources().getString(R.string.mensajeErrorEmpty);
                    Toast msj = Toast.makeText(ChangeNameMail.this, mensaje, Toast.LENGTH_SHORT);
                    msj.show();
                } else {
                    ArrayList<String> lista = new ArrayList<String>();
                    lista.add(id);
                    lista.add(name.getText().toString());
                    lista.add(mail.getText().toString());
                    dialog = ProgressDialog.show(ChangeNameMail.this, "", "Realizando el cambio...", true);
                    new CambiarNombreMail(ChangeNameMail.this).execute(lista);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.change_name_mail, menu);
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
    public void resultado(Boolean done) {
        if (done) {
            dialog.dismiss();
            finish();
        } else {
            String mensaje = getResources().getString(R.string.nuevoIntento);
            Toast msj = Toast.makeText(ChangeNameMail.this, mensaje, Toast.LENGTH_SHORT);
            msj.show();
        }

    }
}
