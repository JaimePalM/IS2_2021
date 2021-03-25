package jar;

public class Programado extends AlarmaState {

	public void nuevaAlarma(ControladorAlarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	public void alarmaOn(ControladorAlarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	

	public void alarmaOff(ControladorAlarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	public void borraAlarma(ControladorAlarma context) {
		this.exitAction(context);
		AlarmaState estadoProgramado = getEstadoProgramado();
		context.setState(estadoProgramado);
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	
	
	
}
