package jar;

import java.util.Date;

public abstract class ControladorAlarmaState {
	
	private static Programado estadoProgramado = new Programado();
	private static Desprogramado estadoDesprogramado = new Desprogramado();
	private static Sonando estadoSonando = new Sonando();

	public static ControladorAlarmaState init(ControladorAlarma context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}

	public void entryAction(ControladorAlarma context) {}

	public void doAction(ControladorAlarma context) {}

	public void exitAction(ControladorAlarma context) {}
	
	
	// Señales que modifican el contexto de la alarma
	public void nuevaAlarma(ControladorAlarma context, String id, Date hora) {}
	public void borraAlarma(ControladorAlarma context, String id) {}
	public void apagar(ControladorAlarma context) {}
	public void alarmaOff(ControladorAlarma context, String id) {}
	public void alarmaOn(ControladorAlarma context, String id) {}

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
