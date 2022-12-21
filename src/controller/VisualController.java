package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Catalog;
import models.Usuario;
import models.VisualMsg;
import vista.VendingMachine;

public class VisualController implements ActionListener {

	/*
	 * TODO GENERATE ATRIBUTES
	 * 
	 * local user data local screen data
	 * 
	 */
	Controller controller;
	VendingMachine screen;

	// Lista botones de productos
	private List<JButton> btns;
	private int index;

	public VisualController(Controller controller) {

		// Button end program listener
		this.controller = controller;
		this.screen = new VendingMachine(this);
	}

	public void open() {

		screen.setVisible(true);

		screen.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.out.println("CERRANDO PROGRAMA");
				controller.saveData();
			}
		});

		screen.setTextFieldBalance(Float.toString(controller.showCurrency()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String[] typeCurrency = controller.getCurrencyTypes();

		////////// Sistema para introducir las monedas
		if (e.getActionCommand() == "monedas") {

			String optionstr = (String) JOptionPane.showInputDialog(
					screen
					, "Selecciona una moneda"
					, "Monedas"
					, JOptionPane.INFORMATION_MESSAGE
					, null
					, typeCurrency
					, typeCurrency[1]);

			List<String> listTypes = new ArrayList<>(Arrays.asList(typeCurrency));

			if (listTypes.contains(optionstr)) {
				controller.insertCoins(Integer.parseInt(optionstr));
			}

		}
		///// Sistema para recoger el número de tarjeta
		if (e.getActionCommand() == "target") {
			String idcliente = (String) JOptionPane.showInputDialog(screen, "Introduce el ID de tu tarjeta");

			if (!controller.hasUser(idcliente)) {
				JOptionPane.showMessageDialog(screen, "Tarjeta incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				controller.setUserLogged(idcliente);
			}
		}
	}

	public void takeProduct(String value) {
		VisualMsg msg = controller.selectProduct(value);

		if (msg.getType() == "PROD") {
			Catalog prod = (Catalog) msg.getMsg();
			screen.setSelectedProd(prod.getKey());
			screen.setTextFieldProduct(prod.getName());
			screen.setTextFieldStatus("precio: " + Integer.toString(prod.getprice()) + " ctms");

		} else if (msg.getType() == "ERR") {
			screen.setTextFieldProduct(value);
			screen.setTextFieldStatus((String) msg.getMsg());

		} else {
			screen.clnTextFieldProduct();
			screen.setTextFieldStatus("Error del selector, llame a mantenimiento");
		}

	}

	public void sellProduct(String value) {
		VisualMsg msg = controller.sellProduct(value);

		if (msg.getType() == "PROD") {
			Catalog prod = (Catalog) msg.getMsg();
			screen.setSelectedProd(prod.getKey());
			screen.setTextFieldProduct(prod.getName());
			screen.setTextFieldStatus("precio: " + Integer.toString(prod.getprice()) + " ctms");

		} else if (msg.getType() == "ERR") {
			screen.setTextFieldProduct(value);
			screen.setTextFieldStatus((String) msg.getMsg());

		} else {
			screen.clnTextFieldProduct();
			screen.setTextFieldStatus("Error del selector, llame a mantenimiento");
		}

	}

	public void returnCoins() {
		if (controller.returnCoins()) {
			screen.setTextFieldBalance(0);
		} else {
			screen.setTextFieldStatus("Error al devolver las monedas, intentelo de nuevo o llame al servicio técnico");
		}

	}

	public void updateBalance(String newValue) {
		screen.setTextFieldBalance(newValue); // - update balance when product its sell

	}

}
