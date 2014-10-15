package com.example.gestordereservas;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MiPanel extends Activity {
	private TextView user,email,nombre,cedula;
	private BaseDatos bd = new BaseDatos(this);
	private String usuario;
	private ArrayList<String> dato = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mi_panel);
		Bundle datos = getIntent().getExtras();
		usuario = datos.getString("Usuario");
		dato = bd.datosUser(usuario);
		user=(TextView)findViewById(R.id.textView5);
		email=(TextView)findViewById(R.id.textView6);
		nombre=(TextView)findViewById(R.id.textView8);
		cedula=(TextView)findViewById(R.id.textView7);
		user.setText(dato.get(1));
		nombre.setText(dato.get(0));
		email.setText(dato.get(2));
		cedula.setText(dato.get(3));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mi_panel, menu);
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
