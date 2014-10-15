package com.example.gestordereservas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class VentanaIngreso extends Activity {
	private ViewFlipper flipper;
	private ImageButton btnatras,btnadelante;
	private ImageButton panel,reserva;
	private ListView lista;
	private String usuario;
	BaseDatos db = new BaseDatos(VentanaIngreso.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_ingreso);
		lista = (ListView)findViewById(R.id.listView1);
		Bundle datos = getIntent().getExtras();
		usuario = datos.getString("Usuario");
		panel = (ImageButton)findViewById(R.id.imageButton3);
		reserva = (ImageButton)findViewById(R.id.imageButton4); 
		panel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(VentanaIngreso.this,MiPanel.class);
				i.putExtra("Usuario", usuario);
				startActivity(i);
			}
		});
		
		reserva.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(VentanaIngreso.this,Reservas.class);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_ingreso, menu);
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
