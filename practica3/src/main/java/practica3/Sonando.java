package practica3;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase del estado Sonando, en el que la alarma mas proxima comienza a sonar.
 * @author Jesus y Jaime
 *
 */
public class Sonando extends ControladorAlarmaState {
	
	// Timer que hace apagarse la alarma
	protected Timer timer = new Timer();
	// Tarea que se realiza cuando se alcanza el tiempo
	protected ApagaAlarmaTask apagaAlarmaTask;
	
	/**
	 * Arranca el timer para salir de Sonando
	 */
	public void entryAction(ControladorAlarma context) {
		context.activarMelodia();
		// Configura el temporizador para apagar la alarma
		apagaAlarmaTask = new ApagaAlarmaTask(context);
		timer.schedule(apagaAlarmaTask, ControladorAlarma.INTERVALO_SONAR);
	}
	
	/**
	 * Desactiva la melodia al salir de Sonando
	 */
	public void exitAction(ControladorAlarma context) {
		context.desactivarMelodia();
	}
	
	public void apagar(ControladorAlarma context) {
		// Cancela el temporizador
		apagaAlarmaTask.cancel();
		context.eliminaAlarma(context.alarmaMasProxima());
		this.exitAction(context);
		ControladorAlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
	}
	
	// Clase de apagar alarma con temporizador
	public class ApagaAlarmaTask extends TimerTask {
		private ControladorAlarma context;
		public ApagaAlarmaTask(ControladorAlarma c) {
			context = c;
		}
		public void run() {
			context.eliminaAlarma(context.alarmaMasProxima());
			getEstadoSonando().exitAction(context);
			ControladorAlarmaState estadoProgramado = getEstadoProgramado();
			context.setState(estadoProgramado);
			estadoProgramado.entryAction(context);
		}
	}
}
