package version06;

/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */

/*
 El paso anterior resolvía el problema de la carga de imágenes, pero tiene una pinta bastante fea ya que es un engorro tener que estar comprobando todo el rato en todas las posibles situaciones donde nos pueda surgir la necesiad de utilizar una imagen si está cargada o no.

 Para evitar esto, vamos a encapsular esto en una rutina a la que llamaremos getSprite(), que recibirá como parámetro un nombre de sprite que necesitemos y devolverá la imagen, cargándola si es necesario.Puesto que puede haber muchas imágenes, la rutina tendrá que recordar qué sprites están cargados y cuáles no. Para ello nada mejor que una HashMap que asocie el nombre de cada sprite con su imagen en memoria.
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
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public HashMap sprites;

	public Invaders() {
		sprites = new HashMap();

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
	}

	public BufferedImage loadImage(String nombre) {
		URL url = null;
		try {
			url = getClass().getClassLoader().getResource(nombre);
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
		g.drawImage(getSprite("bicho.gif"), 40, 40, this);
	}

	public static void main(String[] args) {
		Invaders inv = new Invaders();
	}
}
