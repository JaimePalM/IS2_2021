package jar;

/**
 * Clase del estado Programado, en el que al menos hay una alarma activada.
 * @author Jesús y Jaime
 *
 */
public class Programado extends ControladorAlarmaState {

	
	
	
	public void nuevaAlarma(ControladorAlarma context) {
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	public void alarmaOn(ControladorAlarma context) {
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	

	public void alarmaOff(ControladorAlarma context) {
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	public void borraAlarma(ControladorAlarma context) {
		if (context.alarmasActivadas().isEmpty()) {
			this.exitAction(context);
			ControladorAlarmaState estadoDesprogramado = getEstadoDesprogramado();
			context.setState(estadoDesprogramado);
			estadoDesprogramado.entryAction(context);
			estadoDesprogramado.doAction(context);
		}
	}
	
	
	
}