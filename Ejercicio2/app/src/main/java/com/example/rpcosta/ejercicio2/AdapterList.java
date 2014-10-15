package com.example.rpcosta.ejercicio2;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private int resource;
    Context actividad;

    public AdapterList(Context context, int resc, ArrayList<Item> objects) {
        super(context, resc, objects);
        lista = objects;
        resource = resc;
        actividad = context;
        inflater = LayoutInflater.from(context);

    }

    static class ViewHolder {
        TextView title;
        TextView price;
        TextView subTitle;
        TextView quantity;
        public ImageView image;
        public int position;
    }

    private static class ThumbnailTask extends AsyncTask <String,Void,Bitmap>{
        private int mPosition;
        private ViewHolder mHolder;
        private String  url;

        public ThumbnailTask(int position, ViewHolder holder,String miUrl) {
            mPosition = position;
            mHolder = holder;
            url = miUrl;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

                mHolder.image.setImageBitmap(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... url1) {
            try {

                URL newurl = new URL(url);
                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                return  mIcon_val;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

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
            holder.position = position;
            new ThumbnailTask(position, holder,item.getImage())
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            convertView.setTag(holder);
            if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                holder.subTitle = (TextView) convertView.findViewById(R.id.textView3);
                holder.quantity = (TextView) convertView.findViewById(R.id.textView5);
            }
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            new ThumbnailTask(position, holder,item.getImage())
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            convertView.setTag(holder);

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
