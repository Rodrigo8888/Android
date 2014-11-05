package com.example.rpcosta.ejercicio4.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import com.example.rpcosta.ejercicio4.R;

public class DescripcionItem extends Activity {
    WebView descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_item);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        descripcion = (WebView)findViewById(R.id.webView);
        descripcion.getSettings().setBuiltInZoomControls(true); //Enable Multitouch if supported by ROM
        Bundle b = getIntent().getExtras();
        String desc = b.getString("descripcion");
        descripcion.loadData(desc, "text/html", "UTF-8");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.descripcion_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
               finish();
            }
        return super.onOptionsItemSelected(item);
    }
}
