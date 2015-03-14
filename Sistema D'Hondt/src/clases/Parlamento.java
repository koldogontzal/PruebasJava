package clases;

public abstract class Parlamento {
	
	private ListaPartidos listaPartidos;
	private int numEscagnosTotal;
	
	public Parlamento(ListaPartidos listaPartidos, int numEscagnosTotal) {
		this.listaPartidos = listaPartidos;
		this.numEscagnosTotal = numEscagnosTotal;
	}
	
	protected abstract void inciciarProcedimientoCalcularEscagnos();
	
	protected abstract void calcularEscagnosPorPartido();

	public ListaPartidos getListaPartidos() {
		return this.listaPartidos;
	}

	public int getNumEscagnosTotal() {
		return numEscagnosTotal;
	}

}
