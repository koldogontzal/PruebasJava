package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EliminadorNumerosLineaDelCodigo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// Ahora voy a leer (fichero de entrada)
		File f2 = new File("Invaders_old.java");
		// Defino tambien el fichero de salida
		File f1 = new File("Invaders.java");
		
		// Asocia ficheros al stream
		FileReader fr = new FileReader(f2);
		FileWriter fw = new FileWriter(f1, false); // Este true hace que se a�adan cosas en vez de machacar lo que hab�a en el fichero

		
		//Cre los stream adecuados
		BufferedReader br = new BufferedReader(fr);
		PrintWriter pw = new PrintWriter(fw);
		
		// Leo una linea
		String linea = null;
		while ((linea = br.readLine()) != null) {

			// Borro la primera palabra que es el numero de linea
			String[] partes = linea.split(" ", 2);
			// escribo la nueva linea
			if (partes.length == 0) {
				pw.write(" \n");
			} else if (partes.length == 1) {
				pw.write(partes[0] + "\n");
			} else {
				pw.write(partes[1] + "\n");
			}
		}
		
		// Cierro todo
		br.close();
		fr.close();
		fw.close();
		pw.close();
	}

}
