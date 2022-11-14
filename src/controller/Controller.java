package controller;

import java.util.HashMap;

import DataAccess.Local;
import objects.Catalog;
import objects.Currency;



public class Controller {
	
	Local access;
	private HashMap<Integer, Currency> currency;
	private HashMap<String, Catalog> catalog;

	public Controller() {
		access = new Local();
	}

	public void GetFileAccess() {
		

		currency = access.getCurrencyData();
		catalog = access.getCatalogData();
	
		if((catalog != null) && (currency != null)) {
			// TODO crear logica maquina refrescos
			
		} else{
			System.out.println("No se ha podido inicializar la maquina\nFinaliza la ejecucion");
			System.exit(1);
		}
	}
	
	}