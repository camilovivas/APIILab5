import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Buscaminas;
import modelo.Casilla;

class testBuscarminas {

	@Test
	void test() {
		
	}
	@Test
	public void generarMinas(){
		Buscaminas a = new Buscaminas(Buscaminas.PRINCIPIANTE);
		a.generarMinas();
		a.inicializarCasillasLibres();
		int contador = 0;
		for(int i = 0; i<a.darCasillas().length; i++) {
			for(int j = 0; j<a.darCasillas()[0].length; j++) {
				if(a.darCasillas()[i][j].esMina() == true) {
					contador++;
				}
			}
		}
		
		assertEquals(Buscaminas.CANTIDAD_MINAS_PRINCIPANTE,contador);
	}
	
	@Test
	public void mostrarTablero() {
		Buscaminas c = new Buscaminas(Buscaminas.PRINCIPIANTE);
		c.generarMinas();
		c.inicializarCasillasLibres();
		String tablero = c.mostrarTablero();
		String esperado = "  1 2 3 4 5 6 7 8"+"\n"+"1 - - - - - - - -"+"\n"+"2 - - - - - - - -"+"\n"+"3 - - - - - - - -"+"\n"+"4 - - - - - - - -"+"\n"+"5 - - - - - - - -"+"\n"+"6 - - - - - - - -"+"\n"+"7 - - - - - - - -"+"\n"+"8 - - - - - - - -";
		assertEquals(esperado, tablero);
	}
	
	@Test
	public void recorridoEsquinaDerechaSuperior() {
		Buscaminas b = new Buscaminas(Buscaminas.PRINCIPIANTE);
		b.darCasillas()[0][6] = new Casilla(Casilla.MINA);
		b.darCasillas()[1][6] = new Casilla(Casilla.MINA);
		b.darCasillas()[1][7] = new Casilla(Casilla.MINA);
		b.inicializarCasillasLibres();
		int minas = b.recorridoEsquinaDerechaSuperior();
		assertEquals(3, minas);
	}
	
	@Test
	public void recorridoSuperior() {
		Buscaminas d = new Buscaminas(Buscaminas.PRINCIPIANTE);
		d.darCasillas()[1][3] = new Casilla(Casilla.MINA);
		d.inicializarCasillasLibres();
		int minas = d.recorridoCentroSuperior(3);
		assertEquals(1, minas);
		
	}
	
	@Test
	public void recorridoCentroInferior() {
		Buscaminas e = new Buscaminas(Buscaminas.PRINCIPIANTE);
		e.darCasillas()[6][6]= new Casilla(Casilla.MINA);
		e.inicializarCasillasLibres();
		int minas = e.recorridoCentroInferior(6);
		assertEquals(1, minas);

	
	}
	
	@Test
	public void recorridoGeneral() {
		Buscaminas f = new Buscaminas(Buscaminas.PRINCIPIANTE);
		f.darCasillas()[2][4] = new Casilla(Casilla.MINA);
		f.darCasillas()[2][2] = new Casilla(Casilla.MINA);
		f.inicializarCasillasLibres();
		int minas = f.recorridoGeneral(2, 3);
		assertEquals(2, minas);
	}
	
	@Test
	public void recorridoDerInferior() {
		Buscaminas g = new Buscaminas(Buscaminas.PRINCIPIANTE);
		g.darCasillas()[6][7]= new Casilla(Casilla.MINA);
		g.inicializarCasillasLibres();
		int r = g.recorridoDerInferior();
		assertEquals(1,r);
	}
	
	@Test
	public void recorridoLatterald() {
		Buscaminas h = new Buscaminas(Buscaminas.PRINCIPIANTE);	
		h.darCasillas()[1][6] = new Casilla(Casilla.MINA);
		h.inicializarCasillasLibres();
		int d = h.recorridoLateralDer(2);
		assertEquals(1,d);
	}
	
	@Test
	public void mostrarCantidadMinas () {
		Buscaminas i = new Buscaminas(Buscaminas.PRINCIPIANTE);	
		i.darCasillas()[1][6] = new Casilla(Casilla.MINA);
		i.inicializarCasillasLibres();
		int g = i.cantidadMinasAlrededor(3, 8);
		assertEquals(1,g);
	}
	@Test
	public void gano() {
		Buscaminas d = new Buscaminas(Buscaminas.PRINCIPIANTE);
		d.generarMinas();
		d.inicializarCasillasLibres();
		d.darCasillas()[5][5].destapar();
		boolean a =	d.gano();
		assertEquals(false, a);
		
	}

}
