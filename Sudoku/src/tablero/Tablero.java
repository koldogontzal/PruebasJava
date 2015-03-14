package tablero;

import tablero.GrupoCeldas;

/**
* Clase que implementa un tablero del juego del sudoku. Un tablero está formado principalmente por 81 Celdas dispuestas en un cuadrado de 9x9, aunque también está formado por distintas formaciones de 
* asociaciones de esas mismas Celdas, como son las filas, las columnas y los cuadradosExteriores.
*/
public class Tablero {
	private Celda [][] tablero;
	private GrupoCeldas [] columnas;
	private GrupoCeldas [] filas;
	private GrupoCeldas [] cuadrados;
	
	private static final int FILA = 0;
	private static final int COLUMNA = 1;
	private static final int CUADRADO = 2;
	
	public static final int SOLO_ASIGNADAS = 0;
	public static final int SOLO_ASIGNADAS_Y_UN_SOLO_VALOR_POSIBLE = 1;
	public static final int SOLO_ASIGNADAS_Y_VALOR_CUANDO_UN_SOLO_VALOR_POSIBLE = 2;
	public static final int ASIGNADAS_Y_NUM_VALORES_POSIBLES = 3;
	public static final int ASIGNADAS_Y_TODOS_LOS_VALORES_POSIBLES = 4;
	public static final int BUSCAR_VALOR_POSIBLE_DETERMINADO = 5;

	
	
/**
* Crea las 81 celdas del tablero y las guarda en varias estructuras últiles para su uso para el juego.
*/
	public Tablero () {
		// Crear la tabla principal de celdas
		tablero = new Celda [9][9];
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				tablero [i][j] = new Celda(new Coordenada(i,j)); // Cada Celda tiene también su Coordenada asociada
			}
		}		
		// Crea el GrupoCeldas de columnas
		columnas = new GrupoCeldas [9];
		for (int j=0; j<9; j++) {
			Celda [] arrayCeldasAux = new Celda[9];
			for (int i=0; i<9; i++) {
				arrayCeldasAux[i] = tablero[i][j];
			}
			columnas[j] = new GrupoCeldas (arrayCeldasAux);
		}
		// Crea el GrupoCeldas de filas
		filas = new GrupoCeldas [9];
		for (int i=0; i<9; i++) {
			Celda [] arrayCeldasAux = new Celda[9];
			for (int j=0; j<9; j++) {
				arrayCeldasAux[j] = tablero[i][j];
			}
			filas[i] = new GrupoCeldas (arrayCeldasAux);
		}
		// Crea el GrupoCeldas de cuadrados
		cuadrados = new GrupoCeldas [9];
		for (int cuaExt=0; cuaExt<9; cuaExt++) {
			Celda [] arrayCeldasAux = new Celda[9];
			for (int cuaInt=0; cuaInt<9; cuaInt++) {
				Coordenada pos = Coordenada.fromCuadrados(cuaExt, cuaInt);
				arrayCeldasAux[cuaInt] = tablero[pos.getFila()][pos.getColumna()];
			}
			cuadrados[cuaExt] = new GrupoCeldas (arrayCeldasAux);
		}	
	}

/**
* Método que dada una posición del tablero, devuelve la celda
* @param pos Coordenada de la Celda
* @return Celda La Celda buscada
*/
	public Celda getCelda (Coordenada pos) {
		return tablero[pos.getFila()][pos.getColumna()];
	}

