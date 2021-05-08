package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	public double getI() { // WMC: +1  CCog: +0
		return mImporte;
	}

	public String getC() { // WMC: +1  CCog: +0
		return mConcepto;
	}

	public void setC(String newMConcepto) { // WMC: +1  CCog: +0
		mConcepto = newMConcepto;
	}

	public LocalDateTime getF() { // WMC: +1  CCog: +0
		return mFecha;
	}

	public void setF(LocalDateTime newMFecha) { // WMC: +1  CCog: +0
		mFecha = newMFecha;
	}

	public void setI(double newMImporte) { // WMC: +1  CCog: +0
		mImporte = newMImporte;
	}
}