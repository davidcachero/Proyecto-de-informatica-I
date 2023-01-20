package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
	DecimalFormat df;

	public VisualController(Controller controller) {

		// Button end program listener
		this.controller = controller;
		this.screen = new VendingMachine(this);
		df = new DecimalFormat("0.00");
	}

	public void open() {

		screen.setVisible(true);

		screen.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				controller.saveData();
				System.out.println("CERRANDO PROGRAMA");
			}
		});

		screen.setTextFieldBalance(df.format(controller.showCurrency()));
		screen.hideIntolerance();

	}
	
	public Image getImage(String name, String format, int x , int y) { //Logo_CoffeeBreak
		Image image;
		
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("Files/assets/"+ name + "." + format));
	        image = bufferedImage.getScaledInstance(x, y, Image.SCALE_DEFAULT);
	        
		} catch (IOException e) {
			System.err.println("fallo icono");
			image = new ImageIcon("Files/assets/"+ name +".png").getImage();

		}
		
		return image;
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
				controller.insertCoins(Float.parseFloat(optionstr));
			}

		}
		///// Sistema para recoger el número de tarjeta
		if (e.getActionCommand() == "target") {
			String idcliente = (String) JOptionPane.showInputDialog(screen, "Introduce el ID de tu tarjeta", "Tarjetas",
					JOptionPane.INFORMATION_MESSAGE);

			if (idcliente != null) {
				System.out.println("ENTRANDO AL USUARIO POR ID: " + idcliente);
				if (!controller.hasUser(idcliente)) {
					showError("Tarjeta incorrecta");
					JOptionPane.showMessageDialog(screen, "Tarjeta incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					controller.setUserLogged(idcliente);
					screen.setUserLoggedName(controller.getUser(idcliente).getName());
				}
			} 
		}

	}

	public void takeProduct(String value) {
		VisualMsg msg = controller.selectProduct(value);

		if (msg.getType() == "PROD") {
			Catalog prod = (Catalog) msg.getMsg();
			screen.setSelectedProd(prod.getKey());
			screen.setTextFieldProduct(prod.getName());
			screen.setTextFieldStatus("precio: " + Float.toString(prod.getprice()) + " euro");

		} else if (msg.getType() == "ERR") {
			screen.setTextFieldProduct(value);
			showError("Producto no encontrado");
			screen.setTextFieldStatus((String) msg.getMsg());

		} else {
			screen.clnTextFieldProduct();
			screen.setTextFieldStatus("Error del selector, llame a mantenimiento");
		}

	}

	public void sellProduct(String idProd) {
		VisualMsg msg = controller.sellProduct(idProd);

		if (msg.getType() == "PROD") {
			Catalog prod = (Catalog) msg.getMsg();
			screen.setSelectedProd(prod.getKey());
			screen.setTextFieldProduct(prod.getName());
			screen.setTextFieldStatus("Producto " + prod.getName() + " comprado");

		} else if (msg.getType() == "ERR") {
			screen.setTextFieldProduct(idProd);
			showError("Producto agotado");

		} else {
			screen.clnTextFieldProduct();
			screen.setTextFieldStatus("Error del selector, llame a mantenimiento");
		}

	}

	public void returnCoins() {
		VisualMsg msg = controller.returnCoins();
		if (msg.getType() == "SENT") {
			screen.setTextFieldBalance(df.format(msg.getMsg()));
		} else {
			screen.setTextFieldStatus("Error al devolver las monedas, intentelo de nuevo o llame al servicio técnico");
		}

	}

	public void updateBalance(float newValue) {
		screen.setTextFieldBalance(df.format(newValue)); // - update balance when product its sell

	}

	public void logOffUser() {
		controller.logOffUser();

	}

	public void updateUserName(String name) {
		screen.setUserLoggedName(name);

	}

	public void showError(String err) {
		JOptionPane.showMessageDialog(screen, err, "Error", JOptionPane.ERROR_MESSAGE);

	}

	public void updateIntolerances(String[] productIntolerances) {

		List<String> listTypes = new ArrayList<>(Arrays.asList(productIntolerances));

		if (listTypes.contains("1")) {
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
