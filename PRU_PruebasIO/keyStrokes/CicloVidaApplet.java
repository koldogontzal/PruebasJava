package keyStrokes;

import java.awt.Graphics;
import java.applet.Applet;

public class CicloVidaApplet extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2041604950304126999L;

	int contadorInit = 0;

	int contadorStart = 0;

	int contadorPaint = 0;

	int contadorStop = 0;

	int contadorDestroy = 0;

	public void init() {
		contadorInit++;
	}

	public void start() {
		contadorStart++;
	}

	public void paint(Graphics g)

	{
		contadorPaint++;
		g.drawString("Contador Init: " + contadorInit, 25, 25);
		g.drawString("Contador Start: " + contadorStart, 25, 50);
		g.drawString("Contador Paint: " + contadorPaint, 25, 75);
		g.drawString("Contador Stop: " + contadorStop, 25, 100);
		g.drawString("Contador Destroy: " + contadorDestroy, 25, 125);
	}

	public void stop() {
		contadorStop++;
	}

	public void destroy() {
		contadorDestroy++;
	}
}  
