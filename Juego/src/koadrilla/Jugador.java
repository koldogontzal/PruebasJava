package koadrilla;

import java.awt.event.KeyEvent;

import common.Escenario;

public class Jugador {
	
	private static final int VELOCIDAD_JUGADOR = 5;	
	private int vx;
	private int vy;
	
	private boolean up, right, down, left;
	
	private Escenario escenario;
	
	private int posX;
	private int posY;	
	
	
	public Jugador(Escenario escenario) {
		this.escenario = escenario;
		
		this.posX = 0;
		this.posY = 0;
	}

	public void teclaPulsada(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
		this.actualizarVelocidad();
	}

	private void actualizarVelocidad() {
		vx = 0;
		vy = 0;
		if (down)
			vy = VELOCIDAD_JUGADOR;
		if (up)
			vy = -VELOCIDAD_JUGADOR;
		if (left)
			vx = -VELOCIDAD_JUGADOR;
		if (right)
			vx = VELOCIDAD_JUGADOR;
	}

	public void teclaSoltada(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		}
		this.actualizarVelocidad();
	}
	
	public void actua() {
		this.posX += this.vx;
		this.posY += this.vy;
		
		if (this.posX < 0) 
			this.posX = 0;

		if (this.posY < 0)
			this.posY = 0;
		
		if (this.posX + Escenario.ANCHO_VISTA > Escenario.ANCHO_MUNDO)
			this.posX = Escenario.ANCHO_MUNDO - Escenario.ANCHO_VISTA;
		
		if (this.posY + Escenario.ALTO_VISTA > Escenario.ALTO_MUNDO)
			this.posY = Escenario.ALTO_MUNDO - Escenario.ALTO_VISTA;
		
		this.escenario.setPuntoDeVista(this.posX, this.posY);
		
	}

}
