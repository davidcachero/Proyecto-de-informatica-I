package models;

import java.util.HashMap;

import org.json.simple.JSONObject;

enum BuyStatus {
	aceptada, noDebit
}

enum BuyType {
	card, currency, both
}

public class Purchase {
	
	private Catalog product;
	private BuyStatus status;
	private BuyType type;
	
	private int cardId;
	private int cardSpent;
	
	private HashMap<String, Integer> currencySpent;

	public Purchase(Catalog product, BuyStatus status, BuyType type, int cardId, int cardSpent, HashMap<String, Integer> currencySpent) {

		this.product = product;
		this.status = status;
		this.type = type;
		this.cardId = cardId;
		this.cardSpent = cardSpent;
		this.currencySpent = currencySpent;
	}

	public Catalog getProduct() {
		return product;
	}

	public void setProduct(Catalog product) {
		this.product = product;
	}

	public BuyStatus getStatus() {
		return status;
	}

	public void setStatus(BuyStatus status) {
		this.status = status;
	}

	public BuyType getType() {
		return type;
	}

	public void setType(BuyType type) {
		this.type = type;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getCardSpent() {
		return cardSpent;
	}

	public void setCardSpent(int cardSpent) {
		this.cardSpent = cardSpent;
	}

	public HashMap<String, Integer> getCurrencySpent() {
		return currencySpent;
	}

	public void setCurrencySpent(HashMap<String, Integer> currencySpent) {
		this.currencySpent = currencySpent;
	}
	
	public void setCurrency5cent(String value, int amount) {
		this.currencySpent.put(value, amount);
	}	
	
	public String generateJSON() {
		
		JSONObject jsonCard = new JSONObject();
		jsonCard.put("id_tarjeta", cardId);
		jsonCard.put("gasto_tarjeta", cardSpent);
		
		JSONObject jsonCurrency = new JSONObject();
		jsonCurrency.put("gasto_monedas", currencySpent);

		JSONObject json = new JSONObject();
		json.put("id_producto", product.getId());
		json.put("estado", status);
		json.put("tipo_compra", type);
		json.put("tarjeta", jsonCard);
		json.put("gasto_monedas", jsonCurrency);
		
		return json.toJSONString();
	}
	

}
