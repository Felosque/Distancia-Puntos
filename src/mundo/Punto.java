package mundo;

import java.awt.Color;
import java.awt.Graphics;

public class Punto {

	private double x, y, tamano;
	private int id;
	private Color color;
	
	public Punto(double pX, double pY, double pTam, int pid, Color pColor) {
		x = pX;
		y = pY;
		tamano = pTam;
		color = pColor;
		id = pid;
	}

	public double getX() {
		return x;
	}

	public void setX(double px) {
		if(px < Pintor.PANTANA_ANCHO && px > 0)
		{
			this.x = px;
		}
	}

	public double getY() {
		return y;
	}

	public void setY(double py) {
		if(py < Pintor.PANTANA_ALTO -55 && py > 0)
		{
			this.y = py;
		}
	}

	public double getTamano() {
		return tamano;
	}

	public void setTamano(double tamano) {
		this.tamano = tamano;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getId() {
		return id;
	}

	public void pintar( Graphics gr )
	{
		gr.setColor(color);
		gr.drawOval((int) (x - tamano), (int) (y - tamano), (int)(2 * tamano), (int)(2 *tamano));
		gr.fillOval((int) (x - tamano), (int) (y - tamano), (int)(2 * tamano), (int)(2 *tamano));
		
	}
	
}
