package practica3;

import java.util.Date;

/**
 * Clase que lleva los estados del Controlador de Alarmas.
 * @author Jesus y Jaime
 *
 */
public abstract class ControladorAlarmaState {
	
	private static Programado estadoProgramado = new Programado();
	private static Desprogramado estadoDesprogramado = new Desprogramado();
	private static Sonando estadoSonando = new Sonando();

	/**
	 * Inidicializa el estado
	 * @param context
	 * @return estado con el que se inicializa, que es desprogramado.
	 */
	public static ControladorAlarmaState init(ControladorAlarma context) {
		return estadoDesprogramado;
	}

	public void entryAction(ControladorAlarma context) {}
	public void doAction(ControladorAlarma context) {}
	public void exitAction(ControladorAlarma context) {}
	
	
	// Se?ales que modifican el contexto de la alarma
	
	/**
	 * Crea una alarma nueva con los datos proporcionados.
	 * @param context estado en el que se encuentra el programa.
	 * @param id id de la nueva alarma.
	 * @param hora hora a la que sonara la alarma.
	 */
	public void nuevaAlarma(ControladorAlarma context, String id, Date hora) { }
	
	/**
	 * Borra una alarma de un id dado.
	 * @param context estado en el que se encuentra el programa.
	 * @param id id de la alarma a eliminar.
	 */
	public void borraAlarma(ControladorAlarma context, String id) {	}
	
	/**
	 * Apaga la alarma que esta sonando.
	 * @param context estado en el que se encuentra el programa.
	 */
	public void apagar(ControladorAlarma context) { }
	
	/**
	 * Desactiva una alarma de un id dado.
	 * @param context estado en el que se encuentra el programa.
	 * @param id id de la alarma a desactivar.
	 */
	public void alarmaOff(ControladorAlarma context, String id) { }
	
	/**
	 * Enciende la alarma indicada.
	 * @param context estado en el que se encuentra el programa.
	 * @param id id de la alarma a activar.
	 */
	public void alarmaOn(ControladorAlarma context, String id) { }

	
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
