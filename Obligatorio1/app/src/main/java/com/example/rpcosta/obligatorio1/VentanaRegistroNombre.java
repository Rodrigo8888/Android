package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;import android.content.Intent;



public class VentanaRegistroNombre extends Activity {
    //Controles
    private EditText name;
    private Button continuar;
    private Toast mensaje;

    //Variables
    private String nombre;
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        name = (EditText)findViewById(R.id.editText);
        continuar=(Button)findViewById(R.id.button);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre=name.getText().toString();
                error = getResources().getString(R.string.mensajeErrorEmpty);
                if(nombre.isEmpty()){
                    mensaje=Toast.makeText(VentanaRegistroNombre.this,error,Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                else{
                    Intent i = new Intent(VentanaRegistroNombre.this,VentanaRegistroMail.class);
                    i.putExtra("Nombre",nombre);
                    startActivity(i);

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ventana_registro, menu);
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
}
