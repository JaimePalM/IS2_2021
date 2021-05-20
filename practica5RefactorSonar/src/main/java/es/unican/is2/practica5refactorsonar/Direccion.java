package es.unican.is2.practica5refactorsonar;

public class Direccion {
	protected String calle;
	protected String zip;
	protected String localidad;

	public Direccion(String calle, String zip, String localidad) { // WMC: +1  CCog: 0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
}