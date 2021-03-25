package jar;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ControladorAlarma {

	private AlarmaState state; //Estado de la alarma en un isntante

	protected final static int INTERVALO_SONAR = 12000;
	
	private Map<String, Alarma> alarmas = new HashMap<String, Alarma>();
	private Queue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private Queue<Alarma> alarmasDesactivadas = new PriorityQueue<Alarma>();

	// Constructor
	public ControladorAlarma() {
		state = AlarmaState.init(this);
	}

	//Establece un estado para la alarma que se pase por parametro
	public void setState(AlarmaState value) {
		this.state = value;
	}

	// Señales
	public void nuevaAlarma(String id, Date hora) {
		anhadeAlarma(new Alarma(id, hora));
	}


	// Metodos
	public Alarma alarma(String id) {
		return alarmas.get(id);
	}

	public boolean anhadeAlarma(Alarma a) {
		alarmas.put(a.getId(), a);
		return alarmasActivadas.add(a);
	}

	public boolean eliminaAlarma(Alarma a) {
		alarmas.remove(a.getId());
		return false;
	}

	public Alarma alarmaMasProxima() {
		return alarmasActivadas.peek();
	}

	public void activaAlarma(Alarma a) {
		alarmasActivadas.add(a);
		alarmasDesactivadas.remove(a);
	}

	public void desactivaAlarma(Alarma a) {
		alarmasDesactivadas.add(a);
		alarmasActivadas.remove(a);
	}

	public Alarma[] alarmasActivadas() {
		return null;
	}

	public Alarma[] alarmasDesactivadas(){
		return null;
	}

	public void activarMelodia() {}

	public void desactivarMelodia() {}


}
