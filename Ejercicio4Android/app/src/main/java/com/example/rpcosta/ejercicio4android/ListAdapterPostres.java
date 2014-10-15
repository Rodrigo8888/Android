package com.example.rpcosta.ejercicio4android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpcosta on 13/10/14.
 */
public class ListAdapterPostres extends ArrayAdapter<Postre> {
    private Context actividad;
    private ArrayList<Postre> lista;
    public ListAdapterPostres(Context context, int resource, ArrayList<Postre> objects) {
        super(context, resource, objects);
        actividad=context;
        lista=objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Postre p = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context. LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listpostres, parent, false);
        TextView nombre = (TextView)rowView.findViewById(R.id.textView);
        nombre.setText(p.getNombre());

        return rowView; }

}
