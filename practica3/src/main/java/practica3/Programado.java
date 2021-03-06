package practica3;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase del estado Programado, en el que al menos hay una alarma activada.
 * @author Jesus y Jaime
 *
 */
public class Programado extends ControladorAlarmaState {

	// Timer que hace apagarse la alarma
	protected Timer timer = new Timer();
	// Tarea que se realiza cuando se alcanza el tiempo
	protected SuenaAlarmaTask suenaAlarmaTask = new SuenaAlarmaTask(null);

	/**
	 * Entry que se ha implementado para poder comprobar en cada operacion si el
	 * numero de alarmas activas es 0 y por lo tanto se debe cambiar de estado.
	 * Tambien se actualiza el timer de la alarma mas proxima
	 */
	public void entryAction(ControladorAlarma context) {
		suenaAlarmaTask.cancel();
		if (context.alarmasActivadas().isEmpty()) {
			ControladorAlarmaState estadoDesprogramado = getEstadoDesprogramado();
			context.setState(estadoDesprogramado);
		} else {
			// Configura el temporizador para hacer sonar la alarma
			suenaAlarmaTask = new SuenaAlarmaTask(context);
			timer.schedule(suenaAlarmaTask, context.alarmaMasProxima().getHora());
		}
	}

	// Cada vez que se modifique una alarma se vuelve a hacer el entry de programado
	// por si esa alarma resulta ser ahora o deja de ser la mas proxima.

	public void alarmaOn(ControladorAlarma context, String id) {
		context.activaAlarma(context.alarma(id));
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		estadoProgramado.entryAction(context);
	}
	
	public void alarmaOff(ControladorAlarma context, String id) {
		context.desactivaAlarma(context.alarma(id));
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		estadoProgramado.entryAction(context);
	}

	public void borraAlarma(ControladorAlarma context, String id) {
		context.eliminaAlarma(context.alarma(id));
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		estadoProgramado.entryAction(context);
	}

	public void nuevaAlarma(ControladorAlarma context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id, hora));
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		estadoProgramado.entryAction(context);
	}

	// Clase de hacer sonar la alarma mas proxima
	public class SuenaAlarmaTask extends TimerTask {
		private ControladorAlarma context;
		public SuenaAlarmaTask(ControladorAlarma c) {
			context = c;
		}
		public void run() {
			ControladorAlarmaState estadoSonando = getEstadoSonando();
			context.setState(estadoSonando);
			estadoSonando.entryAction(context);
		}
	}

}