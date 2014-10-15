package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;




public class MyActivity extends Activity {

    //Controles
    private EditText usuario,contraseña;
    private Button ingresar,olvido,registro;
    private Toast mensaje;

    //Variables
    String user;
    String pass;
    String mensajeError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        usuario =(EditText)findViewById(R.id.editText);
        contraseña=(EditText)findViewById(R.id.editText2);
        olvido=(Button)findViewById(R.id.button2);
        registro=(Button)findViewById(R.id.button3);
        ingresar=(Button)findViewById(R.id.button);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user=usuario.getText().toString();
                pass=contraseña.getText().toString();
                //Validación de datos de ingreso
                if(user.isEmpty()||pass.isEmpty()){
                    mensajeError=getResources().getString(R.string.mensajeErrorEmpty);
                    mensaje = Toast.makeText(MyActivity.this,mensajeError,Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                else{
                    Intent i = new Intent(MyActivity.this,VentanaHome.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyActivity.this,VentanaRegistroNombre.class);
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
}
