package koadrilla;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.CacheSprites;
import common.Escenario;

public class Koadrilla extends Canvas implements Escenario, KeyListener {

	private static final long serialVersionUID = -4682478260638863711L;
	
	private BufferStrategy strategy;
	
	private int posicionMundoX;
	private int posicionMundoY;
	
	private BufferedImage fondo, patronFondo;
	
	private CacheSprites cacheSprites;
	
	private Jugador jugador;
	
	private long usedTime;
	
	public Koadrilla() {
		super();
		
		this.cacheSprites = new CacheSprites();
		
		this.jugador = new Jugador(this);
		
		JFrame ventana = new JFrame("Koadrilla");
		JPanel panel = (JPanel)ventana.getContentPane();
		this.setBounds(0, 0, Escenario.ANCHO_VISTA, Escenario.ALTO_VISTA);
		panel.setPreferredSize(new Dimension(Escenario.ANCHO_VISTA, Escenario.ALTO_VISTA));	
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, Escenario.ANCHO_VISTA, Escenario.ALTO_VISTA);
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

	public void keyReleased(KeyEvent e) {
		this.jugador.teclaSoltada(e);
	}

	public void keyTyped(KeyEvent arg0) {}

	public static void main(String[] args) {
		Koadrilla juego = new Koadrilla();
		juego.jugar();
	}

	public void jugar() {
		this.iniciarMundo();
		while (this.isVisible()) {
			
			long startTime = System.currentTimeMillis();
			
			this.actualizarMundo();
			this.comprobarColisiones();
			this.pintarMundo();
			
			do {
				Thread.yield();
			} while (System.currentTimeMillis() - startTime < 17);
			
			this.usedTime = System.currentTimeMillis() - startTime;
		}
		this.pintarGameOver();
		
	}

	private void pintarGameOver() {
		// TODO Auto-generated method stub
		
	}

	private void pintarMundo() {
		Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
		g.drawImage( this.fondo,
		             0, 0, Escenario.ANCHO_VISTA, Escenario.ALTO_VISTA,
		             this.posicionMundoX, this.posicionMundoY, 
		             this.posicionMundoX + Escenario.ANCHO_VISTA, 
		             this.posicionMundoY + Escenario.ALTO_VISTA, 
		             this);
		
		this.pintarEstado(g);
		
		this.strategy.show();
	}

	private void pintarEstado(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.white);
		
		if (this.usedTime > 0)
			g.drawString(String.valueOf(1000 / this.usedTime) + " fps",
					Escenario.ANCHO_VISTA - 50, Escenario.ALTO_VISTA - 50);
		else
			g.drawString("--- fps", Escenario.ANCHO_VISTA - 50, Escenario.ALTO_VISTA - 50);
	}
	

	private void comprobarColisiones() {
		// TODO Auto-generated method stub
		
	}

	private void actualizarMundo() {
		// TODO Auto-generated method stub
		this.jugador.actua();
		
	}

	private void iniciarMundo() {
		this.posicionMundoX = 0;
		this.posicionMundoY = 0;
		
		

		this.patronFondo = this.cacheSprites.getSprite("escenario01.jpg");
		
		this.fondo = this.cacheSprites.crearCompatible(Escenario.ANCHO_MUNDO, Escenario.ALTO_MUNDO, Transparency.OPAQUE);
		Graphics2D g = (Graphics2D) this.fondo.getGraphics();
		g.setPaint(new TexturePaint(this.patronFondo, new Rectangle(0, 0, this.patronFondo.getWidth(), this.patronFondo.getHeight())));
		g.fillRect(0, 0, this.fondo.getWidth(), this.fondo.getHeight());

	}

	public CacheSprites getSpriteCache() {
		return this.cacheSprites;
	}

	public void setPuntoDeVista(int x, int y) {
		this.posicionMundoX = x;
		this.posicionMundoY = y;		
	}
}
