package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ChangeNameMail extends Activity {
    EditText name, mail;
    Button continuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name_mail);
        name = (EditText)findViewById(R.id.editText);
        mail = (EditText)findViewById(R.id.editText2);
        continuar = (Button)findViewById(R.id.button);
        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = name.getText().toString();
                String email = mail.getText().toString();
                if(nombre.isEmpty()||email.isEmpty()){
                    String mensaje = getResources().getString(R.string.mensajeErrorEmpty);
                    Toast msj = Toast.makeText(ChangeNameMail.this,mensaje, Toast.LENGTH_SHORT);
                    msj.show();
                }
                else{

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
}
