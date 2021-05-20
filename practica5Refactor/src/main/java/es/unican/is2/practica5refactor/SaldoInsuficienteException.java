package es.unican.is2.practica5refactor;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
