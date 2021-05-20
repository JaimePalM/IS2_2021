package es.unican.is2.practica5refactor;

public class Direccion {
	public String calle;
	public String zip;
	public String localidad;

	public Direccion(String calle, String zip, String localidad) { // WMC: +1  CCog: 0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
}