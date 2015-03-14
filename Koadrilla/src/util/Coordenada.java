/**
 * 
 */
package util;

/**
 * @author Luis
 *
 */
public class Coordenada {
	private int x;
	private int y;
	
	public Coordenada (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordenada () {
		this(0,0);
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}
	
	public void suma (Coordenada c) {
		this.x = this.x + c.getX();
		this.y = this.y + c.getY();
	}
	
	public void resta (Coordenada c) {
		this.x = this.x - c.getX();
		this.y = this.y - c.getY();
	}
	
	public String toString() {
		return ("(" + this.getX() + "," + this.getY() + ")");
	}
	
	public Coordenada clone () {
		return new Coordenada (this.getX(), this.getY());
	}
}
