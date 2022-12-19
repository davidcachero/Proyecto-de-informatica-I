package controller;

import java.util.HashMap;

import dataAccess.Local;
import models.Catalog;
import models.Currency;
import models.VisualMsg;

public class Controller {

	private Local access;
	private LogicalController machine;
	private VisualController view;

	public Controller() {
		access = new Local();
	}

	// Inicializar conexiones
	public void start() {
		System.out.println("0");

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

	public boolean returnCoins() {

		return machine.returnCoin();

	}

	public int showCurrency() {
		return machine.getTotalCurrency();
	}

	// Conexion con accesos - productos
	public boolean comprobarExistencias(String idProduct) {
		return machine.hasProduct(idProduct);
	}

	public VisualMsg selectProduct(String prod) {
		
		if (machine.hasProduct(prod)) {
			System.out.println("PRODUCTO EXISTE");
			return new VisualMsg("PROD", machine.getProd(prod));
			
		} else {
			return new VisualMsg("ERR", "Producto no encontrado");
			
		}
	}

	public VisualMsg sellProduct(String prod) {
		
		System.out.println("COMPRANDO........ " + prod);
		
		if (machine.hasProduct(prod)) {
			if (machine.productNoEmpty(prod)) {

				VisualMsg msg = machine.takeProduct(prod);

				if (msg.getType() == "SENDED") {
					view.updateBalance((String) msg.getMsg());
					
					System.out.println("PRODUCTO VENDIDO :" + prod);
					
					return new VisualMsg("PROD", machine.getProd(prod));
				}
				else if (msg.getType() == "ERR") {
					return msg;
				}
				else {
					return null;
				}

			}	
			else {
				return new VisualMsg("ERR", "Producto sin existencias");
			}
		} else {
			return new VisualMsg("ERR", "Producto no seleccionado");
			
		}
	}

	// finalizar programa

	public void endProgram() {
		
		System.exit(0);
	}


}