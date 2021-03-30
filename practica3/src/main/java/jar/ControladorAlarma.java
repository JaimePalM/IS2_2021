package jar;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Clase que contiene todas la alarmas y las gestiona.
 * @author Jesús y Jaime
 *
 */
public class ControladorAlarma {

	private ControladorAlarmaState state; //Estado de la alarma en un isntante

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

	// Señales
	public void nuevaAlarma(String id, Date hora) {
		anhadeAlarma(new Alarma(id, hora));
	}
	public void borraAlarma() {
		state.borraAlarma(this);
	}
	public void apagar() {
		state.apagar(this);
	}
	public void alarmaOff() {
		state.alarmaOff(this);
	}
	public void alarmaOn() {
		state.alarmaOn(this);
	}

	// Metodos
	public Alarma alarma(String id) {

		if (alarmasDesactivadas.containsKey(id))
			return alarmasDesactivadas.get(id);
		else if (alarmasActivadas.contains(id)) {
			Iterator<Alarma> iter = alarmasActivadas.iterator();
			while (iter.hasNext())
				if (iter.next().getId().equals(id))
					return iter.next();
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
		if (alarmasActivadas.contains(a) || alarmasDesactivadas.containsKey(a.getId()))
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
		System.out.println("¡¡SONANDO!!");
	}

	public void desactivarMelodia() {
		System.out.println("ALARMA APAGADA");
	}


}
