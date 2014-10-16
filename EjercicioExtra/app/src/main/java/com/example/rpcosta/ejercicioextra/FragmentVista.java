package com.example.rpcosta.ejercicioextra;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by rpcosta on 15/10/14.
 */
public class FragmentVista extends Fragment {
    private Context context;
    int id;
    String texto;
    Boolean tieneTexto;
    private ArrayList<Imagen>nuestraLista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.vista, container, false);
        LinearLayout vista1 = (LinearLayout)rootView.findViewById(R.id.imagenes);

        for(int i=0;i<nuestraLista.size();i++){
            ImageButton btnGreen = new ImageButton(rootView.getContext());
            btnGreen.setImageResource(nuestraLista.get(i).getId());
            LinearLayout ll = new LinearLayout(rootView.getContext());
            ll.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


            ll.setGravity(View.TEXT_ALIGNMENT_CENTER);
            ll.addView(btnGreen);
            if(nuestraLista.get(i).getTex()){
                TextView texto = new TextView(rootView.getContext());
                texto.setText(nuestraLista.get(i).getTexto());
                texto.setLayoutParams(param);
                texto.setGravity(Gravity.CENTER);
                ll.addView(texto);
            }
            vista1.addView(ll);
            /*btnGreen.setOnClickListener(mGreenBallOnClickListener);
            btnGreen.setBackgroundColor(Color.TRANSPARENT);
            btnGreen.setTag(i);
            btnGreen.setId(i);*/


        }


        return rootView;

    }


    public FragmentVista(Context context, ArrayList<Imagen> lista) {
        this.context = context;
        this.nuestraLista=lista;
    }
}

