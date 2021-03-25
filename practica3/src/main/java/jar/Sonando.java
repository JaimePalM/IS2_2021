package jar;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmaState {
	
	protected Timer timer = new Timer();
	protected ApagaAlarmaTask apagaAlarmaTask;
	
	public void entryAction(ControladorAlarma context) {
		context.activarMelodia();
		// Configura el temporizador para apagar la alarma
		apagaAlarmaTask = new ApagaAlarmaTask(context);
		timer.schedule(apagaAlarmaTask, ControladorAlarma.INTERVALO_SONAR);
	}
	
	public void exitAction(ControladorAlarma context) {
		context.desactivarMelodia();
	}
	
	public void apagar(ControladorAlarma context) {
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
		private ControladorAlarma context;
		public ApagaAlarmaTask(ControladorAlarma c) {
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
