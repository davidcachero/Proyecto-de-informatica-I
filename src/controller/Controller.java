package controller;

import java.util.HashMap;

import dataAccess.LocalModelConnexion;
import models.Catalog;
import models.Currency;
import models.Usuario;
import models.VisualMsg;

public class Controller {

	private LocalModelConnexion access;
	private LogicalController machine;
	private VisualController view;

	public Controller() {
		access = new LocalModelConnexion();
	}

	// Inicializar conexiones
	public void start() {

		HashMap<Integer, Currency> currency = access.getCurrencyData();
		HashMap<String, Catalog> catalog = access.getCatalogData();
		HashMap<String, Usuario> users = access.getUsersData();

		if ((catalog != null) && (currency != null) && (users != null)) {
			System.out.println("[DEV] finded data");

			machine = new LogicalController(currency, catalog, users);

			if (machine != null) {

				view = new VisualController(this);
				System.out.println("BUILDING WINDOW..........");
				view.open();


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
		boolean saveUsers = access.saveUser(machine.getUserData());

		if (saveCurrency && saveCatalog && saveUsers) {
			System.out.println("[DEV][PROCESS] BBDD ACTUALIZADA");

		} else {
			System.err.println("[DEV][ERROR] BBDD no se ha podido actualizar");
		}

	}
	
	// Conexion con visual - usuarios

	public void setUserLogged(String idcliente) {
		machine.setUserLogged(idcliente);
		view.updateBalance(Float.toString(machine.getAllBalance()));
		view.updateUserName(machine.getUserLogged().getName());
		
	}

	public void logOffUser() {
		machine.logOffUser();
		view.updateBalance(Float.toString(machine.getAllBalance()));
		view.updateUserName(null);
		
	}

	public Usuario getUser(String idUser) {
		return machine.getUserLogged();
	}

	public boolean hasUser(String idcliente) {
		return machine.hasUserLogged(idcliente);
	}

	// Conexion con visual - monedas
	
	public void insertCoins(int value) {
		VisualMsg msg = machine.insertCoin(value);
		if(msg.getType() == "MSG")
			view.updateBalance((String) msg.getMsg());
		
	}

	public VisualMsg returnCoins() {
		return machine.returnCoin();
	}

	public float showCurrency() {
		return machine.getTotalCurrency();
	}

	public String[] getCurrencyTypes() {
		return access.getCurrencyTypes();
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