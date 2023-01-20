package controller;

import java.util.HashMap;

import models.Catalog;
import models.Currency;
import models.Usuario;
import models.VisualMsg;

public class LogicalController {

	private HashMap<Float, Currency> currency;
	private HashMap<String, Catalog> catalog;
	private HashMap<String, Usuario> users;
	private float balance;
	private Usuario userLogged;

	public LogicalController(HashMap<Float, Currency> currency, HashMap<String, Catalog> catalog,
			HashMap<String, Usuario> users) {

		this.currency = currency;
		this.catalog = catalog;
		this.users = users;
		this.balance = 0;

	}

	public VisualMsg insertCoin(Float value) {
		balance += value;
		Currency currencyUpdated = currency.get(value);
		
		System.out.println("MONEDA INSERTADA: " + value);
		try {
			currencyUpdated.addAmount();
		}
		catch (Exception e) {
			return new VisualMsg("ERR", "MONEDA DEVUELTA - VALOR INVALIDO");
		}
		
		currency.put(value, currencyUpdated);


		return new VisualMsg("MSG", getAllBalance());
	}

	public VisualMsg returnCoin() {
		balance = 0;
		return new VisualMsg("SENT", getAllBalance());

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

			if (userBalance > prodPrice) {
				userLogged.setSaldo(userBalance - prodPrice);
			} else if (userLogged != null & userBalance <= prodPrice) {
				userLogged.setSaldo(0);
				balance = (balance + userBalance) - prodPrice;
			}
		} else {
			balance = balance - prodPrice;
		}

	}

	public VisualMsg takeProduct(String prodId) {
		Catalog prod = catalog.get(prodId);

		// Restar del saldo el precio del producto
		if (this.getAllBalance() > prod.getprice()) {
			System.out.println(
					"[LC 1] SALDO ANTES DE VENTA: " + getAllBalance() + " -- SE LE RESTA -- " + prod.getprice());

			lessBalance(prod.getprice());

			System.out.println("[LC 1] SALDO RESTANTE: " + getAllBalance());

			catalog.get(prodId).removeAmount();
		} else
			return new VisualMsg("ERR", "Saldo insuficiente para comprar " + prod.getName());

		// Enviar producto
		return new VisualMsg("SENT", this.getAllBalance());

	}

	public boolean hasProduct(String idProduct) {
		return catalog.containsKey(idProduct);
	}

	public float getTotalCurrency() {
		return this.balance;
	}

	public HashMap<Float, Currency> getCurrencyData() {
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

	public Float getProdPrice(String prod) {
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

	public String[] getProductIntolerances(String prod) {
		return catalog.get(prod).getIntolerances();
	}

}
