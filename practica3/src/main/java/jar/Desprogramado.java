package jar;

/**
 * Clase del estado Desprogramado, en el que al menos hay una alarma activada.
 * @author Jesús y Jaime
 *
 */
public class Desprogramado extends ControladorAlarmaState {

	/**
	 * Cuando se crea una alarma, al activarse por defecto,
	 * se pasa a Programado
	 */
	public void nuevaAlarma(ControladorAlarma context) {
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	/**
	 * Cuando se activa una alarma, se pasa a Programado
	 */
	public void alarmaOn(ControladorAlarma context) {
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	/*
	public void borrarAlarma(ControladorAlarma context)
	 */
	
	
}
