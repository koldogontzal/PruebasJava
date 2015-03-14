package utils;

import java.util.ArrayList;

public class ListaDePuntos extends ArrayList<Punto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1593514734254955865L;

	private double[] distancias;
	
	public ListaDePuntos() {
		super();
	}
	
	public ListaDePuntos(int tamagno) {
		super(tamagno);
	}
	
	public Punto centroDeMasa() {
		int numPuntos = this.size();
		int sumaX = 0;
		int sumaY = 0;
		for (int i = 0; i < numPuntos; i++) {
			sumaX = sumaX + this.get(i).getCoordX();
			sumaY = sumaY + this.get(i).getCoordY();			
		}
		int retornoX = sumaX / numPuntos;
		int retornoY = sumaY / numPuntos;
		return new Punto(retornoX, retornoY);
	}
	
	public void ordenarCercaniaDesde(Punto p) {
		int numPuntos = this.size();
		this.distancias = new double[numPuntos];
		
		Punto puntoComparacion = p;
		
		for (int i = 0; i < numPuntos; i++) {
			// Calcula las distancias
			for (int j = i; j < numPuntos; j++) {
				this.distancias[j] = puntoComparacion.distanciaHasta(this.get(j));				
			}			
			// Busca el �ndice de la distancia m�s corta
			int indice = i;
			for (int j = i + 1; j < numPuntos; j++) {
				if (this.distancias[j] < this.distancias[indice]) {
					indice = j;
				}
			}
			// Intercambia posiciones en la tabla (la "i" con la "indice")
			Punto aux = this.get(indice);
			this.set(indice, this.get(i));
			this.set(i, aux);
			// Fija el nuevo punto de referencia como el Punto m�s cercano al de esta iteraci�n
			puntoComparacion = aux;
		}		
	}	
}
