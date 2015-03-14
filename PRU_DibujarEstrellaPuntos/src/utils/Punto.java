package utils;

import java.io.File;

public class Punto {
	private int coordX;
	private int coordY;
	private File nombreFichero = null;
	
	public Punto() {
		this(0,0);
	}
	
	public Punto(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public Punto(Punto p) {
		this.coordX = p.getCoordX();
		this.coordY = p.getCoordY();
	}
	
	public int getCoordX() {
		return this.coordX;
	}
	
	public int getCoordY() {
		return this.coordY;
	}
	
	public void setNombreFichero(File fichero) {
		this.nombreFichero = fichero;
	}
	
	public File getNombreFichero() {
		return this.nombreFichero;
	}
	
	public double distanciaHasta(Punto p) {
		int difX = this.coordX - p.getCoordX();
		int difY = this.coordY - p.getCoordY();
		return Math.sqrt(difX * difX + difY * difY);
	}
	
	public String toString() {
		return "(" + this.coordX + "," + this.coordY + ")";
	}
}
