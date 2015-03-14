package utils;

public class Vertice {
	// Vertices vecinos
	private Vertice vx;
	private Vertice vy;
	private Vertice vz;
	
	// nombre del vertive
	private String nombre;
	
	// Vertice venenoso
	private boolean venenoso;
	
	public Vertice(String nombre) {
		this.nombre = nombre;
		this.venenoso = false;
		this.vx = null;
		this.vy = null;
		this.vz = null;
		
	}
	
	@Override
	public boolean equals(Object arg0) {
		Vertice aux = (Vertice)arg0;
		
		return this.nombre.equals(aux.nombre);
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	public void setVecinos(Vertice vx, Vertice vy, Vertice vz) {
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}
	
	public Vertice getVecinoX() {
		return this.vx;
	}

	public Vertice getVecinoY() {
		return this.vy;
	}
	
	public Vertice getVecinoZ() {
		return this.vz;
	}
	
	public boolean getVenenoso() {
		return this.venenoso;
	}
	
	public void setVenenoso() {
		this.venenoso = true;
	}
}
