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
		d.darCasillas()[5][5].destapar();
		boolean a =	d.gano();
		assertEquals(false, a);
		
	}

}
