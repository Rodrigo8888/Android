package com.example.rpcosta.ejercicio4android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by rpcosta on 13/10/14.
 */
public class ListAdapterComida extends ArrayAdapter<Comida> implements Filterable{
    private Context actividad;
    private ArrayList<Comida> lista;
    public ListAdapterComida(Context context, int resource, ArrayList<Comida> objects) {
        super(context, resource, objects);
        actividad = context;
        lista = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Comida c = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context. LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list, parent, false);
        TextView nombre = (TextView)rowView.findViewById(R.id.textView);
        nombre.setText(c.getNombre());

        return rowView; }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
}
