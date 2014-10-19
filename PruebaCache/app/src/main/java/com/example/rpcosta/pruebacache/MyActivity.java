package com.example.rpcosta.pruebacache;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MyActivity extends Activity {

    private ImageView imgView;
    private ImageLoader imgLoader;
    private String strURL = "http://www.capitalradio.es/wp-content/uploads/2014/10/apple1.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        imgView = (ImageView) findViewById(R.id.imageView1);
        imgLoader = new ImageLoader(this);
    }

    public void btnLoadImageClick(View v){

        imgLoader.DisplayImage(strURL, imgView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
