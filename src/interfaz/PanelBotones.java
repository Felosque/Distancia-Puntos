package interfaz;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import mundo.Pintor;

public class PanelBotones extends JPanel implements ActionListener{

	private JButton btAnadir, btBorrar, btEditar, btConfig, btEliminarLinea;
	
	private static final String ANADIR = "Añadir punto";
	private static final String BORRAR = "Borrar Punto";
	private static final String EDITAR = "Editar Punto";
	private static final String CONFIG = "Configuracion";
	private static final String LINEA_ELIMINAR = "Eliminar Linea";
	
	private VentanaPrincipal interfaz;
	
	private DialogoAnadir dialogoAnadir;
	
	public PanelBotones(VentanaPrincipal pInterfaz) {
		
		setLayout(new GridLayout(1, 4));
		
		interfaz = pInterfaz;
		dialogoAnadir = new DialogoAnadir(interfaz);
		
		btAnadir = new JButton(ANADIR);
		btAnadir.addActionListener(this);
		btAnadir.setActionCommand(ANADIR);
		add(btAnadir);
		
		btEditar = new JButton(EDITAR);
		btEditar.addActionListener(this);
		btEditar.setActionCommand(EDITAR);
		add(btEditar);
				
		btBorrar = new JButton(BORRAR);
		btBorrar.addActionListener(this);
		btBorrar.setActionCommand(BORRAR);
		add(btBorrar);
		
		btEliminarLinea = new JButton(LINEA_ELIMINAR);
		btEliminarLinea.addActionListener(this);
		btEliminarLinea.setActionCommand(LINEA_ELIMINAR);
		add(btEliminarLinea);
		
		btConfig = new JButton(CONFIG);
		btConfig.addActionListener(this);
		btConfig.setActionCommand(CONFIG);
		add(btConfig);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals(ANADIR))
		{
			if(interfaz.darModoPreciso() == true)
			{
				dialogoAnadir.Reiniciar();
				dialogoAnadir.setVisible(true);
			}
			else{
				interfaz.cambiarEstadoEditor(Pintor.PUNTO_ANADIR);
			}
		}
		else if(comando.equals(BORRAR))
		{
			if(interfaz.darModoPreciso() == true)
			{
				//Dialogo de borrar
			}
			else{
				interfaz.cambiarEstadoEditor(Pintor.PUNTO_BORRAR);
			}
		}
		else if(comando.equals(EDITAR))
		{
			if(interfaz.darModoPreciso() == true)
			{
				//Dialogo de editar
			}
			else{
				interfaz.cambiarEstadoEditor(Pintor.PUNTO_EDITAR);
			}
		}
		else if(comando.equals(LINEA_ELIMINAR))
		{
			if(interfaz.darModoPreciso() == true)
			{
				//Dialogo de editar
			}
			else{
				interfaz.cambiarEstadoEditor(Pintor.LINEA_ELIMINAR);
			}
		}
		else if(comando.equals(CONFIG))
		{
			//Dialogo config
		}
	
		
	}

	
	
	
	
	
	
	
	
	
	
}
