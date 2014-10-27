package com.example.rpcosta.ejercicio4.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rpcosta.ejercicio4.Asynctask.ManejadorImagenes;
import com.example.rpcosta.ejercicio4.Dominio.Item;
import com.example.rpcosta.ejercicio4.Interfaces.DatosItems;
import com.example.rpcosta.ejercicio4.Interfaces.InterfaceFragment1;
import com.example.rpcosta.ejercicio4.R;

/**
 * Created by rpcosta on 24/10/14.
 */
public class Fragment3 extends Fragment implements DatosItems {
    private Item i;
    private String url;
    private ImageView imagen;
    private View rootView;
    private TextView titulo,subtitulo,precio,cantidad;
    private ManejadorImagenes manejador;
    private InterfaceFragment1 miCtx;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            miCtx = (InterfaceFragment1) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement InterfaceFragment1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Bundle b = getArguments();
        String item = b.getString("item");
        rootView = inflater.inflate(R.layout.fragment3,container,false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        manejador = ManejadorImagenes.getInstance();
        url ="https://api.mercadolibre.com/items/";
        url +=item;
        return rootView;
    }


    @Override
    public void refreshdatos(Item item) {
        titulo = (TextView)rootView.findViewById(R.id.textView1);
        subtitulo = (TextView)rootView.findViewById(R.id.textView2);
        precio = (TextView)rootView.findViewById(R.id.textView3);
        cantidad = (TextView)rootView.findViewById(R.id.textView4);
        imagen = (ImageView) rootView.findViewById(R.id.imageView);
        i = item;
        titulo.setText(i.getTitle());
        if(i.getSubtitle()==null) {
            subtitulo.setText("Sin subtitulo");
        }
        else{
            subtitulo.setText(i.getSubtitle());
        }
        precio.setText("Precio : "+i.getPrice());
        cantidad.setText("Cantidad: "+ i.getAvailable_quantity());
        //Verificar antes si la lista es o no vacia
        if(i.getLista()!=null) {
            manejador.setImagenes(i.getLista().get(0).getUrl(), imagen);
        }
        else{
            manejador.setImagenes(i.getThumbnail(), imagen);
        }
    }


}
