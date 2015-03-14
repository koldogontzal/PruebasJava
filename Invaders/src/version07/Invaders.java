package version07;

/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */

/*
 Ahora que ya tenemos un sistema que nos permite cargar imágenes de forma cómoda, es hora de ver cómo hacer que las cosas en nuestro juego de Java cambien. De la misma forma en que todas las aplicaciones de Windows tienen un núcleo llamado "bucle de proceso de mensajes" o Message Loop, algo muy similar sucede con los juegos. Un juego básicamente de forma continua está haciendo lo siguiente:

 1. Actualizar el "estado del mundo" o el "escenario">
 2. Si es preciso, refrescar la pantalla
 3. Volver al punto 1 

 En el primer paso es donde ocurren todos los movimientos de monstruos, las acciones del jugador, la aparición o desaparición de elementos, los cambios de nivel, la comprobación de si se ha terminado el juego, etc. Por supuesto todo esto no se hace en la misma rutina, pero sí se puede pensar que constituye una etapa claramente diferenciada de la siguiente, en la que - si ha habido cambios en lo que el jugador está viendo, entonces se dibuja de nuevo

 Vamos a implementar este sistema en nuestro juego Java. El bucle principal estará en una rutina a la que llamaremos game(). La rutina que actualiza el estado del mundo será updateWorld() y finalmente la que dibuja la pantalla de momento seguirá siendo paint().

 En este paso, la "actualización del mundo" consistirá en que el bicho cambiará de posición aleatoriamente. Por eso necesitaremos dos variables gloales llamadas posX y posY que guardarán su posición, de forma que el método updateWorld() pueda modificarlas y el método paint() pueda utilizarlas para pintar al bicho en su posición:

 Con esta idea, el código queda como sigue:

 Hemos aprovechado también a incluir el método setResizable(false) en el constructor para que el usuario no pueda redimensionar el tamaño de la ventana.

 Si ejecutamos este programa, nos encontraremos con una sorpresa y es que la ventana se llena inmediatamente de bichos:

 Claramente tenemos un problema : como somos nosotros los que manualmente estamos llamando al método paint(), no estamos borrando lo que había antes en la pantalla, con lo que el bicho se pinta en la nueva posición pero sin que sea eliminado de la antigua.

 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Invaders extends Canvas {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	public HashMap sprites;
	public int posX, posY;

	public Invaders() {
		sprites = new HashMap();
		posX = WIDTH / 2;
		posY = HEIGHT / 2;

		JFrame ventana = new JFrame("Invaders");
		JPanel panel = (JPanel) ventana.getContentPane();
		setBounds(0, 0, WIDTH, HEIGHT);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, WIDTH, HEIGHT);
		ventana.setVisible(true);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ventana.setResizable(false);
	}

	public BufferedImage loadImage(String nombre) {
		URL url = null;
		try {
			//url = getClass().getClassLoader().getResource(nombre);
			return ImageIO.read(new File(nombre));
		} catch (Exception e) {
			System.out.println("No se pudo cargar la imagen " + nombre + " de "
					+ url);
			System.out.println("El error fue : " + e.getClass().getName() + " "
					+ e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public BufferedImage getSprite(String nombre) {
		BufferedImage img = (BufferedImage) sprites.get(nombre);
		if (img == null) {
			img = loadImage("res/" + nombre);
			sprites.put(nombre, img);
		}
		return img;
	}

	public void paint(Graphics g) {
		g.drawImage(getSprite("bicho.gif"), posX, posY, this);
	}

	public void updateWorld() {
		posX = (int) (Math.random() * WIDTH);
		posY = (int) (Math.random() * HEIGHT);
	}

	public void game() {
		while (isVisible()) {
			updateWorld();
			paint(getGraphics());
		}
	}

	public static void main(String[] args) {
		Invaders inv = new Invaders();
		inv.game();
	}
}
