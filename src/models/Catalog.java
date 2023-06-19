package models;

/*
 * TODO descripcion
 */
public class Catalog {
	/*
	 * Aributos
	 * 
	 * id - Codigo de identificacion en el modelo de datos 
	 * key - clave de identificacion en el modelo de datos 
	 * name - nombre del tipo de moneda 
	 * price - valor del producto 
	 * amount - cantidad de productos del mismo tipo 
	 * intolerances - Listado de Strings que muestran las intolerancias de cada producto
	 * 
	 */
	private int id;
	private String key;
	private String name;
	private float price;
	private int amount;
	private String image;


	private String[] intolerances;

	/*
	 * Constructor
	 * 
	 * @param key - nombre del producto
	 * 
	 * @param name - nombre del producto
	 * 
	 * @param price - precio inicial de productos
	 * 
	 * @param initialAmount - cantidad inicial de productos
	 * 
	 * @param intolerances - Listado de Strings que muestran las intolerancias de
	 * cada producto
	 * 
	 */
	public Catalog(String key, String name, Float price, int initialAmount, String[] intolerances) {
		this.key = key;
		this.name = name;
		this.amount = initialAmount;
		this.price = price;
		this.intolerances = intolerances;
	}

	public Catalog(String key, String name, float price, int initialAmount, String image, String[] intolerances) {
		this.key = key;
		this.name = name;
		this.amount = initialAmount;
		this.price = price;
		this.image = image;
		this.intolerances = intolerances;
	}

	// METODOS DE USO DE DATOS

	// METODOS DE CAMBIO DIRECTO DE ATRIBUTOS

	/*
	 * Metodos de recogida y el ajuste del id del producto
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * Metodos de recogida y el ajuste de la clave del producto
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/*
	 * Metodos de recogida y el ajuste del nombre del producto
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Metodos de recogida y el ajuste de la cantidad del producto
	 * 
	 */

	public int getAmount() {
		return amount;
	}

	public void addAmount() {
		amount++;
	}

	public void removeAmount() {
		amount--;
	}

	/*
	 * Metodos de recogida y el ajuste de la cantidad del producto
	 * 
	 */
	public void setPrice(Float value) {
		this.price = value;
	}

	public Float getprice() {
		return price;
	}

	/*
	 * Metodos de recogida y el ajuste de la imagen del producto que se mostrará
	 * 
	 */
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/*
	 * Metodos de recogida y el ajuste de las intolerancias del producto
	 * 
	 * Solo tiene metodo get por que no es un dato que se pueda cambiar en el
	 * programa
	 * 
	 */
	public String[] getIntoleranceId() {
		return intolerances;
	}

}
