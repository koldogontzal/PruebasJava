package utils;

import java.util.ArrayList;
import java.util.Iterator;

public class GeneradorRutas {
	
	private CuboParaHormigas cubo;
	private int numPasos;
	private ArrayList<RutaDeHormiga> lista;
	
	public GeneradorRutas(CuboParaHormigas cubo, int numPasos) {
		this.cubo = cubo;
		this.numPasos = numPasos;
		
		Vertice inicio = this.cubo.getInicio();
		this.lista =  getListaRutasRecursivo(inicio, numPasos);
		
	}
	
	public void setNumPasos(int numPasos) {
		this.numPasos = numPasos;
		
		Vertice inicio = this.cubo.getInicio();
		this.lista =  getListaRutasRecursivo(inicio, numPasos);
		
	}
	
	public int getNumPasos() {
		return this.numPasos;
	}
	
	public ArrayList<RutaDeHormiga> getListaRutas() {		
		return this.lista;
	}
	
	private ArrayList<RutaDeHormiga> getListaRutasRecursivo(Vertice v, int pasos) {
		ArrayList<RutaDeHormiga> ret = new ArrayList<RutaDeHormiga>();
	
		if (pasos > 0) {
			Vertice dx = v.getVecinoX();
			Vertice dy = v.getVecinoY();
			Vertice dz = v.getVecinoZ();
			
			ArrayList<RutaDeHormiga> listax = getListaRutasRecursivo(dx, pasos - 1);
			ArrayList<RutaDeHormiga> listay = getListaRutasRecursivo(dy, pasos - 1);
			ArrayList<RutaDeHormiga> listaz = getListaRutasRecursivo(dz, pasos - 1);
			
			this.añadirListas(ret, v, listax);
			this.añadirListas(ret, v, listay);
			this.añadirListas(ret, v, listaz);
			
		} else {
			ret.add(new RutaDeHormiga(v, null));
		}
		return ret;
	}

	private void añadirListas(ArrayList<RutaDeHormiga> ret, Vertice v, ArrayList<RutaDeHormiga> lista) {
		Iterator<RutaDeHormiga> i = lista.iterator();
		while(i.hasNext()) {
			ret.add(new RutaDeHormiga(v, i.next()));
		}
	}

	public double probabilidadMuerte() {
		int numRutasMuerte = this.numeroRutasMuerte();
		
		int numRutasTotal = this.lista.size();
		
		return (1.0 * numRutasMuerte / numRutasTotal);
	}
	
	public double probabilidadMuerte(Vertice v) {
		int numRutasMuerteVerticeV = 0;
		for (RutaDeHormiga ruta : this.lista) {
			Vertice aux = ruta.getVerticeVenenoso();
			if (aux != null) {
				if (aux.equals(v)) {
					numRutasMuerteVerticeV++;
				}
			}
		}
		int numRutasTotal = this.lista.size();

		return (1.0 * numRutasMuerteVerticeV / numRutasTotal);
	}
	
	public int numeroRutasMuerte() {
		int numRutasMuerte = 0;
		for (RutaDeHormiga ruta:this.lista) {
			if (ruta.isVenenosa()) {
				numRutasMuerte++;
			}
		}
		return numRutasMuerte;
	}

}
