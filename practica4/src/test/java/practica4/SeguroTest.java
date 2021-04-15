package practica4;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class SeguroTest {

	private Seguro seguro;
	// Crea clientes con datos buenos pero da igual cuales
	private Cliente cliente1 = new Cliente("Pepe", "72191738R", false);
	private Cliente cliente2 = new Cliente("Manolo", "72152238K", true);
	
	
	@Test
	public void testSeguro() {
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro!=null);
		} catch (Exception e) {}

	}

	@Test
	public void testPrecio() {
		
		/**
		 * CASOS VALIDOS
		 */
		
		// Comprobamos potencias, coberturas y minusvalia (sin siniestros)
		// Potencias (0,90) y Coberturas
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 400);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(43, cliente1, Cobertura.TERCEROSLUNAS);
			assertTrue(seguro.precio() == 600);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(89, cliente1, Cobertura.TODORIESGO);
			assertTrue(seguro.precio() == 1000);
		} catch (Exception e) {}
		
		// Potencias [90,110]
		try {
			seguro = new Seguro(90, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 420);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(100, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 420);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(110, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 420);
		} catch (Exception e) {}
		
		
		// Potencias >110
		try {
			seguro = new Seguro(150, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 480);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(150, cliente2, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 360);
		} catch (Exception e) {}
		
		// Comprobamos con siniestros
		// Menos de un año
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			assertTrue(seguro.precio() == 600);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(182));
			assertTrue(seguro.precio() == 600);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue(seguro.precio() == 600);
		} catch (Exception e) {}
		
		// Entre 1 y 3 años
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1));
			assertTrue(seguro.precio() == 450);
		} catch (Exception e) {}

		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(2));
			assertTrue(seguro.precio() == 450);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3));
			assertTrue(seguro.precio() == 450);
		} catch (Exception e) {}
		
		// Mas de 3 años o nunca
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));
			assertTrue(seguro.precio() == 400);
		} catch (Exception e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(null);
			assertTrue(seguro.precio() == 400);
		} catch (Exception e) {}
		
		fail("Not yet implemented");
	}

}
