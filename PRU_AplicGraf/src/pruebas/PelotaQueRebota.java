package pruebas;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

public class PelotaQueRebota extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5861498924986669315L;
	
	private Pelota pelota;
	
	private Timer timer;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame f = new Frame("Borde elástico");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.add(new PelotaQueRebota(), BorderLayout.CENTER);
		f.pack();
		f.show();
		
		
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }
	
	public PelotaQueRebota() {
		this.pelota = new Pelota(20, 2, 1);
		this.pelota.setPosicion(150, 150);
		this.pelota.setBordes(300, 300);
		
		this.timer = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				moverPelota();				
			}			
		});
		
		this.timer.start();
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		this.pelota.paint(g);
	}

	

	
	private void moverPelota() {
		
		//this.getGraphics().g
		this.pelota.avanza();
		
		super.repaint();
	}

}
