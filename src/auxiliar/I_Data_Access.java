package auxiliar;

import java.util.HashMap;
import models.Catalog;
import models.Currency;

/**
 * 
 * @author equipoCoffeeBreak
 * 
 *         Esta es la interfaz que utiliza las clases para acceder a los datos
 *         externos.
 * 
 *         Esta implementada en la clase LocalModelConnexion
 *
 */
public interface I_Data_Access {

	public HashMap<Float, Currency> getCurrencyData();

	public HashMap<String, Catalog> getCatalogData();

	public boolean saveCurrency(HashMap<Float, Currency> currency);

	public boolean saveCatalog(HashMap<String, Catalog> catalog);

}
