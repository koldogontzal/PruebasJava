package clases;

public class ListaPartidos {
	private Partido[] lista;
	private int numMaxPartidos;
	private int numPartidos;
	
	public ListaPartidos() {
		this(10);
	}
	
	public ListaPartidos(int numero) {
		this.numMaxPartidos = numero;
		this.lista = new Partido[numero];
		this.numPartidos = 0;
	}
	
	public void agnadirPartido(Partido part) {
		if (this.numPartidos < this.numMaxPartidos) {
			this.lista[this.numPartidos] = part;
			this.numPartidos ++;
		} else {
			System.out.println("ERROR: Ha intentado meter más Partidos que el límite establecido de " + this.numMaxPartidos);
		}
	}
	
	public Partido getPartido(int identificador) {
		Partido retorno = null;
		if (identificador < this.numPartidos) {
			retorno = this.lista[identificador];
		}
		return retorno;
	}
	
	public int getNumPartidos() {
		return numPartidos;
	}

	@Override
	public String toString() {
		String retorno = "";
		int totalEscagnos = 0;
		for (int i = 0; i < this.numPartidos; i++) {
			retorno = retorno + this.lista[i] + "\n";
			totalEscagnos = totalEscagnos + this.lista[i].getEscagnos();
		}
		retorno = retorno + "Total de escaños: " + totalEscagnos;
		return retorno;
	}
}
