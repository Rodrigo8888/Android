package com.example.rpcosta.ejercicio3;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends ArrayAdapter<Cliente> {
    private final Context context;
    private final ArrayList<Cliente> values;

    public listAdapter(Context context, int listViewId, ArrayList<Cliente> values) {
        super(context, listViewId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fila, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView3);
        TextView textView2 = (TextView) rowView.findViewById(R.id.textView4);

        textView1.setText(values.get(position).getNombre());
        textView2.setText(values.get(position).getMail());


        return rowView;
    }
}