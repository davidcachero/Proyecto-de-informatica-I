package controller;

import java.util.HashMap;

import dataAccess.APIConnexion;
import dataAccess.LocalModelConnexion;
import models.Catalog;
import models.Currency;
import models.Usuario;
import models.VisualMsg;

/**
 * 
 * @author equipoCoffeeBreak
 *
 *         El controlador principal es el que se encarga de dirigir la logica
 *         del programa y conectar el resto de controladores.
 */

public class Controller {

	private LocalModelConnexion localAccess;
	private APIConnexion apiAccess;
	private LogicalController machine;
	private VisualController view;

	public Controller() {
		localAccess = new LocalModelConnexion();
	}

	// Inicializar conexiones

	@SuppressWarnings("unchecked")
	public void start() {
		
		HashMap<String, Object> apiData = apiAccess.init();
		
		if (apiData.get("CONF") instanceof HashMap) 
			localAccess.setConfig((HashMap<String, String>) apiData.get("CONF"));
		else
		    System.err.println("OBJECT TYPE ERROR - config data error");
		
		if (apiData.get("PROD") instanceof HashMap) 
			localAccess.saveCatalog((HashMap<String, Catalog>) apiData.get("PROD"));
		else
		    System.err.println("OBJECT TYPE ERROR - products data error");
		
		if (apiData.get("INTO") instanceof HashMap) 
			localAccess.setConfig((HashMap<String, String>) apiData.get("CONF"));
		else
		    System.err.println("OBJECT TYPE ERROR - config data error");

		
		HashMap<Float, Currency> currency = localAccess.getCurrencyData();
		HashMap<String, Catalog> catalog = localAccess.getCatalogData();
		HashMap<String, Usuario> users = localAccess.getUsersData();

		if ((catalog != null) && (currency != null) && (users != null)) {
			System.out.println("[DEV] finded data");

			machine = new LogicalController(currency, catalog, users);

			if (machine != null) {

				view = new VisualController(this);
				view.open();

			} else {
				
				System.err.println("[ERROR PC] LOGICAL CONTROLLER NOT CONECTED\nEND PROGRAM");
				System.exit(1);
			}
			
		} else {
			System.err.println("[ERROR PC] ACCESS DATA NO\nEND PROGRAM");
			System.exit(1);
		}
	}

	// Conexion con visual - usuarios

	public void setUserLogged(String idcliente) {
		machine.setUserLogged(idcliente);
		
		view.updateBalance(machine.getAllBalance());
		view.updateUserName(machine.getUserLogged().getName());
		view.showLogOffBtn();
		
		view.startTimeOut(60); // start with 60 s
		
	}

	public void logOffUser() {
		machine.logOffUser();
		view.updateBalance(machine.getAllBalance());
		view.updateUserName(null);

	}

	// Conexion con logica - usuarios

	public Usuario getUser(String idUser) {
		return machine.getUserLogged();
	}

	public boolean hasUser(String idcliente) {
		return machine.hasUserLogged(idcliente);
	}

	// Conexion con visual - monedas

	public VisualMsg insertCoins(Float value) {
		return machine.insertCoin(value);
		
	}

	public VisualMsg returnCoins() {
		return machine.returnCoin();
	}

	public float showCurrency() {
		return machine.getTotalCurrency();
	}

	public String[] getCurrencyTypes() {
		return localAccess.getCurrencyTypes();
	}

	// Conexion con accesos - monedas

	public void saveData() {

		boolean saveCurrency = localAccess.saveCurrency(machine.getCurrencyData());
		boolean saveCatalog = localAccess.saveCatalog(machine.getCatalogData());
		boolean saveUsers = localAccess.saveUser(machine.getUserData());

		if (saveCurrency && saveCatalog && saveUsers) {
			System.out.println("[PROCESS PC] BBDD ACTUALIZADA");

		} else {
			System.err.println("[ERROR PC] BBDD no se ha podido actualizar");
		}

	}

	// Conexion con accesos - productos

	public boolean comprobarExistencias(String idProduct) {
		return machine.hasProduct(idProduct);
	}

	public VisualMsg selectProduct(String prodId) {

		view.resetIntolerancesVisibility();

		if (machine.hasProduct(prodId)) {
			Catalog prod = machine.getProd(prodId);
			System.out.println("[PROCESS PC] PRODUCTO EXISTE");

			view.updateIntolerances(prod.getIntolerances());
			return new VisualMsg("PROD", prod);

		} else {
			return new VisualMsg("ERR", "");

		}
	}

	public VisualMsg sellProduct(String prod) {

		view.resetIntolerancesVisibility();
		System.out.println("COMPRANDO........ " + prod);

		if (machine.hasProduct(prod)) {
			if (machine.productNoEmpty(prod)) {

				VisualMsg msg = machine.takeProduct(prod);

				if (msg.getType() == "SENT") {
					System.out.println("[PROCESS PC] SALDO TRAS VENTA: " + msg.getMsg());
					view.updateBalance((Float) msg.getMsg());
					view.showIntolerance();

					System.out.println("[PROCESS PC] PRODUCTO VENDIDO :" + prod);

					return new VisualMsg("PROD", machine.getProd(prod));
				} else if (msg.getType() == "ERR") {
					return msg;
				} else {
					return new VisualMsg("ERR", "internal error");
				}
			} else {
				return new VisualMsg("ERR", "Producto sin existencias");
			}
		} else {
			return new VisualMsg("ERR", "Producto no seleccionado");

		}
	}

	// Finaliza el programa

	public void endProgram() {

		System.exit(0);
	}

}