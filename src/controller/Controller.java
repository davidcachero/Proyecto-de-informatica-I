package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import dataAccess.Local;
import models.Catalog;
import models.Currency;
import vista.VendingMachine;

public class Controller {

	private Local access;
	private LogicalController machine;
	private VisualController view;

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

				view = new VisualController(this);
				view.open();

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

	public String returnCoins() {

		return "Saldo: " + Integer.toString(machine.returnCoin());

	}

	public int showCurrency() {
		return machine.getTotalCurrency();
	}

	// Conexion con accesos - productos
	public boolean comprobarExistencias(String idProduct) {
		return machine.hasProduct(idProduct);
	}

	public Catalog takeProduct(String prod) {
		if(machine.hasProduct(prod)) {
			System.out.println("PRODUCTO EXISTE");
			return machine.getProd(prod);
		} 
		return null;
	}

	// finalizar programa

	public void endProgram() {
		System.exit(0);
	}

}