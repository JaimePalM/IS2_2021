package es.unican.is2.practica4.GUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SeguroGUITest {

	private FrameFixture demo;

	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test 
	public void test() {
		
		LocalDate date;
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
	    String text;
	    
	    // Caso 1
		// Escribimos la fecha del siniestro
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		date = LocalDate.now().minusDays(100);
	    text = date.format(formatters);
		demo.textBox("txtFechaUltimoSiniestro").enterText(text);
		// Escribimos el tipo de seguro contratado
		demo.comboBox("comboCobertura").selectItem(2);
		// Escribimos la potencia
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("60");
		// Clickamos el boton minusvalia
		demo.radioButton("btnMinusvalia").click();
		// Pulsamos el boton CALCULAR
		demo.button("btnCalcular").click();
		// Comprobamos la salida generada
		demo.textBox("txtPrecio").requireText("450.0");
		
		
		// Caso 2
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		date = LocalDate.now().minusYears(2);
	    text = date.format(formatters);
		demo.textBox("txtFechaUltimoSiniestro").enterText(text);
		demo.comboBox("comboCobertura").selectItem(0);
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("100");
		// Volvemos a marcar la minusvalia para desmarcarla
		demo.radioButton("btnMinusvalia").click();
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("1100.0");
		
		
		// Caso 3
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		date = LocalDate.now().minusYears(10);
	    text = date.format(formatters);
		demo.textBox("txtFechaUltimoSiniestro").enterText(text);
		demo.comboBox("comboCobertura").selectItem(1);
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("200");
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("720.0");
		
		
		// Caso 4 --> No valido
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		date = LocalDate.now().minusYears(10);
	    text = date.format(formatters);
		demo.textBox("txtFechaUltimoSiniestro").enterText(text);
		demo.comboBox("comboCobertura").selectItem(1);
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("-20");
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("Error. Dato introducido incorrecto.");
	}
	
}
