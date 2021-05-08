package es.unican.is2.practica4.modelo;

/**
 * Clase del enumerado Cobertura de los seguros.
 * @author Jesus Ortega y Jaime Palacios
 *
 */
public enum Cobertura {

	TODO_RIESGO(1000), TERCEROS_LUNAS(600), TERCEROS(400);
 
	private double precio;
	 
	private Cobertura (double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}
}
