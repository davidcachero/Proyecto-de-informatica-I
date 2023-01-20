package models;


/*
 * TODO descripcion
 */
public class Currency {

	/**
	 *  Aributos
	 *  
	 *  id  - Codigo de identificacion en el modelo de datos
	 *  name - nombre del tipo de moneda
	 *  value - valor de la moneda
	 *  amount - cantidad de montedas del mismo tipo
	 */
	private float id;
	private String  name; 
	private float value;	
	private int amount;
	
	
	/*
	 * Constructor
	 * 
	 * @param name - nombre de la moneda, tambien nos servirá como el id
	 * @param value - valor de la moneda 
	 * @param initialAmount - cantidad de monedas inicial
	 */
	public Currency(String name, float value, int initialAmount){
		this.id = value;
		this.name = name;
		this.value = value;
		this.amount = initialAmount;
	}
	

	// METODOS DE USO DE DATOS
	
	public float getTotalPrice() {
		return value * amount;
	}
	
	// METODOS DE CAMBIO DIRECTO DE ATRIBUTOS
	
	/*
	 * Metodos de recogida y el ajuste del id de la moneda
	 */
	public float getId() {
		return id;
	}
	
	public void setId(float id) {
		this.id = id;
	}
	
	
	
	/*
	 * Metodos de recogida y el ajuste del nombre de la moneda
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	/*
	 * Metodos de recogida y el ajuste de la cantidad de las monedas
	 * 
	 */
	
	public int getAmount() {
		return amount;
	}


	public void addAmount(){
		amount += 1;
	}
	
	
	public void removeAmount(){
		amount -= 1;
	}
	
	
	/*
	 * Metodos de recogida y el ajuste de la cantidad de las monedas
	 * 
	 */
	public void setValue(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	
}
