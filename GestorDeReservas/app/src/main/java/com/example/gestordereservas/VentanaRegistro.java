package com.example.gestordereservas;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VentanaRegistro extends Activity {
	private EditText usuario,pass,mail,cedula,nombre;
	private Button ingreso;
	private BaseDatos bd = new BaseDatos(this) ;
	private Toast text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_registro);
		ingreso = (Button)findViewById(R.id.registro);
		ingreso.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nombre =  (EditText)findViewById(R.id.editText5);
				usuario = (EditText)findViewById(R.id.editText1);
				pass=(EditText)findViewById(R.id.editText2);
				mail=(EditText)findViewById(R.id.editText3);
				cedula = (EditText)findViewById(R.id.editText4);
				// TODO Auto-generated method stub
				if(nombre.getText().toString().equalsIgnoreCase("")||cedula.getText().toString().length()!=8||usuario.getText().toString().equalsIgnoreCase("")||pass.getText().toString().equalsIgnoreCase("")||mail.getText().toString().equalsIgnoreCase("")){
					AlertDialog pregunta = new AlertDialog.Builder(VentanaRegistro.this).create();
					pregunta.setTitle("Advertencia");
					pregunta.setMessage("Los datos no son correctos");
					pregunta.setButton(DialogInterface.BUTTON_NEUTRAL,"Aceptar",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					pregunta.show();
				}
				else{
				            if (!(mail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")/* && s.length() > 0*/))
				            {
				            		//verificar mail
				            }
				            else{
				            	//Agregar a Base de datos
				            	Usuario u = new Usuario();
				            	u.setNombre(nombre.getText().toString());
				            	u.setCedula(Integer.parseInt(cedula.getText().toString()));
				            	u.setMail(mail.getText().toString());
				            	u.setPass(pass.getText().toString());
				            	u.setUsuario(usuario.getText().toString());
				            	if(bd.insertardato(u)){
				            		text = Toast.makeText(VentanaRegistro.this, "Ingreso Correcto", Toast.LENGTH_SHORT);
				            		text.show();
				            		finish();
				            	}
				            	
				            }
				        }
			}
				       
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_registro, menu);
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
