package version03;

/**
 * Curso Básico de desarrollo de Juegos en Java - Invaders
 * 
 * (c) 2004 Planetalia S.L. - Todos los derechos reservados. Prohibida su reproducción
 * 
 * http://www.planetalia.com
 *
 * 
 * 
 * El siguiente paso que necesitamos de forma imprescindible es poder pintar cosas encima de la ventana.
 Esto nos genera un problema. Para pintar, según lo que conocemos hasta ahora, necesitamos sobreescribir el método paint de "alguien" - de alguna ventana, o de algún panel.. Sin embargo, ni la clase Invaders es una ventana ni tampoco podríamos hacer nada si lo fuese, ya que la ventana que se muestra es otra que creamos dentro del constructor de Invaders.

 Una posible solución es hacer descender nuestra clase Invaders de la clase Canvas - la clase ancestro por excelencia para los componentes visuales. Siendo un Canvas, heredamos el método paint() que podemos sobreescribir. ¿Y cómo hacer la asociación entre nuestra clase y la ventana que estamos manejando? Sencillo : siendo Canvas un descendiente de Component , podemos añadirnos al panel de la ventana como un componente más: 
 * 
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
	}

	public static void main(String[] args) {
		Invaders inv = new Invaders();
	}
}
