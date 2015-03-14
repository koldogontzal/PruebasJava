package app;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import common.CacheSprites;
import common.Escenario;

public class Celda {

	private int vidaMaxima;
	private int vidaRestante;
	
	private int numCeldasActivasParaActivacion;
	private int numCeldasLisbresParaRespirar;
	private ArrayList<Celda> contiguas;
	
	private int posX;
	private int posY;
	
	private Escenario escenario;
	private CacheSprites sprites;
	
	public static final int TAMAGNO_CELDA = 32;
	
	public static final int TIEMPO_VIDA_MINIMO = 5;	
	public static final int TIEMPO_VIDA_MAXIMO = 25;
	public static final int NUM_VECINOS_MINIMO = 1;
	public static final int NUM_VECINOS_MAXIMO = 6;
	public static final int NUM_ESPACIOS_LIBRES_MINIMO = 0;
	public static final int NUM_ESPACIOS_LIBRES_MAXIMO = 4;
	
	
	public Celda(int x, int y, Escenario escenario) {
		this.posX = x;
		this.posY = y;
		
		this.vidaMaxima = this.dameAleatorio(TIEMPO_VIDA_MINIMO, TIEMPO_VIDA_MAXIMO);
		this.vidaRestante = 0;
		
		this.numCeldasActivasParaActivacion = this.dameAleatorio(NUM_VECINOS_MINIMO, NUM_VECINOS_MAXIMO);
		this.numCeldasLisbresParaRespirar = this.dameAleatorio(NUM_ESPACIOS_LIBRES_MINIMO, NUM_ESPACIOS_LIBRES_MAXIMO);
		this.contiguas = new ArrayList<Celda>(8);
		
		this.escenario = escenario;
		this.sprites = escenario.getSpriteCache();
	}
	
	public void asociarCelda(Celda c) {
		this.contiguas.add(c);
	}
	
	public void activarCelda() {
		this.vidaRestante = this.vidaMaxima;
	}
	
	public boolean estaViva() {
		return (this.vidaRestante != 0);
	}
	
	public void actua() {
		if ((this.calculaCeldasVecinasActivas() >= this.numCeldasActivasParaActivacion) 
				&& (this.calculaCeldasVecinasLibres() >= this.numCeldasLisbresParaRespirar)) {
			this.activarCelda();
		}
		
		if (this.estaViva()) {	
			this.vidaRestante--;
		}
		
	}

	private int calculaCeldasVecinasActivas() {
		int ret = 0;
		Iterator<Celda> i = this.contiguas.iterator();
		while (i.hasNext()) {
			Celda c = i.next();
			if (c.estaViva()) {
				ret++;
			}
		}
		return ret;
	}
	
	private int calculaCeldasVecinasLibres() {
		return 8 - this.calculaCeldasVecinasActivas();
	}
	
	public void pinta(Graphics2D g){
		if (this.estaViva()) {
			g.drawImage(this.sprites.getSprite("bicho.gif"), this.posX, this.posY, this.escenario);
		} else {
			g.drawImage(this.sprites.getSprite("vacio.gif"), this.posX, this.posY, this.escenario);
		}
	}
	
	private int dameAleatorio(int min, int max) {
		return min + (int)(Math.random() * (1 + max - min));
	}
	
	@Override
	public String toString() {
		String ret = "Celda en posicion fila=" + (this.posY / TAMAGNO_CELDA) + " columna=" + (this.posX / TAMAGNO_CELDA) + "\n";
		ret = ret + "\tTiempo de vida solo: " + this.vidaMaxima + "\n";
		ret = ret + "\tNumero minimo de vecinos necesarios para vivir: " + this.numCeldasActivasParaActivacion + "\n";
		ret = ret + "\tNumero minimo de espacios libres para vivir: " + this.numCeldasLisbresParaRespirar + "\n";
		
		return ret;
	}
}
