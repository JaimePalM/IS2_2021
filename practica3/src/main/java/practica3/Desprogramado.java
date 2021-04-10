package practica3;

import java.util.Date;

/**
 * Clase del estado Desprogramado, en el que al menos hay una alarma activada.
 * @author Jesus y Jaime
 *
 */
public class Desprogramado extends ControladorAlarmaState {

	public void nuevaAlarma(ControladorAlarma context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id, hora));
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	public void alarmaOn(ControladorAlarma context, String id) {
		context.activaAlarma(context.alarma(id));
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	public void borraAlarma(ControladorAlarma context, String id) {
		context.eliminaAlarma(context.alarma(id));
	}
		
}
