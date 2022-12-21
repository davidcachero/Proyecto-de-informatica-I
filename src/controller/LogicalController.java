package controller;

import java.util.HashMap;

import models.Catalog;
import models.Currency;
import models.Usuario;
import models.VisualMsg;

public class LogicalController {

	private HashMap<Integer, Currency> currency;
	private HashMap<String, Catalog> catalog;
	private HashMap<String, Usuario> users;
	private float balance;
	private Usuario userLogged;

	public LogicalController(HashMap<Integer, Currency> currency, HashMap<String, Catalog> catalog, HashMap<String, Usuario> users) {

		this.currency = currency;
		this.catalog = catalog;
		this.users = users;
		this.balance = 0; 

	}

	public VisualMsg insertCoin(int value) {
		balance += value;
		Currency currencyUpdated = currency.get(value);
		
		currencyUpdated.addAmount();
		
		currency.put(value, currencyUpdated);

		return new VisualMsg("MSG", Float.toString(getAllBalance()));
	}

	public VisualMsg returnCoin() {
		balance = 0;
		return new VisualMsg("SENDED", Float.toString(getAllBalance()));

	}
	
	public float getAllBalance() {
		if (userLogged != null) {
			return balance + userLogged.getSaldo();
		}
		return balance;
	}
	
	private void lessBalance(float prodPrice) {
		
		if (userLogged != null) {
			float userBalance = userLogged.getSaldo();
			
			if(userBalance > prodPrice) {
				userLogged.setSaldo(userBalance - prodPrice);
			}
			else if (userLogged != null & userBalance <= prodPrice) {
				userLogged.setSaldo(0);
				balance = (balance + userBalance) - prodPrice;
			}
		}
		else {
			balance = balance - prodPrice;
		}
		
	}

	public VisualMsg takeProduct(String prodId) {
		Catalog prod = catalog.get(prodId);

		// Restar del saldo el precio del producto
		if (this.getAllBalance() > prod.getprice())
			lessBalance(prod.getprice());
		else
			return new VisualMsg("ERR", "Saldo insuficiente para comprar " + prod.getName());

		// Enviar producto
		return new VisualMsg("SENDED", Float.toString(balance));

	}

	public boolean hasProduct(String idProduct) {
		return catalog.containsKey(idProduct);
	}

	public float getTotalCurrency() {
		return this.balance;
	}

	public HashMap<Integer, Currency> getCurrencyData() {
		return currency;
	}

	public HashMap<String, Catalog> getCatalogData() {
		return catalog;
	}
	
	public HashMap<String, Usuario> getUserData() {
		return users;
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

	public boolean hasUserLogged(String idUser) {
		return users.containsKey(idUser);
	}

	public Usuario getUser(String idUser) {
		return users.get(idUser);
	}

	public Usuario getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(String idcliente) {
		userLogged = users.get(idcliente);
		
	}

	public void logOffUser() {
		userLogged = null;
		
	}


}
