package tablero;

/**
 * Clase que implementa una <code>Celda</code> del juego del sudoku. Una <code>Celda</code> tiene asociada
 * una Coordenada en el tablero, una lista de valores posibles y si se asigna,
 * un valor determinado.
 */
public class Celda {
	private Posicion pos; // Esta variable contiene la posici�n de la Celda en
	// el tablero del juego

	private int valorCelda; // Esta variable contiene el valor que tiene la
	// celda una vez que ha sido asignada. Hasta ese momento vale -1

	private boolean[] valoresPosibles; // Tabla de booleans que indica si el
	// valor de la posici�n en la tabla (+1) es un valor posible dentro de esta
	// Celda

	private int numeroValoresPosibles; // Variable que lleva cuanta de cu�ntos
	// valores de la tabla valoresPosibles son true.

	private boolean valPosibForz; // variable que indica si se ha forzalo la
	// lista da valores posibles a un unico valor

	/**
	 * Constructor, a partir de una Coordenada que indica la posici�n de la
	 * <code>Celda</code> en el tablero.
	 * 
	 * @param pos
	 *            Coordenada de la <code>Celda</code>.
	 */
	public Celda(Posicion pos) {
		this.pos = pos;
		valorCelda = -1;
		valoresPosibles = new boolean[9];
		for (int i = 0; i < 9; i++) {
			valoresPosibles[i] = true; // En principio, todos los valores son
			// posibles
		}
		numeroValoresPosibles = 9;
		valPosibForz = false;
	}

	/**
	 * M�todo que devuelve el valor de la <code>Celda</code>, en el caso de que esta haya
	 * sido ya asignada. Si a�n no lo ha sido, genera un error por pantalla.
	 * 
	 * @return int Valor asinado a la <code>Celda</code>. Su rango es [1-9], ambos inclusive.
	 *         Si a�n no ha sido asignada, devuelve -1.
	 * @see Celda#putValor
	 */
	public int getValor() {
		// Devuelve, si la celda est� asignada, el valor que contiene
		if (valorCelda != -1) {
			return valorCelda;
		} else {
			System.out
					.println("ERROR: Celda.getValor(): La celda a�n no tiene un valor asignado");
			return valorCelda;
		}
	}

	/**
	 * M�todo que asigna un �nico valor determinado a la <code>Celda</code>. Para poder
	 * asignarlo, el valor tiene que estar dentro de la lista de valores
	 * posibles de la <code>Celda</code>. En caso de que no sea as�, se genera por pantalla
	 * un mensaje de error.
	 * 
	 * @param valor
	 *            Valor que se asignar� a la <code>Celda</code>. Su rango es [1-9], ambos
	 *            inclusive.
	 * @see Celda#getValor
	 */
	public void putValor(int valor) {
		if ((valor > 0) && (valor < 10)) {
			if (valoresPosibles[valor - 1]) {
				// Rango de valor correcto y adem�s es un valor posible
				valorCelda = valor;
				for (int i = 0; i < 9; i++) {
					valoresPosibles[i] = false;
				}
				numeroValoresPosibles = 0;
				valPosibForz = false;
			} else {
				System.out.println("ERROR Celda.putValor(): El numero " + valor
						+ " no puede ir en esta celda");
			}
		} else {
			System.out.println("ERROR Celda.putValor(): El numero " + valor
					+ " esta fuera del rango [1-9]");
		}
	}

	/**
	 * M�todo que dice si la <code>Celda</code> ha sido ya asignada o no.
	 * 
	 * @return boolean Indica el estado de la <code>Celda</code>, si ha sido asignada.
	 */
	public boolean esCeldaAsignada() {
		return (valorCelda != -1);
	}

	/**
	 * M�todo que dice si un determinado valor est� dentro de la lista de
	 * valores permitidos de la <code>Celda</code>
	 * 
	 * @return boolean Indica si es cierto que valor sea un valor posible en la
	 *         <code>Celda</code>
	 */
	public boolean esValorPosible(int valor) {
		return valoresPosibles[valor - 1];
	}

	/**
	 * M�todo que devuelve el n�mero de valores posibles de la <code>Celda</code>
	 * 
	 * @return int Si la <code>Celda</code> no ha sido asignada, devuelve el n�mero de
	 *         valores posibles en la <code>Celda</code>. Su rango es [1-9], ambos inclusive.
	 *         Si la <code>Celda</code> ha sido asignada, devuelve 0.
	 */
	public int numValoresPosibles() {
		return numeroValoresPosibles;
	}

