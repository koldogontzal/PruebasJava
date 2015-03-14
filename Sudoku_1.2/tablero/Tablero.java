package tablero;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que implementa un tablero del juego del sudoku. Un tablero está formado
 * principalmente por 81 Celdas dispuestas en un cuadrado de 9x9, aunque también
 * está formado por distintas formaciones de asociaciones de esas mismas Celdas,
 * como son las filas, las columnas y los cuadradosExteriores.
 */
public class Tablero {
	private Celda[][] tablero = new Celda[9][9];

	private List<Celda> listaResueltas = new ArrayList<Celda>(81);

	public static final int SOLO_ASIGNADAS = 0;

	public static final int SOLO_ASIGNADAS_Y_UN_SOLO_VALOR_POSIBLE = 1;

	public static final int SOLO_ASIGNADAS_Y_VALOR_CUANDO_UN_SOLO_VALOR_POSIBLE = 2;

	public static final int ASIGNADAS_Y_NUM_VALORES_POSIBLES = 3;

	public static final int ASIGNADAS_Y_TODOS_LOS_VALORES_POSIBLES = 4;

	public static final int BUSCAR_VALOR_POSIBLE_DETERMINADO = 5;

	/**
	 * Crea las 81 celdas del tablero y las guarda en varias estructuras últiles
	 * para su uso para el juego.
	 */
	public Tablero() {
		// Crear la tabla principal de celdas
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.tablero[i][j] = new Celda(new Posicion(i, j));
				// Cada Celda tiene también su Coordenada asociada
			}
		}
	}

	private Celda getCelda(Posicion pos) {
		return this.tablero[pos.getFila()][pos.getColumna()];
	}
	
	public void quitarValorPosible (Posicion pos, int valor) {
		Celda celda = this.getCelda(pos);
		celda.quitarValorPosible(valor);		
	}

	public void setValorCelda(Posicion pos, int valor) {
		setValorCeldaRecursivo(pos, valor, 0);
	}

	private void setValorCeldaRecursivo(Posicion pos, int valor, int accion) {
		// accion = 0: Es la primera vez que se llama al método, mete el valor
		// 				en la Celda
		// accion = 1: Es una llamada recursiva, solo fuerza los valores
		// 				posibles
		Celda celda = getCelda(pos);
		if (accion == 0) {
			celda.putValor(valor);
			if (this.listaResueltas.contains(celda)) {
				this.listaResueltas.remove(celda);
			}
		} else {
			celda.fuerzaUnValorPosible(valor);
			if (!this.listaResueltas.contains(celda)) {
				this.listaResueltas.add(celda);
			}
		}
		// Quita el valor de los posibles valores de la fila, columna y cuadrado de la Posicion pos.
		quitaValorPosibleDeFilaColumnaCuadrado(pos, valor);

		// Revisa si han quedado Celdas con un único valor posible
		BuscaCeldasUnUnicoValorPosible(Posicion.FILA, pos.getFila());
		BuscaCeldasUnUnicoValorPosible(Posicion.COLUMNA, pos.getColumna());
		BuscaCeldasUnUnicoValorPosible(Posicion.CUADRADO, pos.getCuadradoExt());

		// Revisa si han quedado Celdas que o bien en su fila, o columna o cuadrado, son las únicas que tienen un determinado valor posible
		// Revisa las filas
		int fila = pos.getFila();
		if ((fila % 3) == 0) {
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.FILA, fila + 1);
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.FILA, fila + 2);
		} else if ((fila % 3) == 1) {
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.FILA, fila - 1);
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.FILA, fila + 1);
		} else {
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.FILA, fila - 1);
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.FILA, fila - 2);
		}
		// Revisa las columnas
		int columna = pos.getColumna();
		if ((columna % 3) == 0) {
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.COLUMNA, columna + 1);
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.COLUMNA, columna + 2);
		} else if ((columna % 3) == 1) {
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.COLUMNA, columna - 1);
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.COLUMNA, columna + 1);
		} else {
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.COLUMNA, columna - 1);
			BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(Posicion.COLUMNA, columna - 2);
		}

	}

	private void BuscaCeldaUnicaEnGrupoConUnValorPosibleDado(int tipo,
			int coordenada) {
		// Primero mira cuántas veces se repite cada uno de los valores posibles
		int[] valores = new int[9];
		Celda [] celdas = new Celda[9];
		for (int i = 0; i < 9; i++) {
			Celda celda = getCelda(new Posicion(tipo, coordenada, i));
			if (!celda.esCeldaAsignada()) {
				for (int j = 0; j < 9; j++) {
					if (celda.esValorPosible(j + 1)) {
						valores[j]++;
						celdas[j] = celda;
					}
				}
			}
		}
		// Busca sólo aquellos valores posibles que solo se repiten una vez
		// y los fuerza como únicos valores posibles
		for (int i=0; i<9; i++){
			if (valores[i] == 1) {
				if (!celdas[i].esValoresPosiblesForzada()) {
					setValorCeldaRecursivo(celdas[i].getPosicion(), i + 1, 1);
				}
			}
		}
	}

	private void BuscaCeldasUnUnicoValorPosible(int tipo, int coordenada) {
		Celda celda;
		for (int i = 0; i < 9; i++) {
			celda = getCelda(new Posicion(tipo, coordenada, i));
			if (celda.esSoloUnValorPosible() && !celda.esValoresPosiblesForzada()) {
				setValorCeldaRecursivo(celda.getPosicion(), celda
						.unicoValorPosible(), 1);
			}
		}
	}

	private void quitaValorPosibleDeFilaColumnaCuadrado(Posicion pos, int valor) {
		// Quita el valor de la Fila
		quitaValorPosibleGrupo(Posicion.FILA, pos, valor);
		// Quita el valor de la Columna
		quitaValorPosibleGrupo(Posicion.COLUMNA, pos, valor);
		// Quita el valor del Cuadrado
		quitaValorPosibleGrupo(Posicion.CUADRADO, pos, valor);
	}

	private void quitaValorPosibleGrupo(int tipo, Posicion pos, int valor) {
		Celda celda;
		int coordenada;
		int actual;
		switch (tipo) {
		case Posicion.FILA:
			coordenada = pos.getFila();
			actual = pos.getColumna();
			break;
		case Posicion.COLUMNA:
			coordenada = pos.getColumna();
			actual = pos.getFila();
			break;
		default:
			coordenada = pos.getCuadradoExt();
			actual = pos.getCuadradoInt();
			break;
		}
		
		for (int i = 0; i < 9; i++) {
			if (i != actual) {
				// Así evito intentar quitar el valor posible de la celda
				// actual. Esto no es necesario en el caso en que la celda
				// actual haya sido actualizada con un valor final. Pero si lo
				// que se intenta es forzar un único valor posible, al pasar
				// luego por aquí, intentaría borrarlo, siendo el único
				celda = this.getCelda(new Posicion(tipo, coordenada, i));
				celda.quitarValorPosible(valor);
			}
		}
	}

	
	/**
	* Método que muestra por pantalla el tablero actual
	* @param modo Entero definido en las constantes public static definidas con la clase, que dice cómo mostrar el tablero
	*/
		public void mostrar (int modo) {
			// Si es cierto, muestra una letra en cada celda, para indicar cuántos valores posibles hay.
			System.out.println("      c o l u m n a   ");
			System.out.println("    1 2 3 4 5 6 7 8 9 ");
			System.out.println("   -------------------");
			for (int i=0; i<9; i++) {
				if ( i == 2) {
					System.out.print("f ");
				} else if (i == 3) {
					System.out.print("i ");
				} else if (i == 5) {
					System.out.print("l ");
				} else if (i == 6) {
					System.out.print("a ");
				} else {
					System.out.print("  ");
				}
				System.out.print((i+1) + "|");
				for (int j=0; j<9; j++) {
					if (tablero[i][j].esCeldaAsignada()) {
						// Si la Celda está asignada, escribe su número
						System.out.print(tablero[i][j].getValor());	
					} else {
						// Si la Celda no está asignada, entonces depende de modo
						
						switch (modo) {
							case SOLO_ASIGNADAS: System.out.print(" "); break;
							
							case SOLO_ASIGNADAS_Y_UN_SOLO_VALOR_POSIBLE: if (tablero[i][j].esSoloUnValorPosible()) {System.out.print("X");} else {System.out.print(" ");}; break;
							
							case SOLO_ASIGNADAS_Y_VALOR_CUANDO_UN_SOLO_VALOR_POSIBLE: if (tablero[i][j].esSoloUnValorPosible()) {System.out.print(tablero[i][j].unicoValorPosible());} else {System.out.print(" ");}; break;
							
							case ASIGNADAS_Y_NUM_VALORES_POSIBLES: char letraPosible = (char)(96 + tablero[i][j].numValoresPosibles()); System.out.print(letraPosible); break;
						
							case ASIGNADAS_Y_TODOS_LOS_VALORES_POSIBLES: tablero[i][j].escribirValoresPosibles(); System.out.print("-"); break;
						}
					}
					if ((j % 3) !=2) {
						System.out.print(" ");
					} else {
						System.out.print("|");			
					}
				}
				System.out.println();
				if ((i % 3) ==2) {
					System.out.println("   -------------------");
				}
			}
		}	

	/**
	* Método que muestra por pantalla el tablero actual
	* @param modo Entero definido en las constantes public static definidas con la clar, que dice cómo mostrar el tablero.
	* @param valor Entero que queremos buscar entre los valores posibles de las Celdas del teclado
	*/
		public void mostrar (int modo, int valor) {
			// Si es cierto, muestra una letra en cada celda, para indicar cuántos valores posibles hay.
			System.out.println("      c o l u m n a   ");
			System.out.println("    1 2 3 4 5 6 7 8 9 ");
			System.out.println("   -------------------");
			for (int i=0; i<9; i++) {
				if ( i == 2) {
					System.out.print("f ");
				} else if (i == 3) {
					System.out.print("i ");
				} else if (i == 5) {
					System.out.print("l ");
				} else if (i == 6) {
					System.out.print("a ");
				} else {
					System.out.print("  ");
				}
				System.out.print((i+1) + "|");
				for (int j=0; j<9; j++) {
					if (tablero[i][j].esCeldaAsignada()) {
						// Si la Celda está asignada, escribe un espacio
						System.out.print(" ");	
					} else {
						// Si la Celda no está asignada, entonces depende de modo
						
						switch (modo) {
							case BUSCAR_VALOR_POSIBLE_DETERMINADO: if (tablero[i][j].esValorPosible(valor)) {
																		System.out.print(valor);
																	} else {
																		System.out.print(" ");																
																	}
																	break;						
						}
					}
					if ((j % 3) !=2) {
						System.out.print(" ");
					} else {
						System.out.print("|");			
					}
				}
				System.out.println();
				if ((i % 3) ==2) {
					System.out.println("   -------------------");
				}
			}
		}	

		
		/**
		 * Método que resuelve el tablero del sudoku hasta que ya no encuentra más Celdas posbiles que descubrir
		 * 
		 */
			public void resolver() {
				boolean salir;
				do {
					salir = true;
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if ((!tablero[i][j].esCeldaAsignada()) && (tablero[i][j].esSoloUnValorPosible())) {
//								if (((i==1) && (j==1)) || ((i==1) && (j==5))) {
//									System.out.println("¡Aquí estoy!");
//								}
								salir = false;
								int valor = tablero[i][j].unicoValorPosible();
								this.setValorCelda(new Posicion(i,j),valor);
							}
						}
					}
				} while (!salir);
			}
	
			
	public void mostrarListaResueltas(boolean todas) {
		Iterator it = this.listaResueltas.iterator();
		if (it.hasNext()) {
			if (todas) {
				while (it.hasNext()) {
					Celda celda = (Celda) it.next();
					System.out.print(celda.getPosicion() + "-"
							+ celda.unicoValorPosible() + " ");
				}
				System.out.println();
			} else {
				Celda celda = (Celda) it.next();
				System.out.print("Pista: la Celda " + celda.getPosicion() + " sólo tiene un valor posible.");
			}
		} else {
			System.out.println("No hay ayuda posible");
		}
	}
	
}
