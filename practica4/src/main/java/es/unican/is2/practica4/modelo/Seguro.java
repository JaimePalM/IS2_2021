package es.unican.is2.practica4.modelo;

import java.time.LocalDate;

public class Seguro {

	@SuppressWarnings("serial")
	public class DatoIncorrectoException extends Exception {}
	 
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	private Cliente tomadorSeguro;
	private Cobertura cobertura;

	public Seguro (int potencia, Cliente cliente, Cobertura cobertura) 
			throws DatoIncorrectoException{
		
		if (potencia <= 0 || cliente == null || cobertura == null)
			throw new DatoIncorrectoException();
		
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}

	public double precio() {

		double precioFinal = 0;

		// Segun cobertura
		switch (cobertura) { 
		case TODO_RIESGO:
			precioFinal = Cobertura.TODO_RIESGO.getPrecio();
			break;

		case TERCEROS_LUNAS:
			precioFinal = Cobertura.TERCEROS_LUNAS.getPrecio();
			break;

		case TERCEROS:
			precioFinal = Cobertura.TERCEROS.getPrecio();
			break;
		}	
	 	
		// Segun potencia
		if (potenciaCV >= 90 && potenciaCV <= 110) 
			precioFinal = precioFinal * 1.05;
		else if (potenciaCV > 110)
			precioFinal = precioFinal * 1.2;

		// Segun siniestralidad
		if (fechaUltimoSiniestro != null) {
			if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(1).minusDays(1)))
				precioFinal += 200;
			else if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3).minusDays(1)))
				precioFinal += 50;
		}
		
		// Segun minusvalia 
		if (tomadorSeguro.isMinusvalia())
			precioFinal = precioFinal * 0.75;		
			
		return precioFinal;
	}

	// Getters
	public LocalDate getFechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) 
			throws DatoIncorrectoException {
		if (fechaUltimoSiniestro != null && fechaUltimoSiniestro.isAfter(LocalDate.now()))
			throw new DatoIncorrectoException();
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}

	public int getPotenciaCV() {
		return potenciaCV;
	}

	public Cliente getTomadorSeguro() {
		return tomadorSeguro;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}
}
