package jar;

public class AlarmaState {
	
	private static Programado estadoProgramado;
	private static Desprogramado estadoDesprogramado;
	private static Sonando estadoSonando;

	public static AlarmaState init(ControladorAlarma context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}

	public void entryAction(ControladorAlarma context) {}

	public void doAction(ControladorAlarma context) {}

	public void exitAction(ControladorAlarma context) {}
	
	
	// Señales que emodifican el contexto de la alarma
	public void nuevaAlarma(ControladorAlarma context) {}
	public void borraAlarma(ControladorAlarma context) {}
	public void apagar(ControladorAlarma context) {}
	public void alarmaOff(ControladorAlarma context) {}
	public void alarmaOn(ControladorAlarma context) {}

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
