package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private static final int LIMITE_DEBITO = 1000;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private List<Movimiento> movimientos;
	
	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) { // WMC: +1  CCog: +0
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		movimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(String concepto, double cantidadIngresada) throws DatoErroneoException { // WMC: +1  CCog: +0
		if (cantidadIngresada <= 0) // WMC: +1  CCog: +1
			throw new DatoErroneoException("No se puede ingresar una cantidad negativa");
		if (concepto == null) // WMC: +1  CCog: +1
			concepto = "Ingreso en efectivo";
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(), cantidadIngresada);
		this.movimientos.add(m);
	}

	public void retirar(String concepto, double cantidadRetirada) throws SaldoInsuficienteException, DatoErroneoException { // WMC: +1  CCog: +0
		if (getSaldo() < cantidadRetirada) // WMC: +1  CCog: +1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		if (cantidadRetirada <= 0) // WMC: +1  CCog: +1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		if (concepto == null) // WMC: +1  CCog: +1
			concepto = "Retirada de efectivo";
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(), -cantidadRetirada);
		this.movimientos.add(m);
	}

	public double getSaldo() { // WMC: +1  CCog: +0
		double r = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) { // WMC: +1  CCog: +1
			Movimiento m = (Movimiento) movimientos.get(i);
			r += m.getImporte();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) { // WMC: +1  CCog: +0
		movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { // WMC: +1  CCog: +0
		return movimientos;
	}
	
	public LocalDate getCaducidadDebito() { // WMC: +1  CCog: +0
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() { // WMC: +1  CCog: +0
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() { // WMC: +1  CCog: +0
		return LIMITE_DEBITO;
	}

}