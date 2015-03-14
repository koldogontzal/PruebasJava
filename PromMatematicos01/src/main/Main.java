package main;

import java.util.ArrayList;
import java.util.Iterator;

import utils.CuboParaHormigas;
import utils.GeneradorRutas;
import utils.RutaDeHormiga;
import utils.Vertice;

public class Main {
	public static void main(String[] args) {
		// Genera el cubo
		int[] numVerticesVenenosos = {6, 7};
		CuboParaHormigas cubo= new CuboParaHormigas(numVerticesVenenosos);
		
		GeneradorRutas gen = new GeneradorRutas(cubo, 12);
		
		ArrayList<RutaDeHormiga> lista = gen.getListaRutas();
		
		Iterator<RutaDeHormiga> i = lista.iterator();
		int num = 1;
		while (i.hasNext()) {
			RutaDeHormiga ruta = i.next();
			System.out.println("Ruta " + num + ": " + ruta);
			System.out.println("\tVenenosa: " + ruta.isVenenosa());
			System.out.println("\tPrimer vértice venenoso: " +ruta.getVerticeVenenoso());
			num++;
			System.out.println();
		}
		
		System.out.println("Probabilidad de morir con " + gen.getNumPasos() + " pasos: " + gen.probabilidadMuerte());
		System.out.println("Número de rutas de muerte: " + gen.numeroRutasMuerte() );
		Vertice v = cubo.getVertice(6);
		System.out.println("Probabilidad de morir en el vertice " + v + " con " + gen.getNumPasos() + " pasos: " + gen.probabilidadMuerte(v));
		v = cubo.getVertice(7);
		System.out.println("Probabilidad de morir en el vertice " + v + " con " + gen.getNumPasos() + " pasos: " + gen.probabilidadMuerte(v));
	}

}
