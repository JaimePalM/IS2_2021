package es.unican.is2.practica5;

public abstract class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { // WMC: +1  CCog: +0
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { // WMC: +1  CCog: +0
		return numCuenta;
	}

	double getSaldoCuenta() {
		double total = 0.0;
		if (this instanceof CuentaAhorro) { // WMC: +1  CCog: +2	
			total += ((CuentaAhorro) this).getSaldo(); 
		} else if (this instanceof CuentaValores)  { // WMC: +1  CCog: +3
			for (Valor v: ((CuentaValores) this).getValores()) { // WMC: +1  // CCog: +4 
				total += v.getCotizacionActual()*v.getNumValores();
			}
		}
		return total;
	}
	
	
}
