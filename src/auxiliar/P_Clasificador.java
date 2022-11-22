package auxiliar;

import java.util.Currency;
import java.util.HashMap;

import models.Catalog;

/**
 * Interfaz para definir las funionalidades del controlador que implemente la logica de una "maquina de refrescos".
 * 
 * Las clases que lo implementen necesitarán un contructor que pida el catalogo de productos y las monedas usadas
 *
 */
public abstract class P_Clasificador {
	
	// TODO TERMINAR EL PROXIMO DIA
	
	protected int balance = 0;
	protected String Message = "";
	protected HashMap<String, Catalog> myCatalog = new HashMap<String, Catalog>();
	protected HashMap<Integer, Currency> myCurrency = new HashMap<Integer, Currency>();
	

	public abstract boolean insertCoin(int valor);
	public abstract String returnCoin(int valor);
	public abstract boolean seleccionarProducto(String clave);
	public abstract boolean hayCambio(double auxBalance);

	public abstract HashMap<String, Catalog> getMyCatalog();
	public abstract HashMap<Integer, Currency> getMyCurrency();
	public abstract int getMessage();
	public abstract String getBalance();
	

}
