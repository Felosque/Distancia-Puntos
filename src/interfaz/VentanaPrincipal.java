package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.border.Border;

import mundo.Linea;
import mundo.Pintor;
import mundo.Punto;

public class VentanaPrincipal extends JFrame implements Runnable{

	private PanelLienzo panelLienzo;
	
	private PanelBotones panelBotones;
	
	private Pintor pintor;
	
	private int contador;
	
	private int[] numero1, numero2;
	
	private Thread h1;
	
	private Thread h2;
	
	public VentanaPrincipal() {
		
		pintor = new Pintor();	
		contador = 0;
		panelLienzo = new PanelLienzo(pintor.getDibujarLineas(), pintor.getDibujarPuntos(), this);
		add(panelLienzo, BorderLayout.CENTER);
		
		panelBotones = new PanelBotones(this);
		add(panelBotones, BorderLayout.SOUTH);
		
		h1 = new Thread(this);
		h1.start();
		
		h2 = new Thread(this);
		h2.start();
	}
	
	public void agregarPunto(int pX, int pY, Color pColor)
	{
		pintor.anadirPunto(pX, pY, pColor);
		refrescarPuntos();
		repaint();
	}
	
	public int darNumeroAzar()
	{
		int numero = (int) (Math.random() * 500) + 1;
		int multi1 = 0;
		if(numero < 250){multi1 = -1;}else {	multi1 = 1;}
		return multi1;
	}
	
	public void moverPuntos()
	{	
		if(contador == 5)
		{
			numero1 = new int[ pintor.getDibujarPuntos().size() +1 ];
			numero2 = new int[ pintor.getDibujarPuntos().size() +1 ];
			for(int i = 0; i < numero1.length; i++)
			{
				numero1[i] = darNumeroAzar();
				numero2[i] = darNumeroAzar();
			}
			contador = 0;
		}
		pintor.moverPuntos( numero1 , numero2);
		refrescarPuntos();
		repaint();
	}
	
	public void eliminarPunto(Punto pPunto)
	{
		pintor.eliminarPuntoCercano(pPunto);
		refrescarPuntos();
		repaint();
	}
	
	public void editarPunto(int pX, int pY, int pX2, int pY2, int pTam, Color pColor)
	{
		pintor.editarPuntoCercano(pintor.darPuntoCercano(pX, pY), pX2, pY2, pTam, pColor);
		refrescarPuntos();
		repaint();
	}
	
	public Punto darPuntoCercano(int pX, int pY)
	{
		return pintor.darPuntoCercano(pX, pY);
	}
	
	public void probandoRamaTesteo()
	{
		//Esto es solo para probar la rama de testeo
	}
	
	public Linea darLineaCercana(int pX, int pY)
	{
		return pintor.darLineaCercana(pX, pY);
	}
	
	public void eliminarLinea(Linea pLinea)
	{
		pintor.eliminarLinea(pLinea);
		repaint();
	}
	
	public void refrescarPuntos()
	{
		panelLienzo.refrescarPantalla(pintor.getDibujarLineas(), pintor.getDibujarPuntos());
	}

	public Color darColorDefecto()
	{
		return pintor.getColorDefecto();
	}
	
	public int darEstadoEditor()
	{
		return pintor.getEditorEstado();
	}
	
	public void cambiarEstadoEditor(int pEstado)
	{
		pintor.setEditorEstado(pEstado);
	}
	
	public boolean darModoPreciso()
	{
		return pintor.isModoPreciso();
	}
	
	public static void main(String[] args) {
		VentanaPrincipal ven = new VentanaPrincipal();
		ven.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ven.setSize(new Dimension(Pintor.PANTANA_ANCHO, Pintor.PANTANA_ALTO));
		ven.setVisible(true);
		ven.setResizable(false);
		System.setProperty("sun.java2d.opengl", "true");
	}
	
	@Override
	public void run() {
		int meses = 0;
		 Thread ct = Thread.currentThread();
		 while(ct == h1) {   
			 //Acciones
			 /*int numero = (int) (Math.random() * 1000) + 1;
			 int numero2 = (int) (Math.random() * 600) + 1;
			 agregarPunto(numero, numero2, Pintor.NEGRO);*/
			 //refrescarPuntos();
			 //moverPuntos();
			 meses++;
			 System.out.println(meses);
		  try {
		   Thread.sleep(1000);
		  }catch(InterruptedException e) {}
		 }
		 
		 Thread ct2 = Thread.currentThread();
		 while(ct2 == h2) {   
			 //Acciones
			 moverPuntos();
			 contador++;
		  try {
		   Thread.sleep(100);
		  }catch(InterruptedException e) {}
		 }
	}

}
