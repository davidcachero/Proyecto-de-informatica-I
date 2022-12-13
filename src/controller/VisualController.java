package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.Catalog;
import models.Usuario;
import vista.VendingMachine;

public class VisualController implements ActionListener {



		/*
		 * TODO GENERATE ATRIBUTES
		 * 
		 * local user data
		 * local machine data
		 * 
		 */

	private JFrame screen;	
	VendingMachine machine;
	
	
	// Lista botones de productos
	private List<JButton> btns;
	private int index;
	
	// Botones de configuracion
	
	// Botones 
	
	public VisualController() {
		
		// init needed atributes
		screen = new JFrame();
		btns = new ArrayList<JButton>();
		index = 0;
		
		// Button end program listener
		screen.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent evt ) {
				System.out.println("CERRANDO");
				finalizarMaquina();
			}
		}); 
	}
	
	private void finalizarMaquina(){
		// TODO llamar al metodo que guarda los datos y termina el programa
	}
	
	// TODO machine data interaccions
	
	private void addProdBtn(HashMap<String, Catalog> products) {
		// llamada al obj Catalog para sacarlos productos
		
		// bucle para crear un boton por producto
		JButton boton = new JButton(/*datos del producto (img & nombre)*/);
		
		// incluir en el panel
		
	}
	
	// TODO user data interaccion
	
	// Para controlar los eventos de la pantalla (pulsar botones)
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton) e.getSource();
		
		String nombreBoton = source.getName();
		String[] parts = nombreBoton.split("-");
		String prefijo = parts[0];
		String sufijo = parts[1];
		String [] options = {"1.00", "2.00","0.50","0.20","0.10"};
		boolean usr=false;
		
		Usuario cliente;
		
		Vector<Usuario> listaUsuarios;
		
		ControlFicheroUsuario controlUsuario;
		//////////Sistema para introducir las monedas
		if(e.getActionCommand()=="monedas") {
			String optionstr = (String)JOptionPane.showInputDialog(machine,"Selecciona una moneda","Monedas",
					JOptionPane.INFORMATION_MESSAGE,null,options,options[1]);
			
			
			if (optionstr.equals("2.00")) {
				////Logica monedas
				
		}
			if (optionstr.equals("1.00")) {
				/////Logica monedas
				
			}
			if (optionstr.equals("0.50")) {
				////Logica monedas
				
		}
			if (optionstr.equals("0.20")) {
				/////Logica monedas
				
			}
			if (optionstr.equals("0.10")) {
				////Logica monedas
				
		}
			
		}
		/////Sistema para recoger el número de tarjeta
		if(e.getActionCommand()=="target") {
			String idcliente = (String)JOptionPane.showInputDialog(machine,"Introduce el ID de tu tarjeta");
			
			listaUsuarios =new Vector<Usuario>();
			
			controlUsuario= new ControlFicheroUsuario(".\\Usuarios.txt"); //////// Hay que hacer el fichero
			listaUsuarios= controlUsuario.leerUsuario();
			////Compruebo que la tarjeta es correcta
			for (Usuario u : listaUsuarios) {
				if (idcliente.equals(u.getId())) {
					cliente = new Usuario(idcliente, u.getSaldo());
					usr=true;
				}
			}
			if (!usr) {
				 JOptionPane.showMessageDialog(machine, "Tarjeta incorrecta",
					      "Error", JOptionPane.ERROR_MESSAGE);
				 usr=false;
			}
			
	
		}
		// Dependiendo de la primera parte del nombre del boton sabemos si han pulsado moneda, producto o retorno
		//System.out.println(prefijo);
		
		/* TODO cambiar nombres en mayusc
		 * 		
		 *switch(prefijo){
			case "PREFIJO NAME":
				int BNT_NAME = Integer.parseInt(sufijo);
				this.FUNCTION(BNT_NAME);
				break;
			
			default:
				System.out.println("Boton no reconocido");
				System.out.println("Finaliza la ejecucion");
				System.exit(1);
		}		
		*/
	}
}
 	