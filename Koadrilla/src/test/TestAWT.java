package test;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import personaje.Personaje;

import util.Coordenada;

public class TestAWT  {
	private Personaje [] personajes;
	
	public TestAWT () {
		personajes = new Personaje [1];
        personajes[0] = new Personaje(new Coordenada(80,200), Color.GREEN, Personaje.DESPLAZAMIENTO_SINUSOIDAL, 100, 15);
		
	}
	
	public void jugar() {
		WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        Frame f1 = new Frame("Koadrilla");
        f1.addWindowListener(l);
        MyCanvas mc = new MyCanvas(this.personajes);
        
        f1.add(mc, BorderLayout.CENTER);
        f1.pack();
        f1.setVisible(true);
        
        Personaje mamakua = personajes[0];
        for (int i=0; i<200; i++) {
			mamakua.irIzquierda(1);
			f1.repaint();
			mamakua.esperar();
			
		}
		for (int i=0; i<300; i++) {
			mamakua.irDerecha(1);
			f1.repaint();
			mamakua.esperar();
			
		}
		for (int i=0; i<80; i++) {
			mamakua.estarQuieto();
			f1.repaint();
			mamakua.esperar();
		}
        

	}
	public static void main(String[] args) {
		TestAWT prueba = new TestAWT();
		prueba.jugar();
	}
	
	class MyCanvas extends Canvas {
	    private Personaje[] personajes;
		
		private static final long serialVersionUID = -1049710538159216558L;

		/**
		 * 
		 */
		
	    public MyCanvas(Personaje [] personajes) {
	       this.personajes = personajes;	
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(1200,900);
	    }

	 
	    public void paint(Graphics g) {
	    	int ancho = getSize().width;
			int alto = getSize().height;

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, ancho, alto);	
			
			for (int i=0; i<this.personajes.length; i++) {
	    		personajes[i].dibujar(g); 		
	    	}
	    }

	    public void update(Graphics g) {
	    	for (int i=0; i<this.personajes.length; i++) {
	    		personajes[i].getPosAnterior().borrar(g);
	    		personajes[i].dibujar(g); 		
	    	}
	        
	    }
	  
	}
}
