package com.example.gestordereservas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
	private EditText usuario;
	private EditText pass;
	private ImageButton registro;
	private Button ingreso;
	private Toast text;
	private BaseDatos db = new BaseDatos(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ingreso =(Button)findViewById(R.id.ingresar);
		ingreso.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						usuario=(EditText) findViewById(R.id.editText1);
						pass=(EditText) findViewById(R.id.editText2);
						if(usuario.getText().toString().equalsIgnoreCase("")||pass.getText().toString().equalsIgnoreCase("")){
							text = Toast.makeText(MainActivity.this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT);
							text.show();
						}
						else{
						
							if(db.usuarioRegistrado(usuario.getText().toString(), pass.getText().toString())){
								Intent ing = new Intent(MainActivity.this,VentanaIngreso.class);
								ing.putExtra("Usuario",usuario.getText().toString());
								startActivity(ing);
							}
							else{
								text = Toast.makeText(MainActivity.this, "Datos Incorrectos", Toast.LENGTH_SHORT);
								text.show();
							}
							
							
							
		
						}
						
					}
				});

		registro = (ImageButton)findViewById(R.id.registro);
		registro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,VentanaRegistro.class);
				startActivity(i);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
