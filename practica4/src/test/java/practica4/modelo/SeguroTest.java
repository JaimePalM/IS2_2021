package practica4.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.*;

import practica4.modelo.Cliente;
import practica4.modelo.Cobertura;
import practica4.modelo.Seguro;
import practica4.modelo.Seguro.DatoIncorrectoException;;

public class SeguroTest {

	private Seguro seguro;
	// Crea clientes con datos buenos pero da igual cuales
	private Cliente cliente1 = new Cliente("Pepe", "72191738R", false);
	private Cliente cliente2 = new Cliente("Manolo", "72152238K", true);
	
	@Before
	public void testGetPotencia() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.getPotenciaCV() == 1);
		} catch (Exception e) { }
	}
	
	@Test
	public void testGetTomador() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.getTomadorSeguro().equals(cliente1));
		} catch (Exception e) { }
	}
	
	@Test
	public void testGetCobertura() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.getCobertura() == Cobertura.TERCEROS);
		} catch (Exception e) { }
	}
	
	@Test
	public void testGetFechaUltimoSiniestroySetFechaUltimoSiniestro() {
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
			assertTrue(seguro.getFechaUltimoSiniestro().equals(LocalDate.now().minusDays(1)));
		} catch (DatoIncorrectoException e) { }
	}
	
	
	
	@Test
	public void testSeguro() {
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro != null);
		} catch (Exception e) {}

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
			assertTrue(seguro.precio() == 400);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(43, cliente1, Cobertura.TERCEROS_LUNAS);
			assertTrue(seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(89, cliente1, Cobertura.TODO_RIESGO);
			assertTrue(seguro.precio() == 1000);
		} catch (DatoIncorrectoException e) {}
		
		// Potencias [90,110]
		try {
			seguro = new Seguro(90, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 420);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(100, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 420);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(110, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 420);
		} catch (DatoIncorrectoException e) {}
				
		// Potencias >110
		try {
			seguro = new Seguro(150, cliente1, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 480);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(150, cliente2, Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 360);
		} catch (DatoIncorrectoException e) {}
		
		// Comprobamos con siniestros
		// Menos de un a�o
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			assertTrue(seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(182));
			assertTrue(seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue(seguro.precio() == 600);
		} catch (DatoIncorrectoException e) {}
		
		// Entre 1 y 3 a�os
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1));
			assertTrue(seguro.precio() == 450);
		} catch (DatoIncorrectoException e) {}

		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(2));
			assertTrue(seguro.precio() == 450);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3));
			assertTrue(seguro.precio() == 450);
		} catch (DatoIncorrectoException e) {}
		
		// Mas de 3 a�os o nunca
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));
			assertTrue(seguro.precio() == 400);
		} catch (DatoIncorrectoException e) {}
		
		try {
			seguro = new Seguro(1, cliente1, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(null);
			assertTrue(seguro.precio() == 400);
		} catch (DatoIncorrectoException e) {}
		
		
		/**
		 * CASOS NO VALIDOS
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