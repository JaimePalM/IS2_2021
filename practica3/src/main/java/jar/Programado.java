package jar;

public class Programado extends AlarmaState {

	public void nuevaAlarma(Alarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	public void alarmaOn(Alarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	

	public void alarmaOff(Alarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	public void borraAlarma(Alarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	
	
	
}
