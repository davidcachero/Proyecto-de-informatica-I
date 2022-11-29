package controller;

import java.util.HashMap;

import dataAccess.Local;
import models.Catalog;
import models.Currency;



public class Controller {
	
	Local access;
	private HashMap<Integer, Currency> currency;
	private HashMap<String, Catalog> catalog;
	private LogicalController machine;

	public Controller() {
		access = new Local();
	}

	// Inicializar conexiones
	public void getFileAccess() {
		

		currency = access.getCurrencyData();
		catalog = access.getCatalogData();
	
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
		

		boolean saveCurrency = access.saveCurrency(currency);
		boolean saveCatalog = access.saveCatalog(catalog);
		
		if (saveCurrency && saveCatalog){
			System.out.println("[DEV][PROCESS] BBDD ACTUALIZADA");
			
		} else {
			System.err.println("[DEV][ERROR] BBDD no se ha podido actualizar");
		}
		
	}
	
	// Conexion con visual - monedas
	public void insertarMonedas(int value) {
		
		machine.insertCoin(value);
	}
	
	public void returnCoins() {
		machine.returnCoin();
	}
	

	// Conexion con accesos - productos
	
	
	
	// finalizar programa
	
	public void endProgram() {
		System.exit(0);
	}
	
	

	
	}