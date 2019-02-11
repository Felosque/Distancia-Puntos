package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mundo.Pintor;

public class DialogoEditar extends JDialog implements ActionListener {

	private JFormattedTextField jtCorX, jtCorY, jtTam;
	private JButton enviar;
	private JComboBox<String> combo;
	private VentanaPrincipal interfaz;
	private int x, y, tam;
	
	public DialogoEditar(VentanaPrincipal pInterfaz, int pX, int pY, int pTam, Color pColor) {
		
		interfaz = pInterfaz;
		x = pX;
		y = pY;
		tam = pTam;
		setLayout(new GridLayout(3, 1));
		setLocationRelativeTo(null);
		setSize(300, 300);
		JLabel titulo = new JLabel("EDITAR PUNTO");
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setVerticalAlignment(JLabel.CENTER);
		titulo.setFont(new Font("ARIAL", Font.BOLD, 20));
		add(titulo);
		
		combo = new JComboBox<String>();
		combo.addItem("Amarillo");
		combo.addItem("Rojo");
		combo.addItem("Azul");
		combo.addItem("Verde");
		combo.addItem("Negro");
		combo.addActionListener(this);
		
		if(pColor == Pintor.AMARILO)
		{
			combo.setSelectedItem(0);
		}else if(pColor == Pintor.ROJO)
		{
			combo.setSelectedItem(1);
		}else if(pColor == Pintor.AZUL)
		{
			combo.setSelectedItem(2);
		}else if(pColor == Pintor.VERDE)
		{
			combo.setSelectedItem(3);
		}else if(pColor == Pintor.NEGRO)
		{
			combo.setSelectedItem(4);
		}
		
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new GridLayout(4, 2));
		
		contenedor.add(new JLabel("Coordenada X: "));
		jtCorX = new JFormattedTextField();
		contenedor.add(jtCorX);
		
		contenedor.add(new JLabel("Coordenada Y: "));
		jtCorY = new JFormattedTextField();
		contenedor.add(jtCorY);
		
		contenedor.add(new JLabel("Tamaño: "));
		jtTam = new JFormattedTextField();
		contenedor.add(jtTam);
		
		contenedor.add(new JLabel("Color: "));
		contenedor.add(combo);
		
		enviar = new JButton("Editar Punto");
		enviar.addActionListener(this);
		
		add(contenedor);
		add(enviar);
		
		jtCorX.setValue(x);
		jtCorY.setValue(y);
		jtTam.setValue(tam);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Color color = Pintor.AMARILO;
		if(combo.getSelectedItem().equals("Amarillo"))
		{
			color = Pintor.AMARILO;
		}else if(combo.getSelectedItem().equals("Rojo"))
		{
			color = Pintor.ROJO;
		}
		else if(combo.getSelectedItem().equals("Verde"))
		{
			color = Pintor.VERDE;
		}
		else if(combo.getSelectedItem().equals("Azul"))
		{
			color = Pintor.AZUL;
		}
		else if(combo.getSelectedItem().equals("Negro"))
		{
			color = Pintor.NEGRO;
		}
		
		setVisible(false);
		interfaz.editarPunto(x, y, Integer.parseInt(jtCorX.getText()), Integer.parseInt(jtCorY.getText()), Integer.parseInt(jtTam.getText()), color);
		interfaz.refrescarPuntos();
	}
	
}
