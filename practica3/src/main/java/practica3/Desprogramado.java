package practica3;

import java.util.Date;

/**
 * Clase del estado Desprogramado, en el que al menos hay una alarma activada.
 * @author Jesus y Jaime
 *
 */
public class Desprogramado extends ControladorAlarmaState {

	/**
	 * Cuando se crea una alarma, al activarse por defecto,
	 * se pasa a Programado
	 */
	public void nuevaAlarma(ControladorAlarma context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id, hora));
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	/**
	 * Cuando se activa una alarma, se pasa a Programado
	 */
	public void alarmaOn(ControladorAlarma context, String id) {
		context.activaAlarma(context.alarma(id));
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	/*
	public void borrarAlarma(ControladorAlarma context)
	 */
	
	
}
