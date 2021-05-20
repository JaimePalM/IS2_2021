package es.unican.is2.practica5refactorsonar;

public abstract class Tarjeta {
	protected String numero; 
	protected String titular;		
	protected CuentaAhorro cuentaAsociada;
	
	protected Tarjeta(String numero, String titular, CuentaAhorro c) { // WMC: +1  CCog: +0
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void retirar(double cantidadRetirada) throws SaldoInsuficienteException, DatoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double cantidadAPagar)
			throws SaldoInsuficienteException, DatoErroneoException;

	public CuentaAhorro getCuentaAsociada() { // WMC: +1  CCog: +0
		return cuentaAsociada;
	}
	
}