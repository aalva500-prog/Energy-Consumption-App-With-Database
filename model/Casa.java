package model;

import java.io.Serializable;

import Services.ServicioLectura;



public class Casa implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private int numero;
private String direccion;
private int habitantes;//cant. de habitantes.
private String identificador;
private float consumoAlterado;
private String cobrador;



public Casa(int numero, String direccion, int habitantes, String identificador, float consumoAlterado, String cobrador) {
	super();
	this.numero = numero;
	this.direccion = direccion;
	this.habitantes = habitantes;
	this.identificador = identificador;
	this.consumoAlterado = consumoAlterado;
	this.cobrador = cobrador;
}



public float getConsumoAlterado() {
	return consumoAlterado;
}

public void setConsumoAlterado(float consumptionMonthAltered) {
	this.consumoAlterado = consumptionMonthAltered;
}

public String getIdentificador() {
	return identificador;
}

public void setIdentificador(String identificador) {
	this.identificador = identificador;
}

public Casa() {
	// TODO Auto-generated constructor stub
}

public String getDireccion() {
	return direccion;
}
public void setDireccion(String address) {
	this.direccion = address;
}

public int getNumero() {
	return numero;
}
public void setNumero(int number) {
	this.numero = number;
}
public int gethabitantes() {
	return habitantes;
}
public void setHabitantes(int quantityOfInhabitants) {
	this.habitantes = quantityOfInhabitants;
}

public String getCobrador() {
	return cobrador;
}

public void setCobrador(String cobrador) {
	this.cobrador = cobrador;
}


public float ConsumoReal(){
	int sum=0;
		for (Lectura item : ServicioLectura.getCons(identificador)) {
			sum+=item.ConsumoDelDia();
			}
		return sum;
	}

	public float Tarifa(){
		return (float) (ConsumoReal()*0.15);
	}

public float TarifaAlterada(){
	return (float) (consumoAlterado*0.15);
}

@Override
public String toString() {
	return  (this.direccion);
}
}
