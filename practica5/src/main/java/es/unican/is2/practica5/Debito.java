package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) { // WMC: +1  CCog: +0
		super(numero, titular, c);
	}
	
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC: +1  CCog: +0
		if (saldoDiarioDisponible<x) { // WMC: +1  CCog: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC: +1  CCog: +0
		if (saldoDiarioDisponible<x) { // WMC: +1  CCog: +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() { // WMC: +1  CCog: +0
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // WMC: +1  CCog: +0
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() { // WMC: +1  CCog: +0
		return mCuentaAsociada;
	}

}