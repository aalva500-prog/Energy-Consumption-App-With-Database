package model;

public class Cobrador {
private String identificador;
private String carnet;
private String nombre;
private String apellidos;
private String municipio;
private int anos;



public Cobrador(String identificador, String carnet, String nombre, String apellidos, String municipio, int anos) {
	super();
	this.identificador = identificador;
	this.carnet = carnet;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.municipio = municipio;
	this.anos = anos;
}


public Cobrador() {
	super();
}


public int getAnos() {
	return anos;
}
public void setAnos(int anos) {
	this.anos = anos;
}
public String getApellidos() {
	return apellidos;
}
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}
public String getCarnet() {
	return carnet;
}
public void setCarnet(String carnet) {
	this.carnet = carnet;
}
public String getIdentificador() {
	return identificador;
}
public void setIdentificador(String identificador) {
	this.identificador = identificador;
}
public String getMunicipio() {
	return municipio;
}
public void setMunicipio(String municipio) {
	this.municipio = municipio;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

@Override
public String toString() {
	return this.nombre;
}


}
