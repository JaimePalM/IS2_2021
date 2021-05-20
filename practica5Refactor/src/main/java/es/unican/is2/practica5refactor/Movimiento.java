package es.unican.is2.practica5refactor;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;
		
	public Movimiento() {} // WMC: +1  CCog: +0
	
	public Movimiento(String concepto, LocalDateTime fecha, double importe) { // WMC: +1  CCog: +0
		this.concepto = concepto;
		this.fecha = fecha;
		this.importe = importe;
	}

	public double getImporte() { // WMC: +1  CCog: +0
		return importe;
	}

	public String getConcepto() { // WMC: +1  CCog: +0
		return concepto;
	}

	public void setC(String newMConcepto) { // WMC: +1  CCog: +0
		concepto = newMConcepto;
	}

	public LocalDateTime getF() { // WMC: +1  CCog: +0
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) { // WMC: +1  CCog: +0
		fecha = newMFecha;
	}

	public void setI(double newMImporte) { // WMC: +1  CCog: +0
		importe = newMImporte;
	}
}