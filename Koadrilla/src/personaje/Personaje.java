package personaje;

import java.awt.Color;
import java.awt.Graphics;

import util.Area;
import util.Coordenada;

public class Personaje {
	private Coordenada pos;
	private Coordenada tamagno;
	private Color color;
	private int [] recorrido;
	private int posRecorrido = 0;
	private Personaje anterior = null;
	
	
	public static final int DESPLAZAMIENTO_RECTO = 1;
	public static final int DESPLAZAMIENTO_SALTOS = 2;
	public static final int DESPLAZAMIENTO_SINUSOIDAL = 3;
	
	private Personaje () {
		
	}
	
	// El tama�o tiene que estar medido en ancho (positivo) y alto (positivo)
	public Personaje (Coordenada tamagno, Color color, int tipoDesplazamiento, int pasos, int alturaPaso) {
		this.pos = new Coordenada (400, 600);
		this.tamagno = tamagno;
		this.color = color;
		
		switch (tipoDesplazamiento) {
		case DESPLAZAMIENTO_RECTO:
			recorrido = new int[1];
			recorrido[0] = 0;
			break;
		case DESPLAZAMIENTO_SALTOS:			
			recorrido = new int[pasos];
			double radio = pasos / 2.0;
			for (int i = 0; i < pasos; i++) {
				double x = i - radio;
				if (x == 0.0) {
					recorrido[i] = (int) (alturaPaso);
				} else {
					recorrido[i] = (int) (x * Math.tan(Math.acos(x / radio)) * alturaPaso / radio);
				}
			}
			break;
		case DESPLAZAMIENTO_SINUSOIDAL:
			recorrido = new int[pasos];
			for (int i=0; i<pasos; i++) {
				recorrido[i] = (int)((1 - Math.cos(2.0 * Math.PI * i / pasos)) * 0.5 * alturaPaso);
			}
			break;			
		}
	}
	
	public void dibujar (Graphics g) {
		Coordenada posOrigen = this.pos.clone();
		posOrigen.resta(new Coordenada(0, this.recorrido[this.posRecorrido]));
		Coordenada posOtraEsquina = posOrigen.clone();
		posOtraEsquina.resta(this.tamagno);
		Area personaje = new Area (posOrigen, posOtraEsquina);
		personaje.dibujaArea(this.color, g);		
	}
	
	public void borrar (Graphics g) {
		Coordenada posOrigen = this.pos.clone();
		posOrigen.resta(new Coordenada(0, this.recorrido[this.posRecorrido]));
		Coordenada posOtraEsquina = posOrigen.clone();
		posOtraEsquina.resta(this.tamagno);
		Area personaje = new Area (posOrigen, posOtraEsquina);
		personaje.dibujaArea(Color.BLACK, g);		
	}
	
	public void irDerecha (int desplazamiento) {
		this.anterior = this.clone();
		pos.suma(new Coordenada(desplazamiento, 0));
		this.avanzarPaso();
	}
	
	public void irIzquierda (int desplazamiento) {
		this.anterior = this.clone();
		pos.suma(new Coordenada(-desplazamiento, 0));
		this.avanzarPaso();
	}
	
	public void estarQuieto() {
		this.anterior = this.clone();
		if (this.posRecorrido != 0) {
			this.avanzarPaso();
		}
	}

	private void avanzarPaso(){
			this.posRecorrido = (this.posRecorrido + 1) % this.recorrido.length;
	}
	
	
	public void esperar() {
		double otra = 0;
		for (int espera=0; espera <200000; espera++) {
			otra = otra + Math.sqrt(espera);
		}
	}
	
	@Override
	protected Personaje clone() {
		Personaje anterior = new Personaje ();
		anterior.pos = this.pos.clone();
		anterior.tamagno = this.tamagno;
		anterior.color = this.color;
		anterior.recorrido = this.recorrido;
		anterior.posRecorrido = this.posRecorrido;
		anterior.anterior = null;
		
		return anterior;
	}
	
	public Personaje getPosAnterior() {
		return anterior;
	}
}

