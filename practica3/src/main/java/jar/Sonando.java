package jar;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmaState {
	
	protected Timer timer = new Timer();
	protected ApagaAlarmaTask apagaAlarmaTask;
	
	
	public void entryAction(Alarma context) {
		context.activarMelodia();
		// Configura el temporizador para apagar la alarma
		apagaAlarmaTask = new ApagaAlarmaTask(context);
		timer.schedule(apagaAlarmaTask, INTERVALO_SONAR);
	}
	
	public void exitAction(Alarma context) {
		context.desactivarMelodia();
	}
	
	public void apagar(Alarma context) {
		this.exitAction(context);
		// Cancela el temporizador
		apagaAlarmaTask.cancel();
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	// Clase de apagar alarma con temporizador
	public class ApagaAlarmaTask extends TimerTask {
		private Alarma context;
		public ApagaAlarmaTask(Alarma c) {
			context = c;
		}
		public void run() {
			AlarmaState estadoProgramado = getEstadoProgramado();
			getEstadoSonando().exitAction(context);
			context.setState(estadoProgramado);
			estadoProgramado.entryAction(context);
			estadoProgramado.doAction(context);
		}
	}
}
