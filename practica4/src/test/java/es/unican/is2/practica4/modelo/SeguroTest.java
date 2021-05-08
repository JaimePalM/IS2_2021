package es.unican.is2.practica4.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.*;

import es.unican.is2.practica4.modelo.Cliente;
import es.unican.is2.practica4.modelo.Cobertura;
import es.unican.is2.practica4.modelo.Seguro;
import es.unican.is2.practica4.modelo.Seguro.DatoIncorrectoException;;

/**
 * Clase de prueba en JUnit de la clase Seguro.
 * @author Jesus Ortega y Jaime Palacios
 *
 */
public class SeguroTest {

	private Seguro seguro;
	// Crea clientes con datos buenos pero da igual cuales
	private Cliente cliente1 = new Cliente("Pepe", "72191738R", false);
	private Cliente cliente2 = new Cliente("Manolo", "72152238K", true);
	
	@Before
	public void testGetPotencia() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 1. Obtenido: " + seguro.getPotenciaCV(), seguro.getPotenciaCV() == 1);
		} catch (Exception e) { 
			fail("No deberia haber lanzado excepción.");
		}
	}
	
	@Test
	public void testGetTomador() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.getTomadorSeguro().equals(cliente1));
		} catch (Exception e) { 
			fail("No deberia haber lanzado excepción.");
		}
	}
	
	@Test
	public void testGetCobertura() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: TERCEROS. Obtenido: " + seguro.getCobertura().name(), seguro.getCobertura() == Cobertura.TERCEROS);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
	}
	
	@Test
	public void testGetFechaUltimoSiniestroySetFechaUltimoSiniestro() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
			assertTrue(seguro.getFechaUltimoSiniestro().equals(LocalDate.now().minusDays(1)));
		} catch (DatoIncorrectoException e) { 
			fail("No deberia haber lanzado excepción.");
		}
		
		/**
		 * Caso No Valido
		 */
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusDays(1));
			fail("Deberia haberse lanzado excepción");
		} catch (DatoIncorrectoException e) {}
	}
	
	
	
	@Test
	public void testSeguro() {
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro != null);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}

	}

	@Test
	public void testPrecioySeguro() {
			
		/**
		 * CASOS VALIDOS
		 */
		
		// Comprobamos potencias, coberturas y minusvalia (sin siniestros)
		// Potencias (0,90) y Coberturas
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 400. Obtenido: " + seguro.precio(), seguro.precio() == 400);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(43, cliente1, Cobertura.TERCEROS_LUNAS);
			assertTrue("Resultado esperado: 600. Obtenido: " + seguro.precio(), seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(89, cliente1, Cobertura.TODO_RIESGO);
			assertTrue("Resultado esperado: 1000. Obtenido: " + seguro.precio(), seguro.precio() == 1000);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// Potencias [90,110]
		try {
			seguro = new Seguro(90, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 420. Obtenido: " + seguro.precio(), seguro.precio() == 420);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(100, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 420. Obtenido: " + seguro.precio(), seguro.precio() == 420);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(110, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 420. Obtenido: " + seguro.precio(), seguro.precio() == 420);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
				
		// Potencias >110
		try {
			seguro = new Seguro(150, cliente1, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 480. Obtenido: " + seguro.precio(), seguro.precio() == 480);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(150, cliente2, Cobertura.TERCEROS);
			assertTrue("Resultado esperado: 360. Obtenido: " + seguro.precio(), seguro.precio() == 360);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// Comprobamos con siniestros
		// Menos de un año
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			assertTrue("Resultado esperado: 600. Obtenido: " + seguro.precio(), seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(182));
			assertTrue("Resultado esperado: 600. Obtenido: " + seguro.precio(), seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue("Resultado esperado: 600. Obtenido: " + seguro.precio(), seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// Entre 1 y 3 años
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1));
			assertTrue("Resultado esperado: 450. Obtenido: " + seguro.precio(), seguro.precio() == 450);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}

		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(2));
			assertTrue("Resultado esperado: 450. Obtenido: " + seguro.precio(), seguro.precio() == 450);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
	 	
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3));
			assertTrue("Resultado esperado: 450. Obtenido: " + seguro.precio(), seguro.precio() == 450);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// Mas de 3 años o nunca
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));
			assertTrue("Resultado esperado: 400. Obtenido: " + seguro.precio(), seguro.precio() == 400);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(null);
			assertTrue("Resultado esperado: 400. Obtenido: " + seguro.precio(), seguro.precio() == 400);
		} catch (DatoIncorrectoException e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		
		/**
		 * CASOS NO VALIDOS precio y seguro
		 */
		
		// Prueba cobertura
		try {
			seguro = new Seguro(1, cliente1, null);
			fail("Se deberia haber lanzado excepcion, cobertura nula");
		} catch (DatoIncorrectoException e) {}
		
		// Prueba potencias
		try {
			seguro = new Seguro(0, cliente1, Cobertura.TERCEROS);
			fail("Se deberia haber lanzado excepcion, potencia cero");
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(-30, cliente1, Cobertura.TERCEROS);
			fail("Se deberia haber lanzado excepcion, potencia negativa");
		} catch (DatoIncorrectoException e) {}
		
		// Prueba cliente
		try {
			seguro = new Seguro(1, null, Cobertura.TERCEROS);
			fail("Se deberia haber lanzado excepcion, cliente nulo");
		} catch (DatoIncorrectoException e) {}
		
	}

}
