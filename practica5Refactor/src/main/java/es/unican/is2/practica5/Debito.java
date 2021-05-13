package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c, LocalDate f) { // WMC: +1  CCog: +0
		super(numero, titular, c, f);
	}
	
	
	@Override
	public void retirar(double cantidadRetirada) throws SaldoInsuficienteException, DatoErroneoException { // WMC: +1  CCog: +0
		if (saldoDiarioDisponible<cantidadRetirada) { // WMC: +1  CCog: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero automático", cantidadRetirada);
		saldoDiarioDisponible-=cantidadRetirada;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double cantidadAPagar) throws SaldoInsuficienteException, DatoErroneoException { // WMC: +1  CCog: +0
		if (saldoDiarioDisponible<cantidadAPagar) { // WMC: +1  CCog: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, cantidadAPagar);
		saldoDiarioDisponible-=cantidadAPagar;
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // WMC: +1  CCog: +0
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

}