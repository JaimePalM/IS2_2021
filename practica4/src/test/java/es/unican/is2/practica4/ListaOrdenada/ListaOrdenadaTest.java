package es.unican.is2.practica4.ListaOrdenada;
import static org.junit.Assert.*;

import org.junit.Test;
//import es.unican.is2.containers.ListaOrdenada;

/**
 * Clase de prueba en JUnit de la clase ListaOrdenada.
 * @author Jesus Ortega y Jaime Palacios
 *
 */
public class ListaOrdenadaTest {

	@Test
	public void testGetyAdd() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		/**
		 * CASOS VALIDOS
		 */
		// add lista vacia y get con un elemento
		try {
			sut.add(2);
			assertTrue("Resultado esperado: 2. Obtenido: " + sut.get(0), sut.get(0) == 2);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// add con un elemento y get con varios elementos
		try {
			sut.add(4);
			assertTrue("Resultado esperado: 4. Obtenido: " + sut.get(1), sut.get(1) == 4);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// add con elementos
		try {
			sut.add(3);
			assertTrue("Resultado esperado: 3. Obtenido: " + sut.get(1), sut.get(1) == 3);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		/**
		 * CASOS NO VALIDOS
		 */
		// add null con elementos
		try {
			sut.add(null);
			fail("Debería haber lanzado excepción.");
		} catch (NullPointerException e) { }
	
		// add null lista vacia
		try {
			sut = new ListaOrdenada<Integer>();
			sut.add(null);
			fail("Debería haber lanzado excepción.");
		} catch (NullPointerException e) { }
		 
		// get con i > tamaño
		try {
			sut.get(0);
			fail("Debería haber lanzado excepción.");
		} catch (IndexOutOfBoundsException e) {	}
		
		// get con i < 0
		try {
			//sut = new ListaOrdenada<Integer>();
			sut.add(2);
			sut.add(3);
			sut.get(-1);
			fail("Debería haber lanzado excepción.");
		} catch (IndexOutOfBoundsException e) {	}
		
		// get con i > tamaño
		try {
			sut.get(5);
			fail("Debería haber lanzado excepción.");
		} catch (IndexOutOfBoundsException e) {	}
	
	}
	
	@Test
	public void testSize() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// size con lista vacia
		try {
			assertTrue("Resultado esperado: 0. Obtenido: " + sut.size(), sut.size() == 0);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// size con un elemento
		try {
			sut.add(2);
			assertTrue("Resultado esperado: 1. Obtenido: " + sut.size(), sut.size() == 1);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// size con elementos 
		try {
			sut.add(5);
			sut.add(8);
			sut.add(9);
			assertTrue("Resultado esperado: 4. Obtenido: " + sut.size(), sut.size() == 4);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
	}

	@Test
	public void testRemove() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		int valor;
		
		// remove con lista con un elemento
		try {
			sut.add(4);
			valor = sut.remove(0);
			assertTrue("Resultado esperado: 4. Obtenido: " + valor, valor == 4);
			assertTrue("Resultado esperado: 0. Obtenido: " + sut.size(), sut.size() == 0);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
	
		// remove con elementos
		try {
			sut.add(2);
			sut.add(4);
			sut.add(8);
			valor = sut.remove(2);
			assertTrue("Resultado esperado: 8. Obtenido: " + valor, valor == 8);
			assertTrue("Resultado esperado: 2. Obtenido: " + sut.size(), sut.size() == 2);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}	
	}

	@Test
	public void testClear() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// clear lista vacia
		try {
			sut.clear();
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
		
		// clear lista con un elemento
		try {
			sut.add(6);
			sut.clear();
			assertTrue("Resultado esperado: 0. Obtenido: " + sut.size(), sut.size() == 0);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}

		// clear lista con elementos
		try {
			sut.add(2);
			sut.add(4);
			sut.add(5);
			sut.add(7);
			sut.add(8);
			sut.clear();
			assertTrue("Resultado esperado: 0. Obtenido: " + sut.size(), sut.size() == 0);
		} catch (Exception e) {
			fail("No deberia haber lanzado excepción.");
		}
	}

}
