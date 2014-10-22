package com.example.rpcosta.ejercicio2.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rpcosta.ejercicio2.Item;
import com.example.rpcosta.ejercicio2.ManejadorImagenes;
import com.example.rpcosta.ejercicio2.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpcosta on 13/10/14.
 */
public class AdapterList extends ArrayAdapter<Item> {

    private ArrayList<Item> lista;
    private LayoutInflater inflater;
    private ManejadorImagenes manejador;
    Context actividad;

    public AdapterList(Context context, int resc, ArrayList<Item> objects) {
        super(context, resc, objects);
        lista = objects;
        actividad = context;
        inflater = LayoutInflater.from(context);
        manejador = ManejadorImagenes.getInstance();

    }

   static class ViewHolder {
        TextView title;
        TextView price;
        TextView subTitle;
        TextView quantity;
        public ImageView image;

    }



    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = lista.get(position);
        ViewHolder holder=null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_list, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textView2);
            holder.price = (TextView) convertView.findViewById(R.id.textView4);
            holder.image = (ImageView)convertView.findViewById(R.id.imageView);
            manejador.setImagenes(item.getImage(),holder.image);
            convertView.setTag(holder);
            if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                holder.subTitle = (TextView) convertView.findViewById(R.id.textView3);
                holder.quantity = (TextView) convertView.findViewById(R.id.textView5);
            }
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            manejador.setImagenes(item.getImage(),holder.image);

        }
        holder.title.setText(item.getTitle());
        holder.price.setText("Precio: " + item.getPrice());
        if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            holder.subTitle.setText(item.getSubtitle());
            holder.quantity.setText("Cantidad: " + item.getQuantity());
        }
        return convertView;

    }



}
