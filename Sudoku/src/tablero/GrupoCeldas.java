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
		// Es el �nico constructor v�lido
		tabla = datos;
		if (datos.length != 9) {
			System.out.println("ERROR GrupoCeldas.CONSTRUCTOR(): La tabla de entrada no contiene 9 celdas");
		}
	}
	
/**
* M�todo que quita de todas las Celdas que componen el grupo un valor posible dado
* @param valor Valor a quitar d elos valores posibles de todas las Celdas del grupo. Su rango es [1-9], ambos inclusive
*/
	public void quitarValorPosible (int valor) {
		// Borra valor de la lista de valores posibles de todas las celdas del GrupoCeldas
		for (int i=0; i<9; i++) {
			tabla[i].quitarValorPosible(valor);
		}
	}
	
/**
* M�todo que busca en el GrupoCeldas, valores dentro de los valores posibles de cada Celda que s�lo se encuentren en una �nica Celda del GrupoCeldas. Este m�todo es muy extra�o, es mejor no usarlo. S�lo lo llama el m�todo Tablero.meterValorCeldaRecurs(), y su estructura de return, es usada �nicamente por Tablero.revisarListadoCeldasValorPosibleUnico(). Esta parte tendr� que revisarla, porque no me gusta como ha quedado nada.
* @return int[] Devuelve un array con nueve enteros, que son 0 si la Celda que ocupa en el GrupoCeldas la misma posici�n que el cero en este array de enteros, no tiene ning�n valor posible �nico dentro del GrupoCeldas. Si es distinto de 0, entonces la Celda del GrupoCeldas que ocupa la misma posici�n que el entero en el array, debe forzarse a tener un �nico valor posible, que es el que se guarda en el array de enteros. 
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
		// Busca si existe un valor que s�lo aparezca una vez como posible
		for (int i=0; i<9; i++) {
			if (valoresAcumulados[i] == 1) {
				// Ha encontrado que exite uno, i, ahora busca la celda j donde est�
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
* M�todo que busca en el GrupoCeldas, Celdas que se hayan quedado con un �nico valor posibles. Este m�todo es muy extra�o, es mejor no usarlo. S�lo lo llama el m�todo Tablero.meterValorCeldaRecurs(), y su estructura de return, es usada �nicamente por Tablero.revisarListadoCeldasValorPosibleUnico(). Esta parte tendr� que revisarla, porque no me gusta como ha quedado nada.
* @return int[] Devuelve un array con nueve enteros, que son 0 si la Celda que ocupa en el GrupoCeldas la misma posici�n que el cero en este array de enteros, no tiene ning�n valor posible �nico dentro del GrupoCeldas. Si es distinto de 0, entonces la Celda del GrupoCeldas que ocupa la misma posici�n que el entero en el array, debe forzarse a tener un �nico valor posible, que es el que se guarda en el array de enteros. 
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