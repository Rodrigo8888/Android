package com.example.gestordereservas;

public class Usuario {
private String nombre;
private String Usuario;
private String pass;
private String mail;
private int cedula;

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getUsuario() {
	return Usuario;
}
public void setUsuario(String usuario) {
	Usuario = usuario;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public int getCedula() {
	return cedula;
}
public void setCedula(int cedula) {
	this.cedula = cedula;
}
}
