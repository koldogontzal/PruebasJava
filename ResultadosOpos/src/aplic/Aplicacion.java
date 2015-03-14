package aplic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Aplicacion {
	
	ArrayList<Opositor> listaOpositores;
	
	public Aplicacion() {
		this.listaOpositores = new ArrayList<Opositor>(200);
		
		// Leer datos primer ejercicio
		this.leePrimerEj();
		
		// Leer datos segundo ejercicio
		this.leeSegundoEj();
		
		// Ordenar la lista
		this.ordenarTabla();
		
		// Resultados
		System.out.println(this);
		
		
	}

	private void ordenarTabla() {
		ArrayList<Opositor> tablaNueva = new ArrayList<Opositor>(200);
		
		while (!this.listaOpositores.isEmpty()) {
			Iterator i = this.listaOpositores.iterator();
			if (i.hasNext()) {
				Opositor opositorMasBajo = this.listaOpositores.get(0);
				
				while (i.hasNext()) {
					Opositor opositorAComparar = (Opositor)i.next();
					if (opositorAComparar.compareTo(opositorMasBajo) > 0) {
						opositorMasBajo = opositorAComparar;
					}
				}
				tablaNueva.add(opositorMasBajo);
				this.listaOpositores.remove(opositorMasBajo);
			}
		}
		
		this.listaOpositores = tablaNueva;
	}

	public static void main(String[] args) {
		new Aplicacion();
	}
	
	private void leeSegundoEj() {
		File filePrimerEj = new File("Datos/segundo_ejercicio.txt");
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filePrimerEj));
			String linea;
			
			while ((linea = bf.readLine()) != null) {
				Opositor opFichero = this.getOpositor(linea);
				Opositor opLista = this.buscarOpositorEnLista(opFichero);
				double nota2 = this.getNota(linea);
				opLista.setNotaEj2(nota2);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	private Opositor buscarOpositorEnLista(Opositor opFichero) {
		int pos = this.listaOpositores.indexOf(opFichero);
		if (pos == -1) {
			System.out.println("No se encuentra el opositor " + opFichero);
			return null;
		} else {
			return this.listaOpositores.get(pos);
		}
	}

	private void leePrimerEj() {
		File filePrimerEj = new File("Datos/primer_ejercicio.txt");
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filePrimerEj));
			String linea;
			
			while ((linea = bf.readLine()) != null) {
				Opositor op = this.getOpositor(linea);
				op.setNotaEj1(this.getNota(linea));
				this.listaOpositores.add(op);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private Opositor getOpositor(String linea) {
		int posPrimerEspacio = linea.indexOf(" ");
		int posUltimoEspacio = linea.lastIndexOf(" ");
		String dni = linea.substring(0, posPrimerEspacio);
		String nombre = linea.substring(posPrimerEspacio + 1, posUltimoEspacio);

		Opositor op = new Opositor(dni, nombre);
		return op;
	}
	
	private double getNota(String linea) {
		int posUltimoEspacio = linea.lastIndexOf(" ");

		String notaConComa = linea.substring(posUltimoEspacio + 1, linea.length());
		String notaConPunto = notaConComa.replace(',', '.');
		
		return Double.parseDouble(notaConPunto);
	}
	
	@Override
	public String toString() {
		Iterator i = this.listaOpositores.iterator();
		int puesto = 1;
		String str = "";
		while (i.hasNext()) {
			str = str + "(" + puesto + ")\t" + i.next() + "\n";
			puesto++;
		}
		return str;
	}

}
