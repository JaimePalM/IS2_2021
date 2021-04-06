package practica3;

import java.util.Date;

/**
 * Clase que lleva los estados del Controlador de Alarmas
 * @author Jesus y Jaime
 *
 */
public abstract class ControladorAlarmaState {
	
	private static Programado estadoProgramado = new Programado();
	private static Desprogramado estadoDesprogramado = new Desprogramado();
	private static Sonando estadoSonando = new Sonando();

	public static ControladorAlarmaState init(ControladorAlarma context) {
		return estadoDesprogramado;
	}

	public void entryAction(ControladorAlarma context) {}
	public void doAction(ControladorAlarma context) {}
	public void exitAction(ControladorAlarma context) {}
	
	
	// Señales que modifican el contexto de la alarma
	public void nuevaAlarma(ControladorAlarma context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id, hora));
	}
	
	public void borraAlarma(ControladorAlarma context, String id) {
		context.eliminaAlarma(context.alarma(id));
	}
	
	public void apagar(ControladorAlarma context) { }
	
	public void alarmaOff(ControladorAlarma context, String id) {
		context.desactivaAlarma(context.alarma(id));
	}

	public void alarmaOn(ControladorAlarma context, String id) {
		context.activaAlarma(context.alarma(id));
	}

	// Getters
	public static ControladorAlarmaState getEstadoProgramado() {
		return estadoProgramado;
	}

	public static ControladorAlarmaState getEstadoDesprogramado() {
		return estadoDesprogramado;
	}

	public static ControladorAlarmaState getEstadoSonando() {
		return estadoSonando;
	}

}
