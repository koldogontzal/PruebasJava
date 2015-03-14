package utils;

public class Vertice {
	// Vertices vecinos
	private Vertice[] vecinos;
	
	// nombre del vertive
	private String nombre;
	
	// Vertice venenoso
	private boolean venenoso;
	
	public Vertice(String nombre) {
		this.nombre = nombre;
		this.venenoso = false;
		this.vecinos = new Vertice[3];
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 != null) {
			Vertice aux = (Vertice)arg0;
		
			return this.nombre.equals(aux.nombre);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	public void setVecinos(Vertice v0, Vertice v1, Vertice v2) {
		this.vecinos[0] = v0;
		this.vecinos[1] = v1;
		this.vecinos[2] = v2;		
	}
	
	public Vertice getVecino(int pos) {
		return this.vecinos[pos];
	}
	
	public boolean isVenenoso() {
		return this.venenoso;
	}
	
	public void setVenenoso() {
		this.venenoso = true;
	}
}
