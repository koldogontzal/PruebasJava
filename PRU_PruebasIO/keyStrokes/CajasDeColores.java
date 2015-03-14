package keyStrokes;

//CajasDeColores.java 
//Dibuja una serie de rectángulos y los rellena con colores de componentes RGB aleatorias 
import java.awt.Graphics;
import java.awt.Color;

public class CajasDeColores extends java.applet.Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3809504172442132790L;

	public void paint(Graphics g)

	{
		int rojo, verde, azul;
		int ancho = getSize().width;
		int alto = getSize().height;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ancho, alto);

		for (int veces = 0; veces < 8000; veces++) {
			/*			for (int j = 30; j < (getSize().height - 25); j += 30)

			 {
			 for (int i = 5; i < (getSize().width - 25); i += 30)

			 {
			 rojo = (int) Math.floor(Math.random() * 256);
			 verde = (int) Math.floor(Math.random() * 256);
			 azul = (int) Math.floor(Math.random() * 256);
			 g.setColor(new Color(rojo, verde, azul));
			 g.fillRect(i, j, 25, 25);
			 g.setColor(Color.black);
			 g.drawRect(i - 1, j - 1, 25, 25);

			 g.draw3DRect(100, 10, 200, 150, false);
			 }
			 }
			 */
			rojo = (int) Math.floor(Math.random() * 256);
			verde = (int) Math.floor(Math.random() * 256);
			azul = (int) Math.floor(Math.random() * 256);
			g.setColor(new Color(rojo, verde, azul));

			int posX = (int) Math.floor(Math.random() * ancho);
			int posY = (int) Math.floor(Math.random() * alto);
			if (veces % 2 == 0) {
				int despX = (int) Math.floor(Math.random() * 20);
				int despY = (int) Math.floor(Math.random() * 20);

				g.drawRect(posX, posY, despX, despY);
			} else {
				int radio = (int) Math.floor(Math.random() * 1000);

				g.drawOval(posX, posY, radio, radio);
			}

		}

	}
}