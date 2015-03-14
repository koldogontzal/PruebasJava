package pruebas;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota {
	
	private int radio;
	private int vx;
	private int vy;
	
	private int x;
	private int y;
	
	private int tamagnoX;
	private int tamagnoY;
	
	private Color color = Color.RED;
	
	public Pelota(int radio) {
		this(radio, 1, 1);		
	}
	
	public Pelota(int radio, int vx, int vy) {
		this.radio = radio;
		this.vx = vx;
		this.vy = vy;
		
		this.x = 0;
		this.y = 0;
	}
	
	public void setPosicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

	public void avanza() {
		this.x += this.vx;
		this.y += this.vy;
		
		if (this.x < 0) {
			this.x = 0;
			this.rebotaX();
		}
		
		if (this.y < 0) {
			this.y = 0;
			this.rebotaY();
		}
		
		if (this.x > this.tamagnoX) {
			this.x = this.tamagnoX;
			this.rebotaX();
		}
		
		if (this.y > this.tamagnoY) {
			this.y = this.tamagnoY;
			this.rebotaY();
		}
	}
	
	private void rebotaX() {
		this.vx = - this.vx;
	}
	
	private void rebotaY() {
		this.vy = -this.vy;
	}
	
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.radio, this.radio);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}

	public void setBordes(int x, int y) {
		this.tamagnoX = x - this.radio;
		this.tamagnoY = y - this.radio;
	}
	
}
