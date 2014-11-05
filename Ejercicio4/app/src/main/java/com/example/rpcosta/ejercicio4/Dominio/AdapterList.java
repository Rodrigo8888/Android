package com.example.rpcosta.ejercicio4.Dominio;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rpcosta.ejercicio4.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by rpcosta on 13/10/14.
 */
public class AdapterList extends ArrayAdapter<Item> {

    Context actividad;
    private ArrayList<Item> lista;
    private LayoutInflater inflater;
    //private ManejadorImagenes manejador;


    public AdapterList(Context context, int resc, ArrayList<Item> objects) {
        super(context, resc, objects);
        lista = objects;
        actividad = context;
        inflater = LayoutInflater.from(context);
        //manejador = ManejadorImagenes.getInstance();

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = lista.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_list, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textView2);
            holder.price = (TextView) convertView.findViewById(R.id.textView4);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            Picasso.with(actividad).load(item.getThumbnail()).into(holder.image);
            //manejador.setImagenes(item.getThumbnail(), holder.image);
            convertView.setTag(holder);
            if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                holder.subTitle = (TextView) convertView.findViewById(R.id.textView3);
                holder.quantity = (TextView) convertView.findViewById(R.id.textView5);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
            Picasso.with(actividad).load(item.getThumbnail()).into(holder.image);
            //manejador.setImagenes(item.getThumbnail(), holder.image);

        }
        holder.title.setText(item.getTitle());
        holder.price.setText("$"+item.getPrice());
        if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (item.getSubtitle() == null) {
                holder.subTitle.setText("Sin subtitulo");
            } else {
                holder.subTitle.setText(item.getSubtitle());
            }

            holder.quantity.setText("Cantidad: " + item.getAvailable_quantity());
        }
        return convertView;

    }

    static class ViewHolder {
        public ImageView image;
        TextView title;
        TextView price;
        TextView subTitle;
        TextView quantity;

    }


}
