package com.example.rpcosta.ejerciciocaminos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity extends Activity {
    private EditText melix,meliy,cafex,cafey,golosinasx,golosinasy,chicasx,chicasy,chicasxx,chicasyy,jefex,jefey,jefexx,jefeyy;
    private int meliCx,meliCy,cafeCx,cafeCy,golCx,golCy,chicasCx,chicasCy,chicasCxx,chicasCyy,jefeCx,jefeCy,jefeCxx,jefeCyy;
    private Button buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Integrando la view

        melix = (EditText) findViewById(R.id.editText1);
        meliy = (EditText) findViewById(R.id.editText2);
        cafex = (EditText) findViewById(R.id.editText3);
        cafey = (EditText) findViewById(R.id.editText4);
        golosinasx = (EditText) findViewById(R.id.editText5);
        golosinasy = (EditText) findViewById(R.id.editText6);
        chicasx = (EditText) findViewById(R.id.editText7);
        chicasy = (EditText) findViewById(R.id.editText8);
        chicasxx = (EditText) findViewById(R.id.editText9);
        chicasyy = (EditText) findViewById(R.id.editText10);
        jefex = (EditText) findViewById(R.id.editText11);
        jefey = (EditText) findViewById(R.id.editText12);
        jefexx = (EditText) findViewById(R.id.editText13);
        jefeyy = (EditText) findViewById(R.id.editText14);
        /*buscar = (Button)findViewById(R.id.button);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Datos ingresados

                meliCx = Integer.parseInt(melix.getText().toString());
                meliCy = Integer.parseInt(melix.getText().toString());
                cafeCx = Integer.parseInt(cafex.getText().toString());
                cafeCy = Integer.parseInt(cafey.getText().toString());
                golCx = Integer.parseInt(golosinasx.getText().toString());
                golCy = Integer.parseInt(golosinasy.getText().toString());
                chicasCx = Integer.parseInt(chicasx.getText().toString());
                chicasCy = Integer.parseInt(chicasy.getText().toString());
                chicasCxx = Integer.parseInt(chicasxx.getText().toString());
                chicasCyy = Integer.parseInt(chicasyy.getText().toString());
                jefeCx = Integer.parseInt(jefex.getText().toString());
                jefeCy = Integer.parseInt(jefey.getText().toString());
                jefeCxx = Integer.parseInt(jefexx.getText().toString());
                jefeCyy = Integer.parseInt(jefeyy.getText().toString());

                //Validaciones
                if((cafeCx > meliCx || golCx > meliCx || chicasCx > meliCx || chicasCxx > meliCx || jefeCx > meliCx || jefeCxx > meliCx)||(cafeCy > meliCy || golCy > meliCy || chicasCy > meliCy || chicasCyy > meliCy || jefeCy > meliCy || jefeCyy > meliCy)||(String.valueOf(meliCx).isEmpty())||(String.valueOf(meliCy).isEmpty())||(String.valueOf(cafeCx).isEmpty())||(String.valueOf(cafeCy).isEmpty())||(String.valueOf(golCx).isEmpty())||(String.valueOf(golCy).isEmpty())||(String.valueOf(chicasCx).isEmpty())||(String.valueOf(chicasCxx).isEmpty())||(String.valueOf(chicasCy).isEmpty())||(String.valueOf(chicasCyy).isEmpty())||(String.valueOf(jefeCx).isEmpty())||(String.valueOf(jefeCy).isEmpty())||(String.valueOf(jefeCxx).isEmpty())||(String.valueOf(jefeCyy).isEmpty())){
                    Toast msj = Toast.makeText(MyActivity.this,"Ninguna coordenada puede superar las coordenadas de Meli, ni puede estar vacía",Toast.LENGTH_SHORT);
                    msj.show();
                }
                else{

                }

            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
