package app;

import java.awt.event.KeyEvent;

import common.Escenario;

public class Observador {
	
	private static final int VARIACION_TIEMPO = 5;
		
	private Escenario escenario;
	
	public Observador(Escenario escenario) {
		this.escenario = escenario;
	}

	public void teclaPulsada(KeyEvent e) {
		boolean up = false;
		boolean down = false;		
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_LEFT:
			down = true;
			break;
		case KeyEvent.VK_RIGHT:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
		
		int tiempoEspera = this.escenario.getTiempoEspera();
		if (up) {
			tiempoEspera = tiempoEspera + Observador.VARIACION_TIEMPO;
		}
		if (down) {
			tiempoEspera = Math.max(0, tiempoEspera - Observador.VARIACION_TIEMPO);
		}
		this.escenario.setTiempoEspera(tiempoEspera);
		
	}
}
