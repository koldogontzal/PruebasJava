package tablero;

/**
 * Clase que implementa una <code>Posicion</code> del tablero del juego del
 * sudoku. Una Coordenada es una fila y una columna. Los valores permitidos para
 * ambos parámetros son [0-8], ambos inclusive. También se puede representar con
 * dos valores que serían cuadradoExterior y cuadradoInterior. El tablero del
 * sudoku se divide a su vez en 9 cuadradosExteriores de 3x3 Celdas. Estos
 * cuadradosExteriores se numeran de izquierda a derecha y de arriba a abajo
 * como:
 * <p>
 * 0 1 2
 * <p>
 * 3 4 5
 * <p>
 * 6 7 8
 * <p>
 * A su vez, dentro de las 3x3 casillas de un cuadradoExterior, se repite el
 * esquema de antes y el valor cuadradoInterior representa una Celda en
 * cuestión, que van numeradas con el mismo esquema:
 * <p>
 * 0 1 2
 * <p>
 * 3 4 5
 * <p>
 * 6 7 8
 */
public class Posicion {

	private int fila;

	private int columna;

	public static final int FILA = 1;

	public static final int COLUMNA = 2;

	public static final int CUADRADO = 3;

	/**
	 * Constructor con parámetros la fila y la columna de la
	 * <code>Posicion</code>.
	 * 
	 * @param fila
	 *            Valor de la fila. Su rango es entre [0-8], ambos inclusive.
	 * @param columna
	 *            Valor de la columna. Su rango es entre [0-8], ambos inclusive.
	 */
	public Posicion(int fila, int columna) {
		this(FILA, fila, columna);
	}

	public Posicion(int tipo, int coordenada1, int coordenada2) {
		coordenada1 = coordenada1 % 9;
		coordenada2 = coordenada2 % 9;
		switch (tipo) {
		case FILA:
			this.fila = coordenada1;
			this.columna = coordenada2;
			break;
		case COLUMNA:
			this.fila = coordenada2;
			this.columna = coordenada1;
			break;
		default:
			// Por default, el caso de CUADRADO
			this.fila = 3 * (coordenada1 / 3) + (coordenada2 / 3);
			this.columna = 3 * (coordenada1 % 3) + (coordenada2 % 3);
			break;
		}
	}

	/**
	 * Método que devuelve el valor de la fila de la <code>Posicion</code>.
	 * 
	 * @return int Valor de la fila de la Coordenada. Su rango es entre [0-8],
	 *         ambos inclusive.
	 */
	public int getFila() {
		return this.fila;
	}

	public int getColumna() {
		return this.columna;
	}

	public int getCuadradoExt() {
		return (3 * (this.fila / 3) + (this.columna / 3));
	}

	public int getCuadradoInt() {
		int difFilas = this.fila - 3 * (this.fila / 3);
		int difColumnas = this.columna - 3 * (this.columna / 3);
		return (3 * difFilas + difColumnas);
	}

	public String toString() {
		return "(" + (this.fila + 1) + "," + (this.columna + 1) + ")";
	}
}
