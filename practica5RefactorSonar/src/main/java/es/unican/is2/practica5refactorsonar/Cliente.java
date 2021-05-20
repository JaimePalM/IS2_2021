package es.unican.is2.practica5refactorsonar;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	protected String nombre;
	protected Direccion direccion;
	protected String telefono;
	protected String dni;
	
    private List<Cuenta> cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, Direccion direccion, String telefono, String dni) {  // WMC: +1	CCog: +0
		this.nombre = titular;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(Direccion nuevaDireccion) { // WMC: +1 CCog: +0
		this.direccion = nuevaDireccion;
	}
	
	public double getSaldoTotal() { // WMC: +1	CCog: +0
		double total = 0.0;
		for (Cuenta c: cuentas) { // WMC: +1  CCog: +1
			total += c.getSaldoCuenta();
		}
		return total;
	}

	public void anhadeCuenta(Cuenta c) { // WMC: +1  CCog: +0
		cuentas.add(c);
	}
	
}