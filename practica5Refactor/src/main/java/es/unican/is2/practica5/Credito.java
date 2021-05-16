package es.unican.is2.practica5;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private static final double COMISION_RETIRADA = 0.05; // Añadimos una comisión de un 5%
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) { // WMC: +1  CCog: +0
		super(numero, titular, c);
		this.credito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param cantidadRetirada Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	@Override
	public void retirar(double cantidadRetirada) throws SaldoInsuficienteException, DatoErroneoException { // WMC: +1  CCog: +0
		if (cantidadRetirada<0) // WMC: +1  CCog: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		
		cantidadRetirada += cantidadRetirada * COMISION_RETIRADA; 
		Movimiento m = new Movimiento("Retirada en cajero automático", LocalDateTime.now(), -cantidadRetirada);
		
		if (getGastosAcumulados()+cantidadRetirada > credito) // WMC: +1  CCog: +1
			throw new SaldoInsuficienteException("Crédito insuficiente");

		movimientosMensuales.add(m);
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double cantidadAPagar) throws SaldoInsuficienteException, DatoErroneoException { // WMC: +1  CCog: +0
		if (cantidadAPagar<0) // WMC: +1  CCog: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + cantidadAPagar > credito) // WMC: +1  CCog: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento("Compra a crédito en: " + datos, LocalDateTime.now(), -cantidadAPagar);
		movimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() { // WMC: +1  CCog: +0
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { // WMC: +1  CCog: +1
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getImporte();
		}
		return -r;
	}
	
	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() { // WMC: +1  CCog: +0
		double r = -getGastosAcumulados();
		Movimiento liq = new Movimiento("Liquidación de operaciones tarjeta crédito", LocalDateTime.now(), r);
	
		if (r != 0) // WMC: +1  CCog: +1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() { // WMC: +1  CCog: +0
		return movimientosMensuales;
	}
	
	public List<Movimiento> getMovimientos() { // WMC: +1  CCog: +0
		return historicoMovimientos;
	}

}