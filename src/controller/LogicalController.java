package controller;

import java.util.HashMap;

import models.Catalog;
import models.Currency;
import models.Intolerance;
import models.Usuario;
import models.VisualMsg;

/**
 * 
 * @author equipoCoffeeBreak
 * 
 *         La logica de todo el programa, toda la logica se almacenado en esta
 *         misma clase, esta clase trabaja de manera directa con el modelo
 *
 */

public class LogicalController {

	private HashMap<Float, Currency> currency;
	private HashMap<String, Catalog> catalog;
	private HashMap<String, Usuario> users;
	private HashMap<String, Intolerance> intolerances;
	private float balance;
	private Usuario userLogged;

	public LogicalController(HashMap<Float, Currency> currency, HashMap<String, Catalog> catalog,
			HashMap<String, Intolerance> intolerances) {

		this.currency = currency;
		this.catalog = catalog;
		this.intolerances = intolerances;
		this.users = new HashMap<String, Usuario>();
		this.balance = 0;

	}

	public void setuser(Usuario usr) {

		users.put("0", usr);
	}

	// Insertar moneda y guardarla en la misma sesion
	public VisualMsg insertCoin(Float value) {
		balance += value;
		Currency currencyUpdated = currency.get(value);

		System.out.println("MONEDA INSERTADA: " + value);
		try {
			currencyUpdated.addAmount();
		} catch (Exception e) {
			return new VisualMsg("ERR", "MONEDA DEVUELTA - VALOR INVALIDO");
		}

		currency.put(value, currencyUpdated);

		return new VisualMsg("MSG", getAllBalance());
	}

	public VisualMsg returnCoin() {
		balance = 0;
		return new VisualMsg("SENT", getAllBalance());

	}

// Consultar el saldo total contemplando monedas y el dinero del usuario
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

	// Comprobacion de la existencia del producto y la capacidad de compra
	public VisualMsg takeProduct(String prodId) {
		Catalog prod = catalog.get(prodId);

		System.out.println("[DEV] saldo actual: " + this.getAllBalance() + " | precio producto: " + prod.getprice());

		// Restar del saldo el precio del producto
		if (this.getAllBalance() >= prod.getprice()) {
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

	// Conjunto GETTERS & SETTERS

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

	public void setUserLogged() {

		userLogged = users.get("0");

	}

	public void logOffUser() {
		userLogged = null;

	}

	public String[] getProductIntolerancesId(String prod) {
		return catalog.get(prod).getIntoleranceId();
	}

//	public String[] getIntoleranceNames(String[] intoleranceId) {
//		String[] names = new String[intoleranceId.length];
//		for (int i = 0; i< intoleranceId.length; i++) {
//			
//			if(intolerances.get(intoleranceId[i]) != null) 
//				names[i] = intolerances.get(intoleranceId[i]).getName();
//		}
//		return names;
//	}

}
