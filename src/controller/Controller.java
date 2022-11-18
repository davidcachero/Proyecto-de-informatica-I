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

	public void GetFileAccess() {
		

		currency = access.getCurrencyData();
		catalog = access.getCatalogData();
	
		if((catalog != null) && (currency != null)) {
			// TODO crear logica maquina refrescos
			
			
			machine = new LogicalController(currency, catalog);
			
			if (machine != null) {
				System.out.println("[DEV] correct data connection ");
				
				// TODO init visual
								
				
			}  else {
				System.err.println("LOGICAL CONTROLLER NOT CONECTED\nEND PROGRAM");
				System.exit(1);
			}
		} else{
			System.err.println("ACCESS DATA NO\nEND PROGRAM");
			System.exit(1);
		}
	}
	
	}