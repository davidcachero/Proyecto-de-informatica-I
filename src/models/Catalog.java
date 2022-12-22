package models;


/*
 * TODO descripcion
 */
public class Catalog {
	/*
	 *  Aributos
	 *  
	 *  id  - Codigo de identificacion en el modelo de datos
	 *  key  - clave de identificacion en el modelo de datos
	 *  name - nombre del tipo de moneda
	 *  price - valor del producto
	 *  amount - cantidad de productos del mismo tipo
	 *  intolerances - Listado de Strings que muestran las intolerancias de cada producto
	 *  
	 */
	private int id;		
	private String key;		
	private String  name; 
	private int price;	
	private int amount;
	private String[] intolerances;


	/*
	 * Constructor
	 * @param key - nombre del producto
	 * @param name - precio del producto
	 * @param price - cantidad inicial de productos
	 * @param initialAmount - cantidad inicial de productos
	 * @param intolerances - Listado de Strings que muestran las intolerancias de cada producto
	 * 
	 */
	public Catalog(String key, String name,  int price, int initialAmount, String[] intolerances){
		this.key = key;
		this.name = name;
		this.amount = initialAmount;
		this.price = price;
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
		
		public void addAmount(){
			amount++;
		}
		
		
		public void removeAmount(){
			amount--;
		}
		
		
		/*
		 * Metodos de recogida y el ajuste de la cantidad del producto
		 * 
		 */
		public void setPrice(int value) {
			this.price = value;
		}
		
		public int getprice() {
			return price;
		}
		
		/*
		 * Metodos de recogida y el ajuste de las intolerancias del producto
		 * 
		 * Solo tiene metodo get por que no es un dato que se pueda cambiar en el programa
		 * 
		 */
		public String[] getIntolerances() {
			return intolerances;
		}

}
