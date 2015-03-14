package utils;

public class CuboParaHormigas {
	// Un cubo está formado por 8 vértices, desde el 0 hasta el 7. La hormiga empieza siempre en el 7
	private Vertice[] vertices = new Vertice[8];
	
	public CuboParaHormigas(int[] numVerticesVenenosos) {
		// Crea el cubo
		for (int i = 0; i < 8; i++) {
			this.vertices[i] = new Vertice("" + i);
		}
		
		// Crea las relaciones entre vecinos
		this.vertices[0].setVecinos(this.vertices[2], this.vertices[4], this.vertices[1]);
		this.vertices[1].setVecinos(this.vertices[0], this.vertices[3], this.vertices[5]);
		this.vertices[2].setVecinos(this.vertices[0], this.vertices[3], this.vertices[7]);
		this.vertices[3].setVecinos(this.vertices[2], this.vertices[6], this.vertices[1]);
		this.vertices[4].setVecinos(this.vertices[0], this.vertices[5], this.vertices[7]);
		this.vertices[5].setVecinos(this.vertices[6], this.vertices[4], this.vertices[1]);
		this.vertices[6].setVecinos(this.vertices[7], this.vertices[3], this.vertices[5]);
		this.vertices[7].setVecinos(this.vertices[2], this.vertices[4], this.vertices[6]);
		
		// Señala los vertices venenosos
		for (int numero: numVerticesVenenosos) {
			this.vertices[numero].setVenenoso();
		}
	}
	
	public Vertice getInicio() {
		return this.vertices[0];
	}

	public Vertice getVertice(int i) {
		return this.vertices[i];
	}
	
	public Vertice getPrimerVerticeVenenoso(long ruta, int numPasos) {
		Vertice ret = this.getInicio();
//System.out.println("Ruta " + ruta);
		NumeroTrinario numTrinRuta = new NumeroTrinario(ruta);
		for (int i = 0; i < numPasos; i++) {
			ret = ret.getVecino(numTrinRuta.getPos(i));
			if (ret.isVenenoso()) {
				break;
			}
		}
		
		return (ret.isVenenoso()? ret: null);
	}
}
