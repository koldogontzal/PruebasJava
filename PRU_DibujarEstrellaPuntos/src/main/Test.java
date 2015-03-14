package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import utils.ListaDePuntos;
import utils.Punto;

public class Test {
	public static void main(String[] args) {
		ListaDePuntos lista = new ListaDePuntos(20);
		Random r = new Random();
		
		for (int i = 0; i < 800; i++) {
			//int x = (int)(320.0 * (1.0 + Math.pow(2.0 * r.nextDouble() - 1.0, 9.0)));
			//int y = (int)(240.0 * (1.0 + Math.pow(2.0 * r.nextDouble() - 1.0, 9.0)));
			int x = (int)(600.0 + r.nextGaussian() * 120.0);
			int y = (int)(450.0 + r.nextGaussian() * 120.0);
			lista.add(new Punto(x, y));
		}
		Punto centro = lista.centroDeMasa();
		//lista.ordenarCercaniaDesde(centro);
		
		System.out.print(lista.toString());
		
		Frame f = new Frame("Listado de puntos");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.add(new RecorridoCanvas(lista), BorderLayout.NORTH);
		f.pack();
		f.show();
		
	}
}

class RecorridoCanvas extends Canvas {
	ListaDePuntos lista;
    /**
	 * 
	 */
	private static final long serialVersionUID = -4538777039051743593L;

	public RecorridoCanvas(ListaDePuntos lista) {
        this.lista = lista;
    }

    public Dimension getPreferredSize() {
        return new Dimension(1250,950);
    }

   /*
    * Paint when the AWT tells us to...
    */
    public void paint(Graphics g) {
    	Rectangle rec = g.getClipBounds();
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, (int)rec.getWidth(), (int)rec.getHeight());
    	
    	
    	int tamagno = this.lista.size();
    	Punto inicio = this.lista.get(0);
    	Punto destino;
    	for (int i = 1; i<tamagno; i++) {
    		destino = this.lista.get(i);
    		
    		g.setColor(Color.BLACK);
    		this.dibujarLinea(g, inicio.getCoordX(), inicio.getCoordY(), 
    				destino.getCoordX(), destino.getCoordY(), 3);
    		
    		g.setColor(Color.red);
       		this.dibujarCirculo(g, inicio.getCoordX(), inicio.getCoordY(), 14);
       		this.dibujarCirculo(g, destino.getCoordX(), destino.getCoordY(), 14);
    		
    		
    		inicio = destino;
    	}
    	
    	/*
        // Dynamically calculate size information
        // (the canvas may have been resized externally...)
        Dimension size = getSize();
        int d = Math.min(size.width, size.height); // diameter
        int ed = d/20; // eye diameter
        int x = (size.width - d)/2;
        int y = (size.height - d)/2;

        // draw head (color already set to foreground)
        g.fillOval(x, y, d, d);
        g.setColor(Color.black);
        g.drawOval(x, y, d, d);

        // draw eyes
        g.fillOval(x+d/3-(ed/2), y+d/3-(ed/2), ed, ed);
        g.fillOval(x+(2*(d/3))-(ed/2), y+d/3-(ed/2), ed, ed);

        //draw mouth
        g.drawArc(x+d/4, y+2*(d/5), d/2, d/3, 0, -180);
        */
    }
    
    private void dibujarLinea(Graphics g, int x0, int y0, int x1, int y1, int grosor) {
    	if (grosor == 0) {
    		
    	} else if (grosor == 1) {
    		g.drawLine(x0, y0, x1, y1);
    	} else {
    		int distancia = (int)Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
    		for (int paso = 0; paso <= distancia; paso++) {
    			this.dibujarCirculo(g, x0 + paso * (x1 - x0) / distancia, y0 + paso * (y1 - y0) / distancia, grosor);
    		}
    		this.dibujarCirculo(g, x1, y1, grosor);
    	}
    }

	private void dibujarCirculo(Graphics g, int x, int y, int grosor) {
		int x0 = x - grosor / 2;
		int y0 = y - grosor / 2;
		
		g.fillOval(x0, y0, grosor, grosor);
	}
}

