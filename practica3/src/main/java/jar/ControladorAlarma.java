package jar;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Clase que contiene todas la alarmas y las gestiona.
 * @author Jesús y Jaime
 *
 */
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
		// Si la alarma ya existe devuelve false
		if (alarmas.containsValue(a))
			return false;
		
		// Anhade la alarma al total y la activa
		alarmas.put(a.getId(), a);
		return alarmasActivadas.add(a);
	}

	public boolean eliminaAlarma(Alarma a) {
		// Si la alarma no exite devuelve false
		if (!alarmas.containsValue(a))
			return false;
		// Eliminamos la alarma del total y de la cola en la que este
		alarmas.remove(a.getId());
		if (alarmasActivadas.contains(a))
			return alarmasActivadas.remove(a);
		else
			return alarmasDesactivadas.remove(a);
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
		Alarma[] arr1 = new Alarma[alarmasActivadas.size()];
		return alarmasActivadas.toArray(arr1);
	}

	public Alarma[] alarmasDesactivadas(){
		Alarma[] arr1 = new Alarma[alarmasDesactivadas.size()];
		return alarmasDesactivadas.toArray(arr1);
	}

	public void activarMelodia() {}

	public void desactivarMelodia() {}


}
