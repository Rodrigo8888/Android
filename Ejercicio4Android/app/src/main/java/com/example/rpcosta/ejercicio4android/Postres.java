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
public class Postres extends Fragment {
    private ArrayList<Postre> lista;
    private ListAdapterPostres adapter;

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.postres, container, false);
        lista = new ArrayList<Postre>();
        Postre p = new Postre();
        p.setNombre("Receta de PastaFrola");
        p.setDescripcion("3 vasos de cerveza y una copa de vino");
        Postre p1 = new Postre();
        p1.setNombre("Receta de Margaritas");
        p1.setDescripcion("3 copas de agua y una de licor");
        Postre p2 = new Postre();
        p2.setNombre("Receta de Merengue");
        p2.setDescripcion("3 tazas de arroz y una de fideos");
        lista.add(p);
        lista.add(p1);
        lista.add(p2);
        ListView listaP = (ListView)rootView.findViewById(R.id.listView);
        adapter = new ListAdapterPostres(this.context, R.id.listView, this.lista);

        listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent i = new Intent(context,ResultadosComida.class);
                i.putExtra("Postre",lista.get(arg2));
                startActivity(i);

            }
        });
        return rootView;

    }


}
