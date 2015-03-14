package sudoku;

import tablero.*;
import shell.Shell;

class Sudoku {
	private Tablero tablero;
		
	public Sudoku() { 
		tablero = new Tablero();
	}
	
	public void jugar() {
//		Scanner key = new Scanner(System.in).useDelimiter("\r\n");
		
		String [] comandos = {"ma", "m1p", "mnp", "mvp", "av", "qv", "p", "r", "s", "h"};
		int [] numParametros = {0, 0, 0, 1, 3, 3, 0, 0, 0, 0};
		String [] descripcion = {"Mostrar solo las celdas que han sido asignadas.",
								"Mostrar las celdas que unicamente tienen un valor po-\n\t\t\tsible y estan aun sin asignar.",
								"Mostrar el numero de valores posibles de cada celda,\n\t\t\tusando letras: a = \"1 valor\", b = \"2 valores\", .. ,\n\t\t\ti = \"9 valores\".",
								"Mostrar las celdas que tienen el parametro como un va-\n\t\t\tlor posible.",
								"Asignar un valor a una celda. Tres parametros separados\n\t\t\tpor comas, cuyo orden es fila, columna, valor.",
								"Quitar un valor determinado de la lista de valores po-\n\t\t\tsibles de una celda. Tres parametros separados por\n\t\t\tcomas, cuyo orden es fila, columna, valor.",
								"Pista",
								"Resolver el sudoku.",
								"Salir del programa.",
								"Mostrar este menu de ayuda."
								};
		
/*	
		// Este Sudoku es dificil y no se llega a resolver
		tablero.setValorCelda(new Posicion (0,2), 7);
		
		tablero.setValorCelda(new Posicion (0,5), 6);
				
		tablero.setValorCelda(new Posicion (0,8), 8);
								
		tablero.setValorCelda(new Posicion (1,8), 3);
		
		tablero.setValorCelda(new Posicion (2,1), 3);
		
		tablero.setValorCelda(new Posicion (2,2), 6);
						
		tablero.setValorCelda(new Posicion (2,3), 2);
		
		tablero.setValorCelda(new Posicion (3,3), 4);
		
		tablero.setValorCelda(new Posicion (3,4), 9);
			
		tablero.setValorCelda(new Posicion (3,6), 1);
		
		tablero.setValorCelda(new Posicion (3,7), 6);

		tablero.setValorCelda(new Posicion (5,1), 2);
		
		tablero.setValorCelda(new Posicion (5,2), 8);
		
		tablero.setValorCelda(new Posicion (5,4), 5);
		
		tablero.setValorCelda(new Posicion (5,5), 3);
		
		tablero.setValorCelda(new Posicion (6,3), 8);

		tablero.setValorCelda(new Posicion (6,5), 2);
		
		tablero.setValorCelda(new Posicion (6,7), 7);
		
		tablero.setValorCelda(new Posicion (7,1), 9);
		
		tablero.setValorCelda(new Posicion (8,0), 3);
		
		tablero.setValorCelda(new Posicion (8,3), 5);
		
		tablero.setValorCelda(new Posicion (8,6), 2);

		tablero.setValorCelda(new Posicion (8,7), 1);
		
		// Erroneos, hay que quitarlos luego estos dos:
		//tablero.setValorCelda(new Posicion (7,4), 6);
		//tablero.setValorCelda(new Posicion (5,3), 6);
		// Estos dos de arriba, fuera, son erroneos
*/

		// Este sudoku es fácil y se resuelve solo

		tablero.setValorCelda(new Posicion (0,1), 6);
		
		tablero.setValorCelda(new Posicion (0,4), 9);
		
		tablero.setValorCelda(new Posicion (1,2), 7);				

		tablero.setValorCelda(new Posicion (1,3), 4);
		
		tablero.setValorCelda(new Posicion (2,0), 9);
		
		tablero.setValorCelda(new Posicion (2,2), 4);
				
		tablero.setValorCelda(new Posicion (2,6), 7);
		
		tablero.setValorCelda(new Posicion (2,8), 8);

		tablero.setValorCelda(new Posicion (3,1), 7);

		tablero.setValorCelda(new Posicion (3,2), 6);

		tablero.setValorCelda(new Posicion (3,4), 8);
		
		tablero.setValorCelda(new Posicion (3,8), 3);
	
		tablero.setValorCelda(new Posicion (4,0), 1);

		tablero.setValorCelda(new Posicion (4,2), 5);

		tablero.setValorCelda(new Posicion (4,3), 7);

		tablero.setValorCelda(new Posicion (4,5), 4);

		tablero.setValorCelda(new Posicion (4,6), 6);
		
		tablero.setValorCelda(new Posicion (4,8), 9);
		
		tablero.setValorCelda(new Posicion (5,0), 3);

		tablero.setValorCelda(new Posicion (5,4), 6);

		tablero.setValorCelda(new Posicion (5,6), 5);

		tablero.setValorCelda(new Posicion (5,7), 7);

		tablero.setValorCelda(new Posicion (6,0), 8);

		tablero.setValorCelda(new Posicion (6,2), 3);
		
		tablero.setValorCelda(new Posicion (6,6), 9);
			
		tablero.setValorCelda(new Posicion (6,8), 2);
	
		tablero.setValorCelda(new Posicion (7,5), 1);
//tablero.mostrar (Tablero.SOLO_ASIGNADAS_Y_UN_SOLO_VALOR_POSIBLE);		

		tablero.setValorCelda(new Posicion (7,6), 3);
//tablero.mostrar (Tablero.SOLO_ASIGNADAS_Y_UN_SOLO_VALOR_POSIBLE);		
		
		tablero.setValorCelda(new Posicion (8,4), 2);

		tablero.setValorCelda(new Posicion (8,7), 4);


		

		Shell lc = new Shell(comandos, numParametros, descripcion);
		lc.muestraComandos();
		
		boolean salir = false;
		while (!salir) {
			int [] comInt = lc.leeComando();
			
			switch (comInt[0]) {
				case 0: tablero.mostrar (Tablero.SOLO_ASIGNADAS); break;
				case 1: tablero.mostrar (Tablero.SOLO_ASIGNADAS_Y_UN_SOLO_VALOR_POSIBLE); break;
				case 2: tablero.mostrar (Tablero.ASIGNADAS_Y_NUM_VALORES_POSIBLES); break;
				case 3: tablero.mostrar (Tablero.BUSCAR_VALOR_POSIBLE_DETERMINADO, comInt[1]);break;
				case 4: tablero.setValorCelda(new Posicion (comInt[1]-1, comInt[2]-1), comInt[3]); break;
				case 5: tablero.quitarValorPosible(new Posicion (comInt[1]-1, comInt[2]-1), comInt[3]);
						// Ahora quedaría revisar el tablero para ver si este cambio modifica algo
						break;
				case 6:tablero.mostrarListaResueltas(false); break;
				case 7: // resolver
						tablero.resolver();
						break;
				case 8: salir = true; break;
				case 9: lc.muestraComandos(); break;			
			}
			
			System.out.println();
		}

	}
	
	public static void main (String [] args) {
		Sudoku	s = new Sudoku();
		s.jugar();
	}
	

	
}
