package clases;

import java.util.ArrayList;
import java.util.Collections;

public class ParlamentoDHondt extends Parlamento {	
	private double[][] coeficientes;
	private double coeficienteDeCorte;
	
	public ParlamentoDHondt(ListaPartidos lista, int numEscagnosTotal) {
		super(lista, numEscagnosTotal);
		
		this.coeficientes = new double [lista.getNumPartidos()] [numEscagnosTotal];
		
		this.inciciarProcedimientoCalcularEscagnos();
	}

	@Override
	protected void inciciarProcedimientoCalcularEscagnos() {
		this.calcularCoeficientes();
		
		this.ordenarCoeficientes();
		
		this.calcularEscagnosPorPartido();
		
	}
	
	private void calcularCoeficientes() {
		int numPartidos = super.getListaPartidos().getNumPartidos();
		
		for (int i = 0; i < numPartidos; i++) {
			double numVotos = (double)super.getListaPartidos().getPartido(i).getVotos();
			for (int j = 0; j < super.getNumEscagnosTotal(); j++) {
				this.coeficientes[i][j] = numVotos / (double)(j + 1);
			}
		}
	}
	
	private void ordenarCoeficientes() {
		int numPartidos = super.getListaPartidos().getNumPartidos();
		ArrayList<Double> listaCoeficientesOrdenada = new ArrayList<Double>(numPartidos * super.getNumEscagnosTotal() + 1);
		
		for (int i = 0; i < numPartidos; i++) {
			for (int j = 0; j < super.getNumEscagnosTotal(); j++) {
				listaCoeficientesOrdenada.add(new Double(this.coeficientes[i][j]));
			}
		}
		// Ordena la lista
		Collections.sort(listaCoeficientesOrdenada);
		/*
		// Imprime la lista
		for (int i = 0; i < this.numEscagnosTotal; i++) {
			System.out.println((i + 1) + ". - " + listaCoeficientesOrdenada.get(numPartidos * this.numEscagnosTotal - i - 1));
		}
		*/
		// El define el Coeficiente de corte
		this.coeficienteDeCorte = listaCoeficientesOrdenada.get((numPartidos - 1) * super.getNumEscagnosTotal());
		//System.out.println("Coeficiente de corte: " + this.coeficienteDeCorte);
	}

	@Override
	protected void calcularEscagnosPorPartido() {
		int numPartidos = super.getListaPartidos().getNumPartidos();
		
		for (int i = 0; i < numPartidos; i++) {
			boolean encontrado = false;
			int numEscagnos = 0;
			do {
				if (this.coeficientes[i][numEscagnos] < this.coeficienteDeCorte) {
					encontrado = true;
				} else {
					numEscagnos++;
				}
			} while (!encontrado);
			
			super.getListaPartidos().getPartido(i).setEscagnos(numEscagnos);
		}	
	}
}
