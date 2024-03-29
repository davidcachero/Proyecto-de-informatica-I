package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import models.Catalog;
import models.Intolerance;
import models.VisualMsg;
import vista.VendingMachine;

public class VisualController implements ActionListener {
	/**
	 * 
	 * @author equipoCoffeeBreak
	 *
	 *         Es el controlador de la parte visual del programa y interactua con la
	 *         vista
	 */
	Controller controller;
	VendingMachine screen;
	DecimalFormat df;
	Timer timer;
	int timeOut;

	public VisualController(Controller controller) {

		// Button end program listener
		this.controller = controller;
		this.screen = new VendingMachine(this);
		df = new DecimalFormat("0.00");
	}

	// Abre la ventana al iniciar la aplicacion

	public void open(HashMap<String, Catalog> prod, HashMap<String, Intolerance> intolerances) {

		System.out.println("[PROCESS VISUAL] INICIANDO LA APLICACION");
		screen.setVisible(true);

		screen.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				controller.saveData();
				System.out.println("[PROCESS VISUAL] CERRANDO PROGRAMA");
			}
		});

		screen.setProductList(prod);
		screen.setIntoleranceList(intolerances);

		screen.setTextFieldBalance(df.format(controller.showCurrency()));
		// TODO screen.hideIntolerance();

	}

	// Inserccion de las imagenes guardadas en archivos

	public Image getImage(String name, String format, int x, int y) { // Utilizacion del Logo_CoffeeBreak

		Image image;
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("Files/assets/" + name + "." + format));
			image = bufferedImage.getScaledInstance(x, y, Image.SCALE_DEFAULT);

		} catch (IOException e) {
			System.err.println("[ERROR VISUAL] fallo icono");
			image = new ImageIcon("Files/assets/product_logo/not_found.png").getImage();

		}

		return image;
	}

	public Image getImageURL(String path, int x, int y) {

		if (path == null)
			return new ImageIcon("Files/assets/product_logo/not_found.png").getImage().getScaledInstance(y, x, 0);

		Image image;
		BufferedImage bufferedImage;

		try {
			URL url = new URL(path);

			bufferedImage = ImageIO.read(url);
			image = bufferedImage.getScaledInstance(x, y, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.err.println("[ERROR VISUAL] fallo icono");
			image = new ImageIcon("Files/assets/product_logo/not_found.png").getImage();

		}

		return image;
	}

	// Acciones para interactuar con la vista con los datos
	@Override
	public void actionPerformed(ActionEvent e) {

		String[] typeCurrency = controller.getCurrencyTypes();

		// Sistema para introducir las monedas
		if (e.getActionCommand() == "monedas") {

			String optionstr = (String) JOptionPane.showInputDialog(screen, "Selecciona una moneda", "Monedas",
					JOptionPane.INFORMATION_MESSAGE, null, typeCurrency, typeCurrency[1]);

			List<String> listTypes = new ArrayList<>(Arrays.asList(typeCurrency));

			if (listTypes.contains(optionstr)) {
				VisualMsg msg = controller.insertCoins(Float.parseFloat(optionstr));

				if (msg.getType() == "MSG")
					screen.setTextFieldBalance((Float) msg.getMsg());

				else if (msg.getType() == "ERR")
					showError((String) msg.getMsg());

			}

		}
		// Sistema para recoger el número de tarjeta
		if (e.getActionCommand() == "target") {
			String idcliente = (String) JOptionPane.showInputDialog(screen, "Introduce el ID de tu tarjeta", "Tarjetas",
					JOptionPane.INFORMATION_MESSAGE);

			if (idcliente != null) {
				System.out.println("[PROCESS VISUAL] ENTRANDO AL USUARIO POR ID: " + idcliente);
				if (!controller.userExist(idcliente)) {
					showError("Tarjeta incorrecta");
					JOptionPane.showMessageDialog(screen, "Tarjeta incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

				} else {
					controller.setUserLogged();
					screen.setUserLoggedName(controller.getUser(idcliente).getName());
				}
			}
		}

	}

// Interaccion con datos de los productos
	public void takeProduct(String value) {
		VisualMsg msg = controller.selectProduct(value);

		if (msg.getType() == "PROD") {
			Catalog prod = (Catalog) msg.getMsg();
			screen.setSelectedProd(prod.getKey());
			screen.setTextFieldProduct(prod.getName());
			screen.setTextFieldStatus("PRECIO: " + Float.toString(prod.getprice()) + " euro");

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

			if (timer != null)
				logOffUser(true);

		} else if (msg.getType() == "ERR") {
			screen.setTextFieldProduct(idProd);
			showError((String) msg.getMsg());

		} else {
			screen.clnTextFieldProduct();
			screen.setTextFieldStatus("Error del selector, llame a mantenimiento");
		}

	}

// Interaccion con datos del saldo introducido
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

// Interaccion con los datos de los usuarios
	public void logOffUser(boolean timerOn, int restTime) {
		if (timerOn) {

			timer.cancel();
			screen.setEndTimeout();
			JOptionPane.showMessageDialog(screen, "Sesion expirada", "Information", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(screen, "Sesion terminada", "Information", JOptionPane.INFORMATION_MESSAGE);
		}

		controller.logOffUser(restTime);

	}

	public void updateTimeOut(int restTime) {
		timeOut = restTime;
	}

	public void logOffUser(boolean timerOn) {
		if (timerOn) {
			timer.cancel();
			screen.setEndTimeout();
			JOptionPane.showMessageDialog(screen, "Sesion finalizada", "Information", JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(screen, "Sesion expirada", "Information", JOptionPane.INFORMATION_MESSAGE);

		}

		controller.logOffUser(timeOut);
		
	}

	public void updateUserName(String name) {
		screen.setUserLoggedName(name);

	}

	public void showLogOffBtn() {
		screen.showLogOffBtn();
	}

// Mostrar los errores por una pantalla distinta
	public void showError(String err) {
		JOptionPane.showMessageDialog(screen, err, "Error", JOptionPane.ERROR_MESSAGE);

	}

// Interaccion con los datos de las intolerancias
	public void updateIntolerances(String[] productIntolerances) {
		screen.showIntolerance(productIntolerances);

	}

	// interaccion con cronometro
	public void startTimeOut(int startTime) {
		this.timer = new Timer();
		screen.createTimeOut(startTime, timer);

	}

	// TODO ver si se puede quitar
	public void countMinum(int time) {
		System.out.println("count: " + time);
	}

	public void setIntoleranceList(HashMap<String, Intolerance> intolerances) {
		screen.hideIntolerances();
		screen.setIntoleranceList(intolerances);

	}

}
