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
		try {
			inicializarPartida();
		} catch (ExceptionNumeroNivelElegido e) {
			System.out.println(e.getMessage());
			
		}
	
	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------
	
	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() throws ExceptionNumeroNivelElegido {

		// TODO
		if(nivel == PRINCIPIANTE) {
			casillas = new Casilla [FILAS_PRINCIPIANTE] [COLUMNAS_PRINCIPIANTE];
			cantidadMinas = CANTIDAD_MINAS_PRINCIPANTE;
			cargar();
		}
		if(nivel ==  INTERMEDIO) {
			casillas = new Casilla [FILAS_INTERMEDIO] [COLUMNAS_INTERMEDIO];
			cantidadMinas = CANTIDAD_MINAS_INTERMEDIO;
		}
		if(nivel == EXPERTO) {
			casillas = new Casilla [FILAS_EXPERTO] [COLUMNAS_EXPERTO] ;
			cantidadMinas = CANTIDAD_MINAS_EXPERTO;
			
		}
		
		if(nivel < 1 || nivel > 3) {
		 throw new ExceptionNumeroNivelElegido("por favor ingrese un numero del 1 al 3");	
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
				}
			}
		}		
	}
	public void cargar() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[0].length; j++) {
				if(casillas[i][j].darValor() != 0) {
					casillas[i][j].modificarValor(cantidadMinasAlrededor(i+1, j+1));
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
	public int cantidadMinasAlrededor(int i, int j) {//throws ExceptionPosicionElegida 
		int reguladorC = j-1;
		int reguladorF = i-1;
		int contador = 0;
		int inicioFila = reguladorF-1;
		int inicioColumna = reguladorC-1;
//			if(i>casillas.length || j>casillas[0].length) {
//				throw new ExceptionPosicionElegida("el numeor ingresado no concuerda");
//			}
//			else {
			if(i == 1 ) {// si esta en en la primera fila
				if(j < casillas[0].length && j > 1) {//si esta en la mitad
					contador = recorridoCentroSuperior(reguladorC);
				}
				else if(j == casillas[0].length) {//esquina superior derecha
					contador = recorridoEsquinaDerechaSuperior();
				}
				else {//si esta en la esquina izquierda
					contador = recorridoEsquinaIzqSuperior();
				}
			}
			else if(i == casillas.length) {//si esta en la ultima fila
				if((j-1) == 0) {// si esta en el rincon izquierdo
					contador = recorridoEsquinaIzqInferior();
				}
				else if(j < casillas[0].length && j>0) {// si esta en la mitad
				contador = recorridoCentroInferior(reguladorC);
				}
				else {
					contador = recorridoDerInferior();
				}	
			}
			else if(j == 1 && (i>1 && i<casillas.length)) {
				contador = recorridoLateralIzq(reguladorF);
			}
			else if(j == casillas[0].length && (i>1 && i<casillas.length)) {
				contador = recorridoLateralDer(reguladorF);
			}
			else {
				contador = recorridoGeneral(reguladorF, reguladorC);
			}
//		}
		return contador;
	}
	
	
	public int recorridoGeneral(int i, int j) {
		int contador = 0;
		if(casillas[i][j-1].esMina() == true) {
			contador++;
		}
		
		if(casillas[i][j+1].esMina() == true) {
			contador++;
		}
		for (int k = j-1; k < j+1; k++) {
			if(casillas[i-1][k].esMina() == true || casillas[i+1][k].esMina() == true) {
				contador++;
				
			}
			else if(casillas[i-1][k].esMina() == true && casillas[i+1][k].esMina() == true) {
				contador = contador+2;
			}
		}
		
		return contador;
	}
	
	public int recorridoEsquinaDerechaSuperior() {
		int regulador = casillas[0].length-2;
		int contador = 0;
		
		if(casillas[0][regulador].esMina()== true) {
			contador++;
		}
		for(int t = regulador; t<(regulador+2); t++) {
				if(casillas[1][t].esMina() == true) {
					contador++;
			}
		}
		
		return contador;
	}
	
	public int recorridoCentroSuperior(int j) {
		int contador = 0;		
		if(casillas[0][j+1].esMina() == true) {
			contador++;
		}
		
		if(casillas[0][j-1].esMina() == true) {
			contador++;
		}
		
		for(int g = j-1; g<j+1; g++) {
			if(casillas[1][g].esMina() == true) {
				contador++;
//				casillas[i][j].modificarValor(contador);
			}
		}
		return contador;
	}
	
	public int recorridoEsquinaIzqSuperior() {
		int contador = 0;
		if(casillas[0][1].esMina() == true) {
			contador++;
		}
		for (int k2 = 0; k2 <2; k2++) {
			if(casillas[1][k2].esMina() == true) {
				contador++;
			}
		}
		return contador;
	}
	
	public int recorridoEsquinaIzqInferior() {
		int ultimaFila = casillas.length-1;
		int contador = 0;
		if(casillas[ultimaFila][1].esMina()== true) {
			contador++;
		}
		for (int i = 0; i <2; i++) {
			if(casillas[ultimaFila-1][i].esMina() == true) {
				contador++;
			}
		}
		
		return contador;
	}
	
	public int recorridoCentroInferior(int j) {
		int ultimaFila = casillas.length-1;
		int contador =0;
		if(casillas[ultimaFila][j-1].esMina() == true) {
			contador++;
		}
		else if(casillas[ultimaFila][j+1].esMina() == true) {
			contador++;
		}
		for (int i = j-1; i < j+1; i++) {
			if(casillas[ultimaFila-1][i].esMina() == true) {
				contador++;
			}
		}
		
		return contador;
	}
	
	public int recorridoDerInferior() {
		int ultimaFila = casillas.length-1;
		int ultimaCol = casillas[0].length-1;
		int contador = 0;
		
		if (casillas[ultimaFila][ultimaCol-1].esMina() == true) {
			contador++;
		}
		for (int i = ultimaCol-1; i <= ultimaCol; i++) {
			if(casillas[ultimaFila-1][i].esMina() == true) {
				contador++;
			}
		}
		return contador;
	}
	
	public int recorridoLateralDer(int i) {
		int ultimaColumna = casillas[0].length-1;
		int contador = 0;
		
		if(casillas[i-1][ultimaColumna].esMina() == true) {
			contador++;
		}
		
		if(casillas[i+1][ultimaColumna].esMina() == true) {
			contador++;
		}
		
		for (int j = i-1; j < i+1; j++) {
			if(casillas[j][ultimaColumna-1].esMina() == true) {
				contador++;
			}
		}
		return contador;
	}
	
	public int recorridoLateralIzq(int i) {
		int contador = 0;
		if(casillas[i-1][0].esMina() == true) {
			contador++;
		}
		
		if(casillas[i+1][0].esMina() == true) {
			contador++;
		}
		for (int j = i-1; j <i+1; j++) {
			if(casillas[j][1].esMina() == true) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {
		
		Random i = new Random();
		Random j = new Random();
		int filas = casillas.length;
		int columnas = casillas[0].length;
		boolean listo = false;
		int contador = 0;
			while(!listo) {
				if(casillas[i.nextInt(filas)][j.nextInt(columnas)] == null) {
						casillas[i.nextInt(filas)][j.nextInt(columnas)] = new Casilla(Casilla.MINA);
						contador++;
				}
				else if(casillas[i.nextInt(filas)][j.nextInt(columnas)] != null){
					casillas[i.nextInt(filas)][j.nextInt(columnas)] = new Casilla(Casilla.MINA);
					contador--;
				}
			if(contador == cantidadMinas) {
					listo = true;
			}
		}
	}

	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en pantalla
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {
		// TODO
		String casilla = " ";
		for(int h = 0; h<casillas[0].length; h++) {
			if(nivel == EXPERTO || nivel == INTERMEDIO) {
				if((h+1)>9) {
					casilla +=" "+(h+1);
				}
				else {
					casilla +=" "+(h+1)+" ";
				}
			}
			
			else {
				casilla +=" "+(h+1);
			}
		}
		casilla += "\n"+1;
		for(int i = 0; i<casillas.length; i ++) {
			for(int j = 0; j<casillas[0].length;j++) {
				if(nivel == EXPERTO || nivel == INTERMEDIO) {
					casilla += " "+casillas[i][j].mostrarValorCasilla()+" ";
				}
				else {
					casilla += " "+casillas[i][j].mostrarValorCasilla();
				}
				if(j == casillas[0].length-1 && i<casillas.length-1) {
					casilla += "\n"+(i+2);
				}
			}
		}

		return casilla; 
	}


	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {

		for(int i = 0; i<casillas.length; i++) {
			for(int j = 0; j<casillas[0].length; j ++) {
				if(casillas[i][j].darSeleccionada() == false) {
					casillas[i][j].destapar();
//					casillas[i][j].modificarValor(cantidadMinasAlrededor(i, j));
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
		// TODO
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
		String msj = " ";
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[0].length; j++) {
				
				if(casillas[i][j].esMina() == false && casillas[i][j].darValor() > 0) {
					casillas[i][j].destapar();
					msj += "se abrio la casilla"+i+1+j+1;
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