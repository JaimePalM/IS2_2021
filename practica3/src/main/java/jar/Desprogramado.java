package jar;

/**
 * Clase del estado Desprogramado, en el que al menos hay una alarma activada.
 * @author Jesús y Jaime
 *
 */
public class Desprogramado extends AlarmaState {

	/**
	 * Cuando se crea una alarma, al activarse por defecto,
	 * se pasa a Programado
	 */
	public void nuevaAlarma(ControladorAlarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	
}
