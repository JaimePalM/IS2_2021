package es.unican.is2.practica5;

public abstract class Tarjeta {
	protected String numero, titular;		
	protected CuentaAhorro cuentaAsociada;
	
	public Tarjeta(String numero, String titular, CuentaAhorro c) { // WMC: +1  CCog: +0
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double cantidadRetirada) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double cantidadAPagar)
			throws saldoInsuficienteException, datoErroneoException;

	public CuentaAhorro getCuentaAsociada() { // WMC: +1  CCog: +0
		return cuentaAsociada;
	}
	
}