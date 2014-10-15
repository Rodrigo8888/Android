package com.example.gestordereservas;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class BaseDatos extends SQLiteOpenHelper {
	
	public BaseDatos (Context context){
		super(context,"Usuarios.db",null,1);
			
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String creaTablaUser="CREATE TABLE USUARIOS (id INTEGER NOT NULL "
				+"PRIMARY KEY AUTOINCREMENT, nombreUsuario VARCHAR(200),nombre VARCHAR(200)"
				+" NOT NULL,pass VARCHAR(30),correo VARCHAR(60),cedula VARCHAR(30))";
		
		db.execSQL(creaTablaUser);
		
	}
	public ArrayList<String> datosUser(String usuario){
		boolean finish=false;
		ArrayList<String> datos = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("Select nombreUsuario,nombre,correo,cedula from USUARIOS", null);
		while(c.moveToNext()&&!finish){
			if(usuario.equals(c.getString(0))){
				datos.add(c.getString(0));
				datos.add(c.getString(1));
				datos.add(c.getString(2));
				datos.add(c.getString(3));
			}
		}
		return datos;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists datos");
		onCreate(db);
		
	}
	
	public boolean usuarioRegistrado(String usuario,String pass){
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("Select nombreUsuario,pass from USUARIOS", null);
		while(c.moveToNext()){
			if(usuario.equals(c.getString(0))&&pass.equals(c.getString(1))){
				return true;
			}
		}
		return false;
	}
	
	public boolean insertardato(Usuario dto){
		boolean estado = true;
		int resultado;
		ContentValues datos = new ContentValues();
		try{
			datos.put("nombre", dto.getNombre());
			datos.put("nombreUsuario", dto.getUsuario());
			datos.put("pass", dto.getPass());
			datos.put("correo", dto.getMail());
			datos.put("cedula", dto.getCedula());
			resultado = (int)this.getWritableDatabase().insert("USUARIOS", "nombreUsuario,nombre,pass,correo,cedula", datos);
			if(resultado>0) estado = true;
			else estado = false;
		}
		catch(Exception e){
			estado = false;
		}
		return estado;
	}
}
