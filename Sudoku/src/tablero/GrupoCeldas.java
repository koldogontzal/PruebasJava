package tablero;

/**
* Clase que implementa un grupo de celdas, que puede ser una fila del tablero, o una columna o un cuadrado exterior 
*/
public class GrupoCeldas {
	private Celda [] tabla;
	
/**
* Al constructor se le pasa un array de Celdas con las 9 celdas que componen el GrupoCeldas
*/
	public GrupoCeldas (Celda [] datos) {
		// Se le pasa una tabla de 9 Celdas y crea un objeto GrupoCeldas
		// Es el único constructor válido
		tabla = datos;
		if (datos.length != 9) {
			System.out.println("ERROR GrupoCeldas.CONSTRUCTOR(): La tabla de entrada no contiene 9 celdas");
		}
	}
	
/**
* Método que quita de todas las Celdas que componen el grupo un valor posible dado
* @param valor Valor a quitar d elos valores posibles de todas las Celdas del grupo. Su rango es [1-9], ambos inclusive
*/
	public void quitarValorPosible (int valor) {
		// Borra valor de la lista de valores posibles de todas las celdas del GrupoCeldas
		for (int i=0; i<9; i++) {
			tabla[i].quitarValorPosible(valor);
		}
	}
	
/**
* Método que busca en el GrupoCeldas, valores dentro de los valores posibles de cada Celda que sólo se encuentren en una única Celda del GrupoCeldas. Este método es muy extraño, es mejor no usarlo. Sólo lo llama el método Tablero.meterValorCeldaRecurs(), y su estructura de return, es usada únicamente por Tablero.revisarListadoCeldasValorPosibleUnico(). Esta parte tendré que revisarla, porque no me gusta como ha quedado nada.
* @return int[] Devuelve un array con nueve enteros, que son 0 si la Celda que ocupa en el GrupoCeldas la misma posición que el cero en este array de enteros, no tiene ningún valor posible único dentro del GrupoCeldas. Si es distinto de 0, entonces la Celda del GrupoCeldas que ocupa la misma posición que el entero en el array, debe forzarse a tener un único valor posible, que es el que se guarda en el array de enteros. 
*/
	public int [] buscarValoresPosiblesUnicos () {
	// Devuelve una tabla de enteros con los valoresPosiblesUnicos de las celdas que forman el GrupoCeldas. 
		int [] valoresAcumulados = new int [9];
		// Rellena una tabla con todos los valores posibles de la celdas del GrupoCeldas, acumulando el numero de incidencias
		for (int i=0; i<9; i++) {
			if (!tabla[i].esCeldaAsignada()) {
				for (int j=0; j<9; j++) {
					if (tabla[i].esValorPosible(j+1)) {
						valoresAcumulados[j]++;
					}
				}
			}
		}
		int [] ValoresPosiblesUnicos = new int[9]; // Esto inicializa la tabla a ceros
		// Busca si existe un valor que sólo aparezca una vez como posible
		for (int i=0; i<9; i++) {
			if (valoresAcumulados[i] == 1) {
				// Ha encontrado que exite uno, i, ahora busca la celda j donde está
				int j=0;
				while (!tabla[j].esValorPosible(i+1)) {
					j++;
				}
				// Rellena la tabla de enteros que devolvera
				ValoresPosiblesUnicos[j] = (i+1);
			}
		}
		return ValoresPosiblesUnicos;
	}
	
/**
* Método que busca en el GrupoCeldas, Celdas que se hayan quedado con un único valor posibles. Este método es muy extraño, es mejor no usarlo. Sólo lo llama el método Tablero.meterValorCeldaRecurs(), y su estructura de return, es usada únicamente por Tablero.revisarListadoCeldasValorPosibleUnico(). Esta parte tendré que revisarla, porque no me gusta como ha quedado nada.
* @return int[] Devuelve un array con nueve enteros, que son 0 si la Celda que ocupa en el GrupoCeldas la misma posición que el cero en este array de enteros, no tiene ningún valor posible único dentro del GrupoCeldas. Si es distinto de 0, entonces la Celda del GrupoCeldas que ocupa la misma posición que el entero en el array, debe forzarse a tener un único valor posible, que es el que se guarda en el array de enteros. 
*/

	public int [] buscarCeldaConUnUnicoValorPosible () {
	// Devuelve una tabla de enteros con los valoresPosiblesUnicos de las celdas que forman el GrupoCeldas. 
		int [] ValoresPosiblesUnicos = new int [9];
		// Rellena una tabla con todos los valores posibles de la celdas del GrupoCeldas, acumulando el numero de incidencias
		for (int i=0; i<9; i++) {
			if (tabla[i].esSoloUnValorPosible()) {
				ValoresPosiblesUnicos[i] = tabla[i].unicoValorPosible();
			} else {
				ValoresPosiblesUnicos[i] = 0;
			}
		}
		return ValoresPosiblesUnicos;
	}
}