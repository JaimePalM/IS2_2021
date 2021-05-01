package es.unican.is2.practica4.modelo;

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
