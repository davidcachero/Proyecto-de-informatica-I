package controller;

import java.util.HashMap;

import models.Catalog;
import models.Currency;
import models.VisualMsg;

public class LogicalController {

	private HashMap<Integer, Currency> currency;
	private HashMap<String, Catalog> catalog;
	private int balance;

	public LogicalController(HashMap<Integer, Currency> currency, HashMap<String, Catalog> catalog) {
		// TODO create atributes
		this.currency = currency;
		this.catalog = catalog;
		this.balance = 200; // TODO cambiar

	}

	public boolean insertCoin(int value) {
		// TODO Auto-generated method stub
		return false;
	}

	public int returnCoin() {
		System.out.println("devolucion llegando");
		return balance;

	}

	public VisualMsg takeProduct(String prodId) {
		Catalog prod = catalog.get(prodId);

		// Restar del saldo el precio del producto
		if (this.balance > prod.getprice())
			this.balance -= prod.getprice();
		else
			return new VisualMsg("ERR", "Saldo insuficiente para comprar " + prod.getName());

		// TODO Añades monedas a la caja

		// Restar de la cantidad guardada del producto
		prod.removeAmount();

		// Enviar producto
		return new VisualMsg("SENDED", balance);

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

	public Catalog getProd(String prod) {
		return catalog.get(prod);
	}

	public String getProdName(String prod) {
		return catalog.get(prod).getName();
	}

	public int getProdPrice(String prod) {
		return catalog.get(prod).getprice();
	}

	public boolean productNoEmpty(String prod) {
		return catalog.get(prod).getAmount() != 0;
	}

}
