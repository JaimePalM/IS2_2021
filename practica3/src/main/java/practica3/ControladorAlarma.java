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

	// Estado de la alarma en un instante
	private ControladorAlarmaState state; 

	// Intervalo de tiempo en el que una alarma se apaga automaticamente
	protected final static int INTERVALO_SONAR = 12000;
	
	// Cola de prioridad de alarmas activas, ordenadas por la hora
	private Queue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	// Mapa de alarmas desactivadas
	private Map<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>();

	/**
	 * Constructor de la clase.
	 * Inicializa el estado del programa.
	 */
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
	
	public void apagar() {
		state.apagar(this);
	}
	
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}

	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}

	// Metodos
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
		if (alarma(a.getId()) != null) 
			return false;
		
		// Anhade la alarma y la activa
		return alarmasActivadas.add(a);
	}

	public boolean eliminaAlarma(Alarma a) {
		// Si la alarma no exite devuelve false
		if (alarma(a.getId()) == null)
			return false;
		// Eliminamos la alarma de la cola en la que este
		if (alarmasActivadas.contains(a))
			return alarmasActivadas.remove(a);
		else
			alarmasDesactivadas.remove(a.getId());
		return true;
	}

	public Alarma alarmaMasProxima() {
		return alarmasActivadas.peek();
	}

	public void activaAlarma(Alarma a) {		
		if (alarmasActivadas.contains(a)) return;
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
		System.out.println("¡¡SONANDO!! -> " + alarmaMasProxima().getId());
	}

	public void desactivarMelodia() {
		System.out.println("ALARMA APAGADA");
	}

}
