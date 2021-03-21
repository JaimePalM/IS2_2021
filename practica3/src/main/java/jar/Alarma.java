package jar;

import java.awt.List;
import java.util.*;

public class Alarma {

	private String id; // Id de la alarma
	private Date hora; // Hora a la que se activa al alarma
	
	private AlarmaState state; //Estado de la alarma en un isntante
	
	private Map<String, Alarma> alarmas = new HashMap<String, Alarma>();
	private Queue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private Queue<Alarma> alarmasDesactivadas = new PriorityQueue<Alarma>();
	
	// Constructor
	public Alarma(String id, Date hora) {
		this.id = id;
		this.hora = hora;
		//state = AlarmaState.init(this);
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
		alarmas.put(a.id, a);
		return alarmasActivadas.add(a);
	}
	
	public boolean eliminaAlarma(Alarma a) {
		alarmas.remove(a.id);
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
	
	
	// Redefinimos el metodo compareTo deacuerdo a las alarmas
	public int compareTo(Alarma a){
		if (this.hora.before(a.hora)) {
			return -1;
		}
		else if (this.hora.after(a.hora)) {
			return 1;
		}
		else 
			return 0;
	}
	
	
}
