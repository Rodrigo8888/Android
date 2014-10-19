package com.example.rpcosta.ejercicio2;

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
    private LruCache<String, Bitmap> mMemoryCache;
    Context actividad;

    public AdapterList(Context context, int resc, ArrayList<Item> objects) {
        super(context, resc, objects);
        lista = objects;
        resource = resc;
        actividad = context;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

    }

    static class ViewHolder {
        TextView title;
        TextView price;
        TextView subTitle;
        TextView quantity;
        public ImageView image;

    }

    private class ThumbnailTask extends AsyncTask <String,Void,Bitmap>{
        private ViewHolder mHolder;
        private String  url;
        private LruCache<String, Bitmap> mMemoryCache;

        public ThumbnailTask(ViewHolder holder,String miUrl) {
            mHolder = holder;
            url = miUrl;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
                mHolder.image.setImageBitmap(getBitmapFromMemCache(url));

        }

        @Override
        protected Bitmap doInBackground(String... url1) {
            try {

                URL newurl = new URL(url);
                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                AdapterList.this.addBitmapToMemoryCache(url,mIcon_val);
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
            if(getBitmapFromMemCache(item.getImage())==null) {
                new ThumbnailTask(holder, item.getImage())
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            }
            else{
                holder.image.setImageBitmap(getBitmapFromMemCache(item.getImage()));
            }
            convertView.setTag(holder);
            if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                holder.subTitle = (TextView) convertView.findViewById(R.id.textView3);
                holder.quantity = (TextView) convertView.findViewById(R.id.textView5);
            }
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            if(getBitmapFromMemCache(item.getImage())==null) {
                new ThumbnailTask(holder, item.getImage())
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            }
            else{
                holder.image.setImageBitmap(getBitmapFromMemCache(item.getImage()));
            }

        }
        holder.title.setText(item.getTitle());
        holder.price.setText("Precio: " + item.getPrice());
        if (actividad.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            holder.subTitle.setText(item.getSubtitle());
            holder.quantity.setText("Cantidad: " + item.getQuantity());
        }
        return convertView;

    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
         mMemoryCache.put(key, bitmap);

    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


}
