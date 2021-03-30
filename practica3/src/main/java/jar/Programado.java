package jar;

/**
 * Clase del estado Programado, en el que al menos hay una alarma activada.
 * @author Jesús y Jaime
 *
 */
public class Programado extends ControladorAlarmaState {

	public void entryAction(ControladorAlarma context) {
		if (context.alarmasActivadas().isEmpty()) {
			ControladorAlarmaState estadoDesprogramado = getEstadoDesprogramado();
			context.setState(estadoDesprogramado);
		}
	}
	
	public void nuevaAlarma(ControladorAlarma context) {
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	public void alarmaOn(ControladorAlarma context) {
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	

	public void alarmaOff(ControladorAlarma context) {
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	public void borraAlarma(ControladorAlarma context) {
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	
	
}