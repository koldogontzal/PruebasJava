package tablero;

/**
* Clase que implementa una corrdenada del tablero del juego del sudoku. Una Coordenada es una fila y una columna. Los valores permitidos para ambos parámetros son [0-8], ambos inclusive. También se puede representar con dos valores que serían cuadradoExterior y cuadradoInterior. El tablero del 
* sudoku se divide a su vez en 9 cuadradosExteriores de 3x3 Celdas. Estos cuadradosExteriores se numeran de izquierda a derecha y de arriba a abajo como:
* <p>0 1 2
* <p>3 4 5
* <p>6 7 8
* <p>
* A su vez, dentro de las 3x3 casillas de un cuadradoExterior, se repite el esquema de antes y el valor cuadradoInterior representa una Celda en cuestión, que van numeradas con el mismo esquema:
* <p>0 1 2
* <p>3 4 5
* <p>6 7 8
*/
public class Coordenada {
	private int fila;
	private int columna;

/**
* Constructor por defecto,  fila 1 y columna 1.
*/
	public Coordenada () {
		this(0,0);
	}

/**
* Constructor con parámetros la fila y la columna de la Coordenada.
* @param fila Valor de la fila. Su rango es entre [0-8], ambos inclusive.
* @param columna Valor de la columna. Su rango es entre [0-8], ambos inclusive.
*/
	public Coordenada (int fila, int columna) {
		if ((fila>=0) && (fila<9)) {
			this.fila = fila;
		} else {
			System.out.println ("ERROR Coordenada.Constructor(): Valor de fila fuera de rango");
		}	
		if ((columna>=0) && (columna<9)) {
			this.columna = columna;
		} else {
			System.out.println ("ERROR Coordenada.Constructor(): Valor de columna fuera de rango");
		}
	}

/**
* Método estático que devuelve una Coordenada creada a partir de sus cuadradoExterior y cuadradoInterior.
* @param cuadradoExterior Valor del cuadrado exterior. Ver definición de la clase. Su rango es entre [0-8], ambos inclusive.
* @param cuadradoInterior Valor del cuadrado interior. Ver definición de la clase. Su rango es entre [0-8], ambos inclusive.
* @return Coordenada Devuelve una Coordenada creada con los valores de sus cuadrados.
*/
	public static Coordenada fromCuadrados (int cuadradoExterior, int cuadradoInterior) {
		int fila = 0;
		int columna = 0;
		if ((cuadradoExterior<9) && (cuadradoExterior>=0) && (cuadradoInterior<9) && (cuadradoInterior>=0)) {
			fila = 3 * (cuadradoExterior / 3) + (cuadradoInterior / 3);
			columna = 3 * (cuadradoExterior % 3) + (cuadradoInterior % 3);
		} else {
			System.out.println("ERROR Coordenada.deCuadrados(): Valor de los cuadrados fuera de rango");
		}
		return new Coordenada(fila, columna);
	}
	
/**
* Método que devuelve el valor de la fila de la Coordenanda.
* @return int Valor de la fila de la Coordenada. Su rango es entre [0-8], ambos inclusive.
*/
	public int getFila () {
		return this.fila;
	}

/**
* Método que devuelve el valor de la columna de la Coordenanda
* @return int Valor de la columna de la Coordenada. Su rango es entre [0-8], ambos inclusive.
*/
	public int getColumna () {
		return this.columna;
	}

/**
* Método que devuelve el valor del cuadrado exterior de la Coordenanda. Ver definición de la clase para explicación.
* @return int Valor del cuadrado exterior de la Coordenanda. Su rango es entre [0-8], ambos inclusive.
*/
	public int getCuadradoExterior () {
		return (3 * (fila/3) + (columna/3));
	}
	
/**
* Método que devuelve el valor del cuadrado interior de la Coordenanda. Ver definición de la clase para explicación.
* @return int Valor del cuadrado interior de la Coordenanda. Su rango es entre [0-8], ambos inclusive.
*/
	public int getCuadradoInterior () {
		int difFilas = fila - (fila / 3);
		int difColumnas = columna - (columna / 3);
		
		return (3 * difFilas + difColumnas);
	}
	
/**
* Método que escribecon formato (fila, columna) la Coordenada por pantalla.
*/
	public void mostrar() {
		System.out.print("("+fila+","+columna+")");
	}
}