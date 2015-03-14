package util;


import java.awt.Color;
import java.awt.Graphics;

public class Area {
	private Coordenada esqSupIzq;
	private Coordenada esqInfDer;
	
	public static final int ESQUINA_SUPERIOR_IZQUIERDA = 0;
	public static final int ESQUINA_SUPERIOR_DERECHA = 1;
	public static final int ESQUINA_INFERIOR_IZQUIERDA = 2;
	public static final int ESQUINA_INFERIOR_DERECHA = 3;
	
	private static final int LADO_IZQUIERDO = 0;
	private static final int LADO_DERECHO = 1;
	private static final int LADO_SUPERIOR = 2;
	private static final int LADO_INFERIOR = 3;
	
	public Area (Coordenada c1, Coordenada c2) {
		this.esqSupIzq = new Coordenada (Math.min(c1.getX(), c2.getX()), 
				Math.min(c1.getY(), c2.getY()));
		this.esqInfDer = new Coordenada (Math.max(c1.getX(), c2.getX()), 
				Math.max(c1.getY(), c2.getY()));
		
	}
	
	
	/**
	 * Devuelve la <code>Coordenada</code> de una de las 4 esquinas del
	 * <code>Area</code>.
	 * 
	 * @param esquina
	 *            Hay que utilizar las constantes definidas arriba:
	 *            <code>ESQUINA_SUPERIOR_IZQUIERDA</code>,
	 *            <code>ESQUINA_SUPERIOR_DERECHA</code>,
	 *            <code>ESQUINA_INFERIOR_IZQUIERDA</code> o si no
	 *            <code>ESQUINA_INFERIOR_DERECHA</code>.
	 * @return
	 */
	public Coordenada CoordenadaEsquina(int esquina) {
		int coordenadaX;
		int coordenadaY;
		if ((esquina % 2) == 0) {
			coordenadaX = this.ordenadaLado(LADO_IZQUIERDO);
		} else {
			coordenadaX = this.ordenadaLado(LADO_DERECHO);
		}
		if ((esquina / 2) == 0) {
			coordenadaY = this.ordenadaLado(LADO_SUPERIOR);
		} else {
			coordenadaY = this.ordenadaLado(LADO_INFERIOR);
		}
		return new Coordenada(coordenadaX, coordenadaY);
	}
	
	private  int ordenadaLado (int lado) {
		switch (lado) {
		case LADO_IZQUIERDO:
			return this.esqSupIzq.getX();
		case LADO_DERECHO:
			return this.esqInfDer.getX();
		case LADO_SUPERIOR:
			return this.esqSupIzq.getY();
		default:
			// Sólo puede ser LADO_INFERIOR
			return this.esqInfDer.getY();
		}
	}
	
	public static Area interseccion (Area a1, Area a2) {
		
		int iX1, iX2, iY1, iY2;
		
		int iz1 = a1.ordenadaLado(LADO_IZQUIERDO);
		int iz2 = a2.ordenadaLado(LADO_IZQUIERDO);
		if (iz1 < iz2) {
			if (iz2 < a1.ordenadaLado(LADO_DERECHO)) {
				iX1 = iz2;
			} else {
				return null;
			}
		} else {
			if (iz1 < a2.ordenadaLado(LADO_DERECHO)) {
				iX1 = iz1;
			} else {
				return null;
			}
		}
		
		int dr1 = a1.ordenadaLado(LADO_DERECHO);
		int dr2 = a2.ordenadaLado(LADO_DERECHO);
		if (dr2 < dr1) {
			if (a1.ordenadaLado(LADO_IZQUIERDO) < dr2) {
				iX2 = dr2;
			} else {
				return null;
			}
		} else {
			if (a2.ordenadaLado(LADO_IZQUIERDO) < dr1) {
				iX2 = dr1;
			} else {
				return null;
			}
		}
		
		int su1 = a1.ordenadaLado(LADO_SUPERIOR);
		int su2 = a2.ordenadaLado(LADO_SUPERIOR);
		if (su1 < su2) {
			if (su2 < a1.ordenadaLado(LADO_INFERIOR)) {
				iY1 = su2;
			} else {
				return null;
			}
		} else {
			if (su1 < a2.ordenadaLado(LADO_INFERIOR)) {
				iY1 = su1;
			} else {
				return null;
			}
		}
		
		// Todo
		int in1 = a1.ordenadaLado(LADO_INFERIOR);
		int in2 = a2.ordenadaLado(LADO_INFERIOR);
		if (in1 < in2) {
			if (a2.ordenadaLado(LADO_SUPERIOR) < in1) {
				iY2 = in1;
			} else {
				return null;
			}
		} else {
			if (a1.ordenadaLado(LADO_SUPERIOR) < in2) {
				iY2 = in2;
			} else {
				return null;
			}
		}
		
		return new Area (new Coordenada (iX1, iY1), new Coordenada (iX2, iY2));
	}
	
	public String toString() {
		return (this.CoordenadaEsquina(ESQUINA_SUPERIOR_IZQUIERDA)
				+ " -> " + CoordenadaEsquina(ESQUINA_INFERIOR_DERECHA));
	}
	
	public void dibujaArea(Color c, Graphics g) {
		Color defecto = g.getColor();
		g.setColor(c);
		int x = this.esqSupIzq.getX();
		int y = this.esqSupIzq.getY();
		int width = this.esqInfDer.getX() - x;
		int height = this.esqInfDer.getY() - y;
		g.fillRect(x, y, width, height);
		g.setColor(defecto);
		
		
	}
}
