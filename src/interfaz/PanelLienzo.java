package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mundo.Linea;
import mundo.Pintor;
import mundo.Punto;

public class PanelLienzo extends JPanel implements MouseListener {

	private ArrayList<Linea> lineas;
	
	private ArrayList<Punto> puntos;
	
	private ArrayList<JTextField> ids;
	
	private DialogoEditar dialogoEditar;
	
	private DialogoAnadir dialogoAnadir;
	
	private VentanaPrincipal inter;
	
	public PanelLienzo(ArrayList<Linea> plineas, ArrayList<Punto> ppuntos, VentanaPrincipal pInterfaz) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		addMouseListener(this);
		inter = pInterfaz;
		ids = new ArrayList<JTextField>();
		
		lineas = plineas;
		puntos = ppuntos;
	}
	
	public void refrescarPantalla(ArrayList<Linea> plineas, ArrayList<Punto> ppuntos)
	{
		lineas = plineas;
		puntos = ppuntos;
		repaint();
	}
	
	
	@Override
	public void paint(Graphics gr) {
		for(int i = 0; i < puntos.size(); i++){
			
			puntos.get(i).pintar(gr);
//			ids.add(new JTextField(" " + (int)puntos.get(i).getId()));
//			ids.get(i).setBounds((int) puntos.get(i).getX() + 5, (int)puntos.get(i).getY() +5 , 15, 12);
//			ids.get(i).setFont(new Font("ARIAL", Font.BOLD, 10));
			//ids.get(i).setOpaque(false);
//			add(ids.get(i));
		}
		for(int j = 0; j < lineas.size(); j++){
			lineas.get(j).pintar(gr);
		}
		int espaciado = 50;
		for(int i = 0; i < 100; i++)
		{
			gr.setColor(new Color(0, 0, 0));
			gr.drawLine(espaciado, 0, espaciado, Pintor.PANTANA_ALTO);
			gr.drawLine(0, espaciado, Pintor.PANTANA_ANCHO, espaciado);
			espaciado += 50;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
        double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
        anadirMouse.editarCoordenadas((int) mouseX,(int) mouseY, 5);
        anadirMouse.setVisible(true);
        System.out.println(mouseX+"   "+mouseY);*/
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(inter.darModoPreciso() == false)
		{
			Punto punto = inter.darPuntoCercano(e.getX(), e.getY());
			if(inter.darEstadoEditor() == 0)
			{
				JOptionPane.showMessageDialog(this, "¿Qué quieres que el editor haga? \n - Seleccionalo en los botones de abajo.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(inter.darEstadoEditor() == 1) //Agregar
			{
				//inter.agregarPunto(e.getX(), e.getY(), inter.darColorDefecto());
				dialogoAnadir = new DialogoAnadir(inter);
				dialogoAnadir.setVisible(true);
			}
			else if(inter.darEstadoEditor() == 2) // Borrar
			{	
				if(punto == null) {
					JOptionPane.showMessageDialog(this, "No se ha encontrado un punto cercano para borrar", "Error al borrar", JOptionPane.ERROR_MESSAGE);
				}else
				{
					inter.eliminarPunto(punto);
				}
			}
			else if(inter.darEstadoEditor() == 3) // Editar
			{
				if(punto == null){
					JOptionPane.showMessageDialog(this, "No se ha encontrado un punto cercano para editar.", "Error al editar", JOptionPane.ERROR_MESSAGE);	
				}else
				{
					dialogoEditar = new DialogoEditar(inter, (int)punto.getX(), (int)punto.getY(), (int)punto.getTamano(), punto.getColor());
					dialogoEditar.setVisible(true);
				}
			}
			else if(inter.darEstadoEditor() == 4) // Eliminar Linea
			{
				if(punto == null){
					JOptionPane.showMessageDialog(this, "No se ha encontrado una linea cercana para eliminar.", "Error al eliminar", JOptionPane.ERROR_MESSAGE);	
				}else
				{
					Linea linea = inter.darLineaCercana(e.getX(), e.getY());
					inter.eliminarLinea(linea);
					inter.refrescarPuntos();
				}
			}
		}
//		anadirMouse.editarCoordenadas( e.getX(), e.getY(), 5);
//      anadirMouse.setVisible(true);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
