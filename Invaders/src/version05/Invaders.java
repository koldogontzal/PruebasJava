package version05;

/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */

/*
 * Si ya has programado en Java, te habrás dado cuenta que la forma de cargar la imagen es realmente patética - al hacerlo dentro del propio paint, lo que estamos haciendo es cargar la imagen cada vez que se redibuja la ventana, algo no muy hábil precisamente.

 Se podría pensar que lo ideal es hacerlo es cargar la imagen en el constructor, pero pensemos por un momento qué ocurriría si por ejemplo nuestro juego es un applet y tenemos un centenar de imágenes de distintos bichos y niveles. Al descargar el usuario las clases Java del juego, se quedaría esperando una eternidad a que el programa descargase todas las imágenes, que realmente a lo mejor no se llegan a utilizar porque el usuario nunca llega a alcanzar el nivel en el que aparecen

 Por este motivo se suele utilizar una técnica llamada carga tardía, que consiste en cargar la imagen una única vez, pero aplazar este instante hasta el momento en que realmente se hace necesario visualizar esa imagen
 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Invaders extends Canvas {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public BufferedImage bicho = null;

	public Invaders() {
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

	public void paint(Graphics g) {
		if (bicho == null)
			bicho = loadImage("res/bicho.gif");
		g.drawImage(bicho, 40, 40, this);
	}

	public static void main(String[] args) {
		Invaders inv = new Invaders();
	}
}
