package mundo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Pintor {

	private ArrayList<Punto> dibujarPuntos;
	private ArrayList<Linea> dibujarLineas;
	
	private int editorEstado;
	
	private boolean modoPreciso;
	
	private int tamanoDefecto;
	
	private int distanciaDefecto;
	
	private Color colorDefecto;
	
	
	public static final int SIN_ESTADO = 0;
	public static final int PUNTO_ANADIR = 1;
	public static final int PUNTO_BORRAR = 2;
	public static final int PUNTO_EDITAR = 3;
	public static final int LINEA_ELIMINAR = 4;
	
	public static final int INVALIDO = -999999999;
	
	public static final Color AMARILO = new Color(255, 255, 0);
	public static final Color AZUL = new Color(0, 0, 255);
	public static final Color NEGRO = new Color(0, 0, 0);
	public static final Color ROJO = new Color(255, 0, 0);
	public static final Color VERDE = new Color(0, 255, 0);
	
	public static final int PANTANA_ANCHO = 1200;
	public static final int PANTANA_ALTO = 600;
	
	public Pintor() {
		dibujarPuntos = new ArrayList<Punto>();
		dibujarLineas = new ArrayList<Linea>();
		editorEstado = SIN_ESTADO;
		tamanoDefecto = 3;
		distanciaDefecto = 200;
		colorDefecto = NEGRO;
		modoPreciso = false;
		/*dibujarPuntos.add(new Puntos(250, 100, 4, 0, NEGRO));
		dibujarPuntos.add(new Puntos(100, 300, 4, 1, ROJO));
		dibujarPuntos.add(new Puntos(300, 300, 4, 2, ROJO));
		dibujarPuntos.add(new Puntos(200, 300, 4, 3, ROJO));
		dibujarPuntos.add(new Puntos(250, 250, 4, 4, ROJO));
		dibujarPuntos.add(new Puntos(400, 300, 4, 5, VERDE));*/
		VerificarLineas();
	}
	
	public void moverPuntos(int[] multi1, int[] multi2)
	{
		for(int i = 0; i < dibujarPuntos.size(); i++)
		{

			//dibujarLineas.add(new Linea(dibujarPuntos.get(i).getX(), dibujarPuntos.get(i).getY(), (int)dibujarPuntos.get(i).getX() + 1 * multi1, (int)dibujarPuntos.get(i).getY() + 1 * multi2));
			editarPuntoCercano(dibujarPuntos.get(i), (int)dibujarPuntos.get(i).getX() + 1 * multi1[i], (int)dibujarPuntos.get(i).getY() + 1 * multi2[i], (int)dibujarPuntos.get(i).getTamano(), dibujarPuntos.get(i).getColor());
			System.out.println(dibujarLineas.size());
		}
	}
	
	public void VerificarLineas()
	{
		dibujarLineas.clear();
		for(int i = 0; i < dibujarPuntos.size(); i++)
		{
			for(int j = i + 1; j < dibujarPuntos.size(); j++)
			{
				double distancia = Math.sqrt((Math.pow(Math.abs((dibujarPuntos.get(i).getX() - dibujarPuntos.get(j).getX())), 2) + Math.abs((Math.pow((dibujarPuntos.get(i).getY() - dibujarPuntos.get(j).getY()), 2)))));
				if(distancia <= distanciaDefecto)
				{
					dibujarLineas.add(new Linea(dibujarPuntos.get(i).getX(), dibujarPuntos.get(i).getY(), dibujarPuntos.get(j).getX(), dibujarPuntos.get(j).getY()));
				}
			}
		}
		System.out.println("Lineas Totales: " + dibujarLineas.size());
		System.out.println("Puntos Totales: " + dibujarPuntos.size());
	}
	
	public Punto darPuntoCercano(int pX, int pY)
	{
		Punto puntoCercano = null;
		if(dibujarPuntos.size() > 0)
		{
			boolean encontro = false;
			for(int i = 0; i < dibujarPuntos.size() && !encontro; i++) {
				double distancia = Math.sqrt((Math.pow(Math.abs((dibujarPuntos.get(i).getX() - pX)), 2) + Math.abs((Math.pow((dibujarPuntos.get(i).getY() - pY), 2)))));
				if(distancia  <= dibujarPuntos.get(i).getTamano() * 1.2)
				{
					encontro = true;
					puntoCercano = dibujarPuntos.get(i);
				}
			}
		}
		return puntoCercano;
	}

	public Linea darLineaCercana(int pX, int pY)
	{
		Linea lineaCercana = null;
		if(dibujarLineas.size() > 0 )
		{
			boolean encontro = false;
			for(int i = 0; i < dibujarLineas.size() && !encontro; i++)
			{
				double distancia = ( Math.abs( (dibujarLineas.get(i).darPendiente() * pX) - (pY + dibujarLineas.get(i).darConstante())) ) / Math.sqrt( (Math.pow(dibujarLineas.get(i).darPendiente(),2) + 1) );
				System.out.println(distancia);
				if(distancia  <= 1000)
				{
					encontro = true;
					lineaCercana = dibujarLineas.get(i);
					System.out.println(distancia);
				}
			}
		}
		return lineaCercana;
	}
	
	public void eliminarLinea(Linea pLinea)
	{
		if(pLinea != null)
		{
			System.out.println(pLinea);
			dibujarLineas.remove(pLinea);
		}
	}
	
	public void anadirPunto(int pX, int pY, Color pColor)
	{
		if(dibujarPuntos.size() == 0)
		{
			dibujarPuntos.add(new Punto(pX, pY, tamanoDefecto, 0, pColor));
		}
		else
		{
			dibujarPuntos.add(new Punto(pX, pY, tamanoDefecto, dibujarPuntos.size()+1, pColor));
		}
		VerificarLineas();
	}
	
	public void eliminarPuntoCercano(Punto pPunto)
	{
		if(pPunto != null)
		{
			dibujarPuntos.remove(pPunto);
		}
		VerificarLineas();
	}
	
	public void editarPuntoCercano(Punto pPunto, int pX, int pY, int pTam, Color pColor)
	{
		if(pPunto != null)
		{
			pPunto.setTamano(pTam);
			pPunto.setX(pX);
			pPunto.setY(pY);
			pPunto.setColor(pColor);
		}
		VerificarLineas();
	}
	
	public int getEditorEstado()
	{
		return editorEstado;
	}
	
	public void setEditorEstado(int pEstado)
	{
		editorEstado = pEstado;
	}

	public int getTamanoDefecto() {
		return tamanoDefecto;
	}

	public void setTamanoDefecto(int tamanoDefecto) {
		this.tamanoDefecto = tamanoDefecto;
	}

	public Color getColorDefecto() {
		return colorDefecto;
	}

	public void setColorDefecto(Color colorDefecto) {
		this.colorDefecto = colorDefecto;
	}
	
	public boolean isModoPreciso() {
		return modoPreciso;
	}

	public void setModoPreciso(boolean modoPreciso) {
		this.modoPreciso = modoPreciso;
	}
	
	public int getDistanciaDefecto() {
		return distanciaDefecto;
	}

	public void setDistanciaDefecto(int distanciaDefecto) {
		this.distanciaDefecto = distanciaDefecto;
	}

	public ArrayList<Punto> getDibujarPuntos() {
		return dibujarPuntos;
	}
	
	public ArrayList<Linea> getDibujarLineas() {
		return dibujarLineas;
	}

	

}
