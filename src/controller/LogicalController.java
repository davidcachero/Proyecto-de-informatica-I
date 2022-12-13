package controller;

import java.util.HashMap;

import models.Catalog;
import models.Currency;

public class LogicalController {

	private HashMap<Integer, Currency> currency;
	private HashMap<String, Catalog> catalog;
	private int balance;

	public LogicalController(HashMap<Integer, Currency> currency, HashMap<String, Catalog> catalog) {
		// TODO create atributes
		this.currency = currency;
		this.catalog = catalog;
		this.balance = 0;

		// TODO create text controller conection

		// TODO create visual controller conection
	}

	public boolean insertCoin(int value) {
		// TODO Auto-generated method stub
		return false;
	}

	public int returnCoin() {
		System.out.println("devolucion llegando");
		return balance;

	}

	public void takeProduct() {
		// TODO Auto-generated method stub

	}

	public boolean hasProduct(String idProduct) {
		return catalog.containsKey(idProduct);
	}

	public int getTotalCurrency() {
		return this.balance;
	}

	public HashMap<Integer, Currency> getCurrencyData() {
		return currency;
	}

	public HashMap<String, Catalog> getCatalogData() {
		return catalog;
	}

	public String getProdName(String prod) {
		return catalog.get(prod).getName();
	}
	
	public int getProdPrice(String prod) {
		return catalog.get(prod).getprice();
	}

}
