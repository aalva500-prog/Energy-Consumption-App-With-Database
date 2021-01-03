package model;
import java.io.Serializable;

public class Lectura  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String fecha;
private float RegistroInicial;
private float Registrofinal;
private String identificador; 
private String idecasa;




public Lectura(String fecha, float registroInicial, float registrofinal, String identificador, String idecasa) {
	super();
	this.fecha = fecha;
	this.RegistroInicial = registroInicial;
	this.Registrofinal = registrofinal;
	this.identificador = identificador;
	this.idecasa = idecasa;
}

public String getIdentificador() {
	return identificador;
}

public void setIdentificador(String identificador) {
	this.identificador = identificador;
}

public String  getFecha() {
	return fecha;
}


public Lectura() {
	super();
}

public void setFecha(String date) {
	this.fecha = date;
}


public float getRegistroFinal() {
	return Registrofinal;
}


public void setRegistroFinal(float finalRegistration) {
	this.Registrofinal = finalRegistration;
}


public float getRegistroInicial() {
	return RegistroInicial;
}


public void setRegistroInicial(float startRegistration) {
	this.RegistroInicial = startRegistration;
}


public float ConsumoDelDia(){
	return Registrofinal-RegistroInicial;
}

public String getIdecasa() {
	return idecasa;
}

public void setIdecasa(String idecasa) {
	this.idecasa = idecasa;
}



}
