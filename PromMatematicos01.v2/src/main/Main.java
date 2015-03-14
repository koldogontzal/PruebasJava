package main;

import utils.CuboParaHormigas;
import utils.GeneradorRutas;
import utils.Vertice;

public class Main {
	public static void main(String[] args) {
		// Genera el cubo
		int[] numVerticesVenenosos = {6, 7};
		CuboParaHormigas cubo= new CuboParaHormigas(numVerticesVenenosos);
		
		int numPasos = 14;
		
		System.out.println("Pasos: " + numPasos);
		
		GeneradorRutas gen = new GeneradorRutas(cubo, numPasos);
		
		System.out.println("Probabilidad de morir con " + gen.getNumPasos() + " pasos: " + gen.getProbabilidadMuerte());
		System.out.println("Número de rutas: " + gen.getNumRutas());
		System.out.println("Número de rutas de muerte: " + gen.getNumRutasMuertas());
		Vertice v = cubo.getVertice(6);
		System.out.println("Probabilidad de morir en el vertice " + v + " con " + gen.getNumPasos() + " pasos: " + (1.0 * gen.getNumRutasMuertas(v) / gen.getNumRutas()));
		v = cubo.getVertice(7);
		System.out.println("Probabilidad de morir en el vertice " + v + " con " + gen.getNumPasos() + " pasos: " + (1.0 * gen.getNumRutasMuertas(v) / gen.getNumRutas()));
	}

}
