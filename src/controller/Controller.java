package controller;

import java.util.HashMap;

import dataAccess.Local;
import models.Catalog;
import models.Currency;
import vista.VendingMachine;

public class Controller {

	private Local access;
	private LogicalController machine;
	private VendingMachine view;

	public Controller() {
		access = new Local();
	}

	// Inicializar conexiones
	public void start() {

		HashMap<Integer, Currency> currency = access.getCurrencyData();
		HashMap<String, Catalog> catalog = access.getCatalogData();

		if ((catalog != null) && (currency != null)) {
			System.out.println("[DEV] finded data");

			machine = new LogicalController(currency, catalog);

			if (machine != null) {

				view = new VendingMachine(this);
				view.setVisible(true);

				System.out.println("BUILDING WINDOW..........");

			} else {
				System.err.println("LOGICAL CONTROLLER NOT CONECTED\nEND PROGRAM");
				System.exit(1);
			}
		} else {
			System.err.println("ACCESS DATA NO\nEND PROGRAM");
			System.exit(1);
		}
	}

	// Conexion con accesos - monedas
	public void saveData() {

		boolean saveCurrency = access.saveCurrency(machine.getCurrencyData());
		boolean saveCatalog = access.saveCatalog(machine.getCatalogData());

		if (saveCurrency && saveCatalog) {
			System.out.println("[DEV][PROCESS] BBDD ACTUALIZADA");

		} else {
			System.err.println("[DEV][ERROR] BBDD no se ha podido actualizar");
		}

	}

	// Conexion con visual - monedas
	public boolean insertarMonedas(int value) {

		return machine.insertCoin(value);
	}

	public void returnCoins() {

		view.setTextFieldPrecio("Saldo: " + Integer.toString(machine.returnCoin()));

	}

	public int showCurrency() {
		return machine.getTotalCurrency();
	}

	// Conexion con accesos - productos
	public boolean comprobarExistencias(String idProduct) {
		return machine.hasProduct(idProduct);
	}

	public void takeProduct(String prod) {
		if(machine.hasProduct(prod)) {
			System.out.println("PRODUCTO EXISTE");
			view.setLblTxt("Producto seleccionado: " + machine.getProd(prod));
		}
	}

	// finalizar programa

	public void endProgram() {
		System.exit(0);
	}

}