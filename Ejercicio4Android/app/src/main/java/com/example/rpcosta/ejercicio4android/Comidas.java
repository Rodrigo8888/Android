package com.example.rpcosta.ejercicio4android;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by rpcosta on 13/10/14.
 */
public class Comidas extends Fragment {
    ListAdapterComida adapter;
    ArrayList<Comida> lista;

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comidas, container, false);
        lista = new ArrayList<Comida>();
        Comida c = new Comida();
        c.setNombre("Receta de Ravioles con Tuco");
        c.setDescripciçon("3 vasos de leche, un huevo duro y un pedazo de pan");
        Comida c1 = new Comida();
        c1.setNombre("Receta de Tallarines con Tuco");
        c1.setDescripciçon("1 vaso con agua, harina, pan casero");
        Comida c2 = new Comida();
        c2.setNombre("Receta de Lasagna");
        c2.setDescripciçon("Masa hojaldrada, jamon, queso, carne");
        lista.add(c);
        lista.add(c1);
        lista.add(c2);
        ListView listaC = (ListView)rootView.findViewById(R.id.listView);
        adapter = new ListAdapterComida(this.context, R.id.listView, this.lista);
        listaC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent i = new Intent(context,ResultadosComida.class);
                i.putExtra("Comida",lista.get(arg2));
                startActivity(i);
            }
        });
        return rootView;

    }

}
