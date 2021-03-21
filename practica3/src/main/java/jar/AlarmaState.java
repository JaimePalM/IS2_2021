package jar;

public class AlarmaState {

	protected final static int INTERVALO_SONAR = 12000;
	
	private static Programado estadoProgramado;
	private static Desprogramado estadoDesprogramado;
	private static Sonando estadoSonando;

	public static AlarmaState init(Alarma context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}

	public void entryAction(Alarma context) {}

	public void doAction(Alarma context) {}

	public void exitAction(Alarma context) {}
	
	
	// Señales que emodifican el contexto de la alarma
	public void nuevaAlarma(Alarma context) {}
	public void borraAlarma(Alarma context) {}
	public void apagar(Alarma context) {}
	public void alarmaOff(Alarma context) {}
	public void alarmaOn(Alarma context) {}

	// Getters
	public static Programado getEstadoProgramado() {
		return estadoProgramado;
	}

	public static Desprogramado getEstadoDesprogramado() {
		return estadoDesprogramado;
	}

	public static Sonando getEstadoSonando() {
		return estadoSonando;
	}

}
