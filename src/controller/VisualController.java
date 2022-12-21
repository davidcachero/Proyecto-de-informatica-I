package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import models.Catalog;
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

	public VisualController(Controller controller) {

		// Button end program listener
		this.controller = controller;
		this.screen = new VendingMachine(this);
	}

	public void open() {

		screen.setVisible(true);

		screen.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				controller.saveData();
				System.out.println("CERRANDO PROGRAMA");
			}
		});

		screen.setTextFieldBalance(Float.toString(controller.showCurrency()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String[] typeCurrency = controller.getCurrencyTypes();

		////////// Sistema para introducir las monedas
		if (e.getActionCommand() == "monedas") {

			String optionstr = (String) JOptionPane.showInputDialog(screen, "Selecciona una moneda", "Monedas",
					JOptionPane.INFORMATION_MESSAGE, null, typeCurrency, typeCurrency[1]);

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
				screen.setUserLoggedName(controller.getUser(idcliente).getName());
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
		VisualMsg msg = controller.returnCoins();
		if (msg.getType() == "SENDED") {
			screen.setTextFieldBalance(msg.getMsg().toString());
		} else {
			screen.setTextFieldStatus("Error al devolver las monedas, intentelo de nuevo o llame al servicio técnico");
		}

	}

	public void updateBalance(String newValue) {
		screen.setTextFieldBalance(newValue); // - update balance when product its sell

	}

	public void logOffUser() {
		controller.logOffUser();

	}

	public void updateUserName(String name) {
		screen.setUserLoggedName(name);
		
	}
	
	public void updateIntolerances(String[] productIntolerances) {
		
		List<String> listTypes = new ArrayList<>(Arrays.asList(productIntolerances));
		
		if (listTypes.contains("1")) {
			System.err.println("FRUTOS SECOS");
			screen.showIntoleranceFrutSecos();
			
		}
		if (listTypes.contains("2")) {
			screen.showIntoleranceGlucosa();
			
		} 
		if (listTypes.contains("3")) {
			screen.showIntoleranceGluten();
			
		} 
		if (listTypes.contains("4")) {
			screen.showIntoleranceSulfitos();
			
		}
	}

	public void resetIntolerancesVisibility() {
		screen.hideIntolerance();
		
	}

	public void showIntolerance() {
		screen.showIntolerance();
		
	}

}
