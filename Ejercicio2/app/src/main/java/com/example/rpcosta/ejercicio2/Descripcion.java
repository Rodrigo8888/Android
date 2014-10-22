package com.example.rpcosta.ejercicio2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpcosta.ejercicio2.Asynctask.ItemDescription;
import com.example.rpcosta.ejercicio2.Interfaces.DatosItems;


public class Descripcion extends Activity implements DatosItems {
    String id;
    Item i;
    String url;
    ImageView imagen;
    TextView titulo,subtitulo,precio,cantidad;
    private ManejadorImagenes manejador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        manejador = ManejadorImagenes.getInstance();
        Bundle b = getIntent().getExtras();
        id = b.getString("id");
        url ="https://api.mercadolibre.com/items/";
        url +=id;
        new ItemDescription(Descripcion.this).execute(url);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.descripcion, menu);
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

    @Override
    public void refreshdatos(Item item) {
        titulo = (TextView)findViewById(R.id.textView1);
        subtitulo = (TextView)findViewById(R.id.textView2);
        precio = (TextView)findViewById(R.id.textView3);
        cantidad = (TextView)findViewById(R.id.textView4);
        imagen = (ImageView) findViewById(R.id.imageView);
        i = item;
        titulo.setText(i.getTitle());
        if(!i.getSubtitle().equals("null")) {
            subtitulo.setText(i.getSubtitle());
        }
        else{
            subtitulo.setText("Sin subtitulo");
        }
        precio.setText("Precio : "+i.getPrice());
        cantidad.setText("Cantidad: "+ i.getQuantity());
        manejador.setImagenes(i.getImage(),imagen);

    }
}
