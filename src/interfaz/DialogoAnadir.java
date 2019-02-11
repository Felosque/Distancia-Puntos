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

public class DialogoAnadir extends JDialog implements ActionListener {

	private JFormattedTextField jtCorX, jtCorY, jtTam;
	private JButton enviar;
	private JComboBox<String> combo;
	private VentanaPrincipal interfaz;
	
	public DialogoAnadir(VentanaPrincipal pInterfaz) {
		
		interfaz = pInterfaz;
		setLayout(new GridLayout(3, 1));
		setLocationRelativeTo(null);
		setSize(300, 300);
		JLabel titulo = new JLabel("CREAR PUNTO");
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
		
		enviar = new JButton("Crear Punto");
		enviar.addActionListener(this);
		
		add(contenedor);
		add(enviar);
		
	}

	public void editarCoordenadas(int pX, int pY, int pTam)
	{
		jtCorX.setValue(pX);
		jtCorY.setValue(pY);
		jtTam.setValue(pTam);
	}
	
	public void Reiniciar()
	{
		jtCorX.setValue("");
		jtCorY.setValue("");
		jtTam.setValue(5);
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
		repaint();
		interfaz.agregarPunto(Integer.parseInt(jtCorX.getText()), Integer.parseInt(jtCorY.getText()), color);
		interfaz.refrescarPuntos();
	}
	
}
