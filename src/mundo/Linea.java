package mundo;

import java.awt.Color;
import java.awt.Graphics;

public class Linea {

	private double x1, y1, x2, y2, pendiente;
	
	public Linea(double px1, double py1, double px2, double py2) {		
		x1 = px1;
		y1 = py1;
		x2 = px2;
		y2 = py2;
		pendiente = (x2 - x1) / (y2 - y1);
	}
	
	public double darPendiente()
	{	
		return pendiente;
	}
	
	public double darConstante()
	{
		return (y1) + ( (pendiente) * (x1));
	}
	
	public void pintar(Graphics gr)
	{
		gr.setColor(new Color(0, 0, 0));
		gr.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		
	}

}
