package es.unican.is2.practica4.modelo;

/**
 * Clase de negocio Cliente.
 * @author Jesus Ortega y Jaime Palacios
 *
 */
public class Cliente {

	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	public Cliente (String nombre, String dni, boolean minusvalia) {
		this.nombre = nombre;
		this.dni = dni;
		this.minusvalia = minusvalia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public boolean isMinusvalia() {
		return minusvalia;
	}
	
	
}
