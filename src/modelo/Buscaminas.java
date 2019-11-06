/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * laboratorio 1 de apoII
 * @author Camilo Vivas - camilo-152000@hotmail.com
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package modelo;
import java.util.Random;
import exception.*;

public class Buscaminas {


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) {
		this.nivel = nivel;
		perdio = false;
		inicializarPartida(nivel);
		generarMinas();
		inicializarCasillasLibres();
	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------
	
	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida(int nivel) {
		if(nivel == PRINCIPIANTE) {
			casillas = new Casilla [FILAS_PRINCIPIANTE] [COLUMNAS_PRINCIPIANTE];
			cantidadMinas = CANTIDAD_MINAS_PRINCIPANTE;
			
		}
		if(nivel ==  INTERMEDIO) {
			casillas = new Casilla [FILAS_INTERMEDIO] [COLUMNAS_INTERMEDIO];
			cantidadMinas = CANTIDAD_MINAS_INTERMEDIO;
		}
		if(nivel == EXPERTO) {
			casillas = new Casilla [FILAS_EXPERTO] [COLUMNAS_EXPERTO] ;
			cantidadMinas = CANTIDAD_MINAS_EXPERTO;
			
		}
	}
	public int darNivel() {
		return nivel;
	}
	

	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {
		for(int i = 0; i<casillas.length; i++) {
			for(int j = 0; j<casillas[0].length;j++) {
				if(casillas[i][j] == null) {
					casillas[i][j] = new Casilla(Casilla.LIBRE);
					casillas[i][j].modificarValor(cantidadMinasAlrededor(i+1, j+1));
				}
			}
		}		
	}
	

	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {
		if(nivel == PRINCIPIANTE) {
			for(int i = 0; i < cantidadMinas;) {
				int fila = (int)(Math.random()*8);
				int columna = (int)(Math.random()*8);
				if(casillas[fila][columna] == null) {
					casillas[fila][columna] = new Casilla(Casilla.MINA);
					i++;
				}
			}
		}
		else if(nivel == INTERMEDIO) {
			for(int i = 0; i < cantidadMinas;) {
				int fila = (int)(Math.random()*16);
				int columna = (int)(Math.random()*16);
				if(casillas[fila][columna] == null) {
					casillas[fila][columna] = new Casilla(Casilla.MINA);
					i++;
				}
			}
		}
		else if(nivel == EXPERTO) {
			for(int i = 0; i < cantidadMinas;) {
				int fila = (int)(Math.random()*16);
				int columna = (int)(Math.random()*30);
				if(casillas[fila][columna] == null) {
					casillas[fila][columna] = new Casilla(Casilla.MINA);
					i++;
				}
			}
		}
	}
	
	

	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una casillas
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int i, int j) {
		int cantidadMinasAlrededor = 0;
		try {
			if(casillas[i-1][j-1].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i-1][j].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i-1][j+1].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i][j+1].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i+1][j+1].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i+1][j].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i+1][j-1].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		try {
			if(casillas[i][j-1].esMina()) {
				cantidadMinasAlrededor++;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException e) {
			
		}
		return cantidadMinasAlrededor;
	}
	

	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {
		for(int i = 0; i<casillas.length; i++) {
			for(int j = 0; j<casillas[0].length; j ++) {
				if(casillas[i][j].darSeleccionada() == false) {
					casillas[i][j].destapar();
					casillas[i][j].modificarValor(cantidadMinasAlrededor(i, j));
				}		
			}
		}
	}

	/**
	 * Metodo dar del atributo casillas
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas(){
		return casillas;
	}


	/**
	 * Este metodo se encargaa de abrir una casilla
	 * Si se abre una casilla de tipo Mina, se marca que el jugador perdio el juego.
	 * @param i - la fila donde esta la casilla 
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int i, int j) {
		// TODO
		boolean posible = true;
		
		if(casillas[i][j].esMina() == true) {
			posible = false;
		}
		else {
			casillas[i][j].destapar();
			casillas[i][j].modificarValor(cantidadMinasAlrededor(i, j));
		}
		return posible;
	}


	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
		boolean gano = false;
		int contador = 0;
		int c = (casillas.length * casillas[0].length) - cantidadMinas;
		for(int i = 0; i< casillas.length && !gano; i++) {
			for(int j = 0; j<casillas[i].length && !gano; j++) {
				if(casillas[i][j].esMina() == false) {
				if(casillas[i][j].darSeleccionada() == true) {// si no es mina
					contador++;
					if(contador == c) {// no ha sido seleccionada
						gano = true;
					}
					}
				}
			}
		}
		return gano;
	}


	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo valor sea Mayor que 0
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber casillas posibles para dar una pista, retorna el mensaje no hay pistas para dar
	 */
	public String darPista() {
		String msj = "";
		boolean fin = false;
		for (int i = 0; i < casillas.length && !fin; i++) {
			for (int j = 0; j < casillas[0].length && !fin; j++) {		
				if(casillas[i][j].esMina() == false && casillas[i][j].darValor() > 0) {
					casillas[i][j].destapar();
					msj += i+","+j;
					fin = true;
				}
			}
		}
		return  msj;
	}
	
	/***
	 * Metodo dar del atributo perdio
	 * @return boolean el atributo
	 */
	public boolean darPerdio(){
		return perdio;
	}

}