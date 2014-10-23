package com.example.rpcosta.ejercicio4;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by rpcosta on 22/10/14.
 */
public class Fragment1 extends Fragment {
    private Button buscar;
    private EditText query;
    private static String Cquery = "query";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1,container,false);
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        String busqueda = prefs.getString(Cquery, null);
        query = (EditText) rootView.findViewById(R.id.editText1);
        if(busqueda!=null){
            query.setText(busqueda);
        }
        buscar = (Button) rootView.findViewById(R.id.button1);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (query.getText().toString().isEmpty()) {
                    Toast msj = Toast.makeText(MyActivity.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT);
                    msj.show();
                } else {
                    editor.putString(Cquery,query.getText().toString());
                    editor.commit();
                    Intent i = new Intent(MyActivity.this, Resultados.class);
                    i.putExtra(Cquery,query.getText().toString());
                    startActivity(i);

                }
            }
        });

        return rootView;
    }
}
