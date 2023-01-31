package models;

public class Usuario {

	/*
	 * Aributos
	 * 
	 * id - Codigo de identificacion en el modelo de datos 
	 * name - nombre del usuario
	 * saldo - valor del saldo del usuario
	 * 
	 */

	private String id;
	private String name;
	private float saldo;

	/*
	 * Constructor
	 * 
	 * @param id - identificador del usuario
	 * 
	 * @param name - nombre del usuario
	 * 
	 * @param saldo - cantidad de saldo del usuario
	 * 
	 */

	public Usuario(String id, String name, float saldo) {
		this.id = id;
		this.name = name;
		this.saldo = saldo;
	}

	// METODOS DE USO DE DATOS

	// METODOS DE CAMBIO DIRECTO DE ATRIBUTOS

	/*
	 * Metodos de recogida y el ajuste del id del usuario
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean checkId(String id) {
		return this.id.equals(id);
	}

	/*
	 * Metodos de recogida y el ajuste del saldo para el usuario
	 */
	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	/*
	 * Metodos de recogida y el ajuste del nombre del usuario
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
