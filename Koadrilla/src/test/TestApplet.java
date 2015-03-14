package test;

import java.awt.Color;
import java.awt.Graphics;

import personaje.Personaje;

import util.Coordenada;

public class TestApplet extends java.applet.Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -982798754720311902L;

	@Override
	public void init() {
		this.resize(1000,800);
	}
	
	public void paint(Graphics g) {
		int ancho = getSize().width;
		int alto = getSize().height;

				
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ancho, alto);
		
		Personaje mamakua = new Personaje(new Coordenada(80,200), Color.GREEN, Personaje.DESPLAZAMIENTO_SINUSOIDAL, 100, 15);
		
		for (int i=0; i<200; i++) {
			mamakua.borrar(g);
			mamakua.irIzquierda(1);
			mamakua.dibujar(g);
			mamakua.esperar();
			
		}
		for (int i=0; i<300; i++) {
			mamakua.borrar(g);
			mamakua.irDerecha(1);
			mamakua.dibujar(g);
			mamakua.esperar();
			
		}
		for (int i=0; i<80; i++) {
			mamakua.borrar(g);
			mamakua.estarQuieto();
			mamakua.dibujar(g);
			mamakua.esperar();
		}
	}
	
	
	
		/*
		Area aRoja = new Area(new Coordenada((int) (Math.random() * ancho),
				(int) (Math.random() * alto)), new Coordenada((int) (Math
				.random() * ancho), (int) (Math.random() * alto)));
		Area aVerde = new Area(new Coordenada((int) (Math.random() * ancho),
				(int) (Math.random() * alto)), new Coordenada((int) (Math
				.random() * ancho), (int) (Math.random() * alto)));
		Area aAzul = new Area(new Coordenada((int) (Math.random() * ancho),
				(int) (Math.random() * alto)), new Coordenada((int) (Math
				.random() * ancho), (int) (Math.random() * alto)));
		Area iAmarillo = Area.interseccion(aRoja, aVerde);
		Area iCyan = Area.interseccion(aAzul, aVerde);
		Area iMagenta = Area.interseccion(aAzul, aRoja);
		Area iBlanco = null;
		if (iAmarillo != null)
			iBlanco = Area.interseccion(iAmarillo, aAzul);

		aRoja.dibujaArea(Color.RED, g);
		aVerde.dibujaArea(Color.GREEN, g);
		aAzul.dibujaArea(Color.BLUE, g);
		if (iAmarillo != null)
			iAmarillo.dibujaArea(Color.YELLOW, g);
		if (iCyan != null)
			iCyan.dibujaArea(Color.CYAN, g);
		if (iMagenta != null)
			iMagenta.dibujaArea(Color.MAGENTA, g);
		if (iBlanco != null)
			iBlanco.dibujaArea(Color.WHITE, g);
		*/
}