/**
* Método que muestra por pantalla el tablero actual
* @param modo Entero definido en las constantes public static definidas con la clar, que dice cómo mostrar el tablero
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
* Método que asigna a una Celda dada su posición en el tablero, un valor dado.
* @param pos Coordenada de la Celda.
* @param valor Valor con el que se quiere asignar a la celda. Su rango es [1-9], ambos inclusive.
*/
	public void meterValorCelda (Coordenada pos, int valor) {
		meterValorCeldaRecurs (pos, valor, true);
	}
	
	private void meterValorCeldaRecurs (Coordenada pos, int valor, boolean escribir) {
		// Este método es el que se encarga verdaderamente de asignar a la Celda señalada por pos, el int valor. Es un método que realiza llamadas recursivas a él mismo, e identifica la primera vez que se le llama
		// mediante un boolean, el parámetro escribir. Cuando se le llama por primera vez, asigna el valor a la Celda. Pero esa asignación puede desencadenar que haya otras Celdas que se queden
		// con un único valor posible, con lo que la función se vuelve a llamar sobre esas celdas pero con un valor de escribir igual a false. En este caso, no se asigna a las celdas el valor sino que se fuerza 
		// sus valores posibles a contener sólo el valor, pero sin asignar la Celda.
		int fila = pos.getFila();
		int columna = pos.getColumna();
		int cuadrado = pos.getCuadradoExterior();
		// La Celda no tiene que estar asignada y además, valor tiene que ser uno de los valores posibles suyos
		if ((!tablero[fila][columna].esCeldaAsignada()) && (tablero[fila][columna].esValorPosible(valor))) {
			if (escribir) {
				// Es la primera vez que se llama al método, con lo que asigna valor al valor de la celda.
				tablero[fila][columna].putValor(valor);
			} else {
				// No es la primera vez que se llama al método, esta vez se le ha llamado recursivamente: Sólo fuerza el valor como único posible.
				tablero[fila][columna].fuerzaUnValorPosible(valor);
			}
			// A continuación, quita ese valor tanto de toda la fila, como la columna, como el cuadrado de la Celda.
			filas[fila].quitarValorPosible(valor);
			columnas[columna].quitarValorPosible(valor);
			cuadrados[cuadrado].quitarValorPosible(valor);
			// Ahora debería revisar que al quitar valores, ninguna Celda se haya quedado con solo un único valor posible.
			revisarListadoCeldasValorPosibleUnico(filas[fila].buscarCeldaConUnUnicoValorPosible(), FILA, fila);
			revisarListadoCeldasValorPosibleUnico(columnas[columna].buscarCeldaConUnUnicoValorPosible(), COLUMNA, columna);
			revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado].buscarCeldaConUnUnicoValorPosible(), CUADRADO, cuadrado);			

			// Ahora al haber quitado valores posibles de varias celdas, puede darse el caso de que existan otras celdas que, o bien en su fila o en su columna o en su cuadrado, sean las única en tener un
			// determinado valor único. Hay que buscar Celdas en esa situación. El dónde buscar, depende de la posción de la Celda actual. No explico el porqué, pero hay que mirar así:
			//
			// Revisa las filas que pueden verse afectadas
			revisarListadoCeldasValorPosibleUnico(filas[fila].buscarValoresPosiblesUnicos(), FILA, fila);
			if ((fila % 3) == 0) {
				revisarListadoCeldasValorPosibleUnico(filas[fila+1].buscarValoresPosiblesUnicos(), FILA, fila+1);
				revisarListadoCeldasValorPosibleUnico(filas[fila+2].buscarValoresPosiblesUnicos(), FILA, fila+2);				
			} else if ((fila % 3) == 1) {
				revisarListadoCeldasValorPosibleUnico(filas[fila-1].buscarValoresPosiblesUnicos(), FILA, fila-1);
				revisarListadoCeldasValorPosibleUnico(filas[fila+1].buscarValoresPosiblesUnicos(), FILA, fila+1);					
			} else {
				revisarListadoCeldasValorPosibleUnico(filas[fila-1].buscarValoresPosiblesUnicos(), FILA, fila-1);
				revisarListadoCeldasValorPosibleUnico(filas[fila-2].buscarValoresPosiblesUnicos(), FILA, fila-2);					
			}
			
			// Revisa las columnas que pueden verse afectadas
			revisarListadoCeldasValorPosibleUnico(columnas[columna].buscarValoresPosiblesUnicos(), COLUMNA, columna);
			if ((columna % 3) == 0) {
				revisarListadoCeldasValorPosibleUnico(columnas[columna+1].buscarValoresPosiblesUnicos(), COLUMNA, columna+1);
				revisarListadoCeldasValorPosibleUnico(columnas[columna+2].buscarValoresPosiblesUnicos(), COLUMNA, columna+2);				
			} else if ((columna % 3) == 1) {
				revisarListadoCeldasValorPosibleUnico(columnas[columna-1].buscarValoresPosiblesUnicos(), COLUMNA, columna-1);
				revisarListadoCeldasValorPosibleUnico(columnas[columna+1].buscarValoresPosiblesUnicos(), COLUMNA, columna+1);					
			} else {
				revisarListadoCeldasValorPosibleUnico(columnas[columna-1].buscarValoresPosiblesUnicos(), COLUMNA, columna-1);
				revisarListadoCeldasValorPosibleUnico(columnas[columna-2].buscarValoresPosiblesUnicos(), COLUMNA, columna-2);					
			}
			
			// Revisa los cuadros que pueden verse afectados
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado);			
				// columnas de cuadrados
			if ((cuadrado % 3) == 0) {
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado+1].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado+1);
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado+2].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado+2);				
			} else if ((cuadrado % 3) == 1) {
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado-1].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado-1);
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado+1].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado+1);					
			} else {
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado-1].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado-1);
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado-2].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado-2);					
			}	

				// filas de cuadrados
			if ((cuadrado / 3) == 0) {
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado+3].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado+3);
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado+6].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado+6);				
			} else if ((cuadrado / 3) == 1) {
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado-3].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado-3);
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado+3].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado+3);					
			} else {
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado-3].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado-3);
				revisarListadoCeldasValorPosibleUnico(cuadrados[cuadrado-6].buscarValoresPosiblesUnicos(), CUADRADO, cuadrado-6);					
			}
		}
	}
	
	private void revisarListadoCeldasValorPosibleUnico (int [] lista, int tipoGrupo, int numeroGrupo) {
		for (int i=0; i<9; i++) {
			if (lista[i] != 0) {
				// Cuando la posición i de lista tiene un valor distinto de cero, eso quiere decir que en la numeroGrupo estructura tipoGrupo en la posición i hay un único valor posible que es lista[i] 
				Coordenada pos;
				if (tipoGrupo == FILA) {
					pos = new Coordenada (numeroGrupo, i);
				} else if (tipoGrupo == COLUMNA) {
					pos = new Coordenada (i, numeroGrupo);
				} else {
					// tipoGrupo == CUADRADO
					pos = Coordenada.fromCuadrados (numeroGrupo, i);
				}
				if ((tablero[pos.getFila()][pos.getColumna()].numValoresPosibles() > 1) || (!tablero[pos.getFila()][pos.getColumna()].esValoresPosiblesForzada() && (tablero[pos.getFila()][pos.getColumna()].numValoresPosibles() == 1))) {
				// esta comprobacion es necesaria para evitar que se meta en recursividad infinita. Si la celda ya tiene un unico valor posible o esta asignada, entonces no llama a la funcion
					meterValorCeldaRecurs (pos, lista[i], false);
				}
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
//						if (((i==1) && (j==1)) || ((i==1) && (j==5))) {
//							System.out.println("¡Aquí estoy!");
//						}
						salir = false;
						int valor = tablero[i][j].unicoValorPosible();
						meterValorCelda(new Coordenada(i,j),valor);
					}
				}
			}
		} while (!salir);
	}
	
}