package practica4;

import java.time.LocalDate;

enum Cobertura {TODORIESGO, TERCEROSLUNAS, TERCEROS}

public class Seguro {
	
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	private Cliente tomadorSeguro;
	private Cobertura cobertura;
	
	public Seguro (int potencia, Cliente cliente, Cobertura cobertura) {
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}

	public double precio() {
		
		return -1;
	}

	// Getters
	public LocalDate getFechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) {
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
