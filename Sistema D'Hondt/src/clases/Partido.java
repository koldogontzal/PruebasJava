package clases;

public class Partido {
	private String nombre;
	private long votos;
	private int escagnos;
	
	public Partido(String nombre) {
		this(nombre, -1L, -1);
	}
	
	public Partido(String nombre, long votos) {
		this(nombre, votos, -1);
	}
	
	public Partido(String nombre, long votos, int escagnos) {
		this.nombre = nombre;
		this.votos = votos;
		this.escagnos = escagnos;
	}

	public long getVotos() {
		return votos;
	}

	public void setVotos(long votos) {
		this.votos = votos;
	}

	public int getEscagnos() {
		return escagnos;
	}

	public void setEscagnos(int escagnos) {
		this.escagnos = escagnos;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		String retorno = this.nombre + " (";
		if (this.votos != -1L) {
			retorno = retorno + "votos: " + this.votos;
		}
		if (this.escagnos != -1) {
			retorno = retorno + " - escaños: " + this.escagnos;
		}
		return retorno + ")";
	}
}
