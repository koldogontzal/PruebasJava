package app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.CacheSprites;
import common.Escenario;

public class Juego extends Canvas implements Escenario, KeyListener {

	private static final long serialVersionUID = -4682478260638863711L;
	
	private BufferStrategy strategy;
	private BufferedImage fondo, patronFondo;
	
	private CacheSprites cacheSprites;
	
	private Observador jugador;
	
	private int tiempoEspera = 250;
	
	private Celda[][] celdas;
	
	public Juego() {
		super();
		
		this.cacheSprites = new CacheSprites();
		
		this.jugador = new Observador(this);
		
		this.celdas = new Celda[Escenario.NUM_FILAS][Escenario.NUM_COLUMNAS];
		
		JFrame ventana = new JFrame("Juego de la vida");
		JPanel panel = (JPanel)ventana.getContentPane();
		this.setBounds(0, 0, Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO);
		panel.setPreferredSize(new Dimension(Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO));	
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO);
		ventana.setVisible(true);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ventana.setResizable(false);
		super.createBufferStrategy(2);
		this.strategy = super.getBufferStrategy();
		super.requestFocus();
		super.addKeyListener(this);
		
		super.setIgnoreRepaint(true);
		
		
		
		
	}

	public void keyPressed(KeyEvent e) {
		this.jugador.teclaPulsada(e);
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.jugar();
	}

	public void jugar() {
		
		this.iniciarMundo();
		
		this.escribirCeldas();
		
		while (this.isVisible()) {
			
			long startTime = System.currentTimeMillis();
			
			this.actualizarMundo();
			this.pintarMundo();
			
			do {
				Thread.yield();
			} while (System.currentTimeMillis() - startTime < this.tiempoEspera);

		}
		this.pintarGameOver();
		
	}

	private void escribirCeldas() {
		for (int i = 0; i < Escenario.NUM_FILAS; i++) {
			for (int j = 0; j < Escenario.NUM_COLUMNAS; j++) {
				System.out.println(this.celdas[i][j]);
			}
		}
	}

	private void pintarGameOver() {
		// TODO Auto-generated method stub
		
	}

	private void pintarMundo() {
		Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
		g.drawImage(this.fondo,
		             0, 0, Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO,
		             0, 0, Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO,
		             this);
		for (int i = 0; i < Escenario.NUM_FILAS; i++) {
			for (int j = 0; j < Escenario.NUM_COLUMNAS; j++) {
				this.celdas[i][j].pinta(g);
			}
		}
		
		this.pintarEstado(g);
		
		this.strategy.show();
	}

	private void pintarEstado(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.white);
		
		g.drawString("Paso de tiempo: " + this.tiempoEspera + "    Hora actual: " + System.currentTimeMillis(), 10, Escenario.ALTO_MUNDO - 50);

	}


	private void actualizarMundo() {
		for (int i = 0; i < Escenario.NUM_FILAS; i++) {
			for (int j = 0; j < Escenario.NUM_COLUMNAS; j++) {
				this.celdas[i][j].actua();
			}
		}
	}

	private void iniciarMundo() {
		// Inicia el fondo		
		this.patronFondo = this.cacheSprites.getSprite("fondo.gif");
		this.fondo = this.cacheSprites.crearCompatible(Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO, Transparency.OPAQUE);
		Graphics2D g = (Graphics2D) this.fondo.getGraphics();
		g.setPaint(new TexturePaint(this.patronFondo, new Rectangle(0, 0, this.patronFondo.getWidth(), this.patronFondo.getHeight())));
		g.fillRect(0, 0, this.fondo.getWidth(), this.fondo.getHeight());
		
		// Inicia las celdas
		for (int i = 0; i < Escenario.NUM_FILAS; i++) {
			for (int j  = 0; j < Escenario.NUM_COLUMNAS; j++) {
				this.celdas[i][j] = new Celda(j * Celda.TAMAGNO_CELDA, i * Celda.TAMAGNO_CELDA, this);
			}
		}
		
		// Asocia vecinos
		for (int i = 0; i < Escenario.NUM_FILAS; i++) {
			for (int j = 0; j < Escenario.NUM_COLUMNAS; j++) {
				for (int vecinoNum = 0; vecinoNum < 8; vecinoNum++) {
					this.celdas[i][j].asociarCelda(this.obtenCelda(i, j, vecinoNum));
				}
			}
		}
		
		// Activa ciertas celdas
		for (int i = 0; i < Escenario.NUM_FILAS; i++) {
			for (int j = 0; j < Escenario.NUM_COLUMNAS; j++) {
				if (Math.random() < Escenario.PROBABILIDAD_CELDAS_ACTIVAS_INICIALES) {
					this.celdas[i][j].activarCelda();
				}
			}
		}
		
	}

	private Celda obtenCelda(int i, int j, int vecinoNum) {	
		
		// Los vecions van numerados del 0 al 7
		// 0 1 2
		// 3   4
		// 5 6 7
		//
		// Hay que hacer un cambio a:
		// 0 1 2
		// 3   5
		// 6 7 8		
		if (vecinoNum > 3) {
			vecinoNum++;
		}
		
		int fila = i + (vecinoNum / 3) - 1 + Escenario.NUM_FILAS;
		int columna = j + (vecinoNum % 3) - 1 + Escenario.NUM_COLUMNAS;
		
		fila = fila % Escenario.NUM_FILAS;
		columna = columna % Escenario.NUM_COLUMNAS;
		
		return this.celdas[fila][columna];
	}

	public CacheSprites getSpriteCache() {
		return this.cacheSprites;
	}

	public int getTiempoEspera() {
		return this.tiempoEspera;
	}

	public void setTiempoEspera(int tiempoEspera) {
		this.tiempoEspera = tiempoEspera;		
	}
}
