package clases;

public class ParlamentoProporcional extends Parlamento {
	
	private int[] escagnosPorPartido;
	private double[] escagnosPorPartidoDecimales;
	private long totalVotos = 0L;
	
	public ParlamentoProporcional(ListaPartidos lista, int numEscagnosTotal) {
		super(lista, numEscagnosTotal);
		
		this.escagnosPorPartido = new int[lista.getNumPartidos()];
		this.escagnosPorPartidoDecimales = new double[lista.getNumPartidos()];
		
		this.inciciarProcedimientoCalcularEscagnos();
	}
	
	@Override
	protected void inciciarProcedimientoCalcularEscagnos() {
		int numPartidos = super.getListaPartidos().getNumPartidos();
		// Calcula el número total de votos emitidos
		for (int i = 0; i < numPartidos; i++) {
			this.totalVotos = this.totalVotos + super.getListaPartidos().getPartido(i).getVotos();
		}
		// Calcula las tablas provisionales con y sin decimales
		for (int i = 0; i < numPartidos; i++) {
			this.escagnosPorPartidoDecimales[i] = (double)(super.getListaPartidos().getPartido(i).getVotos() * super.getNumEscagnosTotal()) / (double)this.totalVotos;
			this.escagnosPorPartido[i] = (int)(this.escagnosPorPartidoDecimales[i] + 0.5);
		}
		
		this.calcularEscagnosPorPartido();
	}

	@Override
	protected void calcularEscagnosPorPartido() {
		int numPartidos = super.getListaPartidos().getNumPartidos();
		
		for (int i = 0; i < numPartidos; i++) {
			super.getListaPartidos().getPartido(i).setEscagnos(this.escagnosPorPartido[i]);
		}
	}
}