	/**
	 * M�todo que quita un valor de la lista de valores posibles de la
	 * <code>Celda</code>. Genera un error si el valor est� fuera de rango.
	 * 
	 * @param valor
	 *            Valor a quitar de la lista de valores posibles. Su rango es
	 *            [1-9], ambos inclusive.
	 */
	public void quitarValorPosible(int valor) {
		if ((valor > 0) && (valor < 10)) {
			if (valoresPosibles[valor - 1]) {
				// el valor est� en el rango permitido y adem�s, a�n es un valor
				// posible de la Celda
				if (numeroValoresPosibles > 1) {
					// A�n hay m�s valores posibles dentro de la lista, con lo
					// que quitamos �ste sin problema.
					valoresPosibles[valor - 1] = false;
					numeroValoresPosibles--;
				} else {
					// Serio problema, porque estamos intentando quitar el
					// �ltimo valor posible. Esto no deber�a ocurrir nunca.
					// Habr� que analizar cu�ndo pasa.
					System.out
							.println("ERROR Celda.quitarValorPosible(): El numero "
									+ valor
									+ " es el �nico que queda como valor posible en "
									+ this.getPosicion()+ "y no puede"
									+ " quitarse de la lista");
				}

			}
		} else {
			System.out.println("ERROR Celda.quitarValorPosible(): El numero "
					+ valor + " esta fuera del rango [1-9]");
		}
	}

	/**
	 * M�todo que dice si en una <code>Celda</code> sin asignar hay un �nico valor posible
	 * 
	 * @return boolean Cierto si la <code>Celda</code> est� a�n sin asignar y tiene un �nico
	 *         valor posible. En otro caso, falso.
	 */
	public boolean esSoloUnValorPosible() {
		return (numeroValoresPosibles == 1);
	}

	/**
	 * M�todo que, en caso de que s�lo haya un �nico valor posible para la
	 * <code>Celda</code>, devuelve dicho valor.
	 * 
	 * @return int �nico valor posible para la <code>Celda</code>, que a�n debe estar sin
	 *         asignar. Su rango es [1-9], ambos inclusive. Si existen m�s de un
	 *         �nico valor posible, genera un error por pantalla y devuelve -1.
	 *         Si la <code>Celda</code> est� asignada, genera un error por pantalla y
	 *         devuelve 0.
	 */
	public int unicoValorPosible() {
		if (esCeldaAsignada()) {
			System.out
					.println("ERROR Celda.unicoValorPosible(): La celda ya esta asignada");
			return 0;
		}
		int retorno = 0;
		for (int i = 0; i < 9; i++) {
			if (valoresPosibles[i]) {
				retorno = i + 1;
			}
		}
		if (esSoloUnValorPosible()) {
			return retorno;
		} else {
			System.out
					.println("ERROR Celda.unicoValorPosible(): Se intenta leer el valor de una celda que tiene mas de un unico valor posible");
			return -1;
		}
	}

	/**
	 * M�todo que fuerza la lista de valores posibles a un �nico valor. La <code>Celda</code>
	 * ha de estar sin asignar y dicho valor, debe estar entre la lista de
	 * valores posibles. En caso contrario, genera un mensaje de error por la
	 * pantalla.
	 * 
	 * @param valor
	 *            Valor al que se fuerza la lista de valores posibles. Su rango
	 *            es [1-9], ambos inclusive.
	 */
	public void fuerzaUnValorPosible(int valor) {
		if (esCeldaAsignada()) {
			System.out
					.println("ERROR Celda.fuerzaUnValorPosible(): La celda ya est� asignada");
		} else if (valoresPosibles[valor - 1]) {
			for (int i = 0; i < 9; i++) {
				valoresPosibles[i] = false;
			}
			valoresPosibles[valor - 1] = true;
			numeroValoresPosibles = 1;
			valPosibForz = true;
		} else {
			System.out
					.println("ERROR Celda.fuerzaUnValorPosible(): El valor posible que se quiere forzar, no es valido en esta celda");
		}
	}

	/**
	 * M�todo que dice si la lista de valores posibles ha sido forzada a un
	 * �nico valor.
	 * 
	 * @return Boolean true si la lista ha sido forzada a contener un �nico
	 *         valor posible mediante el m�todo fuerzaUnValorPosible().
	 */
	public boolean esValoresPosiblesForzada() {
		return valPosibForz;
	}

	public void escribirValoresPosibles() {
		for (int i = 0; i < 9; i++) {
			if (valoresPosibles[i]) {
				System.out.print(i + 1);
			}
		}
	}

	/**
	 * M�todo que devuelve la Coordenada de la <code>Celda</code>.
	 * @return Coordenada Coordenada de la <code>Celda</code>.
	 */
	public Posicion getPosicion() {
		return pos;
	}
}
