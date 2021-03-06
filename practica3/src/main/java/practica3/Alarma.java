package practica3;

import java.util.*;

/**
 * Clase que define la Alarma
 * @author Jesus y Jaime
 *
 */
public class Alarma implements Comparable<Alarma> {

	private String id; // Id de la alarma
	private Date hora; // Hora a la que se activa al alarma

	/**
	 * Constructor de la clase Alarma
	 * @param id id de la alarma a crear
	 * @param hora hora a que sonara la alarma
	 */
	public Alarma(String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}

	public String getId() {
		return id;
	}

	public Date getHora() {
		return hora;
	}
	
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
