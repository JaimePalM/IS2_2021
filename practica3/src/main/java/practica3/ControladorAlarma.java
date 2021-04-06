package practica3;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Clase que contiene todas la alarmas y las gestiona.
 * @author Jesus y Jaime
 *
 */
public class ControladorAlarma {

	private ControladorAlarmaState state; //Estado de la alarma en un instante

	protected final static int INTERVALO_SONAR = 12000;
	
	private Queue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private Map<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>();

	// Constructor
	public ControladorAlarma() {
		state = ControladorAlarmaState.init(this);
	}

	//Establece un estado para la alarma que se pase por parametro
	public void setState(ControladorAlarmaState value) {
		this.state = value;
	}

	// Senhales
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}
	
	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}
	
	public void apagar(ControladorAlarma context) { }
	
	public void alarmaOff(ControladorAlarma context, String id) {
		state.alarmaOff(this, id);
	}

	public void alarmaOn(ControladorAlarma context, String id) {
		state.alarmaOn(this, id);
	}

	// M�todos
	public Alarma alarma(String id) {

		if (alarmasDesactivadas.containsKey(id))
			return alarmasDesactivadas.get(id);
		else {
			for (Alarma a: alarmasActivadas) {
				if (a.getId().equals(id))
					return a;
			}
		}	
		return null;
	}

	public boolean anhadeAlarma(Alarma a) {
		// Si la alarma ya existe devuelve false
		if (alarmasActivadas.contains(a) || alarmasDesactivadas.containsKey(a.getId()))
			return false;
		
		// Anhade la alarma y la activa
		return alarmasActivadas.add(a);
	}

	public boolean eliminaAlarma(Alarma a) {
		// Si la alarma no exite devuelve false
		if (!alarmasActivadas.contains(a) && !alarmasDesactivadas.containsKey(a.getId()))
			return false;
		// Eliminamos la alarma de la cola en la que este
		if (alarmasActivadas.contains(a))
			return alarmasActivadas.remove(a);
		else
			return alarmasDesactivadas.containsKey(a.getId());
	}

	public Alarma alarmaMasProxima() {
		return alarmasActivadas.peek();
	}

	public void activaAlarma(Alarma a) {		
		alarmasActivadas.add(a);
		alarmasDesactivadas.remove(a.getId());
	}

	public void desactivaAlarma(Alarma a) {
		alarmasDesactivadas.put(a.getId(), a);
		alarmasActivadas.remove(a);
	}

	public Queue<Alarma> alarmasActivadas() {
		return alarmasActivadas;
	}

	public Map<String,Alarma> alarmasDesactivadas(){
		return alarmasDesactivadas;
	}

	public void activarMelodia() {
		System.out.println("��SONANDO!! -> " + alarmaMasProxima().getId());
	}

	public void desactivarMelodia() {
		System.out.println("ALARMA APAGADA");
	}

}
