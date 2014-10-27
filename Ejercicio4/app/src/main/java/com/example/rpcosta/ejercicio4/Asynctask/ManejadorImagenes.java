package com.example.rpcosta.ejercicio4.Asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rpcosta on 20/10/14.
 */
public class ManejadorImagenes {
    private static ManejadorImagenes Mimg;
    private LruCache<String, Bitmap> mMemoryCache;

    private ManejadorImagenes() {
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

    public static ManejadorImagenes getInstance() {
        if (Mimg == null) {
            Mimg = new ManejadorImagenes();
        }
        return Mimg;
    }

    public void setImagenes(String url, ImageView img) {
        if (getBitmapFromMemCache(url) == null) {
            new ThumbnailTask(url, img)
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
        } else {
            img.setImageBitmap(getBitmapFromMemCache(url));
        }
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        mMemoryCache.put(key, bitmap);

    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);

    }

    private class ThumbnailTask extends AsyncTask<String, Void, Bitmap> {
        private String url;
        private ImageView imagen;

        public ThumbnailTask(String miUrl, ImageView img) {
            imagen = img;
            url = miUrl;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imagen.setImageBitmap(getBitmapFromMemCache(url));

        }

        @Override
        protected Bitmap doInBackground(String... url1) {
            try {

                URL newurl = new URL(url);
                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                addBitmapToMemoryCache(url, mIcon_val);
                return mIcon_val;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}
