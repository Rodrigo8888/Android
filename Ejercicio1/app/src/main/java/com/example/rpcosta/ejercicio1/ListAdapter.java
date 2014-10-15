package com.example.rpcosta.ejercicio1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Item> {
    private final Context contexto;
    private final ArrayList<Item> values;


    public ListAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, R.layout.lista, objects);
        this.contexto=context;
        this.values = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = values.get(position);
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context. LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.lista, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView);
        textView1.setText(item.getTitle());
        ImageView imagen = (ImageView)rowView.findViewById((R.id.imageView));
        ImageLoader.getInstance().displayImage(item.getImage(), imagen);

        return rowView; }

}
