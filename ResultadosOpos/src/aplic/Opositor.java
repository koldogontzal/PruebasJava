package aplic;

public class Opositor implements Comparable {
	
	private String dni;
	private String nombre;
	private double notaEj1;
	private double notaEj2;
	private double notaEj3;
	
	public Opositor(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
		this.notaEj1 = 0;
		this.notaEj2 = 0;
		this.notaEj3 = 0;
	}
	
	public int compareTo(Object o) {
		Double esteObjeto = new Double(this.getNota());
		Double otroObjeto = new Double(((Opositor)o).getNota());
		
		return esteObjeto.compareTo(otroObjeto);
	}
	
	@Override
	public boolean equals(Object obj) {
		Opositor op = (Opositor)obj;
		return this.getDni().equals(op.getDni());
	}


	public double getNota() {
		return this.notaEj1 + this.notaEj2 + this.notaEj3;
	}
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getNotaEj1() {
		return notaEj1;
	}


	public void setNotaEj1(double notaEj1) {
		this.notaEj1 = notaEj1;
	}


	public double getNotaEj2() {
		return notaEj2;
	}


	public void setNotaEj2(double notaEj2) {
		this.notaEj2 = notaEj2;
	}


	public double getNotaEj3() {
		return notaEj3;
	}


	public void setNotaEj3(double notaEj3) {
		this.notaEj3 = notaEj3;
	}
	
	@Override
	public String toString() {
		return this.dni + "\t" + this.nombre + "\t(" + this.getNota() + ") "+ this.notaEj1 + " - " + this.notaEj2 + " - " + this.notaEj3;
	}
	

}
