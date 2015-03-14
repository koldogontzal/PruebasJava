package version04;

/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 * 
 */

/*
 * Como pretendemos dar al juego un aspecto algo vistoso, está claro que no vamos a poder conformarnos con pintar los gráficos mediante las herramientas de Graphics2D de Java. Por ello los gráficos deben ser imágenes externas que iremos cargando según vayamos necesitando

 El siguiente paso consistirá en cargar una imagen y mostrarla sobre la ventana. Para poder tener transparencias sin complicaciones vamos a utilizar para los bichos imágenes en formato .gif. Para cargar la imagen utilizaremos la clase ImageIO , existente desde el JDK 1.4

 La imagen a cargar se llamará bicho.gif y debe estar en un subdirectorio llamado "res" (recursos en inglés) que debe colgar del directorio del proyecto (pero no del directorio del paquete). Es decir, si la clase está en c:\planetalia\curso\java\version04\Invaders.class, entonces el subdirectorio de recursos debe estar en c:\planetalia\curso\java\res\):

 Se puede ver que la gestión de errores es inexistente : cualquier fallo en la carga de la imagen hace que el programa se cierre. Intentaremos mejorar esto posteriormente.

 También es interesante señalar que en lugar de poner una ruta de archivo "a pedal" utilizamos getClass().getClassLoader().getResource(...), que permite obtener una URL apuntando a un subdirectorio relativo al sitio del cual fue cargada la clase. Esto permite que nuestro programa funcione de forma transparente si luego decidimos convertirlo en applet o en programa autoejecutable de Java utilizando WebStart. 

 Pero el caso es que no funciona, así qu elo sustituyo por un File
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
			
			//url = getClass().getClassLoader().getResource(nombre); // Modificado por LUIS

			return ImageIO.read(new File(nombre)); // Modificado por LUIS
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
		BufferedImage bicho = loadImage("res/bicho.gif");
		g.drawImage(bicho, 40, 40, this);
	}

	public static void main(String[] args) {
		Invaders inv = new Invaders();
		
	}
}
