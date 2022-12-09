package controller;

import java.util.HashMap;

import dataAccess.Local;
import models.Catalog;
import models.Currency;



public class Controller {
	
	private Local access;
	private LogicalController machine;
	private VisualController view;

	public Controller() {
		access = new Local();
	}

	// Inicializar conexiones
	public void getFileAccess() {
		

		HashMap<Integer, Currency> currency = access.getCurrencyData();
		HashMap<String, Catalog> catalog = access.getCatalogData();
	
		if((catalog != null) && (currency != null)) {
			// TODO crear logica maquina refrescos
			
			
			machine = new LogicalController(currency, catalog);
			
			if (machine != null) {
				System.out.println("[DEV] correct data connection ");
				
				// TODO init visual
								
				
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
		
		if (saveCurrency && saveCatalog){
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
		machine.returnCoin();
	}
	
	public int showCurrency() {
		return machine.getTotalCurrency();
	}
	

	// Conexion con accesos - productos
	public boolean comprobarExistencias(int idProduct) {
		return machine.hasProduct(idProduct);
	}
	
	public void takeProduct() {
		machine.takeProduct();
	}
	
	
	// finalizar programa
	
	public void endProgram() {
		System.exit(0);
	}
	
	

	
	}