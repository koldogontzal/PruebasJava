package utils;

public class GeneradorRutas {
	private CuboParaHormigas cubo;
	private int numPasos;
	
	private long numRutasMuertas;
	
	public GeneradorRutas(CuboParaHormigas cubo, int numPasos) {
		this.cubo = cubo;
		this.numPasos = numPasos;
		
		this.numRutasMuertas = this.getNumRutasMuertasPrivado();
	}
	
	public long getNumRutas() {
		return (long)Math.pow(3, this.numPasos);
	}
	
	public long getNumRutasMuertas(Vertice v) {
		long ret = 0;
		
		for (long i = 0; i < this.getNumRutas(); i++) {

			Vertice vMuerte = this.cubo.getPrimerVerticeVenenoso(i, this.numPasos);
			if (v.equals(vMuerte)) {
				ret++;
			}
			
			if ((i & 0xFFFFFFFF) == 0) {
				System.out.println("Porcentaje: " +  0.001 * (int) (100000.0 * i / this.getNumRutas()));
			}
//System.out.println(i);
			
		}
		return ret;
	}

	private long getNumRutasMuertasPrivado() {
		long ret = 0;
		
		for (long i = 0; i < this.getNumRutas(); i++) {
//System.out.println("Ruta "+ i);
			Vertice vMuerte = this.cubo.getPrimerVerticeVenenoso(i, this.numPasos);
			if (vMuerte != null) {
				ret++;
			}

			if ((i & 0xFFFFFF) == 0) {
				System.out.println("Porcentaje: " + 0.001 * (int) (100000.0 * i / this.getNumRutas()));
			}
		}
		return ret;
	}
	
	public long getNumRutasMuertas() {
		return this.numRutasMuertas;
	}
	
	public int getNumPasos() {
		return this.numPasos;
	}
	
	public double getProbabilidadMuerte() {
		return (1.0 * this.numRutasMuertas / this.getNumRutas());
	}

}
