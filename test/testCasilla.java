import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Casilla;

class testCasilla {
	
	@Test
	public void esMina() {
		Casilla a = new Casilla(Casilla.MINA);
		boolean verificacion = a.esMina();
		assertTrue(verificacion == true);
		
	}
	
	@Test
	public void mostrarvalorCasilla() {
		Casilla b = new Casilla(Casilla.LIBRE);
		String c = b.mostrarValorCasilla();
		assertEquals("-", c);
	}
	
	@Test
	public void destapar() {
		Casilla c = new Casilla(Casilla.LIBRE);
		c.destapar();
		boolean d = c.darSeleccionada();
		assertEquals(true, d);
	}
	@Test
	public void darValor() {
		Casilla c = new Casilla(Casilla.LIBRE);
		c.modificarValor(2);
		int s = c.darValor();
		assertEquals(2, s);
	}

}
